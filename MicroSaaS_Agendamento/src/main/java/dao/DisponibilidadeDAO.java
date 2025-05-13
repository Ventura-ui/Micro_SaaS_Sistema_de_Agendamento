package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Disponibilidade;

public class DisponibilidadeDAO {
	
	public void inserirOuAtualizar(Connection conn ,Disponibilidade d){
		String sql = "INSERT INTO Disponibilidade (id_prestador, dia_semana, horario_inicio, horario_fim, horario_descanso_inicio, horario_descanso_fim, tempo_servico) " +
	             "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, d.getIdPrestador());
	        stmt.setInt(2, d.getDiaSemana());
	        stmt.setTime(3, d.getHorario_inicio());
	        stmt.setTime(4, d.getHorario_fim());
	        stmt.setTime(5, d.getHorario_descanso_inicio());
	        stmt.setTime(6, d.getHorario_descanso_fim());
	        stmt.setTime(7, d.getTempo_servico());
	        stmt.executeUpdate();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	}


    public List<Disponibilidade> listarPorPrestador(Connection conn, int idPrestador){
        List<Disponibilidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM Disponibilidade WHERE id_prestador = ? ORDER BY dia_semana, horario_inicio";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idPrestador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Disponibilidade d = new Disponibilidade();
                    d.setIdDisponibilidade(rs.getInt("id_disponibilidade"));
                    d.setIdPrestador(rs.getInt("id_prestador"));
                    d.setDiaSemana(rs.getInt("dia_semana"));
                    d.setHorario_inicio(rs.getTime("horario_inicio"));
                    d.setHorario_fim(rs.getTime("horario_fim"));
                    d.setHorario_descanso_inicio(rs.getTime("horario_descanso_inicio"));
                    d.setHorario_descanso_fim(rs.getTime("horario_descanso_fim"));
                    d.setTempo_servico(rs.getTime("tempo_servico"));
                    lista.add(d);
                }
            }
        }catch (Exception e) {
			e.printStackTrace();
		}
        
        return lista;
    }
    
    public Disponibilidade buscarPorPrestadorEDia(Connection conn, int idPrestador, int diaSemana) {
    	String sql = "SELECT * FROM Disponibilidade WHERE id_prestador = ? AND dia_semana = ?";
    	
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, idPrestador);
            stmt.setInt(2, diaSemana);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Disponibilidade d = new Disponibilidade();
                d.setHorario_inicio(rs.getTime("horario_inicio"));
                d.setHorario_fim(rs.getTime("horario_fim"));
                d.setHorario_descanso_inicio(rs.getTime("horario_descanso_inicio"));
                d.setHorario_descanso_fim(rs.getTime("horario_descanso_fim"));
                d.setTempo_servico(rs.getTime("tempo_servico"));
                return d;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
