package InterfaceGráfica;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JanelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JTextField cpf;
	private JTextField txtNome;
	private JTextField txtRua;
	private JTextField txtNumero;
	private JTextField nome;
	private JTextField rua;
	private JTextField numero;
	private JTextField txtBairro;
	private JTextField bairro;
	private JTextField txtMunicipio;
	private JTextField municipio;
	private JTextField cep;
	private JTextField txtCep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastro frame = new JanelaCadastro();
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
	public JanelaCadastro() {
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 485, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 472, 304);
		contentPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{100, 365, 0};
		gbl_panel.rowHeights = new int[]{29, 30, 29, 30, 29, 30, 29, 38, 29, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		txtCpf = new JTextField();
		txtCpf.setBackground(SystemColor.inactiveCaptionBorder);
		txtCpf.setEditable(false);
		txtCpf.setText("CPF / CNPJ");
		GridBagConstraints gbc_txtCpf = new GridBagConstraints();
		gbc_txtCpf.fill = GridBagConstraints.BOTH;
		gbc_txtCpf.insets = new Insets(0, 0, 5, 5);
		gbc_txtCpf.gridx = 0;
		gbc_txtCpf.gridy = 0;
		panel.add(txtCpf, gbc_txtCpf);
		txtCpf.setColumns(10);
		
		cpf = new JTextField();
		GridBagConstraints gbc_cpf = new GridBagConstraints();
		gbc_cpf.fill = GridBagConstraints.BOTH;
		gbc_cpf.insets = new Insets(0, 0, 5, 0);
		gbc_cpf.gridx = 1;
		gbc_cpf.gridy = 0;
		panel.add(cpf, gbc_cpf);
		cpf.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setText("Nome");
		txtNome.setBackground(SystemColor.inactiveCaptionBorder);
		txtNome.setEditable(false);
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.fill = GridBagConstraints.BOTH;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.gridx = 0;
		gbc_txtNome.gridy = 1;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		
		nome = new JTextField();
		GridBagConstraints gbc_nome = new GridBagConstraints();
		gbc_nome.fill = GridBagConstraints.BOTH;
		gbc_nome.insets = new Insets(0, 0, 5, 0);
		gbc_nome.gridx = 1;
		gbc_nome.gridy = 1;
		panel.add(nome, gbc_nome);
		nome.setColumns(10);
		
		txtRua = new JTextField();
		txtRua.setText("Rua");
		txtRua.setBackground(SystemColor.inactiveCaptionBorder);
		txtRua.setEditable(false);
		txtRua.setColumns(10);
		GridBagConstraints gbc_txtRua = new GridBagConstraints();
		gbc_txtRua.fill = GridBagConstraints.BOTH;
		gbc_txtRua.insets = new Insets(0, 0, 5, 5);
		gbc_txtRua.gridx = 0;
		gbc_txtRua.gridy = 2;
		panel.add(txtRua, gbc_txtRua);
		
		rua = new JTextField();
		GridBagConstraints gbc_rua = new GridBagConstraints();
		gbc_rua.fill = GridBagConstraints.BOTH;
		gbc_rua.insets = new Insets(0, 0, 5, 0);
		gbc_rua.gridx = 1;
		gbc_rua.gridy = 2;
		panel.add(rua, gbc_rua);
		rua.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setText("Numero");
		txtNumero.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_txtNumero = new GridBagConstraints();
		gbc_txtNumero.fill = GridBagConstraints.BOTH;
		gbc_txtNumero.insets = new Insets(0, 0, 5, 5);
		gbc_txtNumero.gridx = 0;
		gbc_txtNumero.gridy = 3;
		panel.add(txtNumero, gbc_txtNumero);
		txtNumero.setColumns(10);
		
		numero = new JTextField();
		GridBagConstraints gbc_numero = new GridBagConstraints();
		gbc_numero.fill = GridBagConstraints.BOTH;
		gbc_numero.insets = new Insets(0, 0, 5, 0);
		gbc_numero.gridx = 1;
		gbc_numero.gridy = 3;
		panel.add(numero, gbc_numero);
		numero.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setText("Bairro");
		txtBairro.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_txtBairro = new GridBagConstraints();
		gbc_txtBairro.fill = GridBagConstraints.BOTH;
		gbc_txtBairro.insets = new Insets(0, 0, 5, 5);
		gbc_txtBairro.gridx = 0;
		gbc_txtBairro.gridy = 4;
		panel.add(txtBairro, gbc_txtBairro);
		txtBairro.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		bairro = new JTextField();
		GridBagConstraints gbc_bairro = new GridBagConstraints();
		gbc_bairro.fill = GridBagConstraints.BOTH;
		gbc_bairro.insets = new Insets(0, 0, 5, 0);
		gbc_bairro.gridx = 1;
		gbc_bairro.gridy = 4;
		panel.add(bairro, gbc_bairro);
		bairro.setColumns(10);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setEditable(false);
		txtMunicipio.setText("Municipio");
		txtMunicipio.setBackground(SystemColor.inactiveCaptionBorder);
		GridBagConstraints gbc_txtMunicipio = new GridBagConstraints();
		gbc_txtMunicipio.fill = GridBagConstraints.BOTH;
		gbc_txtMunicipio.insets = new Insets(0, 0, 5, 5);
		gbc_txtMunicipio.gridx = 0;
		gbc_txtMunicipio.gridy = 5;
		panel.add(txtMunicipio, gbc_txtMunicipio);
		txtMunicipio.setColumns(10);
		
		municipio = new JTextField();
		GridBagConstraints gbc_municipio = new GridBagConstraints();
		gbc_municipio.fill = GridBagConstraints.BOTH;
		gbc_municipio.insets = new Insets(0, 0, 5, 0);
		gbc_municipio.gridx = 1;
		gbc_municipio.gridy = 5;
		panel.add(municipio, gbc_municipio);
		municipio.setColumns(10);
		
		txtCep = new JTextField();
		txtCep.setEditable(false);
		txtCep.setBackground(SystemColor.inactiveCaptionBorder);
		txtCep.setText("CEP");
		GridBagConstraints gbc_txtCep = new GridBagConstraints();
		gbc_txtCep.fill = GridBagConstraints.BOTH;
		gbc_txtCep.insets = new Insets(0, 0, 5, 5);
		gbc_txtCep.gridx = 0;
		gbc_txtCep.gridy = 6;
		panel.add(txtCep, gbc_txtCep);
		txtCep.setColumns(10);
		
		cep = new JTextField();
		GridBagConstraints gbc_cep = new GridBagConstraints();
		gbc_cep.fill = GridBagConstraints.BOTH;
		gbc_cep.insets = new Insets(0, 0, 5, 0);
		gbc_cep.gridx = 1;
		gbc_cep.gridy = 6;
		panel.add(cep, gbc_cep);
		cep.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.anchor = GridBagConstraints.WEST;
		gbc_btnCancelar.fill = GridBagConstraints.VERTICAL;
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 0;
		gbc_btnCancelar.gridy = 8;
		panel.add(btnCancelar, gbc_btnCancelar);
		GridBagConstraints gbc_btnOK = new GridBagConstraints();
		gbc_btnOK.anchor = GridBagConstraints.EAST;
		gbc_btnOK.fill = GridBagConstraints.VERTICAL;
		gbc_btnOK.gridx = 1;
		gbc_btnOK.gridy = 8;
		panel.add(btnOK, gbc_btnOK);
	}
	
}
