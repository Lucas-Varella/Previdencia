package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.JdbcController;
import controller.ParticipanteController;
import controller.ScreenController;
import model.Participante;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class ParticipanteScreen extends JFrame {

	private JPanel contentPane;
	JList participantes; 

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ParticipanteScreen frame = new ParticipanteScreen();
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
	public ParticipanteScreen() {
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblParticipantes = new JLabel("Participantes");
		lblParticipantes.setBackground(Color.LIGHT_GRAY);
		lblParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParticipantes.setBounds(192, 11, 171, 14);
		contentPane.add(lblParticipantes);
		
		participantes = new JList<Participante>();
		participantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		participantes.setBounds(21, 36, 313, 220);
		populateParticipantes();
		contentPane.add(participantes);
		
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
		btnCadastrarParticipante.setBounds(544, 33, 170, 23);
		contentPane.add(btnCadastrarParticipante);
		
		JButton btnEditarParticipante = new JButton("Editar Participante");
		btnEditarParticipante.setToolTipText("Edita informacoes cadastrais do participante.");
		btnEditarParticipante.setBackground(Color.DARK_GRAY);
		btnEditarParticipante.setForeground(Color.WHITE);
		btnEditarParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(participantes.getSelectedValue() != null) {
					setVisible(false);
					ScreenController.getInstance().showEditionScreen((Participante)participantes.getSelectedValue());
				}else {
					JOptionPane.showMessageDialog(null, "Favor selecionar Participante para edicao.");
				}
				
			}
		});
		btnEditarParticipante.setBounds(544, 67, 170, 23);
		contentPane.add(btnEditarParticipante);
		
		JButton btnRemoverParticipante = new JButton("Remover Participante");
		btnRemoverParticipante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(participantes.getSelectedValue() != null) {
					if(0 == JOptionPane.showConfirmDialog(null, "Participante e Conta atribuida serao excluidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
						JdbcController.getInstance().removeParticipante((Participante)participantes.getSelectedValue());
						populateParticipantes();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Favor selecionar Participante para remocao.");

				}
			}
		});
		btnRemoverParticipante.setToolTipText("Remove participante da listagem, apagando sua conta e hist\u00F3rico.");
		btnRemoverParticipante.setForeground(Color.WHITE);
		btnRemoverParticipante.setBackground(Color.DARK_GRAY);
		btnRemoverParticipante.setBounds(544, 101, 170, 23);
		contentPane.add(btnRemoverParticipante);
		
		JButton btnAcessarConta = new JButton("Acessar Conta");
		btnAcessarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(participantes.getSelectedValue() != null) {
					setVisible(false);
					Participante part = (Participante)participantes.getSelectedValue();
					ScreenController.getInstance().showContaScreen(JdbcController.getInstance().findContaById(part.getIdConta()));
				} else {
					JOptionPane.showMessageDialog(null, "Favor selecionar Participante para acesso a conta.");

				}				
				
				
			}
		});
		btnAcessarConta.setToolTipText("Acesso a conta para operacoes de contribuicao, e para resgate.");
		btnAcessarConta.setBackground(Color.DARK_GRAY);
		btnAcessarConta.setForeground(Color.WHITE);
		btnAcessarConta.setBounds(544, 135, 170, 23);
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
		btnVoltar.setBounds(629, 358, 85, 23);
		contentPane.add(btnVoltar);
		populateParticipantes();
	}
	public void populateParticipantes() {
		contentPane.remove(participantes);
		DefaultListModel<Participante> dlm = new DefaultListModel<Participante>();
		ArrayList<Participante> parts = ParticipanteController.getInstance().getParticipantes();
		for(Participante p : parts ){
		 dlm.addElement(p);
		}   
		participantes = new JList<Participante>(dlm);
		participantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		participantes.setBounds(21, 36, 513, 345);
		contentPane.add(participantes);
		this.repaint();

		
	}
}
