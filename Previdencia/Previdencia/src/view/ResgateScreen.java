package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.JdbcController;
import controller.ScreenController;
import model.Conta;
import model.Participante;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class ResgateScreen extends JFrame {

	private JPanel contentPane;
	private Conta conta;
	private JTextField tfValor;
	private JTextField tfParcelas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private boolean parcelas;



	/**
	 * Create the frame.
	 */
	public ResgateScreen(Conta conta) {
		this.conta = conta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResgateDaConta = new JLabel("Resgate da conta "+conta.getIdConta());
		lblResgateDaConta.setBounds(145, 11, 149, 22);
		contentPane.add(lblResgateDaConta);
		
		JLabel lblSaldoAtual = new JLabel("Saldo Atual(Portabilidade): R$"+conta.getSaldoPortabilidade());
		lblSaldoAtual.setBounds(62, 44, 291, 14);
		contentPane.add(lblSaldoAtual);
		

		
		JComboBox cbTipo = new JComboBox();
		
		JRadioButton rdbtnTotal = new JRadioButton("Resgate Total");
		buttonGroup.add(rdbtnTotal);
		rdbtnTotal.setForeground(Color.WHITE);
		rdbtnTotal.setBackground(Color.DARK_GRAY);
		rdbtnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch((String)cbTipo.getSelectedItem()) {
				case "PORTABILIDADE" :
					tfValor.setText(""+conta.getSaldoPortabilidade());
					tfValor.setEditable(false);
					break;
				case "ADICIONAL" :
					tfValor.setText(""+conta.getSaldoContribuicoesAdicionais());
					tfValor.setEditable(false);
					break;
				case "NORMAL" :
					tfValor.setText(""+conta.getSaldoContribuicoesNormais());
					tfValor.setEditable(false);
					break;
				}
			}
		});
		rdbtnTotal.setBounds(62, 90, 130, 23);
		contentPane.add(rdbtnTotal);
		
		JRadioButton rdbtnResgateParcial = new JRadioButton("Resgate Parcial");
		buttonGroup.add(rdbtnResgateParcial);
		rdbtnResgateParcial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfValor.setText("");
				tfValor.setEditable(true);
			}
		});
		rdbtnResgateParcial.setBackground(Color.DARK_GRAY);
		rdbtnResgateParcial.setForeground(Color.WHITE);
		rdbtnResgateParcial.setBounds(189, 90, 149, 23);
		contentPane.add(rdbtnResgateParcial);
		
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"PORTABILIDADE", "ADICIONAL", "NORMAL", "TOTAL"}));
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch((String)cbTipo.getSelectedItem()) {
				case "PORTABILIDADE" :
					rdbtnResgateParcial.setEnabled(true);
					rdbtnTotal.setEnabled(true);
					tfValor.setText("");
					tfValor.setEditable(true);
					lblSaldoAtual.setText("Saldo Atual(Portabilidade): R$"+conta.getSaldoPortabilidade());
					break;
				case "ADICIONAL" :
					rdbtnResgateParcial.setEnabled(true);
					rdbtnTotal.setEnabled(true);
					tfValor.setText("");
					tfValor.setEditable(true);
					lblSaldoAtual.setText("Saldo Atual(Adicional): R$"+conta.getSaldoContribuicoesAdicionais());
					break;
				case "NORMAL" :
					rdbtnResgateParcial.setEnabled(true);
					rdbtnTotal.setEnabled(true);
					tfValor.setText("");
					tfValor.setEditable(true);
					lblSaldoAtual.setText("Saldo Atual(Normal): R$"+conta.getSaldoContribuicoesNormais());
					break;
				case "TOTAL" :
					lblSaldoAtual.setText("Saldo Atual(Total): R$"+(conta.getSaldoContribuicoesNormais()+conta.getSaldoContribuicoesAdicionais()+conta.getSaldoPortabilidade()));
					tfValor.setText(""+(conta.getSaldoContribuicoesNormais()+conta.getSaldoContribuicoesAdicionais()+conta.getSaldoPortabilidade()));
					tfValor.setEditable(false);
					rdbtnResgateParcial.setEnabled(false);
					rdbtnTotal.setEnabled(false);
					break;

				}
			}
		});
		cbTipo.setBounds(189, 66, 149, 20);
		contentPane.add(cbTipo);
		
		JLabel lblTipoDeResgate = new JLabel("Tipo de Saldo:");
		lblTipoDeResgate.setBounds(62, 69, 117, 14);
		contentPane.add(lblTipoDeResgate);
		
		JButton button = new JButton("Cancelar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(0 == JOptionPane.showConfirmDialog(null, "Perdera os dados preenchidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
					setVisible(false);
					ScreenController.getInstance().showContaScreen(conta);
				}	
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.setBounds(221, 178, 117, 23);
		contentPane.add(button);
		
		JLabel lblValorAResgatar = new JLabel("Valor a Resgatar :");
		lblValorAResgatar.setBounds(62, 120, 117, 14);
		contentPane.add(lblValorAResgatar);
		
		tfValor = new JTextField();
		tfValor.setBounds(189, 117, 149, 20);
		contentPane.add(tfValor);
		tfValor.setColumns(10);
		
	
		
		Participante part = JdbcController.getInstance().findParticipanteByContaId(conta.getIdConta());
		if(part.getSituacaoParticipante().equals("ATIVO")||part.getSituacaoParticipante().equals("SUSPENSO")||part.getSituacaoParticipante().equals("VINCULADO")) {
			tfParcelas = new JTextField();
			tfParcelas.setText("1");
			tfParcelas.setBounds(189, 142, 149, 20);
			contentPane.add(tfParcelas);
			tfParcelas.setColumns(10);
			JLabel lblNumeroDeParcelas = new JLabel("Numero de parcelas :");
			lblNumeroDeParcelas.setBounds(62, 145, 117, 14);
			contentPane.add(lblNumeroDeParcelas);
			parcelas = true;
		}
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Double.parseDouble(tfValor.getText());
					
					try {
						if(Double.parseDouble(tfValor.getText()) <= 0) {
							JOptionPane.showMessageDialog(null, "Favor inserir somente numeros maiores que zero em valor");
						}else if(parcelas && !(Integer.parseInt(tfParcelas.getText()) <= 60) || !(Integer.parseInt(tfParcelas.getText()) > 0) ) {
							JOptionPane.showMessageDialog(null, "Maximo de 60 parcelas, minimo de 1");
						//validacao de resgate: Resgate normal efetuado em menos de 2 anos
						}else if(((String)cbTipo.getSelectedItem()).equals("NORMAL") && !JdbcController.getInstance().validateResgateNormal(conta.getIdConta())) {
							JOptionPane.showMessageDialog(null, "Resgates de contribuicoes normais so podem ocorrer a cada 2 anos!");
						//valida contas cadastradas ha mais de 3 anos.
						}else if(!JdbcController.getInstance().validateIdadeConta(conta.getIdConta())) { 
							JOptionPane.showMessageDialog(null, "Deve haver periodo de carencia de 3 anos de criacao de conta ate poder resgatar!");
						//valida resgate normal <= 20% do saldo
						}else if( ( ( (String) cbTipo.getSelectedItem() ).equals("NORMAL") ) && ( (Double.parseDouble(tfValor.getText())) > ( 0.2*conta.getSaldoContribuicoesNormais() ) ) ) {
							JOptionPane.showMessageDialog(null, "Somente pode-se realizar resgate de 20% das contribuicoes normais! valor maximo: "+(conta.getSaldoContribuicoesNormais()*0.2));
						}else {
							//solicita confirmacao para resgate total
							if (((String)cbTipo.getSelectedItem()).equals("TOTAL")){ 
								if(0 == JOptionPane.showConfirmDialog(null, "O resgate total dos saldos acarretara no desligamento do participante. Confirma?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
									//realiza a operacao de resgate, verificando o saldo.
									if(!JdbcController.getInstance().resgatar(conta, (String)cbTipo.getSelectedItem(), Double.parseDouble(tfValor.getText()), Integer.parseInt(tfParcelas.getText()))) {
									    JOptionPane.showMessageDialog(null, "Nao ha saldo suficiente para resgate", "Atencao", JOptionPane.WARNING_MESSAGE, null);
									}else {
										//realiza operacoes de cancelamento do resgate total.
										JdbcController.getInstance().resgateTotal(conta);
									    JOptionPane.showMessageDialog(null, "Resgatado valor de R$"+Double.parseDouble(tfValor.getText())+" Do saldo de "+(String)cbTipo.getSelectedItem()+".");
									    setVisible(false);
									    ScreenController.getInstance().showContaScreen(conta);
									}
								}
								
							} else {
								//resgate parcial, somente valida saldo positivo
								
								if(!JdbcController.getInstance().resgatar(conta, (String)cbTipo.getSelectedItem(), Double.parseDouble(tfValor.getText()), Integer.parseInt(tfParcelas.getText()))) {
								    JOptionPane.showMessageDialog(null, "Nao ha saldo suficiente para resgate", "Atencao", JOptionPane.WARNING_MESSAGE, null);
								}else {
								    JOptionPane.showMessageDialog(null, "Resgatado valor de R$"+Double.parseDouble(tfValor.getText())+"0 Do saldo de "+(String)cbTipo.getSelectedItem()+".");
								    setVisible(false);
								    ScreenController.getInstance().showContaScreen(JdbcController.getInstance().findContaById(conta.getIdConta()));
								}
							}
							
						}
						
					}catch(NumberFormatException e3) {
					    JOptionPane.showMessageDialog(null, "Favor informar somente numeros inteiros em Numero de Parcelas.");

					}
					
				}catch(NumberFormatException e3) {
				    JOptionPane.showMessageDialog(null, "Favor informar somente numeros em Valor Resgate, no formato '00.00'.");

				}
			}
		});
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.setBounds(75, 178, 117, 23);
		contentPane.add(btnConfirmar);
		

		

	}
}
