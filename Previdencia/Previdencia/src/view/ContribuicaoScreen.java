package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Conta;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ContribuicaoScreen extends JFrame {

	private JPanel contentPane;
	private Conta conta;
	private JTextField tfValor;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ContribuicaoScreen frame = new ContribuicaoScreen();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		
		JLabel lblSaldoAtual = new JLabel("Saldo Atual: Favor Selecionar tipo de Contribuição");
		lblSaldoAtual.setBounds(24, 45, 314, 14);
		contentPane.add(lblSaldoAtual);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setBounds(171, 70, 148, 20);
		contentPane.add(cbTipo);
		
		JLabel lblTipoDeContribuio = new JLabel("Tipo de Contribui\u00E7\u00E3o: ");
		lblTipoDeContribuio.setBounds(24, 70, 208, 14);
		contentPane.add(lblTipoDeContribuio);
		
		JLabel lblValorContribuir = new JLabel("Valor \u00E0 Contribuir:  ");
		lblValorContribuir.setBounds(24, 94, 148, 14);
		contentPane.add(lblValorContribuir);
		
		tfValor = new JTextField();
		tfValor.setBounds(171, 91, 148, 20);
		contentPane.add(tfValor);
		tfValor.setColumns(10);
	}
}
