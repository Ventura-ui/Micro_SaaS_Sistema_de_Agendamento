package controller;

import java.io.IOException;
import java.sql.Connection;

import dao.ClienteDAO;
import dao.PrestadorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cliente;
import model.Prestador;
import utils.ConnectionFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private ClienteDAO clienteDAO;
    private PrestadorDAO prestadorDAO;

    @Override	
    public void init() throws ServletException {
        clienteDAO = new ClienteDAO();
        prestadorDAO = new PrestadorDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipoUsuario = request.getParameter("tipoUsuario");
        
        Connection connection = null;

        try {
            HttpSession session = request.getSession();
            connection = ConnectionFactory.getConnection();
            
            if ("cliente".equals(tipoUsuario)) {
                Cliente cliente = clienteDAO.buscarPorEmailESenha(connection, email, senha);
                if (cliente != null) {
                    session.setAttribute("usuarioLogado", cliente);
                    session.setAttribute("tipoUsuario", "cliente");
                    response.sendRedirect(request.getContextPath() + "/cliente/dashboardCliente.jsp");
                    return;
                }
            } else if ("prestador".equals(tipoUsuario)) {
                Prestador prestador = prestadorDAO.buscarPorEmailESenha(connection, email, senha);
                if (prestador != null) {
                    session.setAttribute("usuarioLogado", prestador);
                    session.setAttribute("tipoUsuario", "prestador");
                    response.sendRedirect(request.getContextPath() + "/prestador/dashboardPrestador.jsp");
                    return;
                }
            }

            request.setAttribute("erro", "Email ou senha inválidos.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        } catch (Exception e) {
        	request.setAttribute("mensagem", "Erro ao realizar cadastro: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request, response);
        }
    }
}