package BancoDeDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Logica.Endereco;

public class MapeadorEndereco implements InterfaceMapeador<Endereco>{

	Connection con;
		
	public MapeadorEndereco(Connection con) throws SQLException{
		this.con = con;
	}
		
	public void put(Endereco endereco) throws SQLException {
		if (this.get(endereco.getCep())!=null) {
			atualizaExistente(endereco);	
		} 
		else {
			insereNovo(endereco);	
		}	
	}
	
	@Override
	public Endereco get(Object cepp) throws SQLException{
		Endereco endereco;
		int cep = (int) cepp;
		PreparedStatement stmt = con.prepareStatement("SELECT RUA,BAIRRO,MUNICIPIO FROM ENDERECO WHERE CEP=?");
		stmt.setInt(1, cep);
		ResultSet rs = stmt.executeQuery();
		try {
			if (rs.next()) {
				String rua = rs.getString("RUA");
				String bairro = rs.getString("BAIRRO");
				String municipio = rs.getString("MUNICIPIO");
				endereco = new Endereco(cep, rua, bairro, municipio);
				return endereco;
			} else {
				return null;
			}
		} finally {
			rs.close();
			stmt.close();
		}
	}

	public void atualizaExistente(Endereco endereco) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("UPDATE ENDERECO SET RUA=?, BAIRRO=?, MUNICIPIO=? WHERE CEP = ?");
		try {
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getMunicipio());
			stmt.setLong(4, endereco.getCep());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}


	public void insereNovo(Endereco endereco) throws SQLException{
		PreparedStatement stmt = con.prepareStatement("INSERT INTO ENDERECO (RUA,BAIRRO,MUNICIPIO,CEP) "+
				"VALUES (?,?,?,?)");
		try {
			stmt.setString(1, endereco.getRua());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getMunicipio());
			stmt.setLong(4, endereco.getCep());
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	@Override
	public void remove(Object i) throws SQLException {
		PreparedStatement stmt = con.prepareStatement("DELETE FROM ENDERECO WHERE CEP=?");
		int cep = (int) i;
		stmt.setLong(1,cep);
		stmt.execute();
		stmt.close();

	}
}

