package model;

import java.time.LocalDate;
import java.time.LocalTime;

import model.Enum.StatusAgendamento;

public class Agendamento {
	
	private int idAgendamento;
    private int idPrestador;
    private int idCliente;
    private LocalDate data;
    private LocalTime horario;
    private StatusAgendamento status = StatusAgendamento.solicitado;
    
    public Agendamento() {
	}
    
	public Agendamento(int idAgendamento, int idPrestador, int idCliente, LocalDate data, LocalTime horario) {
		this.idAgendamento = idAgendamento;
		this.idPrestador = idPrestador;
		this.idCliente = idCliente;
		this.data = data;
		this.horario = horario;
	}

	public int getIdAgendamento() {
		return idAgendamento;
	}

	public void setIdAgendamento(int idAgendamento) {
		this.idAgendamento = idAgendamento;
	}

	public int getIdPrestador() {
		return idPrestador;
	}

	public void setIdPrestador(int idPrestador) {
		this.idPrestador = idPrestador;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public StatusAgendamento getStatus() {
		return status;
	}

	public void setStatus(StatusAgendamento status) {
		this.status = status;
	}
}
