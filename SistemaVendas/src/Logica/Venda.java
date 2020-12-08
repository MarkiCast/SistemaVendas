package Logica;

import java.math.BigDecimal;

public class Venda {

	private Long id;
	Cliente cliente;
	private int desconto = 0;
	private BigDecimal valorTotal;
	private String tipoPagamento;
	Produto[] produtos;
	Integer[] quantidades;

	public Venda(Long id, Cliente cliente, int desconto, BigDecimal valorTotal, String tipoPagamento) {
		this.id = id;
		this.desconto = desconto;
		this.tipoPagamento = tipoPagamento;
		this.cliente = cliente;
		this.valorTotal = valorTotal;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setProdutos(Produto[] produtos) {
		this.produtos = produtos;
	}

	public Integer[] getQuantidades() {
		return quantidades;
	}

	public void setQuantidades(Integer[] qtd) {
		this.quantidades = qtd;
	}

	public Produto[] getProdutos() {
		return produtos;
	}
}
