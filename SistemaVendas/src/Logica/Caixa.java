package Logica;

import java.util.ArrayList;
import org.xml.sax.SAXException;
import java.util.Date;
import java.text.*;

import BancoDeDados.FachadaSistemaDatabase;

import javax.xml.transform.TransformerException;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

class Caixa { 

	FachadaSistemaDatabase facade;
	ArrayList<BigDecimal> listaValores;
	Venda venda;
	
	int id = -1;

	public Caixa() throws SQLException{
		this.listaValores = new ArrayList<BigDecimal>();
		this.facade = new FachadaSistemaDatabase();
	}

	public void cadastraCliente(String[] array) throws SQLException{
		//getCpf(), getNome(), getRua(), getNumero(), getBairro(), getMunicipio(), getCep()
		try {
			Cliente cliente = (Cliente) facade.get(Long.parseLong(array[0]), "Cliente");
			if (cliente == null) {
				Endereco endereco = (Endereco) facade.get(Integer.parseInt(array[6]), "Endereco");
				if (endereco == null) {
					endereco = new Endereco(Integer.parseInt(array[6]), array[2], array[4], array[5]);
					facade.put(endereco, "Endereco");
				}
				cliente = new Cliente(Long.parseLong(array[0]), array[1], endereco, Integer.parseInt(array[3]));
				facade.put(cliente, "Cliente");
			}
			else {
				System.out.println("cliente repetido");
			}
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e);
		}
	}
	
	public void fechamentoDeCaixa() throws SQLException {
		BigDecimal valorFinal = new BigDecimal(0);
		for (BigDecimal b : listaValores) {
			valorFinal = valorFinal.add(b);
		}
		JOptionPane.showMessageDialog(null, "Valor do fechamento = " + valorFinal);
		listaValores.clear();
	}

	public void cancelaVenda(String id) throws SQLException {
		facade.delete(Long.parseLong(id), "Venda");
	}

	public boolean verificaCpf(String input) throws NumberFormatException, SQLException {
		try {
			Cliente cliente = (Cliente) facade.get(Long.parseLong(input), "Cliente");
			if (cliente == null) {
				return false;
			} else {
				return true;
			}
		} catch (NumberFormatException | SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public Cliente achaCliente(String input) throws SQLException {
		Long l = Long.parseLong(input);
		Cliente cliente = (Cliente) facade.get(l, "Cliente");
		return cliente;
	}

	public void criarVenda(String cpf, String desc, String total, String pagamento, String[][] produtos) throws SQLException {
		Cliente cliente = achaCliente(cpf);
		int desconto = Integer.parseInt(desc);
		BigDecimal valorTotal = new BigDecimal(total.replaceAll(",", ""));	
		
		id++;
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("ddMMyyyyHHmmss");
		Long idVenda = Long.parseLong(time.format(date) + id);
		
		Produto[] prod = new Produto[produtos.length];
		Integer[] quantidades = new Integer[produtos.length];
		
		
		for (int i = 0; i < produtos.length; i++) {
			prod[i] = findProduto(produtos[i][1]);
			quantidades[i] = Integer.parseInt(produtos[i][0]);
		}
		
		Venda sell = new Venda(idVenda, cliente, desconto, valorTotal, pagamento);
		sell.setProdutos(prod);
		sell.setQuantidades(quantidades);
		listaValores.add(valorTotal);
		facade.put(sell, "Venda");
		
	}

	public Produto findProduto(String upc) throws SQLException {
		Long l = Long.parseLong(upc);
		Produto produto = (Produto) facade.get(l, "Produto");
		if (produto == null) {
			return null;
		}
		return produto;
	}
}