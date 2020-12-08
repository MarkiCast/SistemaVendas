package Logica;

import java.math.BigDecimal;

public class Produto {

	private BigDecimal valor;
	private Long upc;
	private String desc;

	public Produto(BigDecimal valor, Long upc, String desc) {
		this.valor = valor;
		this.upc = upc;
		this.desc = desc;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getUpc() {
		return upc;
	}

	public void setUpc(Long upc) {
		this.upc = upc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}