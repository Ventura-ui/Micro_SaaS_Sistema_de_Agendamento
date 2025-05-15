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
	
	private int chamadosPorPagina = 6;

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
	
	public Agendamento buscarPorID(Connection conn, int idAgendamento) {
		Agendamento a = new Agendamento();
		String sql = "SELECT * FROM Agendamento WHERE id_agendamento = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idAgendamento);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					a.setIdAgendamento(rs.getInt("id_agendamento"));
					a.setIdCliente(rs.getInt("id_cliente"));
					a.setIdPrestador(rs.getInt("id_prestador"));
					a.setData(rs.getDate("data_agendamento").toLocalDate());
	                a.setHorario(rs.getTime("hora_agendamento").toLocalTime());

					String statusStr = rs.getString("status");
					a.setStatus(StatusAgendamento.valueOf(statusStr));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}

	public List<Agendamento> listarPorPrestador(Connection conn, int idPrestador, int pagina) {
		List<Agendamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM Agendamento WHERE id_prestador = ? ORDER BY data_agendamento, hora_agendamento "
				+ "LIMIT ? OFFSET ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestador);
			stmt.setInt(2, chamadosPorPagina);
			stmt.setInt(3, chamadosPorPagina * pagina);
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
	
	public List<Agendamento> listarPorPrestadorEStatus(Connection conn, int idPrestador, StatusAgendamento status, int pagina) {
		List<Agendamento> lista = new ArrayList<>();
		String sql = "SELECT * FROM Agendamento WHERE id_prestador = ? AND status = ? ORDER BY data_agendamento, hora_agendamento "
				+ "LIMIT ? OFFSET ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idPrestador);
			stmt.setString(2, status.name());
			stmt.setInt(3, chamadosPorPagina);
			stmt.setInt(4, chamadosPorPagina * pagina);
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
	
	public int getTotalPaginas(Connection conn) {
		int totalPaginas = 0;
		String sql = "SELECT COUNT(id_agendamento) AS totalChamados FROM Agendamento";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			totalPaginas = rs.getInt("totalChamados");
			totalPaginas = (int) Math.ceil((double) totalPaginas / chamadosPorPagina);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPaginas;
	}
	
	public int getTotalPaginasPorStatus(Connection conn, StatusAgendamento status) {
		int totalPaginas = 0;
		String sql = "SELECT COUNT(id_agendamento) AS totalChamados FROM Agendamento WHERE status LIKE ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			totalPaginas = rs.getInt("totalChamados");
			totalPaginas = (int) Math.ceil((double) totalPaginas / chamadosPorPagina);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPaginas;
	}
}
