package InterfaceGráfica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JanelaVenda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtCpf;
	private JTextField cpfCliente;
	private JTextField txtCliente;
	private JTextField nomeCliente;
	private JTextField txtTotal;
	private JTextField txtDesconto;
	private JTextField txtFinal;
	private JTextField textTotal;
	private JTextField textDesconto;
	private JTextField textFinal;
	private JTextField txtPagamento;
	private JTextField txtPorcentagem;
	private JTextField textPorcentagem;
	private String cpf;
	
	public static JCheckBox[] checkList = new JCheckBox[4];

	/**
	 * Test the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaVenda frame = new JanelaVenda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JanelaVenda() {
		setTitle("Venda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 360, 684, 301);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{110, 179, 99, 153, 118, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 33, 32, 50, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		cpfCliente = new JTextField(11);
		cpfCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					cpf = cpfCliente.getText();
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
		
		textTotal = new JTextField();
		GridBagConstraints gbc_textTotal = new GridBagConstraints();
		gbc_textTotal.insets = new Insets(0, 0, 5, 0);
		gbc_textTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTotal.gridx = 4;
		gbc_textTotal.gridy = 1;
		panel.add(textTotal, gbc_textTotal);
		textTotal.setColumns(10);
		
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
		
		textPorcentagem = new JTextField();
		GridBagConstraints gbc_textPorcentagem = new GridBagConstraints();
		gbc_textPorcentagem.insets = new Insets(0, 0, 5, 0);
		gbc_textPorcentagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPorcentagem.gridx = 4;
		gbc_textPorcentagem.gridy = 2;
		panel.add(textPorcentagem, gbc_textPorcentagem);
		textPorcentagem.setColumns(10);
		
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
		
		textDesconto = new JTextField();
		GridBagConstraints gbc_textDesconto = new GridBagConstraints();
		gbc_textDesconto.insets = new Insets(0, 0, 5, 0);
		gbc_textDesconto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDesconto.gridx = 4;
		gbc_textDesconto.gridy = 3;
		panel.add(textDesconto, gbc_textDesconto);
		textDesconto.setColumns(10);
		
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
		
		JCheckBox chckbxCredito = new JCheckBox("Credito");
		GridBagConstraints gbc_chckbxCredito = new GridBagConstraints();
		gbc_chckbxCredito.anchor = GridBagConstraints.WEST;
		gbc_chckbxCredito.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCredito.gridx = 1;
		gbc_chckbxCredito.gridy = 4;
		panel.add(chckbxCredito, gbc_chckbxCredito);
		
		JCheckBox chckbxDebito = new JCheckBox("Debito");
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
		
		textFinal = new JTextField();
		GridBagConstraints gbc_textFinal = new GridBagConstraints();
		gbc_textFinal.insets = new Insets(0, 0, 5, 0);
		gbc_textFinal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFinal.gridx = 4;
		gbc_textFinal.gridy = 5;
		panel.add(textFinal, gbc_textFinal);
		textFinal.setColumns(10);
		
		JCheckBox chckbxCheque = new JCheckBox("Cheque");
		GridBagConstraints gbc_chckbxCheque = new GridBagConstraints();
		gbc_chckbxCheque.anchor = GridBagConstraints.WEST;
		gbc_chckbxCheque.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCheque.gridx = 1;
		gbc_chckbxCheque.gridy = 6;
		panel.add(chckbxCheque, gbc_chckbxCheque);
		
		JCheckBox chckbxCrediario = new JCheckBox("Crediario");
		GridBagConstraints gbc_chckbxCrediario = new GridBagConstraints();
		gbc_chckbxCrediario.anchor = GridBagConstraints.WEST;
		gbc_chckbxCrediario.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCrediario.gridx = 1;
		gbc_chckbxCrediario.gridy = 7;
		panel.add(chckbxCrediario, gbc_chckbxCrediario);
		
		JButton btnNewButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 8;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("OK");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 8;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 684, 362);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setShowVerticalLines(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Quantidade", "C\u00F3digo", "Produto", "Unidade", "Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Long.class, String.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(148);
		table.getColumnModel().getColumn(2).setPreferredWidth(177);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(64);
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
                for (JCheckBox box: JanelaVenda.checkList)
                    if (!box.isSelected())
                        box.setEnabled(false);
        }
        else {
            selectionCounter--;
            // check for less than max selections:
            if (selectionCounter < MAX_SELECTIONS)
                for (JCheckBox box: JanelaVenda.checkList)
                    box.setEnabled(true);
        }
		
	}
}
