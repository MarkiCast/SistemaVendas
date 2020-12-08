package Logica;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

import BancoDeDados.FachadaSistemaDatabase;
import InterfaceGráfica.MenuPrincipal;

public class Controle {

	private Caixa caixa;
	private MenuPrincipal menu;
	private Cliente cliente;
	private Produto produto;
	private boolean checkJanelaVenda;
	private boolean checkCancelamento = true;
	private boolean criaVenda = true;

	public Controle(Caixa caixa, MenuPrincipal menu) {
		this.caixa = caixa;
		this.menu = menu;
	}

	public void loop() throws SAXException, IOException, ParserConfigurationException, TransformerException, Exception {

		boolean condition = true;
		while (condition) {

			// Cadastro de clientes
			if (menu.checkJanelaCadastro) {
				if (menu.janelaCadastro.checkCadastro) {
					caixa.cadastraCliente(menu.janelaCadastro.getInfo());
					menu.checkJanelaCadastro = false;				
				}
			}

			// Abriu janela venda
			if (menu.checkJanelaVenda) {
				checkJanelaVenda = true;
				menu.checkJanelaVenda = false;
			}

			// Fechou janela venda
			if (menu.janelaVendaFechada) {
				checkJanelaVenda = false;
			}

			// Checando CPF escrito na janela de venda (Auto-complete)
			if (checkJanelaVenda) {
				if (menu.janelaVenda.checkCpf) {
					String cpf = menu.janelaVenda.getCpfCliente();
					if (caixa.verificaCpf(cpf)) {
						cliente = caixa.achaCliente(cpf);
						menu.janelaVenda.setNomeCliente(cliente.getNome());
					}
				}
			}
			
			// Confirma se ele quer mesmo cancelar a venda
			if (menu.checkJanelaCancelamento) {
				
				if (JOptionPane.showConfirmDialog(null, "Deseja cancelar essa venda?", "Cancelamento", JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
					caixa.cancelaVenda(menu.id);
				}
				
				menu.checkJanelaCancelamento = false;
			}

			// Checagem dos produtos (Auto-Complete)
			if (checkJanelaVenda) {
				if (menu.janelaVenda.checkProduto) {
					// Auto Complete
					for (int i = 0; i < menu.janelaVenda.table.getRowCount(); i++) {
						if (menu.janelaVenda.table.getValueAt(i, 1) != "") {
							produto = caixa.findProduto(menu.janelaVenda.table.getValueAt(i, 1).toString());
							if (produto != null) {
								menu.janelaVenda.table.setValueAt(produto.getDesc(), i, 2);
								menu.janelaVenda.table.setValueAt(produto.getValor(), i, 3);
								menu.janelaVenda.table.setValueAt(produto.getValor().multiply(getBigDecimal(menu.janelaVenda.table.getValueAt(i, 0))), i, 4);
							} else {
								menu.janelaVenda.table.setValueAt("", i, 1);
								menu.janelaVenda.table.setValueAt("", i, 2);
								menu.janelaVenda.table.setValueAt("", i, 3);
								menu.janelaVenda.table.setValueAt(0, i, 4);
							}

						}
					}

					// Sort das quantidades
					for (int i = menu.janelaVenda.table.getRowCount() - 1; i >= 1; i--) {
						if (menu.janelaVenda.table.getValueAt(i, 1) != "") {
							for (int j = i - 1; j >= 0; j--) {
								if (menu.janelaVenda.table.getValueAt(j, 1) != "") {
									if (menu.janelaVenda.table.getValueAt(i, 1).equals(menu.janelaVenda.table.getValueAt(j, 1))) {
										menu.janelaVenda.table.setValueAt(getBigDecimal(menu.janelaVenda.table.getValueAt(j, 0)).add(getBigDecimal(menu.janelaVenda.table.getValueAt(i, 0))), j, 0);
										menu.janelaVenda.table.setValueAt(produto.getValor().multiply(getBigDecimal(menu.janelaVenda.table.getValueAt(j, 0))),j, 4);
										for (int k = 1; k <= 3; k++) {
											menu.janelaVenda.table.setValueAt("", i, k);
										}
										menu.janelaVenda.table.setValueAt(new BigDecimal(1), i, 0);
										menu.janelaVenda.table.setValueAt(new BigDecimal(0), i, 4);
									}
								}
							}
						}
					}
					menu.janelaVenda.checkProduto = false;
				}
			}

			// Checagem do desconto e total
			if (checkJanelaVenda) {
				if (menu.janelaVenda.checkPorcentagem) {
					BigDecimal soma = new BigDecimal(0);
					for (int i = 0; i < menu.janelaVenda.table.getRowCount(); i++) {
						if (menu.janelaVenda.table.getValueAt(i, 1) != "") {
							soma = soma.add(getBigDecimal(menu.janelaVenda.table.getValueAt(i, 4)));
						}
					}

					try {
						
						menu.janelaVenda.setTotalVenda(String.format("%.2f", soma));
						BigDecimal desconto = soma.multiply( (new BigDecimal(menu.janelaVenda.getPorcentagem())).divide(new BigDecimal(100))   );
						menu.janelaVenda.setDesconto(String.format("%.2f", desconto));
						menu.janelaVenda.setValorFinal(String.format("%.2f", (soma.subtract(desconto))));
						
					} catch (Exception e) {
						print(e);
						menu.janelaVenda.checkPorcentagem = false;
					}

				}
			}

			// Habilitando botão de OK na JanelaVenda (tem que ter CPF, valor final (mesmo
			// que 0) e pelo menos 1 checkbox de pagamento selecionada)
			if (checkJanelaVenda) {
				if (caixa.verificaCpf(menu.janelaVenda.getCpfCliente()) & menu.janelaVenda.getValorFinal() != "" & menu.janelaVenda.getPagamento().contains("1")) {
					menu.janelaVenda.btnOk.setEnabled(true);
				} else {
					menu.janelaVenda.btnOk.setEnabled(false);
				}
			}

			// Finalização da venda
			if (checkJanelaVenda) {
				if (menu.janelaVenda.checkVenda) {
					String[] info = menu.janelaVenda.getInfo();
					caixa.criarVenda(info[0], info[1], info[2], info[3], menu.janelaVenda.getProdutos());
					
					menu.janelaVenda.checkVenda = false;
					menu.janelaVenda.getFrmJanelaVenda().dispatchEvent(new WindowEvent(menu.janelaVenda.getFrmJanelaVenda(), WindowEvent.WINDOW_CLOSING));
				}
			}

			// Abre a janela de venda com a venda lá depois de clicar Cancelamento de venda no menu principal
			if (menu.checkCancelamento) {
				
				Venda	v = (Venda) caixa.facade.get(menu.id, "Venda");
				Long[] l = new Long[v.produtos.length];
				
				for (int i = 0; i < v.produtos.length; i++) {
					l[i] = v.produtos[i].getUpc();
				}
				
				menu.createJanelaVenda(v.getCliente().getCpf(), v.getDesconto(), v.getValorTotal(), l , v.getQuantidades());
				menu.checkCancelamento = false;
			}	
			
			// Fechamento de caixa
			if (menu.checkFechamento) {
				caixa.fechamentoDeCaixa();
				menu.checkFechamento = false;
				condition = false;
			}

		}
	}

	public void print(Object object) {
		System.out.println(object);
	}
	
	public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }

}
