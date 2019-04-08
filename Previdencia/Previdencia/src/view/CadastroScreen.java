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
		setBounds(100, 100, 540, 307);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeParticipante = new JLabel("Cadastro de Participante");
		lblCadastroDeParticipante.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCadastroDeParticipante.setBounds(160, 11, 226, 50);
		contentPane.add(lblCadastroDeParticipante);
		
		tfNome = new JTextField();
		tfNome.setBounds(160, 65, 200, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(105, 68, 55, 14);
		contentPane.add(lblNome);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				long idConta = ScreenController.getInstance().insertParticipante(tfNome.getText());
				JOptionPane.showMessageDialog(null, "Criado Participante '"+ tfNome.getText() +"', atribuída conta número "+idConta+".");
				tfNome.setText("");
				setVisible(false);
				ScreenController.getInstance().showParticipanteScreen();
			}
		});
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.setBounds(142, 118, 101, 23);
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
		btnCancelar.setBounds(253, 118, 101, 23);
		contentPane.add(btnCancelar);
	}
}
