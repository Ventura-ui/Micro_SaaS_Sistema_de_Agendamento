package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dao.DisponibilidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Disponibilidade;
import utils.ConnectionFactory;

@WebServlet("/buscarHorariosDisponiveis")
public class BuscarHorariosDisponiveisServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPrestador = Integer.parseInt(request.getParameter("id_prestador"));
		String dataStr = request.getParameter("data_agendamento");
		LocalDate dataAgendamento = LocalDate.parse(dataStr);
        DayOfWeek diaSemana = dataAgendamento.getDayOfWeek();
        int diaSemanaInt = diaSemana.getValue();
        
        List<String> horarios = new ArrayList<>();
        
        try {
        	Connection conn = ConnectionFactory.getConnection();
        	DisponibilidadeDAO dao = new DisponibilidadeDAO();
        	Disponibilidade d = dao.buscarPorPrestadorEDia(conn, idPrestador, diaSemanaInt);
        	
            if (d != null) {
                LocalTime inicio = d.getHorario_inicio().toLocalTime();
                LocalTime fim = d.getHorario_fim().toLocalTime();
                LocalTime descansoInicio = d.getHorario_descanso_inicio().toLocalTime();
                LocalTime descansoFim = d.getHorario_descanso_fim().toLocalTime();
                Duration duracaoServico = Duration.between(LocalTime.MIN, d.getTempo_servico().toLocalTime());

                LocalTime atual = inicio;

                while (atual.plus(duracaoServico).compareTo(fim) <= 0) {
                    LocalTime proximo = atual.plus(duracaoServico);

                    if (proximo.isBefore(descansoInicio) || atual.isAfter(descansoFim)) {
                        horarios.add(proximo.toString());
                    }

                    atual = proximo;
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        request.setAttribute("horariosDisponiveis", horarios);
        request.setAttribute("id_prestador", idPrestador);
        request.setAttribute("dataSelecionada", dataStr);
        request.getRequestDispatcher("/cliente/agendamento.jsp").forward(request, response);
	}
	
}
