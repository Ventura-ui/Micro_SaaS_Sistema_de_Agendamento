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
import model.Prestador;
import model.Enum.StatusAgendamento;
import utils.ConnectionFactory;

@WebServlet("/ListarAgendamentosServlet")
public class ListarAgendamentosServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		Prestador prestador = (Prestador) sessao.getAttribute("usuarioLogado");
		String statusParam = request.getParameter("status");
		
		StatusAgendamento status = null;
		if (statusParam != null && !statusParam.isEmpty()) {
	        status = StatusAgendamento.valueOf(statusParam);
	    }
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			AgendamentoDAO dao = new AgendamentoDAO();
			
			if(status == null) {
				List<Agendamento> lista = dao.listarPorPrestador(conn, prestador.getId_prestador());
		        request.setAttribute("agendamentos", lista);
		        request.setAttribute("statusSelecionado", statusParam);
		        request.getRequestDispatcher("/prestador/agenda.jsp").forward(request, response);
			}else {
				List<Agendamento> lista = dao.listarPorPrestadorEStatus(conn, prestador.getId_prestador(), status);
		        request.setAttribute("agendamentos", lista);
		        request.setAttribute("statusSelecionado", statusParam);
		        request.getRequestDispatcher("/prestador/agenda.jsp").forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
