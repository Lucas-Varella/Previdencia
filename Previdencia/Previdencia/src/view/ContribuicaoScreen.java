package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.JdbcController;
import controller.ScreenController;
import model.Conta;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContribuicaoScreen extends JFrame {

	private JPanel contentPane;
	private Conta conta;
	private JTextField tfValor;



	/**
	 * Create the frame.
	 */
	public ContribuicaoScreen(Conta conta) {
		this.conta = conta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 267);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblContribuioConta = new JLabel("Contribui\u00E7\u00E3o \u00E0 conta "+conta.getIdConta());
		lblContribuioConta.setBounds(119, 11, 217, 23);
		contentPane.add(lblContribuioConta);
		
		JLabel lblSaldoAtual = new JLabel("Saldo Atual(Portabilidade): R$"+conta.getSaldoPortabilidade());
		lblSaldoAtual.setBounds(24, 45, 314, 14);
		contentPane.add(lblSaldoAtual);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch((String)cbTipo.getSelectedItem()) {
				case "PORTABILIDADE" :
					lblSaldoAtual.setText("Saldo Atual(Portabilidade): R$"+conta.getSaldoPortabilidade());
					break;
				case "ADICIONAL" :
					lblSaldoAtual.setText("Saldo Atual(Adicional): R$"+conta.getSaldoContribuicoesAdicionais());
					break;
				case "NORMAL" :
					lblSaldoAtual.setText("Saldo Atual(Normal): R$"+conta.getSaldoContribuicoesNormais());
					break;
				}
			}
		});
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"PORTABILIDADE", "ADICIONAL", "NORMAL"}));
		cbTipo.setBounds(154, 70, 165, 20);
		contentPane.add(cbTipo);
		
		JLabel lblTipoDeContribuio = new JLabel("Tipo de Contribui\u00E7\u00E3o: ");
		lblTipoDeContribuio.setBounds(24, 70, 208, 14);
		contentPane.add(lblTipoDeContribuio);
		
		JLabel lblValorContribuir = new JLabel("Valor \u00E0 Contribuir:  ");
		lblValorContribuir.setBounds(24, 94, 148, 14);
		contentPane.add(lblValorContribuir);
		
		tfValor = new JTextField();
		tfValor.setBounds(154, 91, 165, 20);
		contentPane.add(tfValor);
		tfValor.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(tfValor.getText());
					valor = valor * 100;
					valor = (Math.round(valor));
					valor = valor / 100;
					tfValor.setText(""+valor);
					if(Double.parseDouble(tfValor.getText()) <= 0) {
						JOptionPane.showMessageDialog(null, "Favor inserir somente numeros positivos em valor");
					}else {
					    JdbcController.getInstance().contribuir(conta, (String)cbTipo.getSelectedItem(), Double.parseDouble(tfValor.getText()));
					    JOptionPane.showMessageDialog(null, "Contribuido valor de R$"+Double.parseDouble(tfValor.getText())+" Para o saldo de "+(String)cbTipo.getSelectedItem()+".");
					    setVisible(false);
					    ScreenController.getInstance().showContaScreen(JdbcController.getInstance().findContaById(conta.getIdConta()));

					}
				} catch (NumberFormatException e1) {
				    JOptionPane.showMessageDialog(null, "Favor informar somente numeros em valor, no formato '00.00'.");
				}
			}
		});
		btnConfirmar.setBounds(85, 127, 101, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(0 == JOptionPane.showConfirmDialog(null, "Perdera os dados preenchidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
					setVisible(false);
					ScreenController.getInstance().showContaScreen(conta);
				}				
			}
		});
		btnCancelar.setBounds(218, 127, 101, 23);
		contentPane.add(btnCancelar);
	}
}
