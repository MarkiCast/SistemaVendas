package BancoDeDados;

import java.sql.SQLException;

public interface InterfaceMapeador <E>{
	public void put(E o) throws SQLException;
	
	public void insereNovo(E e) throws SQLException;
	
	public void atualizaExistente(E e) throws SQLException;
	
	public E get(Object oid) throws SQLException;
	
	public void remove(Object l) throws SQLException;
}
