package BancoDeDados;

import java.sql.SQLException;
import java.util.ArrayList;
import Logica.Cliente;
import Logica.Produto;
import Logica.Venda;

public class Test {
	public static void main(String [] args) throws SQLException {
		//Cliente cliente;
		try {
			FachadaSistemaDatabase facade = new FachadaSistemaDatabase();
			//Produto produto = new Produto(10, 0L, "ovo");
			//facade.put(produto, "Produto");
			//facade.get(0L, "Produto");
			//PUT PRODUTO OK!
			
			//Produto p = (Produto) facade.get(0L, "Produto");
			//System.out.println(p.getUpc());
			//System.out.println(p.getDesc());
			//GET PRODUTO OK!
			
			//Cliente c = new Cliente("João", 0L, "rua 1");
			//facade.put(c, "Cliente");
			//Cliente retorno = (Cliente) facade.get(0L, "Cliente");
			//System.out.println(retorno.getCpf());
			//System.out.println(retorno.getEndereco());
			//GET E PUT CLIENTE OK!
			
			//ArrayList<Produto> produtos = new ArrayList<Produto>();
			//produtos.add(produto);
			//byte b = 0;
			//Venda venda = new Venda(0L, 0, b, c, produtos, 0001, 031220);
			//facade.put(venda, "Venda");
			//Venda retorno = (Venda) facade.get(0L, "Venda");
			//System.out.println(retorno.getCliente().getCpf());
			//System.out.println(retorno.getProduto().get(0).getUpc());
			//VENDA GET E PUT OK!
			
		} catch(SQLException e) {
			System.out.println("problemas ao chamar fachada");
			e.printStackTrace();
		}
	}
}
