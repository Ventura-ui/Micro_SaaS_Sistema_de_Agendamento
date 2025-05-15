package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.LogAgendamento;

public class LogAgendamentoDAO {
	
	public void inserirLogAgendamento(Connection conn, LogAgendamento log) throws Exception {
		String sql = "INSERT INTO Log_Status_Agendamento (id_agendamento, status_antigo, status_novo) "
				+ "VALUES (?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, log.getId_agendamento());
			stmt.setString(2, log.getStatus_antigo());
			stmt.setString(3, log.getStatus_novo());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<LogAgendamento> listarPorPrestador(Connection conn, int idAgendamento) {
		List<LogAgendamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM Log_Status_Agendamento WHERE id_agendamento = ? ORDER BY data_alteracao";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idAgendamento);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					LogAgendamento log = new LogAgendamento();
					log.setIdLog(rs.getInt("id_log"));
					log.setId_agendamento(rs.getInt("id_agendamento"));
					log.setStatus_antigo(rs.getString("status_antigo"));
			        log.setStatus_novo(rs.getString("status_novo"));
			        log.setData_alteracao(rs.getTimestamp("data_alteracao"));

					lista.add(log);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
}
