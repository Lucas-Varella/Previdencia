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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroScreen extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;



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
		lblNome.setBounds(82, 68, 78, 14);
		contentPane.add(lblNome);
		
		JComboBox<String> cbTipo = new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"ATIVO", "BENEFICIO", "VINCULADO"}));
		cbTipo.setBounds(160, 87, 200, 20);
		contentPane.add(cbTipo);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tfNome.getText().equals("")) {
					long idConta = ScreenController.getInstance().insertParticipante(tfNome.getText(), (String)cbTipo.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Criado Participante '"+ tfNome.getText() +"', atribuida conta numero "+idConta+".");
					tfNome.setText("");
					setVisible(false);
					ScreenController.getInstance().populateParticipantes();
					ScreenController.getInstance().showParticipanteScreen();
				}else {
					JOptionPane.showMessageDialog(null, "Favor preencher campo Nome.");

				}
				
			}
		});
		btnConfirmar.setForeground(Color.WHITE);
		btnConfirmar.setBackground(Color.DARK_GRAY);
		btnConfirmar.setBounds(142, 118, 101, 23);
		contentPane.add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(0 == JOptionPane.showConfirmDialog(null, "Perdera os dados preenchidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
					setVisible(false);
					ScreenController.getInstance().populateParticipantes();
					ScreenController.getInstance().showParticipanteScreen();
				}
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.DARK_GRAY);
		btnCancelar.setBounds(253, 118, 101, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o: ");
		lblSituao.setBounds(82, 93, 69, 14);
		contentPane.add(lblSituao);
		

	}
}
