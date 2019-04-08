package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScreenController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.List;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfDataNascimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroScreen frame = new CadastroScreen();
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
	public CadastroScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 540, 365);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeParticipante = new JLabel("Cadastro de Participante");
		lblCadastroDeParticipante.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDeParticipante.setBounds(160, 11, 226, 50);
		contentPane.add(lblCadastroDeParticipante);
		
		JLabel lblPreenchaTodosOs = new JLabel("Preencha todos os campos para continuar.");
		lblPreenchaTodosOs.setBounds(48, 57, 336, 14);
		contentPane.add(lblPreenchaTodosOs);
		
		tfNome = new JTextField();
		tfNome.setBounds(170, 79, 200, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(48, 82, 121, 14);
		contentPane.add(lblNome);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento:");
		lblDataDeNascimento.setBounds(48, 107, 121, 14);
		contentPane.add(lblDataDeNascimento);
		
		tfDataNascimento = new JTextField();
		tfDataNascimento.setBounds(170, 104, 200, 20);
		contentPane.add(tfDataNascimento);
		tfDataNascimento.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.setBounds(170, 173, 101, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(0 == JOptionPane.showConfirmDialog(null, "Perderá os dados preenchidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
					setVisible(false);
					ScreenController.getInstance().showParticipanteScreen();
				}
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(269, 173, 101, 23);
		contentPane.add(btnCancelar);
	}
}
