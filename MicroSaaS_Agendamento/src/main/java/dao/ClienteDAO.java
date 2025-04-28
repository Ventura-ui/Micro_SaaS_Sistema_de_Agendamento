package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Cliente;

public class ClienteDAO {
	
	public boolean cadastrarCliente(Connection connection, Cliente cliente) {
		String sql = "INSERT INTO Cliente (nome, cpf, endereco, contato, email, senha) VALUES (?, ?, ?, ?, ?, ?)";
		
		
		if(cliente != null) {
			
			int rows = -1;
			try(PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getCpf());
				stmt.setString(3, cliente.getEndereco());
				stmt.setString(4, cliente.getContato());
				stmt.setString(5, cliente.getEmail());
				stmt.setString(6, cliente.getSenha());
				
				rows = stmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return rows > 0;
		}
		
		return false;
	}
	
	public Cliente buscarPorEmailESenha(Connection connection, String email, String senha) {
		String sql = "SELECT * FROM Cliente WHERE email = ? AND senha = ?";
		
		try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setContato(rs.getString("contato"));
                    cliente.setEndereco(rs.getString("endereco"));
                    return cliente;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
