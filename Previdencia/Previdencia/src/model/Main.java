package model;

import controller.JdbcController;
import controller.MainController;
import controller.ParticipanteController;

public class Main {

	public static void main(String[] args) {
		//instancia controlador principal, mostra tela principal
		MainController mainController = new MainController();
		mainController.showMainScreen();
		mainController.test();
		System.out.println(ParticipanteController.getInstance().getParticipantes().size());
	}

}
