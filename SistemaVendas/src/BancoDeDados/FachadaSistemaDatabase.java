package BancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class FachadaSistemaDatabase {
	
	private String jdbcURL = "jdbc:postgresql://localhost/postgres"; // endereço db
	private String username = "postgres";
	private String password = "postgres";
	private Connection con;
	HashMap<String, InterfaceMapeador> mappersCollection;
	
	public FachadaSistemaDatabase() throws SQLException { //realizo coneccao e instancio mapeadores
		con = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("connected");
		
		mappersCollection = new HashMap<String, InterfaceMapeador>();
		MapeadorCliente mc = new MapeadorCliente(con);
		MapeadorProduto mp = new MapeadorProduto(con);
		MapeadorVenda mv = new MapeadorVenda(con);
		MapeadorEndereco me = new MapeadorEndereco(con);
		
		mappersCollection.put("Cliente", mc);
		mappersCollection.put("Produto", mp);
		mappersCollection.put("Venda", mv);
		mappersCollection.put("Endereco", me);
	}
	
	public void fimConexao() throws SQLException { 
		try {
			con.close();
			System.out.println("connection terminated");
		} catch (SQLException e) {
			System.out.println("error in finalizing connection");
			e.printStackTrace();
		}
	}
	
	public Object get(Object oid, String persistenceClass) throws SQLException { //realiza a conexao e chama o mapeamento para as tabela
		InterfaceMapeador<?> im = (InterfaceMapeador<?>) mappersCollection.get(persistenceClass);
		return im.get(oid);
	}
	
	public void put(Object o, String persistenceClass) throws SQLException { //realiza a conexao e chama o mapeamento para as tabela
		@SuppressWarnings("unchecked")
		InterfaceMapeador<Object> im = (InterfaceMapeador<Object>) mappersCollection.get(persistenceClass);
		im.put(o);
	}
	
	public void delete(Object o, String persistenceClass) throws SQLException {
		@SuppressWarnings("unchecked")
		InterfaceMapeador<Object> im = (InterfaceMapeador<Object>) mappersCollection.get(persistenceClass);
		im.remove(o);
	}
}
