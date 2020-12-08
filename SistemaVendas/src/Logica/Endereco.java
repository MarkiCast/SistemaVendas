package Logica;

public class Endereco {

	private String rua;
	private String bairro;
	private String municipio;
	private int cep;

	public Endereco (int cep, String rua, String bairro, String municipio) {
		this.rua = rua;
		this.bairro = bairro;
		this.municipio = municipio;
		this.cep = cep;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
}
