package controller.cliente;

import java.io.IOException;
import java.sql.Connection;

import dao.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import utils.ConnectionFactory;

@WebServlet("/cliente/cadastroCliente")
public class CadastroClienteServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cliente/cadastroCliente.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		String contato = request.getParameter("contato");
		
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
		cliente.setSenha(senha);
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEndereco(endereco);
		cliente.setContato(contato);
		Connection conn = null;
		try {
			conn = ConnectionFactory.getConnection();
			ClienteDAO dao = new ClienteDAO();
			dao.cadastrarCliente(conn, cliente);
			conn.close();
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (Exception e) {
			request.setAttribute("mensagem", "Erro ao realizar cadastro: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request, response);
		}
	}
}
