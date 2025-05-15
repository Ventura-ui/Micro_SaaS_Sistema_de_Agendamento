package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Prestador;

public class PrestadorDAO {

	private int chamadosPorPagina = 6;

	public boolean cadastrarPrestador(Connection connection, Prestador prestador) {
		String sql = "INSERT INTO Prestador (nome_fantasia, nome_completo, foto_perfil, endereco, descricao, email, senha)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		if (prestador != null) {

			int rows = -1;
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, prestador.getNome_fantasia());
				stmt.setString(2, prestador.getNome_completo());
				stmt.setString(3, prestador.getFoto_perfil());
				stmt.setString(4, prestador.getEndereco());
				stmt.setString(5, prestador.getDescricao());
				stmt.setString(6, prestador.getEmail());
				stmt.setString(7, prestador.getSenha());

				rows = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rows > 0;
		}

		return false;
	}

	public Prestador buscarPorEmailESenha(Connection connection, String email, String senha) {
		String sql = "SELECT * FROM Prestador WHERE email = ? AND senha = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Prestador prestador = new Prestador();
					prestador.setId_prestador(rs.getInt("id_prestador"));
					prestador.setNome_fantasia(rs.getString("nome_fantasia"));
					prestador.setNome_completo(rs.getString("nome_completo"));
					prestador.setFoto_perfil(rs.getString("foto_perfil"));
					prestador.setSenha(rs.getString("senha"));
					prestador.setData_criacao(rs.getTimestamp("data_criacao"));
					prestador.setEmail(rs.getString("email"));
					prestador.setEndereco(rs.getString("endereco"));
					prestador.setDescricao(rs.getString("descricao"));
					return prestador;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Prestador> buscarPorCidade(Connection conn, String cidade, int pagina) {
		List<Prestador> lista = new ArrayList<>();
		String sql = "SELECT * FROM Prestador WHERE endereco LIKE ? " + "LIMIT ? OFFSET ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + (cidade != null ? cidade : "") + "%");
			stmt.setInt(2, chamadosPorPagina);
			stmt.setInt(3, chamadosPorPagina * pagina);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Prestador prestador = new Prestador();
				prestador.setId_prestador(rs.getInt("id_prestador"));
				prestador.setNome_fantasia(rs.getString("nome_fantasia"));
				prestador.setNome_completo(rs.getString("nome_completo"));
				prestador.setFoto_perfil(rs.getString("foto_perfil"));
				prestador.setEndereco(rs.getString("endereco"));
				prestador.setDescricao(rs.getString("descricao"));
				prestador.setData_criacao(rs.getTimestamp("data_criacao"));
				prestador.setEmail(rs.getString("email"));
				prestador.setSenha(rs.getString("senha"));
				lista.add(prestador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Prestador> listarPrestadores(Connection conn, int pagina) {
		List<Prestador> prestadores = new ArrayList<>();
		String sql = "SELECT * FROM Prestador " + "ORDER BY nome_fantasia " + "LIMIT ? OFFSET ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql);) {

			stmt.setInt(1, chamadosPorPagina);
			stmt.setInt(2, chamadosPorPagina * pagina);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Prestador prestador = new Prestador();
				prestador.setId_prestador(rs.getInt("id_prestador"));
				prestador.setNome_fantasia(rs.getString("nome_fantasia"));
				prestador.setNome_completo(rs.getString("nome_completo"));
				prestador.setFoto_perfil(rs.getString("foto_perfil"));
				prestador.setEndereco(rs.getString("endereco"));
				prestador.setDescricao(rs.getString("descricao"));
				prestador.setData_criacao(rs.getTimestamp("data_criacao"));
				prestador.setEmail(rs.getString("email"));
				prestador.setSenha(rs.getString("senha"));

				prestadores.add(prestador);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return prestadores;
	}

	public int getTotalPaginas(Connection conn) {
		int totalPaginas = 0;
		String sql = "SELECT COUNT(id_prestador) AS totalChamados FROM Prestador";
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
	
	public int getTotalPaginasPorCidade(Connection conn, String cidade) {
		int totalPaginas = 0;
		String sql = "SELECT COUNT(id_prestador) AS totalChamados FROM Prestador WHERE endereco LIKE ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + (cidade != null ? cidade : "") + "%");
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
