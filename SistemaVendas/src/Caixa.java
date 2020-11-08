import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

class Caixa {
	
	ArrayList<Venda> listaVendas;
	LoadStoreXml lsx;
	Venda venda = new Venda();
	int id = -1; //Tem que salvar isso em algum lugar pra quando salvar as vendas, nenhuma ter id igual
	
	public Caixa() {
		ArrayList<Venda> listaVendas = new ArrayList<Venda>();
		this.listaVendas = listaVendas;
		this.lsx = new LoadStoreXml();
	}
	
	public void fechamentoDeCaixa() throws SAXException, Exception,
            IOException, ParserConfigurationException, TransformerException {

		int valorFinal = 0;
		for (Venda v : listaVendas){
			if(v == null){
				continue;
			}
			int preco = v.getValorTotal();
			valorFinal = valorFinal + preco;
			lsx.salvaVenda(v);
		}
		listaVendas.clear();
	}
	
	public void cancelaVenda(int idVenda) {
		Venda cancelada = null;
		for (Venda v : listaVendas) {
			if (v.getId() == idVenda) {
				cancelada = v;
			}
		}
		if (!(cancelada == null)) {
			listaVendas.remove(cancelada);
		}
	}


	public boolean verificaCpf(String input) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		Cliente cliente = lsx.findCliente(input);
		if(cliente==null){
			return false;
		} else {
			return true;
		}
	}
	
	public Cliente achaCliente(String input) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		Cliente cliente = lsx.findCliente(input);
		return cliente;
	}
	
	public void encheListaProdutos() throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		if(lsx.fileVazia("produtos")){
			Produto calca = new Produto(100, 1L, "Calca");
			Produto camisa = new Produto(50, 2L, "Camisa");
			Produto sapato = new Produto(150, 3L, "sapato n40");
			lsx.cadastraProduto(calca);
			lsx.cadastraProduto(camisa);
			lsx.cadastraProduto(sapato);
		}
	}
	
	public boolean produtoValido(String input) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		Produto produto = lsx.findProduto(input);
		if(produto == null) {
			return false;
		}
		venda.produtos.add(produto);
		return true;
		//Produto produto = produtos.get(upc); (Isso tava aqui desde o outro metodo, n sei exatamente pra que serve)
	}

	public int criarVenda(String cpf, int desc, byte pagamento) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		Cliente cliente = lsx.findCliente(cpf);
		id++;
		Venda sell = new Venda(id,desc,pagamento,cliente);
		sell.setProdutos(venda.getProduto());
		sell.calcularTotal();
		listaVendas.add(sell);
		return sell.getValorTotal();
	}

	public void clearVenda() {
		venda.produtos.clear();
	}

	public void showVendas() { //Ta s� aqui pra teste, talvez seja interessante implementar dps
		if (listaVendas.isEmpty()) {
			System.out.print("Lista vazia");
		} 
		else {
			for(Venda v : listaVendas) {
				System.out.print(v.getCliente().getCpf() + " " + v.getValorTotal() + "\n");
			}
		}
	}
	
	public void cadastraCliente(String nome, String cpf) throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		lsx.cadastraCliente(nome,cpf);
	}

	public boolean vendaValida(int idVenda) {
		Venda vendaCancelada = null;
		if(idVenda < listaVendas.size()) {
			vendaCancelada = listaVendas.get(idVenda);
		}
		if(vendaCancelada == null) {
			return false;
		}
		return true;
	}

	public String retornaCliente(int idVenda) {
		return listaVendas.get(idVenda).cliente.getNome();
	}

	public int retornaValor(int idVenda) {
		return listaVendas.get(idVenda).getValorTotal();
	}
	
}

