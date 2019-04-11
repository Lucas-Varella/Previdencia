package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.JdbcController;
import controller.ParticipanteController;
import controller.ScreenController;
import model.Conta;
import model.Contribuicao;
import model.Participante;
import model.Resgate;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ExtratoScreen extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private Conta conta;


	/**
	 * Create the frame.
	 */
	public ExtratoScreen(Conta conta) {
		this.conta = conta;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 421);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbExtrato = new JLabel("Extrato Conta "+conta.getIdConta());
		lbExtrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbExtrato.setHorizontalAlignment(SwingConstants.CENTER);
		lbExtrato.setBounds(160, 11, 440, 20);
		contentPane.add(lbExtrato);
		
		JButton btnRetornar = new JButton("Retornar");
		btnRetornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				ScreenController.getInstance().showContaScreen(conta);
			}
		});
		btnRetornar.setForeground(Color.WHITE);
		btnRetornar.setBackground(Color.DARK_GRAY);
		btnRetornar.setBounds(350, 333, 89, 23);
		contentPane.add(btnRetornar);
		
		table = new JTable();
		ArrayList<Contribuicao> contrs = JdbcController.getInstance().loadContribuicoes(conta.getIdConta());
		String[][] dataC = new String[contrs.size()][4];
		int xc = 0;
		for(Contribuicao c : contrs) {
			if(c.getIdConta() == conta.getIdConta()) {
				
				dataC[xc][0] = ""+c.getIdContribuicao();
				dataC[xc][1] = ""+c.getTipoContribuicao();
				dataC[xc][2] = ""+c.getValorContribuicao();
				dataC[xc][3] = ""+c.getDataContribuicao();
				xc++;
			}
			
		}
		String[] colsC = {"ID", "Tipo","Valor","Data"};
		DefaultTableModel contrModel = new DefaultTableModel(dataC, colsC);
		table.setModel(contrModel);
		table.setForeground(Color.WHITE);
		table.setBackground(Color.DARK_GRAY);
		table.setBounds(322, 42, 301, 280);
		JScrollPane paneC = new JScrollPane(table);
		paneC.setBounds(407, 42, 373, 280);
		contentPane.add(paneC, BorderLayout.SOUTH);
		
		table_1 = new JTable();
		ArrayList<Resgate> resgs = JdbcController.getInstance().loadResgates(conta.getIdConta());
		String[][] dataR = new String[resgs.size()][5];
		int x = 0;
		for(Resgate r : resgs) {
			if(r.getIdConta() == conta.getIdConta()) {
				
				dataR[x][0] = ""+r.getIdResgate();
				dataR[x][1] = ""+r.getTipoResgate();
				dataR[x][2] = ""+r.getValor();
				dataR[x][3] = ""+r.getNumeroParcelas();
				dataR[x][4] = ""+r.getDataResgate();
				x++;
			}
			
		}
		String[] colsR = {"ID", "Tipo","Valor","Parcelas","Data"};
		DefaultTableModel resgModel = new DefaultTableModel(dataR, colsR);
		table_1.setModel(resgModel);
		table_1.setForeground(Color.WHITE);
		table_1.setBackground(Color.DARK_GRAY);
		table_1.setBounds(10, 42, 301, 280);
		JScrollPane paneR = new JScrollPane(table_1);
		paneR.setBounds(10, 42, 373, 280);
		contentPane.add(paneR, BorderLayout.SOUTH);
		
		
		JLabel lblContribuies = new JLabel("Resgates");
		lblContribuies.setHorizontalAlignment(SwingConstants.CENTER);
		lblContribuies.setBounds(104, 27, 165, 14);
		contentPane.add(lblContribuies);
		
		JLabel lblResgates = new JLabel("Contribuicoes");
		lblResgates.setHorizontalAlignment(SwingConstants.CENTER);
		lblResgates.setBounds(505, 27, 165, 14);
		contentPane.add(lblResgates);
		
	}
	
}
