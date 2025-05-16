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
	    List<Prestador> prestadores = null;
	    
	    int pagina = 0;
	    int totalPaginas = 0;
	    
	    String paginaStr = request.getParameter("pagina");
	    if (paginaStr != null){
	    	pagina = Integer.parseInt(request.getParameter("pagina"));
	    }

	    
	    try {
	        conn = ConnectionFactory.getConnection();
	       
	        
	        if (cidade != null && !cidade.isEmpty()) {
	            prestadores = dao.buscarPorCidade(conn, cidade, pagina);
	            totalPaginas = dao.getTotalPaginasPorCidade(conn, cidade);
	        } else {
	            prestadores = dao.listarPrestadores(conn, pagina);
	            totalPaginas = dao.getTotalPaginas(conn);
	        }
	        
	        for (Prestador prestador : prestadores) {
	            List<String> imagens = imagensDAO.buscarImagensPorPrestador(prestador.getId_prestador(), conn);
	            prestador.setImagens(imagens);
	        }
	        
	        System.out.println("[DEBUG] Prestadores encontrados: " + prestadores.size());
	        prestadores.forEach(p -> System.out.println(p.getNome_fantasia()));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("erro", "Falha ao carregar prestadores");
	        request.getRequestDispatcher("/erro.jsp").forward(request, response);
	    }
	    
	    request.getSession().setAttribute("pagina", pagina);
	    request.getSession().setAttribute("totalPaginas", totalPaginas);
	    request.getSession().setAttribute("prestadores", prestadores);
	    request.getSession().setAttribute("cidadeSelecionada", cidade);
	    response.sendRedirect(request.getContextPath() + "/cliente/dashboardCliente.jsp");
	}
}
