package controller;

import java.awt.Window;

import view.*;

public class ScreenController {
	
	private static final ScreenController instance = new ScreenController();
	private MainScreen mainScreen;
	private ParticipanteScreen participanteScreen;
	private CadastroScreen cadastroScreen;
	
	public ScreenController() {
		mainScreen = new MainScreen();
		participanteScreen = new ParticipanteScreen();
		cadastroScreen = new CadastroScreen();
	}
	
	public void showMainScreen() {
		mainScreen.setVisible(true);
	}
	public void showParticipanteScreen() {
		participanteScreen.setVisible(true);
	}

	public static ScreenController getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public void showCadastroScreen() {
		cadastroScreen.setVisible(true);
		
	}

	public long insertParticipante(String text) {
		return MainController.getInstance().insertParticipante(text);
		
	}

	public void populateParticipantes() {
		participanteScreen.populateParticipantes();
		
	}

}
