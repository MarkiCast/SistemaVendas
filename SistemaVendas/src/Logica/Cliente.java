package Logica;

public class Cliente {

	private String nome;
	private Long cpf;
	private Endereco endereco;
	private int numero;

	public Cliente(Long cpf, String nome, Endereco endereço, int numero) {
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereço;
		this.setNumero(numero);
	}
	
	public String getNome() {
		return nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
