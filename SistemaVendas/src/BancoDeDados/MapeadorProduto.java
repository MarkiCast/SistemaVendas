package BancoDeDados;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Logica.Produto;

public class MapeadorProduto implements InterfaceMapeador<Produto>{
	Connection con;
	
	public MapeadorProduto(Connection con) throws SQLException{
		this.con = con;
	}

	@Override
	public void put(Produto produto) throws SQLException {
		if(this.get(produto.getUpc())!=null) {
			atualizaExistente(produto);
		} else {
			insereNovo(produto);
		}
		
	}
	
	@Override
	public void insereNovo(Produto produto) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("INSERT INTO PRODUTO (UPC,VALOR,DESCRICAO) "+
				"VALUES (?,?,?)");
		try {
			stmt.setLong(1, produto.getUpc());
			stmt.setBigDecimal(2, produto.getValor());
			stmt.setString(3, produto.getDesc());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void atualizaExistente(Produto e) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE PRODUTO SET VALOR=?, DESCRICAO=? WHERE UPC=?");
		try {
			stmt.setBigDecimal(1, e.getValor());
			stmt.setString(2, e.getDesc());
			stmt.setLong(3, e.getUpc());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}
	
	@Override
	public Produto get(Object upc) throws SQLException {
		Produto produto;
		Long l = (Long) upc;
		PreparedStatement stmt = con.prepareStatement("SELECT VALOR,DESCRICAO FROM PRODUTO WHERE UPC=?");
		stmt.setLong(1, l);
		ResultSet rs = stmt.executeQuery();
		try {
			if (rs.next()) {
				BigDecimal valor = rs.getBigDecimal("VALOR");
				String descricao = rs.getString("DESCRICAO");
				produto = new Produto(valor, l, descricao);
				return produto;
			} else {
				return null;
			}
		} finally {
			rs.close();
			stmt.close();
		}
	}
	
	@Override
	public void remove(Object l) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM PRODUTO WHERE UPC=?");
		Long upc = (Long) l;
		stmt.setLong(1, upc);	
		stmt.execute();
		stmt.close();				
	}


}
