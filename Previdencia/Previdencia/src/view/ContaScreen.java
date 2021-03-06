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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ContaScreen extends JFrame {

	private JPanel contentPane;
	private Conta conta;



	/**
	 * Create the frame.
	 */
	public ContaScreen(Conta conta) {
		this.conta = conta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 537, 352);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConta = new JLabel("Conta: "+conta.getIdConta()+"    |    Criada em "+conta.getDataCadastro());
		lblConta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConta.setBounds(84, 11, 437, 39);
		contentPane.add(lblConta);
		
		JLabel lblParticipante = new JLabel("Participante: "+ JdbcController.getInstance().findParticipanteByContaId(conta.getIdConta()).getNomeParticipante() +",  Situacao: "+ JdbcController.getInstance().findParticipanteByContaId(conta.getIdConta()).getSituacaoParticipante());
		lblParticipante.setBounds(35, 54, 327, 14);
		contentPane.add(lblParticipante);
		
		JLabel lblSaldoportabilidade = new JLabel("Saldo Portabilidade: R$"+conta.getSaldoPortabilidade());
		lblSaldoportabilidade.setBounds(35, 79, 327, 14);
		contentPane.add(lblSaldoportabilidade);
		
		JLabel lblSaldocontribuicoesadicionais = new JLabel("Saldo Contribuicoes Adicionais: R$"+conta.getSaldoContribuicoesAdicionais());
		lblSaldocontribuicoesadicionais.setBounds(35, 104, 327, 14);
		contentPane.add(lblSaldocontribuicoesadicionais);
		
		JLabel lblSaldocontribuicoesnormais = new JLabel("Saldo Contribuicoes Nomais: R$"+conta.getSaldoContribuicoesNormais());
		lblSaldocontribuicoesnormais.setBounds(35, 129, 327, 14);
		contentPane.add(lblSaldocontribuicoesnormais);
		
		JLabel lblSaldototal = new JLabel("Saldo Total: R$"+(conta.getSaldoContribuicoesAdicionais()+conta.getSaldoContribuicoesNormais()+conta.getSaldoPortabilidade()));
		lblSaldototal.setBounds(35, 154, 327, 14);
		contentPane.add(lblSaldototal);
		
		JButton btnContribuirConta = new JButton("Contribuir \u00E0 Conta");
		btnContribuirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenController.getInstance().showContribuicaoScreen(conta);
			}
		});
		btnContribuirConta.setForeground(Color.WHITE);
		btnContribuirConta.setBackground(Color.DARK_GRAY);
		btnContribuirConta.setBounds(35, 192, 148, 23);
		
		JButton btnResgatarValor = new JButton("Resgatar Valor");
		btnResgatarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenController.getInstance().showResgateScreen(conta);
			}
		});
		btnResgatarValor.setForeground(Color.WHITE);
		btnResgatarValor.setBackground(Color.DARK_GRAY);
		btnResgatarValor.setBounds(193, 192, 129, 23);
		
		JButton btnExtrato = new JButton("Consultar Extrato");
		btnExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenController.getInstance().showExtratoScreen(conta);
			}
		});
		btnExtrato.setForeground(Color.WHITE);
		btnExtrato.setBackground(Color.DARK_GRAY);
		btnExtrato.setBounds(248, 150, 173, 23);
		
		if(!JdbcController.getInstance().findParticipanteByContaId(conta.getIdConta()).getSituacaoParticipante().equals("CANCELADO")) {
			contentPane.add(btnContribuirConta);
			contentPane.add(btnResgatarValor);
			contentPane.add(btnExtrato);

		}else {
			lblConta.setText("Conta: "+conta.getIdConta()+"    |    Criada em "+conta.getDataCadastro()+"  !CANCELADA!");
			lblSaldoportabilidade.setText("Saldo Portabilidade: R$0.0");
			lblSaldocontribuicoesadicionais.setText("Saldo Contribuicoes Adicionais: R$0.0");
			lblSaldocontribuicoesnormais.setText("Saldo Contribuicoes Normais: R$0.0");
			lblSaldototal.setText("Saldo Total: R$0.0");
		}
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.setForeground(Color.WHITE);
		btnRetornar.setBackground(Color.DARK_GRAY);
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ScreenController.getInstance().showParticipanteScreen();
			}
		});
		btnRetornar.setBounds(332, 192, 89, 23);
		contentPane.add(btnRetornar);
		
		
	}
}
