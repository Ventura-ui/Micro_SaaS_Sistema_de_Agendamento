package model;

import java.sql.Timestamp;

public class LogAgendamento {
	
	private int idLog;
	private int id_agendamento;
	private String status_antigo;
	private String status_novo;
	private Timestamp data_alteracao;
	
	public LogAgendamento() {}

	public LogAgendamento(int idLog, int id_agendamento, String status_antigo, String status_novo,
			Timestamp data_alteracao) {
		super();
		this.idLog = idLog;
		this.id_agendamento = id_agendamento;
		this.status_antigo = status_antigo;
		this.status_novo = status_novo;
		this.data_alteracao = data_alteracao;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public int getId_agendamento() {
		return id_agendamento;
	}

	public void setId_agendamento(int id_agendamento) {
		this.id_agendamento = id_agendamento;
	}

	public String getStatus_antigo() {
		return status_antigo;
	}

	public void setStatus_antigo(String status_antigo) {
		this.status_antigo = status_antigo;
	}

	public String getStatus_novo() {
		return status_novo;
	}

	public void setStatus_novo(String status_novo) {
		this.status_novo = status_novo;
	}

	public Timestamp getData_alteracao() {
		return data_alteracao;
	}

	public void setData_alteracao(Timestamp data_alteracao) {
		this.data_alteracao = data_alteracao;
	}
}
