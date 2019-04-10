package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.JdbcController;
import controller.ScreenController;
import model.Participante;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class EditionScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditionScreen frame = new EditionScreen();
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
	public EditionScreen(Participante part) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEdicaoDeParticipante = new JLabel("Edicao de Participante");
		lblEdicaoDeParticipante.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEdicaoDeParticipante.setBounds(119, 24, 290, 29);
		contentPane.add(lblEdicaoDeParticipante);
		
		JLabel lblNomeAntigo = new JLabel("Nome Antigo:");
		lblNomeAntigo.setBounds(52, 68, 97, 14);
		contentPane.add(lblNomeAntigo);
		
		JLabel lblNomeantigo = new JLabel(part.getNomeParticipante());
		lblNomeantigo.setBounds(152, 68, 186, 14);
		contentPane.add(lblNomeantigo);
		
		JLabel lblNomeNovo = new JLabel("Nome :");
		lblNomeNovo.setBounds(52, 93, 97, 14);
		contentPane.add(lblNomeNovo);
		
		tfName = new JTextField();
		tfName.setBounds(152, 90, 186, 20);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ATIVO", "BENEFICIO", "VINCULADO", "SUSPENSO"}));
		comboBox.setBounds(152, 115, 186, 20);
		contentPane.add(comboBox);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfName.getText().equals("")) {
					JdbcController.getInstance().editParticipante(part, tfName.getText(), (String)comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Editado Participante  de ID "+ part.getIdParticipante() +", com o nome: " + tfName.getText()+" e Situacao "+(String)comboBox.getSelectedItem());
					setVisible(false);
					ScreenController.getInstance().populateParticipantes();
					ScreenController.getInstance().showParticipanteScreen();
				}else {
					JOptionPane.showMessageDialog(null, "Favor preencher campo Nome.");

				}
				
			}
		});
		btnConfirmar.setBounds(110, 147, 106, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(0 == JOptionPane.showConfirmDialog(null, "Perdera os dados preenchidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
					setVisible(false);
					ScreenController.getInstance().populateParticipantes();
					ScreenController.getInstance().showParticipanteScreen();
				}
			}
		});
		btnCancelar.setBounds(232, 147, 106, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblSituaoNova = new JLabel("Situa\u00E7\u00E3o :");
		lblSituaoNova.setBounds(52, 118, 97, 14);
		contentPane.add(lblSituaoNova);
		
		
	}
}
