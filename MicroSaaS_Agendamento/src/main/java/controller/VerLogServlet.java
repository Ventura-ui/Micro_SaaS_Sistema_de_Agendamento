package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import dao.LogAgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LogAgendamento;
import utils.ConnectionFactory;

@WebServlet("/VerLogServlet")
public class VerLogServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAgendamento = Integer.parseInt(request.getParameter("id_agendamento"));

        try{
        	Connection conn = ConnectionFactory.getConnection();
            LogAgendamentoDAO dao = new LogAgendamentoDAO();
            List<LogAgendamento> logs = dao.listarPorPrestador(conn, idAgendamento);

            request.setAttribute("logs", logs);
            request.getRequestDispatcher("/prestador/log_agendamento.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
