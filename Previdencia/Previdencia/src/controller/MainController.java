package controller;

import java.sql.*;

public class MainController {
	
	ScreenController screenController;
	JdbcController jdbcController;
	ParticipanteController participanteController;
	public static final MainController instance = new MainController();

	
	public MainController() {
		//inicializa controladores
		screenController = new ScreenController();
		screenController = screenController.getInstance();
		jdbcController = new JdbcController();
		jdbcController = jdbcController.getInstance();
		jdbcController.jdbcConnect();
		participanteController = new ParticipanteController();
		participanteController = participanteController.getInstance();
	}
	//m�todo para retornar inst�ncia do objeto
	public static MainController getInstance() {
		return instance;
	}
	
	public void showMainScreen() {
		screenController.showMainScreen();
	}
	public void showParticipanteScreen() {
		screenController.showParticipanteScreen();
	}
	public Connection getConnection() {
		return jdbcController.getConnection();
	}
	public long insertParticipante(String nomeParticipante, String situacaoParticipante) {
		// TODO Auto-generated method stub
		return jdbcController.insertParticipante(nomeParticipante, situacaoParticipante);
	}
	
	public void newParticipante(int idParticipante, Date dataCadastro, String nomeParticipante, int idConta, String situacaoParticipante) {
		participanteController.newParticipante(idParticipante, dataCadastro, nomeParticipante, idConta, situacaoParticipante);
		
	}

}
