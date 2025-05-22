package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import dao.AgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Agendamento;
import model.Cliente;
import model.Prestador;
import model.Enum.StatusAgendamento;
import utils.ConnectionFactory;

@WebServlet("/ListarAgendaCliente")
public class AgendaClienteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		Cliente cliente = (Cliente) sessao.getAttribute("usuarioLogado");
		String statusParam = request.getParameter("status");
		
		int pagina = 0;
	    int totalPaginas = 0;
		
	    String paginaStr = request.getParameter("pagina");
	    if (paginaStr != null){
	    	pagina = Integer.parseInt(request.getParameter("pagina"));
	    }
	    
		StatusAgendamento status = null;
		if (statusParam != null && !statusParam.isEmpty()) {
	        status = StatusAgendamento.valueOf(statusParam);
	    }
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			AgendamentoDAO dao = new AgendamentoDAO();
			
			if(status == null) {
				List<Agendamento> lista = dao.listarPorCliente(conn, cliente.getId_cliente(), pagina);
				totalPaginas = dao.getTotalPaginas(conn);
		        request.getSession().setAttribute("agendamentos", lista);
		        request.getSession().setAttribute("pagina", pagina);
			    request.getSession().setAttribute("totalPaginas", totalPaginas);
		        request.getSession().setAttribute("statusSelecionado", statusParam);
		        response.sendRedirect(request.getContextPath() + "/cliente/agendaCliente.jsp");
			}else {
				List<Agendamento> lista = dao.listarPorClienteEStatus(conn, cliente.getId_cliente(), status, pagina);
				totalPaginas = dao.getTotalPaginasPorStatus(conn, status);
				request.getSession().setAttribute("agendamentos", lista);
		        request.getSession().setAttribute("pagina", pagina);
			    request.getSession().setAttribute("totalPaginas", totalPaginas);
		        request.getSession().setAttribute("statusSelecionado", statusParam);
		        response.sendRedirect(request.getContextPath() + "/cliente/agendaCliente.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
