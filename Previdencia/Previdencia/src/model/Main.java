package model;

import controller.JdbcController;
import controller.MainController;

public class Main {

	public static void main(String[] args) {
		//instancia controlador principal, mostra tela principal
		MainController mainController = new MainController();
		mainController.showMainScreen();
	}

}
