package BancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Logica.Cliente;
import Logica.Endereco;

public class MapeadorCliente implements InterfaceMapeador<Cliente>{
	
	Connection con;
	private MapeadorEndereco map_endereco;
	
	public MapeadorCliente(Connection con) throws SQLException{
		this.con = con;
		map_endereco = new MapeadorEndereco(con);
	}
	
	public void put(Cliente cliente) throws SQLException {
		if (this.get(cliente.getCpf())!=null) {
			atualizaExistente(cliente);	
		} 
		else {
			insereNovo(cliente);	
		}	
	}
	
	public Cliente get(Object l) throws SQLException{
		Cliente cliente;
		Endereco endereco;
		PreparedStatement stmt = con.prepareStatement("SELECT NOME,ENDERECO_CEP,NUMERO FROM CLIENTE WHERE CPF=?");
		Long cpf = (Long) l;
		stmt.setLong(1, cpf);
		ResultSet rs = stmt.executeQuery();
		try {
			if (rs.next()) {
				endereco = map_endereco.get(rs.getInt("ENDERECO_CEP"));
				String nome = rs.getString("NOME");
				int numero = rs.getInt("NUMERO");
				cliente = new Cliente(cpf, nome, endereco, numero);
				return cliente;
			} else {
				return null;
			}
		} finally {
			rs.close();
			stmt.close();
		}
	}
	
	public void atualizaExistente(Cliente cliente) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE CLIENTE SET NOME=?, ENDERECO_CEP=?, NUMERO=? WHERE CPF = ?");
		try {
			stmt.setLong(4, cliente.getCpf());
			stmt.setString(1, cliente.getNome());
			stmt.setInt(2, cliente.getEndereco().getCep());
			stmt.setInt(3, cliente.getNumero());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}
	
	
	public void insereNovo(Cliente cliente) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("INSERT INTO CLIENTE (CPF,NOME,ENDERECO_CEP,NUMERO) "+
				"VALUES (?,?,?,?)");
		try {
			stmt.setLong(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setInt(3, cliente.getEndereco().getCep());
			stmt.setInt(4, cliente.getNumero());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}


	@Override
	public void remove(Object l) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM CLIENTE WHERE CPF=?");
		Long cpf = (Long) l;
		stmt.setLong(1,cpf);
		stmt.execute();
		stmt.close();
		
	}

}
