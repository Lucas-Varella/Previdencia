package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.sql.Date;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;

public class ParticipanteScreen extends JFrame {

	private JPanel contentPane;
	private JTable tbParticipantes;
	private DefaultTableModel tableModel;



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
				
				if(tbParticipantes.getSelectedRow() != -1) {
					int row = tbParticipantes.getSelectedRow();
					Participante part = JdbcController.getInstance().findParticipanteById(Integer.parseInt((String)tbParticipantes.getValueAt(row, 0)));
					if(!part.getSituacaoParticipante().equals("CANCELADO")) {
						setVisible(false);
						ScreenController.getInstance().showEditionScreen(part);

					}else {
						JOptionPane.showMessageDialog(null, "Nao e possivel editar participante cancelado.");

					}
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
				if(tbParticipantes.getSelectedRow() != -1) {
					if(0 == JOptionPane.showConfirmDialog(null, "Participante e Conta atribuida serao excluidos. Tem Certeza?", "Confirmar", JOptionPane.YES_NO_OPTION)) {
						int row = tbParticipantes.getSelectedRow();
						JdbcController.getInstance().removeParticipante(JdbcController.getInstance().findParticipanteById(Integer.parseInt((String)tbParticipantes.getValueAt(row, 0))));
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
				int row = tbParticipantes.getSelectedRow();
				if(row != -1) {
					setVisible(false);
					ScreenController.getInstance().showContaScreen(JdbcController.getInstance().findContaById(Integer.parseInt((String)tbParticipantes.getValueAt(row,2))));
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
		
		tbParticipantes = new JTable();
		
	    ArrayList<Participante> parts = ParticipanteController.getInstance().getParticipantes();
	    String[][] data = new String[parts.size()][5];
	    for (Participante p : parts) {
    		int x = parts.indexOf(p);
            data[x][0] = ""+p.getIdParticipante();
            data[x][1] = ""+p.getNomeParticipante();
            data[x][2] = ""+p.getIdConta();
            data[x][3] = ""+p.getSituacaoParticipante();
            data[x][4] = ""+p.getDataCriacao();           
	    }
	    String[] cols = {"ID", "Nome", "ID Conta", "Situacao", "Data Criacao"};
	    tableModel = new DefaultTableModel(data, cols);
	    tbParticipantes.setModel(tableModel);
		tbParticipantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbParticipantes.setForeground(Color.WHITE);
		tbParticipantes.setBackground(Color.DARK_GRAY);
		tbParticipantes.setBounds(30, 52, 504, 324);
		JScrollPane pane = new JScrollPane(tbParticipantes);
		pane.setBounds(25, 30, 509, 346);
		contentPane.add(pane, BorderLayout.SOUTH);
	}
	
	public void populateParticipantes() {
		ArrayList<Participante> parts = ParticipanteController.getInstance().getParticipantes();
	    String[][] data = new String[parts.size()][5];
	    for (Participante p : parts) {
    		int x = parts.indexOf(p);
            data[x][0] = ""+p.getIdParticipante();
            data[x][1] = ""+p.getNomeParticipante();
            data[x][2] = ""+p.getIdConta();
            data[x][3] = ""+p.getSituacaoParticipante();
            data[x][4] = ""+p.getDataCriacao();           
	    }
	    String[] cols = {"ID", "Nome", "ID Conta", "Situacao", "Data Criacao"};
	    tableModel = new DefaultTableModel(data, cols);
	    tbParticipantes.setModel(tableModel);
	    tableModel.fireTableDataChanged();
	    this.repaint();
	}
}
