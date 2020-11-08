import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class Controle {

	private Caixa caixa;
	private Scanner scanner;
	
	public Controle(Caixa caixa, Scanner scanner) {
		this.caixa = caixa;
		this.scanner = scanner;
	}
	
	public void loop() throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {
		print("Ponto de Vendas");
		while (true) {
			print("Digite 1 para vender, 2 para cadastrar cliente, 3 para cancelar uma venda, 4 para sair e fechar caixa, 5 para verificar vendas");
			
			int num = scanner.nextInt();
			switch (num) {
				//Venda
				case 1: {
					//Verificação do cpf do cliente
					Scanner inputcpf = new Scanner(System.in);
					print("Insira o cpf do cliente");
					String cpf = inputcpf.nextLine();
					if (!caixa.verificaCpf(cpf)) {
						print("Cpf não cadastrado, operação cancelada");
						break;
					} 
										
					//Adicionando produtos
					print("Digite 0 para parar");
					String upc = "";
					ArrayList<String> upcs = new ArrayList<String>();
					do {
						Scanner inputupc = new Scanner(System.in);
						print("Insira o codigo do produto");
						upc = inputupc.nextLine();
						print("upc: "+upc);
						if (!caixa.produtoValido(upc) && !upc.equals("0")) {
							print("Produto invalido");
						}
						else {
							upcs.add(upc);
						}
					} while (!upc.equals("0"));
					
					//Desconto
					print("Insira o valor do desconto (0 a 100)");
					Scanner inputdesc = new Scanner(System.in);
					int desc = inputdesc.nextInt();
					
					//Formas de pagamento
					print("Qual a forma de pagamento? 1-Credito  2-Debito  3-Cheque  4-Crediario (Só tem 1 forma, mas é pra ter até 2)");
					Scanner inputform = new Scanner(System.in);
					byte pagamento = inputform.nextByte();
					
					//Criando a venda
					System.out.print("Valor total foi: " + caixa.criarVenda(cpf,desc,pagamento) + "\n");
					
					caixa.clearVenda();
					break;
				}
				
				//Cadastro de cliente
				case 2: {
					Scanner inputnome = new Scanner(System.in);
				    print("insira nome do cliente");
				    String nome = inputnome.nextLine();
				               
				    Scanner inputcpf = new Scanner(System.in);
				    print("insira cpf do cliente");
				    String cpf = inputcpf.nextLine();
					caixa.cadastraCliente(nome, cpf);
					break;
				}
				
				//Cancelamento de venda
				case 3: {
					Scanner inputvenda = new Scanner(System.in);
					print("digite o id da venda");
					int venda = inputvenda.nextInt();
					
					if (!caixa.vendaValida(venda)) {
						print("Venda não existe, operação cancelada");
						continue;
					}
					
					System.out.println("cliente: "+ caixa.retornaCliente(venda));
					System.out.println("valor: "+caixa.retornaValor(venda));
					System.out.println("digite 's' para confirmar, 'n' para cancelar");
					
					do {
						Scanner inputcon = new Scanner(System.in);
						String confirm = inputcon.nextLine();
						if(confirm.equals("n")){
							print("Operação cancelada");
							break;
						}
						if(confirm.equals("s")){
							caixa.cancelaVenda(venda);
							break;
						}
						print("String invalida");
					} while (true);
									
					break;
				}
				
				//Fechamento de caixa
				case 4: {
					caixa.fechamentoDeCaixa();
					break;
				}
				
				//Mostra lista de vendas, cpf - valor total
				case 5: {
					caixa.showVendas();
					break;
				}
			}
			
		}
		
	}
	
	public void print(String message) {
		System.out.print(message + "\n");
	}
	
}
