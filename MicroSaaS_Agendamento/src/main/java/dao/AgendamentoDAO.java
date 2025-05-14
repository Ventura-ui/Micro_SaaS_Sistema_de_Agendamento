package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Agendamento;
import model.Enum.StatusAgendamento;

public class AgendamentoDAO {

	public void inserirAgendamento(Connection conn, Agendamento a) throws Exception {
		String sql = "INSERT INTO Agendamento (id_cliente, id_prestador, data_agendamento, hora_agendamento, status) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			
			stmt.setInt(1, a.getIdCliente());
			stmt.setInt(2, a.getIdPrestador());
			stmt.setDate(3, java.sql.Date.valueOf(a.getData()));
	        stmt.setTime(4, java.sql.Time.valueOf(a.getHorario()));
			stmt.setString(5, a.getStatus().name());

			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			throw new Exception("Erro na transferência: " + e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public List<Agendamento> listarPorPrestador(Connection conn, int idPrestador) {
		List<Agendamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM Agendamento WHERE id_prestador = ? ORDER BY data_agendamento, hora_agendamento";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestador);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Agendamento a = new Agendamento();
					a.setIdAgendamento(rs.getInt("id_agendamento"));
					a.setIdCliente(rs.getInt("id_cliente"));
					a.setIdPrestador(rs.getInt("id_prestador"));
					a.setData(rs.getDate("data_agendamento").toLocalDate());
	                a.setHorario(rs.getTime("hora_agendamento").toLocalTime());

					String statusStr = rs.getString("status");
					a.setStatus(StatusAgendamento.valueOf(statusStr));

					lista.add(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public List<Agendamento> listarPorPrestadorEStatus(Connection conn, int idPrestador, StatusAgendamento status) {
		List<Agendamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM Agendamento WHERE id_prestador = ? AND status = ? ORDER BY data_agendamento, hora_agendamento";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestador);
			stmt.setString(2, status.name());
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Agendamento a = new Agendamento();
					a.setIdAgendamento(rs.getInt("id_agendamento"));
					a.setIdCliente(rs.getInt("id_cliente"));
					a.setIdPrestador(rs.getInt("id_prestador"));
					a.setData(rs.getDate("data_agendamento").toLocalDate());
	                a.setHorario(rs.getTime("hora_agendamento").toLocalTime());

					String statusStr = rs.getString("status");
					a.setStatus(StatusAgendamento.valueOf(statusStr));

					lista.add(a);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public void atualizarStatus(Connection conn, int idAgendamento, StatusAgendamento novoStatus) {
	    String sql = "UPDATE Agendamento SET status = ? WHERE id_agendamento = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, novoStatus.name());
	        stmt.setInt(2, idAgendamento);
	        stmt.executeUpdate();
	    }catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
