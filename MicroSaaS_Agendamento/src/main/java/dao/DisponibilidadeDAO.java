package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Disponibilidade;

public class DisponibilidadeDAO {
	
	public void inserirOuAtualizar(Connection conn ,Disponibilidade d){
		String sql = "INSERT INTO Disponibilidade (id_prestador, dia_semana, horario) " +
	             "VALUES (?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, d.getIdPrestador());
	        stmt.setInt(2, d.getDiaSemana());
	        stmt.setTime(3, d.getHorario());
	        stmt.executeUpdate();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	}


    public List<Disponibilidade> listarPorPrestador(Connection conn, int idPrestador){
        List<Disponibilidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM Disponibilidade WHERE id_prestador = ? ORDER BY dia_semana, horario";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPrestador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disponibilidade d = new Disponibilidade();
                    d.setIdDisponibilidade(rs.getInt("id_disponibilidade"));
                    d.setIdPrestador(rs.getInt("id_prestador"));
                    d.setDiaSemana(rs.getInt("dia_semana"));
                    d.setHorario(rs.getTime("horario"));
                    lista.add(d);
                }
            }
        }catch (Exception e) {
			e.printStackTrace();
		}
        
        return lista;
    }
	
}
