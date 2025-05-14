package controller;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.AgendamentoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Agendamento;
import model.Cliente;
import utils.ConnectionFactory;

@WebServlet("/agendamentoServlet")
public class FazerAgendamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idPrestador = Integer.parseInt(request.getParameter("id_prestador"));
		LocalDate dataAgendamento = LocalDate.parse(request.getParameter("data_agendamento"));
		LocalTime horaAgendamento = LocalTime.parse(request.getParameter("hora_agendamento"));
		Connection conn = null;

		try {
			conn = ConnectionFactory.getConnection();
			HttpSession sessao = request.getSession();
			Cliente usuario = (Cliente) sessao.getAttribute("usuarioLogado");

			if (dataAgendamento != null && horaAgendamento != null) {
				Agendamento agendamento = new Agendamento();
				agendamento.setIdCliente(usuario.getId_cliente());
				agendamento.setIdPrestador(idPrestador);
				agendamento.setData(dataAgendamento);
				agendamento.setHorario(horaAgendamento);

				AgendamentoDAO dao = new AgendamentoDAO();
				dao.inserirAgendamento(conn, agendamento);

				request.setAttribute("mensagem", "Agendamento feito com sucesso!");
				request.getRequestDispatcher("/cliente/agendamento.jsp").forward(request, response);
			}

		} catch (Exception e) {
			request.setAttribute("mensagem", "Não foi possível fazer o agendamento nesse horário!");
			request.getRequestDispatcher("/cliente/agendamento.jsp").forward(request, response);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
