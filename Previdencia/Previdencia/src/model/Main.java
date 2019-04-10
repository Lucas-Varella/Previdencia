package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.JdbcController;
import controller.MainController;
import controller.ParticipanteController;

public class Main {

	public static void main(String[] args) {
		//instancia controlador principal, mostra tela principal
		MainController mainController = new MainController();
		mainController.showMainScreen();
		System.out.println("actions on total");
	}

}
