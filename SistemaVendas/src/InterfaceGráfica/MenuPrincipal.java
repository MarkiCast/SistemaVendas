package InterfaceGráfica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MenuPrincipal{

	private JFrame frmPontoDeVenda;
	private JPanel panel;
	public String[] cadastro;

	public boolean checkCadastro = false;
	public volatile boolean checkFechamento = false;
	public boolean checkJanelaVenda = false;
	public boolean janelaVendaFechada = false;
	public boolean checkJanelaCancelamento = false;
	public boolean checkJanelaCadastro = false;
	public boolean checkCancelamento = false;;
	
	public String id;
	
	public JanelaCadastro janelaCadastro;
	public JanelaVenda janelaVenda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
		frmPontoDeVenda.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPontoDeVenda = new JFrame();
		frmPontoDeVenda.setForeground(SystemColor.activeCaption);
		frmPontoDeVenda.setSize(new Dimension(450, 325));
		frmPontoDeVenda.setFont(null);
		frmPontoDeVenda.setTitle("Ponto de Venda");
		frmPontoDeVenda.setBackground(SystemColor.window);
		frmPontoDeVenda.setBounds(100, 100, 450, 300);
		frmPontoDeVenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPontoDeVenda.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 239);
		panel.setBackground(SystemColor.window);
		frmPontoDeVenda.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{434, 0};
		gbl_panel.rowHeights = new int[] {40, 30, 40, 30, 40, 30, 30, 40};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton venda = new JButton("Venda");
		venda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaVenda = new JanelaVenda();
				checkJanelaVenda = true;
				WindowListener listener = new WindowAdapter() {
					public void windowClosing(WindowEvent evt) {
						janelaVendaFechada = true;
					}
				};
				janelaVenda.getFrmJanelaVenda().addWindowListener(listener);
			}
		});
		venda.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_venda = new GridBagConstraints();
		gbc_venda.fill = GridBagConstraints.BOTH;
		gbc_venda.insets = new Insets(0, 0, 5, 0);
		gbc_venda.gridx = 0;
		gbc_venda.gridy = 0;
		panel.add(venda, gbc_venda);
		
		JButton cadastroCliente = new JButton("Cadastro de Cliente");
		cadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				janelaCadastro = new JanelaCadastro();
				checkJanelaCadastro = true;	
			}
		});
		cadastroCliente.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_cadastroCliente = new GridBagConstraints();
		gbc_cadastroCliente.fill = GridBagConstraints.BOTH;
		gbc_cadastroCliente.insets = new Insets(0, 0, 5, 0);
		gbc_cadastroCliente.gridx = 0;
		gbc_cadastroCliente.gridy = 2;
		panel.add(cadastroCliente, gbc_cadastroCliente);
		
		JButton cancelamento = new JButton("Cancelamento de Venda");
		cancelamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id = JOptionPane.showInputDialog("Insira ID da venda");
				if (id != null) {
					checkCancelamento = true;
				}
			}
		});
		cancelamento.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_cancelamento = new GridBagConstraints();
		gbc_cancelamento.fill = GridBagConstraints.BOTH;
		gbc_cancelamento.insets = new Insets(0, 0, 5, 0);
		gbc_cancelamento.gridx = 0;
		gbc_cancelamento.gridy = 4;
		panel.add(cancelamento, gbc_cancelamento);
		
		JButton fechamento = new JButton("Fechamento de Caixa");
		fechamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"Sim", "Não"};
				int answer = JOptionPane.showOptionDialog(null, "Deseja mesmo fechar o caixa?", "Cancelar", JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[0]);
				if (answer == JOptionPane.YES_OPTION) {
					checkFechamento = true;
					frmPontoDeVenda.dispatchEvent(new WindowEvent(frmPontoDeVenda, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		fechamento.setBackground(SystemColor.activeCaption);
		GridBagConstraints gbc_fechamento = new GridBagConstraints();
		gbc_fechamento.fill = GridBagConstraints.BOTH;
		gbc_fechamento.gridx = 0;
		gbc_fechamento.gridy = 6;
		panel.add(fechamento, gbc_fechamento);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaption);
		frmPontoDeVenda.setJMenuBar(menuBar);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setBackground(SystemColor.activeCaption);
		menuBar.add(mnAjuda);
		
		JMenuItem mntmAtalhos = new JMenuItem("Atalhos");
		mntmAtalhos.setBackground(SystemColor.activeCaption);
		mntmAtalhos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "V - Venda \n"
						+ "C - Cadastro de Cliente \n"
						+ "M - Cancelamento de Venda \n"
						+ "F - Fechamento de Caixa \n"
						, "Atalhos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnAjuda.add(mntmAtalhos);
		
//		frmPontoDeVenda.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_V) {
//					venda.getAction().actionPerformed();
//				}
//				if(e.getKeyCode() == KeyEvent.VK_C) {
//					venda.getAction().actionPerformed(arg0);
//				}
//				if(e.getKeyCode() == KeyEvent.VK_M) {
//					venda.getAction().actionPerformed(arg0);
//				}
//				if(e.getKeyCode() == KeyEvent.VK_F) {
//					venda.getAction().actionPerformed(arg0);
//				}
//			}
//		});
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public void createJanelaVenda(Long cpf, int desconto, BigDecimal valorTotal, Long[] produtos, Integer[] quantidades) {
		// TODO Auto-generated method stub
		janelaVenda = new JanelaVenda();
		checkJanelaVenda = true;
		janelaVenda.setCpfCliente(cpf.toString());
		janelaVenda.setPorcentagem(Integer.toString(desconto));
		janelaVenda.setValorFinal(String.valueOf(valorTotal));
		janelaVenda.checkPorcentagem = false;
		
		for (int i = 0; i < produtos.length; i++) {
			janelaVenda.table.setValueAt(new BigDecimal(quantidades[i]), i, 0);
			janelaVenda.table.setValueAt(produtos[i].toString(), i, 1);
		}
		
		WindowListener listener = new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				janelaVendaFechada = true;
				checkCancelamento = false;
				checkJanelaCancelamento = false;
			}
		};
		janelaVenda.getFrmJanelaVenda().addWindowListener(listener);
		
		checkJanelaCancelamento = true;
	}
	
}

