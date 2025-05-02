package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import dao.ImagemPortfolioDAO;
import dao.PrestadorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prestador;
import utils.ConnectionFactory;

@WebServlet("/dashboardCliente")
public class ClienteDashboardServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String cidade = request.getParameter("cidade");
	    PrestadorDAO dao = new PrestadorDAO();
	    ImagemPortfolioDAO imagensDAO = new ImagemPortfolioDAO();
	    Connection conn = null;
	    try {
	        conn = ConnectionFactory.getConnection();
	        List<Prestador> prestadores;
	        
	        if (cidade != null && !cidade.isEmpty()) {
	            prestadores = dao.buscarPorCidade(conn, cidade);
	        } else {
	            prestadores = dao.listarPrestadores(conn);
	        }
	        
	        for (Prestador prestador : prestadores) {
	            List<String> imagens = imagensDAO.buscarImagensPorPrestador(prestador.getId_prestador(), conn);
	            prestador.setImagens(imagens);
	        }
	        
	        System.out.println("[DEBUG] Prestadores encontrados: " + prestadores.size());
	        prestadores.forEach(p -> System.out.println(p.getNome_fantasia()));
	        
	        request.setAttribute("prestadores", prestadores);
	        request.getRequestDispatcher("/cliente/dashboardCliente.jsp").forward(request, response);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Falha ao carregar prestadores");
	        request.getRequestDispatcher("/erro.jsp").forward(request, response);
	    }
	}
}
