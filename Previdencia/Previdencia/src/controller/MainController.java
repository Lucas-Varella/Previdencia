package controller;

import java.sql.*;

public class MainController {
	
	ScreenController screenController;
	JdbcController jdbcController;
	private static final MainController instance = new MainController();

	
	public MainController() {
		//inicializa controladores
		screenController = new ScreenController();
		jdbcController = new JdbcController();
		jdbcController.jdbcConnect();
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

}
