
public class Produto {

	private int valor;
	private Long upc;
	private String desc;
	
	public Produto(int valor, Long upc, String desc) {
		this.valor = valor;
		this.upc = upc;
		this.desc = desc;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
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
	public Produto() {
		valor = 0;
		upc = 0L;
		desc = "";
	}
}