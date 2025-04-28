package model;

import java.sql.Timestamp;

public class Prestador {
	
	private int id_prestador;
	private String nome_fantasia;
	private String nome_completo;
	private String foto_perfil;
	private String endereco;
	private String descricao;
	private Timestamp data_criacao;
	private String email;
	private String senha;
	
	public Prestador() {}

	public Prestador(int id_prestador, String nome_fantasia, String nome_completo, String foto_perfil, String endereco,
			String descricao, String email, String senha) {
		super();
		this.id_prestador = id_prestador;
		this.nome_fantasia = nome_fantasia;
		this.nome_completo = nome_completo;
		this.foto_perfil = foto_perfil;
		this.endereco = endereco;
		this.descricao = descricao;
		this.email = email;
		this.senha = senha;
	}

	public int getId_prestador() {
		return id_prestador;
	}

	public void setId_prestador(int id_prestador) {
		this.id_prestador = id_prestador;
	}

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getFoto_perfil() {
		return foto_perfil;
	}

	public void setFoto_perfil(String foto_perfil) {
		this.foto_perfil = foto_perfil;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Timestamp data_criacao) {
		this.data_criacao = data_criacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