//	public void loopCaixa() throws SAXException, Exception,
//            IOException, ParserConfigurationException, TransformerException {
//		
//		//Xml handler de clientes 
//		LoadStoreXml lsx = new LoadStoreXml();
//		
////		//Hashmap de clientes
////		HashMap<Long, Cliente> clientes = new HashMap<Long, Cliente>();
////		Cliente marcelo = new Cliente("Marcelo",11986559920L);
////		Cliente klaus = new Cliente("Klaus",1L);
////		Cliente teste = new Cliente("teste",2L);
////		clientes.put(marcelo.getCpf(), marcelo);
////		clientes.put(klaus.getCpf(), klaus);
////		clientes.put(teste.getCpf(), teste);
//		
////		//Hashmap de produtos
////		HashMap<Long, Produto> produtos = new HashMap<Long,Produto>();
////		Produto calça = new Produto(100, 1L, "Calça");
////		Produto camisa = new Produto(50, 2L, "Camisa");
////		Produto sapato = new Produto(150, 3L, "sapato n40");
////		produtos.put(calça.getUpc(), calça);
////		produtos.put(camisa.getUpc(), camisa);
////		produtos.put(sapato.getUpc(), sapato);
//		
//		
//		boolean valid = true;
//		
//		int cont = -1; // contador que serve como id das compras
//		while (valid) {
//			//Inicio da operação
//			Scanner in = new Scanner(System.in);
//			System.out.printf("%n"
//					+ "Ponto de Vendas %n"
//					+ "Digite 1 para vender, 2 para cadastrar cliente, 3 para cancelar uma venda, 4 para sair e fechar caixa %n");
//			int option = in.nextInt();		
//			switch (option) {
//				case 1: {
//					//CPF do cliente
//					Scanner inputcpf = new Scanner(System.in);
//					boolean pessoaInvalida = true;
//					Cliente cliente = new Cliente();
//					while(pessoaInvalida){
//						System.out.printf("Insira cpf do cliente %n");
//						String input = inputcpf.nextLine();
//						cliente = lsx.findCliente(input);
//						if(cliente==null){
//							System.out.println("pessoa não encontrada");
//							continue;
//						} else {
//							pessoaInvalida = false;
//						}
//					}
//					
//					//Cadastrando produtos
//					ArrayList<Produto> produtosVenda = new ArrayList<Produto>();
//					System.out.printf("Digite 0 para parar %n");
//					String upc = "";
//					do {
//						Scanner inputproduto = new Scanner(System.in);
//						System.out.printf("Insira o codigo do produto %n");	
//						upc = inputproduto.nextLine();
//						System.out.println("upc: "+upc);
//						Produto prod = lsx.findProduto(upc);
//						if(prod == null && !upc.equals("0")){
//							System.out.println("produto nao encontrado");
//							continue;
//						}
//						//Produto produto = produtos.get(upc);
//						produtosVenda.add(prod);
//					} while (!upc.equals("0"));
//					
//					//Detalhes da Venda
//					//Desconto
//					System.out.printf("Insira o valor do desconto (0 a 100) %n");
//					int desconto;
//					Scanner inputdesconto = new Scanner(System.in);
//					desconto = inputdesconto.nextInt();
//					
//					//Forma de pagamento
//					System.out.printf("Qual a forma de pagamento? 1-Credito  2-Debito  3-Cheque  4-Crediário %n");
//					Scanner inputpagamento = new Scanner(System.in);
//					byte pagamento = inputpagamento.nextByte();
//					
//					cont += 1;
//					Venda venda = new Venda(cont, desconto, pagamento, cliente, produtosVenda);
//					listaVendas.add(venda);
//					
//					venda.calcularTotal();
//					System.out.printf("O valor total foi "+venda.getValorTotal());
//					System.out.printf("\nO id da venda foi "+venda.getId());
//					break;
//				}
//				
//				case 2: {
//					//lsx.cadastraCliente();
//					break;
//				}
//				
//				case 3: {
//					System.out.println("digite o id da venda");
//					int venda = in.nextInt();
//					cancelaVenda(venda);
//					break;
//				}
//				
//				case 4: {
//					valid = false;
//					System.out.print("Fechamento de Caixa");
//					break;
//				}
//				
//				case 5: { // testa acha cliente
//					Scanner scan = new Scanner(System.in);
//        			System.out.println("insira cpf do cliente");
//        			String c = scan.nextLine();
//					Cliente cli = lsx.findCliente(c);
//					System.out.println(cli.getNome());
//					break;
//				}
//			}
//		}
//	}