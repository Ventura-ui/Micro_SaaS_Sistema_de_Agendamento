package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import dao.DisponibilidadeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Disponibilidade;
import model.Prestador;
import utils.ConnectionFactory;

@WebServlet("/CadastrarDisponibilidadeServlet")
public class CadastrarDisponibilidadeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestador prestador = (Prestador) request.getSession().getAttribute("usuarioLogado");
		int idPrestador = prestador.getId_prestador();
		int cadastradosComSucesso = 0;
		
		try {
			DisponibilidadeDAO dao = new DisponibilidadeDAO();
			Connection conn = ConnectionFactory.getConnection();
			for (int dia = 1; dia <= 7; dia++) {
		        String inicioStr = request.getParameter("inicio_" + dia);
		        String fimStr = request.getParameter("fim_" + dia);
		        String descanso_inicioStr = request.getParameter("descanso_inicio_" + dia);
		        String descanso_fimStr = request.getParameter("descanso_fim_" + dia);
		        String tempo_servicoStr = request.getParameter("tempo_servico_" + dia);
		        
		        LocalTime inicio = LocalTime.parse(inicioStr, formatter);
		        LocalTime fim = LocalTime.parse(fimStr, formatter);
		        LocalTime descansoInicio = LocalTime.parse(descanso_inicioStr, formatter);
		        LocalTime descansoFim = LocalTime.parse(descanso_fimStr, formatter);


		        if (inicioStr != null && !inicioStr.isEmpty()) {
		            try {
		            	if (inicio.isBefore(fim) && descansoInicio.isBefore(descansoFim)) {
		            		Disponibilidade d = new Disponibilidade();
			                d.setIdPrestador(idPrestador);
			                d.setDiaSemana(dia);
			                d.setHorario_inicio(Time.valueOf(inicioStr + ":00"));
			                d.setHorario_fim(Time.valueOf(fimStr + ":00"));
			                d.setHorario_descanso_inicio(Time.valueOf(descanso_inicioStr + ":00"));
			                d.setHorario_descanso_fim(Time.valueOf(descanso_fimStr + ":00"));
			                d.setTempo_servico(Time.valueOf(tempo_servicoStr + ":00"));
			                dao.inserirOuAtualizar(conn, d);
			                cadastradosComSucesso++;
		            	} 
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String mensagem = (cadastradosComSucesso > 0) ? "Disponibilidade cadastrada com sucesso!" : "Não foi possível cadastrar seus horários!";
		request.getSession().setAttribute("mensagem", mensagem);
		response.sendRedirect(request.getContextPath() + "/prestador/disponibilidade.jsp");
	}
}
