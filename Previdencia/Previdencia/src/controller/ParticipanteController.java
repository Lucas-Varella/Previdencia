package controller;

import java.sql.Date;
import java.util.ArrayList;

import model.Participante;

public class ParticipanteController {
	
	private static final ParticipanteController instance = new ParticipanteController();
	private ArrayList<Participante> participantes;
	
	public ParticipanteController() {
		participantes = JdbcController.getInstance().loadParticipantes();
	}
	public void newParticipante(int idParticipante, Date dataCadastro, String nomeParticipante, int idConta) {
		Participante participante = new Participante(idParticipante, dataCadastro, nomeParticipante, idConta);
		participantes.add(participante);
	}
	public ArrayList<Participante> getParticipantes() {
		return this.participantes;
	}
	public static ParticipanteController getInstance() {
		return instance;
	}

	

}
