
import java.util.ArrayList;

public class Venda {

	private int id;
	private int valorTotal = 0;
	private int desconto = 100;
	private byte tipoPagamento;
	Cliente cliente;
	ArrayList<Produto> produtos;

	public Venda(int id, int desconto, byte tipoPagamento, Cliente cliente, ArrayList<Produto> produtos) {
		this.id = id;
		this.desconto = desconto;
		this.tipoPagamento = tipoPagamento;
		this.cliente = cliente;
		this.produtos = produtos;
	}

	public int getId() {
		return id;
	}
	
	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getDesconto() {
		return desconto;
	}

	public void setDesconto(int desconto) {
		this.desconto = desconto;
	}

	public byte getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(byte tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public ArrayList<Produto> getProduto() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	} 
	
	public void calcularTotal() {
		for(int i = 0; i < produtos.size()-1; i++) {
			valorTotal += produtos.get(i).getValor();
		}
		valorTotal = valorTotal*((100-desconto)/100);  //bug arredondamento por valorTotal ser inteiro
	}
	
}
