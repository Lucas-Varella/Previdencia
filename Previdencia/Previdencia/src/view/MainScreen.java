package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ScreenController;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrevidnciaTela = new JLabel("Previd\u00EAncia - Tela Principal");
		lblPrevidnciaTela.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrevidnciaTela.setBounds(109, 11, 226, 48);
		contentPane.add(lblPrevidnciaTela);
		
		JButton btnParticipantes = new JButton("Clique para listar participantes");
		btnParticipantes.setForeground(Color.WHITE);
		btnParticipantes.setBackground(Color.DARK_GRAY);
		btnParticipantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ScreenController.getInstance().showParticipanteScreen();
			}
		});
		btnParticipantes.setBounds(24, 49, 388, 161);
		contentPane.add(btnParticipantes);
	}
}
