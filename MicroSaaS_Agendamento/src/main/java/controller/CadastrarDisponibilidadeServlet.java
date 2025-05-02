package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Prestador prestador = (Prestador) request.getSession().getAttribute("usuarioLogado");
		int idPrestador = prestador.getId_prestador();
		boolean success = false;
		
		try {
			DisponibilidadeDAO dao = new DisponibilidadeDAO();
			Connection conn = ConnectionFactory.getConnection();
			for (int dia = 1; dia <= 7; dia++) {
		        String inicioStr = request.getParameter("inicio_" + dia);
		        String fimStr = request.getParameter("fim_" + dia);

		        if (inicioStr != null && fimStr != null && !inicioStr.isEmpty() && !fimStr.isEmpty()) {
		            try {
		                Disponibilidade d = new Disponibilidade();
		                d.setIdPrestador(idPrestador);
		                d.setDiaSemana(dia);
		                d.setHorarioInicio(Time.valueOf(inicioStr + ":00"));
		                d.setHorarioFim(Time.valueOf(fimStr + ":00"));
		                dao.inserirOuAtualizar(conn, d);
		                success = true;
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String mensagem = success ? "Disponibilidade cadastrada com sucesso!" : "Não foi possível cadastrar seus horários!";
	    request.setAttribute("mensagem", mensagem);
	    request.getRequestDispatcher("/prestador/disponibilidade.jsp").forward(request, response);
	}
}
