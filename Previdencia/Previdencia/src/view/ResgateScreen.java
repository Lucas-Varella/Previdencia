package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScreenController;
import model.Conta;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class ResgateScreen extends JFrame {

	private JPanel contentPane;
	private Conta conta;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ResgateScreen frame = new ResgateScreen();
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
		
		JLabel lblSaldoAtual = new JLabel("Saldo Atual: Favor Selecionar tipo de Resgate");
		lblSaldoAtual.setBounds(62, 44, 291, 14);
		contentPane.add(lblSaldoAtual);
		
		JComboBox cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"PORTABILIDADE", "ADICIONAL", "NORMAL"}));
		cbTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch((String)cbTipo.getSelectedItem()) {
				case "PORTABILIDADE" :
					lblSaldoAtual.setText("Saldo Atual(Portabilidade): R$"+conta.getSaldoPortabilidade()+"0");
					break;
				case "ADICIONAL" :
					lblSaldoAtual.setText("Saldo Atual(Adicional): R$"+conta.getSaldoContribuicoesAdicionais()+"0");
					break;
				case "NORMAL" :
					lblSaldoAtual.setText("Saldo Atual(Normal): R$"+conta.getSaldoContribuicoesNormais()+"0");
					break;
				}
			}
		});
		cbTipo.setBounds(189, 66, 149, 20);
		contentPane.add(cbTipo);
		
		JLabel lblTipoDeResgate = new JLabel("Tipo de Resgate:");
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
		button.setBounds(264, 127, 89, 23);
		contentPane.add(button);
		

	}

}
