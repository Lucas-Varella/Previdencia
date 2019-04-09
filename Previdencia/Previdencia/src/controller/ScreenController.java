package controller;

import java.awt.Window;

import model.Conta;
import model.Participante;
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
		participanteScreen.populateParticipantes();
		participanteScreen.setVisible(true);
	}

	public static ScreenController getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}

	public void showCadastroScreen() {
		cadastroScreen.setVisible(true);
		
	}

	public long insertParticipante(String nomeParticipante, String situacaoParticipante) {
		return MainController.getInstance().insertParticipante(nomeParticipante, situacaoParticipante);
		
	}

	public void populateParticipantes()  {
		participanteScreen = new ParticipanteScreen();
		participanteScreen.populateParticipantes();
		
	}

	public void showEditionScreen(Participante selectedValue) {
		EditionScreen edscr = new EditionScreen(selectedValue);
		edscr.setVisible(true);
		
	}

	public void showContaScreen(Conta conta) {
		ContaScreen contascr = new ContaScreen(conta);
		contascr.setVisible(true);
	}

	public void showContribuicaoScreen(Conta conta) {
		ContribuicaoScreen contrscr = new ContribuicaoScreen(conta);
		contrscr.setVisible(true);
		
	}

	public void showResgateScreen(Conta conta) {
		ResgateScreen resgscr = new ResgateScreen(conta);
		resgscr.setVisible(true);
		
	}

}
