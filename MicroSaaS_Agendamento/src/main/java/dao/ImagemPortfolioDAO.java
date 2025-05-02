package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImagemPortfolioDAO {
	
	public void salvarImagem(Connection connection, int idPrestador, String caminhoImagem) {
		try {
			String sql = "INSERT INTO Imagem_Portfolio (id_prestador, caminho_imagem) VALUES (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, idPrestador);
			stmt.setString(2, caminhoImagem);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> buscarImagensPorPrestador(int id, Connection conn) {
	    List<String> imagens = new ArrayList<>();
	    String sql = "SELECT * FROM Imagem_Portfolio WHERE id_prestador = ?";
	    
	    try(PreparedStatement stmt = conn.prepareStatement(sql);) {
	    	stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		        imagens.add(rs.getString("caminho_imagem"));
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return imagens;
	}
	
}
