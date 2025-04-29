package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
	
}
