package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScreenController;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ParticipanteScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParticipanteScreen frame = new ParticipanteScreen();
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
	public ParticipanteScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblParticipantes.setBounds(91, 11, 171, 14);
		contentPane.add(lblParticipantes);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(21, 36, 313, 220);
		contentPane.add(list);
		
		JButton btnCadastrarParticipante = new JButton("Cadastrar Participante");
		btnCadastrarParticipante.setToolTipText("Cadastro de novo participante");
		btnCadastrarParticipante.setForeground(Color.WHITE);
		btnCadastrarParticipante.setBackground(Color.DARK_GRAY);
		btnCadastrarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ScreenController.getInstance().showCadastroScreen();
			}
		});
		btnCadastrarParticipante.setBounds(344, 33, 170, 23);
		contentPane.add(btnCadastrarParticipante);
		
		JButton btnEditarParticipante = new JButton("Editar Participante");
		btnEditarParticipante.setToolTipText("Edita informa\u00E7\u00F5es cadastrais do participante.");
		btnEditarParticipante.setBackground(Color.DARK_GRAY);
		btnEditarParticipante.setForeground(Color.WHITE);
		btnEditarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditarParticipante.setBounds(344, 67, 170, 23);
		contentPane.add(btnEditarParticipante);
		
		JButton btnRemoverParticipante = new JButton("Remover Participante");
		btnRemoverParticipante.setToolTipText("Remove participante da listagem, apagando sua conta e hist\u00F3rico.");
		btnRemoverParticipante.setForeground(Color.WHITE);
		btnRemoverParticipante.setBackground(Color.DARK_GRAY);
		btnRemoverParticipante.setBounds(344, 101, 170, 23);
		contentPane.add(btnRemoverParticipante);
		
		JButton btnAcessarConta = new JButton("Acessar Conta");
		btnAcessarConta.setToolTipText("Acesso \u00E0 conta para opera\u00E7\u00F5es de resgate, contribui\u00E7\u00E3o, e para resgate.");
		btnAcessarConta.setBackground(Color.DARK_GRAY);
		btnAcessarConta.setForeground(Color.WHITE);
		btnAcessarConta.setBounds(344, 135, 170, 23);
		contentPane.add(btnAcessarConta);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setBackground(Color.DARK_GRAY);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ScreenController.getInstance().showMainScreen();
			}
		});
		btnVoltar.setBounds(429, 279, 85, 23);
		contentPane.add(btnVoltar);
	}
}
