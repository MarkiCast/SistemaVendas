package InterfaceGráfica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JanelaVenda {

	private JFrame frmJanelaVenda;
	private JPanel panel;
	public JTable table;
	private JTextField txtCpf;
	private JTextField cpfCliente;
	private JTextField txtCliente;
	private JTextField nomeCliente;
	private JTextField txtTotal;
	private JTextField txtDesconto;
	private JTextField txtFinal;
	private JTextField totalVenda;
	private JTextField desconto;
	private JTextField valorFinal;
	private JTextField txtPagamento;
	private JTextField txtPorcentagem;
	private JTextField porcentagem;
	private JCheckBox chckbxCredito;
	private JCheckBox chckbxDebito;
	private JCheckBox chckbxCheque;
	private JCheckBox chckbxCrediario;
	public JButton btnOk;
	
	public boolean checkPorcentagem = true;
	public boolean checkCpf = false;
	public boolean checkItem = false;
	public boolean checkVenda = false;
	public boolean checkProduto = false;

	public static JCheckBox[] checkList = new JCheckBox[4];

	/**	
	 * Test the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaVenda frame = new JanelaVenda();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaVenda() {
		initialize();
		frmJanelaVenda.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJanelaVenda = new JFrame();
		frmJanelaVenda.setTitle("Venda");
		frmJanelaVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJanelaVenda.setBounds(100, 100, 700, 668);
		frmJanelaVenda.getContentPane().setLayout(null);
		frmJanelaVenda.setForeground(SystemColor.activeCaption);
		frmJanelaVenda.setFont(null);
		frmJanelaVenda.setBackground(SystemColor.window);

		panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 360, 684, 276);
		frmJanelaVenda.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 110, 179, 99, 153, 118, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 33, 32, 50, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		txtCpf = new JTextField();
		txtCpf.setEditable(false);
		txtCpf.setBackground(SystemColor.inactiveCaptionBorder);
		txtCpf.setText("CPF");
		GridBagConstraints gbc_txtCpf = new GridBagConstraints();
		gbc_txtCpf.insets = new Insets(0, 0, 5, 5);
		gbc_txtCpf.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCpf.gridx = 0;
		gbc_txtCpf.gridy = 1;
		panel.add(txtCpf, gbc_txtCpf);
		txtCpf.setColumns(10);

		cpfCliente = new JTextField(14);
		cpfCliente.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}
			public void warn() {
				if(cpfCliente.getText() != "") {
					checkCpf = true;
				} else {
					setNomeCliente("");
				}
			}
		});

		GridBagConstraints gbc_cpfCliente = new GridBagConstraints();
		gbc_cpfCliente.insets = new Insets(0, 0, 5, 5);
		gbc_cpfCliente.fill = GridBagConstraints.BOTH;
		gbc_cpfCliente.gridx = 1;
		gbc_cpfCliente.gridy = 1;
		panel.add(cpfCliente, gbc_cpfCliente);
		cpfCliente.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setEditable(false);
		txtTotal.setText("Total");
		txtTotal.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_txtTotal = new GridBagConstraints();
		gbc_txtTotal.insets = new Insets(0, 0, 5, 5);
		gbc_txtTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTotal.gridx = 3;
		gbc_txtTotal.gridy = 1;
		panel.add(txtTotal, gbc_txtTotal);
		txtTotal.setColumns(10);

		totalVenda = new JTextField();
		GridBagConstraints gbc_totalVenda = new GridBagConstraints();
		gbc_totalVenda.insets = new Insets(0, 0, 5, 0);
		gbc_totalVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_totalVenda.gridx = 4;
		gbc_totalVenda.gridy = 1;
		panel.add(totalVenda, gbc_totalVenda);
		totalVenda.setColumns(10);

		txtCliente = new JTextField();
		txtCliente.setBackground(SystemColor.inactiveCaptionBorder);
		txtCliente.setText("Cliente");
		txtCliente.setEditable(false);
		GridBagConstraints gbc_txtCliente = new GridBagConstraints();
		gbc_txtCliente.insets = new Insets(0, 0, 5, 5);
		gbc_txtCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCliente.gridx = 0;
		gbc_txtCliente.gridy = 2;
		panel.add(txtCliente, gbc_txtCliente);
		txtCliente.setColumns(10);

		nomeCliente = new JTextField();
		GridBagConstraints gbc_nomeCliente = new GridBagConstraints();
		gbc_nomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_nomeCliente.fill = GridBagConstraints.BOTH;
		gbc_nomeCliente.gridx = 1;
		gbc_nomeCliente.gridy = 2;
		panel.add(nomeCliente, gbc_nomeCliente);
		nomeCliente.setColumns(10);

		txtPorcentagem = new JTextField();
		txtPorcentagem.setText("Desconto (%)");
		txtPorcentagem.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPorcentagem.setEditable(false);
		txtPorcentagem.setColumns(10);
		txtPorcentagem.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_txtPorcentagem = new GridBagConstraints();
		gbc_txtPorcentagem.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorcentagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorcentagem.gridx = 3;
		gbc_txtPorcentagem.gridy = 2;
		panel.add(txtPorcentagem, gbc_txtPorcentagem);

		porcentagem = new JTextField(3);
		porcentagem.setText("0");
		GridBagConstraints gbc_porcentagem = new GridBagConstraints();
		gbc_porcentagem.insets = new Insets(0, 0, 5, 0);
		gbc_porcentagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_porcentagem.gridx = 4;
		gbc_porcentagem.gridy = 2;
		panel.add(porcentagem, gbc_porcentagem);
		porcentagem.setColumns(10);
		porcentagem.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				warn();
			}
			public void removeUpdate(DocumentEvent e) {
				warn();
			}
			public void insertUpdate(DocumentEvent e) {
				warn();
			}
			public void warn() {
				if(porcentagem.getText() != "") {
					checkPorcentagem = true;
				} else { checkPorcentagem = false; }
			}
		});

		txtDesconto = new JTextField();
		txtDesconto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDesconto.setBackground(SystemColor.inactiveCaptionBorder);
		txtDesconto.setEditable(false);
		txtDesconto.setText("Desconto");
		GridBagConstraints gbc_txtDesconto = new GridBagConstraints();
		gbc_txtDesconto.insets = new Insets(0, 0, 5, 5);
		gbc_txtDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDesconto.gridx = 3;
		gbc_txtDesconto.gridy = 3;
		panel.add(txtDesconto, gbc_txtDesconto);
		txtDesconto.setColumns(10);

		desconto = new JTextField();
		GridBagConstraints gbc_desconto = new GridBagConstraints();
		gbc_desconto.insets = new Insets(0, 0, 5, 0);
		gbc_desconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_desconto.gridx = 4;
		gbc_desconto.gridy = 3;
		panel.add(desconto, gbc_desconto);
		desconto.setColumns(10);

		txtPagamento = new JTextField();
		txtPagamento.setBackground(SystemColor.inactiveCaptionBorder);
		txtPagamento.setEditable(false);
		txtPagamento.setText("Pagamento");
		GridBagConstraints gbc_txtPagamento = new GridBagConstraints();
		gbc_txtPagamento.insets = new Insets(0, 0, 5, 5);
		gbc_txtPagamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPagamento.gridx = 0;
		gbc_txtPagamento.gridy = 4;
		panel.add(txtPagamento, gbc_txtPagamento);
		txtPagamento.setColumns(10);

		chckbxCredito = new JCheckBox("Credito");
		GridBagConstraints gbc_chckbxCredito = new GridBagConstraints();
		gbc_chckbxCredito.anchor = GridBagConstraints.WEST;
		gbc_chckbxCredito.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCredito.gridx = 1;
		gbc_chckbxCredito.gridy = 4;
		panel.add(chckbxCredito, gbc_chckbxCredito);

		chckbxDebito = new JCheckBox("Debito");
		GridBagConstraints gbc_chckbxDebito = new GridBagConstraints();
		gbc_chckbxDebito.anchor = GridBagConstraints.WEST;
		gbc_chckbxDebito.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxDebito.gridx = 1;
		gbc_chckbxDebito.gridy = 5;
		panel.add(chckbxDebito, gbc_chckbxDebito);

		txtFinal = new JTextField();
		txtFinal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFinal.setEditable(false);
		txtFinal.setBackground(SystemColor.inactiveCaptionBorder);
		txtFinal.setText("Valor Final");
		GridBagConstraints gbc_txtFinal = new GridBagConstraints();
		gbc_txtFinal.insets = new Insets(0, 0, 5, 5);
		gbc_txtFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFinal.gridx = 3;
		gbc_txtFinal.gridy = 5;
		panel.add(txtFinal, gbc_txtFinal);
		txtFinal.setColumns(10);

		valorFinal = new JTextField();
		GridBagConstraints gbc_valorFinal = new GridBagConstraints();
		gbc_valorFinal.insets = new Insets(0, 0, 5, 0);
		gbc_valorFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_valorFinal.gridx = 4;
		gbc_valorFinal.gridy = 5;
		panel.add(valorFinal, gbc_valorFinal);
		valorFinal.setColumns(10);

		chckbxCheque = new JCheckBox("Cheque");
		GridBagConstraints gbc_chckbxCheque = new GridBagConstraints();
		gbc_chckbxCheque.anchor = GridBagConstraints.WEST;
		gbc_chckbxCheque.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCheque.gridx = 1;
		gbc_chckbxCheque.gridy = 6;
		panel.add(chckbxCheque, gbc_chckbxCheque);

		chckbxCrediario = new JCheckBox("Crediario");
		GridBagConstraints gbc_chckbxCrediario = new GridBagConstraints();
		gbc_chckbxCrediario.anchor = GridBagConstraints.WEST;
		gbc_chckbxCrediario.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCrediario.gridx = 1;
		gbc_chckbxCrediario.gridy = 7;
		panel.add(chckbxCrediario, gbc_chckbxCrediario);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"Sim", "Não"};
				int answer = JOptionPane.showOptionDialog(null, "Deseja mesmo cancelar?", "Cancelar", JOptionPane.DEFAULT_OPTION, JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[0]);
				if (answer == JOptionPane.YES_OPTION) {
					checkCpf = false;
					checkItem = false;
					checkPorcentagem = false;
					checkProduto = false;
					checkVenda = false;
					frmJanelaVenda.dispatchEvent(new WindowEvent(frmJanelaVenda, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);

		btnOk = new JButton("OK");
		btnOk.setEnabled(false);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkVenda = true;
				
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 8;
		panel.add(btnOk, gbc_btnNewButton_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 684, 362);
		frmJanelaVenda.getContentPane().add(scrollPane);

		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setShowVerticalLines(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
				{1, "", "", null, 0},
			},
			new String[] {
				"Quantidade", "Codigo", "Produto", "Unidade", "Total"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				BigDecimal.class, String.class, String.class, BigDecimal.class, BigDecimal.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});			
		table.getColumnModel().getColumn(1).setPreferredWidth(148);
		table.getColumnModel().getColumn(2).setPreferredWidth(177);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(64);
		
		table.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				if(e.getColumn() == 0 || e.getColumn() == 1) {
					checkProduto = true;
				}
			}
			
		});
		scrollPane.setViewportView(table);

		Listener listener = new Listener();
		checkList[0] = chckbxCredito;
		checkList[1] = chckbxDebito;
		checkList[2] = chckbxCheque;
		checkList[3] = chckbxCrediario;
		for (int i = 0; i < 4; i++) {
			checkList[i].addItemListener(listener);
		}

	}

	public String getCpfCliente() {
		return cpfCliente.getText();
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente.setText(cpfCliente);
	}

	public String getNomeCliente() {
		return nomeCliente.getText();
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente.setText(nomeCliente);
	}

	public String getTotalVenda() {
		return totalVenda.getText();
	}

	public void setTotalVenda(String totalVenda) {
		this.totalVenda.setText(totalVenda);
	}

	public String getDesconto() {
		return desconto.getText();
	}

	public void setDesconto(String desconto) {
		this.desconto.setText(desconto);
	}

	public String getValorFinal() {
		return valorFinal.getText();
	}

	public void setValorFinal(String valorFinal) {
		this.valorFinal.setText(valorFinal);
	}

	public String getPorcentagem() {
		return porcentagem.getText();
	}

	public void setPorcentagem(String porcentagem) {
		this.porcentagem.setText(porcentagem);
	}

	public JFrame getFrmJanelaVenda() {
		return frmJanelaVenda;
	}
	
	public String getPagamento() {
		char code[] = {'0', '0', '0', '0'};
		
		if (chckbxCredito.isSelected()) {
			code[0] = '1';
		}
		if (chckbxDebito.isSelected()) {
			code[1] = '1';
		}
		if (chckbxCheque.isSelected()) {
			code[2] = '1';
		}
		if (chckbxCrediario.isSelected()) {
			code[3] = '1';
		}
		String pagamento = new String(code);
		return pagamento;
	}
	
	public String[] getInfo() {
		String[] array = new String[] {getCpfCliente(), getPorcentagem(), getValorFinal(), getPagamento()};
		return array;
	}

	public String[][] getProdutos() {
		int rowCount = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 1) != "") {
				rowCount++;
			}
		}
		String[][] matrix = new String[rowCount][2];
		for (int i = 0; i < table.getRowCount(); i++) {
			if (table.getValueAt(i, 1) != "") {
				matrix[i][0] = (String) table.getValueAt(i,0).toString();
				matrix[i][1] = (String) table.getValueAt(i,1).toString();
			}
		}
		return matrix;
	}
	
}

class Listener implements ItemListener {

	private final int MAX_SELECTIONS = 2;
	private int selectionCounter = 0;

	@Override
	public void itemStateChanged(ItemEvent e) {
		JCheckBox source = (JCheckBox) e.getSource();

		if (source.isSelected()) {
			selectionCounter++;
			// check for max selections:
			if (selectionCounter == MAX_SELECTIONS)
				for (JCheckBox box : JanelaVenda.checkList)
					if (!box.isSelected())
						box.setEnabled(false);
		} else {
			selectionCounter--;
			// check for less than max selections:
			if (selectionCounter < MAX_SELECTIONS)
				for (JCheckBox box : JanelaVenda.checkList)
					box.setEnabled(true);
		}

	}
}
