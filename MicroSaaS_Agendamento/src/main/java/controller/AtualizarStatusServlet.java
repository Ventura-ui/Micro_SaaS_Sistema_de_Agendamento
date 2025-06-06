package controller;

import java.io.IOException;
import java.sql.Connection;

import dao.AgendamentoDAO;
import dao.LogAgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Agendamento;
import model.LogAgendamento;
import model.Enum.StatusAgendamento;
import utils.ConnectionFactory;

@WebServlet("/AtualizarStatusServlet")
public class AtualizarStatusServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idAgendamento = Integer.parseInt(request.getParameter("id_agendamento"));
        String novoStatus = request.getParameter("novo_status");
        
        
        try {
        	Connection conn = ConnectionFactory.getConnection();
            AgendamentoDAO dao = new AgendamentoDAO();
            Agendamento agendamento = dao.buscarPorID(conn, idAgendamento);
            
            LogAgendamento log = new LogAgendamento();
            log.setId_agendamento(idAgendamento);
            log.setStatus_antigo(agendamento.getStatus().name());
            log.setStatus_novo(novoStatus);
            LogAgendamentoDAO logDao = new LogAgendamentoDAO();
            logDao.inserirLogAgendamento(conn, log);
            
            dao.atualizarStatus(conn, idAgendamento, StatusAgendamento.valueOf(novoStatus));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar status");
            return;
        }
        
        response.sendRedirect(request.getContextPath() + "/ListarAgendamentosServlet");
	}
}
