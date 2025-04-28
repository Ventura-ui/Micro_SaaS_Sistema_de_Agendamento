package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cliente;
import model.Prestador;

public class PrestadorDAO {
	
	public boolean cadastrarPrestador(Connection connection, Prestador prestador) {
		String sql = "INSERT INTO Prestador (nome_fantasia, nome_completo, foto_perfil, endereco, descricao, email, senha)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		if(prestador != null) {
			
			int rows = -1;
			try(PreparedStatement stmt = connection.prepareStatement(sql)) {
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
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
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
	
}
