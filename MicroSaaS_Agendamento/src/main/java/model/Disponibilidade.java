package model;

import java.sql.Time;

public class Disponibilidade {
	
	private int idDisponibilidade;
    private int idPrestador;
    private int diaSemana;
    private Time horario_inicio;
    private Time horario_fim;
    private Time horario_descanso_inicio;
    private Time horario_descanso_fim;
    private Time tempo_servico;
    
    public Disponibilidade() {
		
	}
    
    public Disponibilidade(int idDisponibilidade, int idPrestador, int diaSemana, Time horario_inicio, Time horario_fim,
			Time horario_descanso_inicio, Time horario_descanso_fim, Time tempo_servico) {
		super();
		this.idDisponibilidade = idDisponibilidade;
		this.idPrestador = idPrestador;
		this.diaSemana = diaSemana;
		this.horario_inicio = horario_inicio;
		this.horario_fim = horario_fim;
		this.horario_descanso_inicio = horario_descanso_inicio;
		this.horario_descanso_fim = horario_descanso_fim;
		this.tempo_servico = tempo_servico;
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

	public Time getHorario_inicio() {
		return horario_inicio;
	}

	public void setHorario_inicio(Time horario_inicio) {
		this.horario_inicio = horario_inicio;
	}

	public Time getHorario_fim() {
		return horario_fim;
	}

	public void setHorario_fim(Time horario_fim) {
		this.horario_fim = horario_fim;
	}

	public Time getHorario_descanso_inicio() {
		return horario_descanso_inicio;
	}

	public void setHorario_descanso_inicio(Time horario_descanso_inicio) {
		this.horario_descanso_inicio = horario_descanso_inicio;
	}

	public Time getHorario_descanso_fim() {
		return horario_descanso_fim;
	}

	public void setHorario_descanso_fim(Time horario_descanso_fim) {
		this.horario_descanso_fim = horario_descanso_fim;
	}

	public Time getTempo_servico() {
		return tempo_servico;
	}

	public void setTempo_servico(Time tempo_servico) {
		this.tempo_servico = tempo_servico;
	}
}
