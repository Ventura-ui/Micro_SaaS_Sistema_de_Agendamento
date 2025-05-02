package model;

import java.sql.Time;

public class Disponibilidade {
	
	private int idDisponibilidade;
    private int idPrestador;
    private int diaSemana;
    private Time horarioInicio;
    private Time horarioFim;
    
    public Disponibilidade() {
		
	}
    
	public Disponibilidade(int idDisponibilidade, int idPrestador, int diaSemana, Time horarioInicio, Time horarioFim) {
		this.idDisponibilidade = idDisponibilidade;
		this.idPrestador = idPrestador;
		this.diaSemana = diaSemana;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
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

	public Time getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(Time horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public Time getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(Time horarioFim) {
		this.horarioFim = horarioFim;
	}
}
