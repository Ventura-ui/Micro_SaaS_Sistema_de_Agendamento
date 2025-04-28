package model;

public class Cliente {
	
	private int id_cliente;
	private String nome;
	private String cpf;
	private String endereco;
	private String contato;
	private String email;
	private String senha;
	
	public Cliente() {}
	
	public Cliente(int id_cliente, String nome, String cpf, String endereco, String contato, String email, String senha) {
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.contato = contato;
		this.email = email;
		this.senha = senha;
	}

	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
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
