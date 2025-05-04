package model;

import java.sql.Time;

public class Disponibilidade {
	
	private int idDisponibilidade;
    private int idPrestador;
    private int diaSemana;
    private Time horario;
    
    public Disponibilidade() {
		
	}
    
	public Disponibilidade(int idDisponibilidade, int idPrestador, int diaSemana, Time horario) {
		super();
		this.idDisponibilidade = idDisponibilidade;
		this.idPrestador = idPrestador;
		this.diaSemana = diaSemana;
		this.horario = horario;
	}

	public int getIdDisponibilidade() {
		return idDisponibilidade;
	}

	public void setIdDisponibilidade(int idDisponibilidade) {
		this.idDisponibilidade = idDisponibilidade;
	}

	public int getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}
}
