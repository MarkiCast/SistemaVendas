package BancoDeDados;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Logica.Venda;
import Logica.Cliente;
import Logica.Produto;

public class MapeadorVenda implements InterfaceMapeador<Venda> {
	Connection con;
	private MapeadorCliente map_cliente;
	private MapeadorProduto map_produto;
	
	public MapeadorVenda(Connection con) throws SQLException{
		this.con = con;
		map_cliente = new MapeadorCliente(con);
		map_produto = new MapeadorProduto(con);
	}
	
	public void put(Venda venda) throws SQLException {  // vendas nunca substituem outras
		if(this.get(venda.getId())==null) {
			insereNovo(venda);
		}
		else {
			atualizaExistente(venda);
		}
	}

	public Venda get(Object idd) throws SQLException{
		Long id = Long.parseLong((String) idd);
		Cliente cliente;
		int desconto;
		BigDecimal total;
		String tipo_pagamento;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		ArrayList<Integer> quantidade = new ArrayList<Integer>();
		Venda venda;
		
		PreparedStatement stmt = con.prepareStatement("SELECT CLIENTE_CPF," +
				"DESCONTO,TIPO_PAGAMENTO,TOTAL FROM VENDA WHERE ID=?");
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try {
			if(rs.next()) { 	// in first iteration we grab some info on client we don't need to re-grab for every row
				cliente = map_cliente.get(rs.getLong("CLIENTE_CPF"));
				desconto = rs.getInt("DESCONTO");
				total = rs.getBigDecimal("TOTAL");
				tipo_pagamento = rs.getString("TIPO_PAGAMENTO");
				
				PreparedStatement stm = con.prepareStatement("SELECT PRODUTO_UPC,QUANTIDADE FROM ITEMVENDA WHERE ID in (?)");
				stm.setLong(1, id);
				ResultSet r = stm.executeQuery();
				try {
					
					while(r.next()) {
						produtos.add(map_produto.get(r.getLong("PRODUTO_UPC")));
						quantidade.add(r.getInt("QUANTIDADE"));
					}
				} finally {
					r.close();
					stm.close();
				}
			} else {
				return null;  //no elements returned in query
			}
			venda = new Venda(id, cliente, desconto, total, tipo_pagamento);
			
			Produto[] p = new Produto[produtos.size()];
			p = produtos.toArray(p);
			venda.setProdutos(p);
			
			Integer[] qtd = new Integer[quantidade.size()];
			qtd = quantidade.toArray(qtd);
			venda.setQuantidades(qtd);
			
			return venda;
		} finally {
			rs.close();
			stmt.close();
		}
	}
	
	public void insereNovo(Venda venda) throws SQLException{
		
		//atributos que nao mudam nas iteracoes sao instaciados
		Long cpf = venda.getCliente().getCpf(); 
		int desconto = venda.getDesconto();
		String tipo_pagamento = venda.getTipoPagamento();
		BigDecimal valorTotal = venda.getValorTotal();
		Long id = venda.getId();
		Produto[] produtos = venda.getProdutos();
		Integer[] quantidades = venda.getQuantidades();
		
		for (int i = 0; i < produtos.length; i++) {
			PreparedStatement stm = con.prepareStatement("INSERT INTO ITEMVENDA (ID," +
					"PRODUTO_UPC,QUANTIDADE) VALUES (?,?,?)");
			try {
				stm.setLong(1, id);
				stm.setLong(2, produtos[i].getUpc());
				stm.setInt(3, quantidades[i]);
				stm.execute();
			} finally {
				stm.close();
			}
		}
		
		
		PreparedStatement stmt = con.prepareStatement("INSERT INTO VENDA (CLIENTE_CPF," +
				"DESCONTO,TIPO_PAGAMENTO,TOTAL,ID) VALUES (?,?,?,?,?)");
		try {
			stmt.setLong(1, cpf);
			stmt.setInt(2, desconto);
			stmt.setString(3, tipo_pagamento);
			stmt.setBigDecimal(4, valorTotal);
			stmt.setLong(5, id);
			//exec
			stmt.execute();
		} finally {
			stmt.close();
		}
		
	}
	
	// not used
	public void atualizaExistente(Venda e) throws SQLException {
		
	}

	public void remove(Object l) throws SQLException {
		Long id = (Long) l;
		
		PreparedStatement stmt = con.prepareStatement("DELETE FROM ITEMVENDA WHERE ID=?");
		stmt.setLong(1, id);	
		stmt.execute();
		stmt.close();
		
		PreparedStatement stm = con.prepareStatement("DELETE FROM VENDA WHERE ID=?");
		stm.setLong(1, id);	
		stm.execute();
		stm.close();	
	}
	
}
