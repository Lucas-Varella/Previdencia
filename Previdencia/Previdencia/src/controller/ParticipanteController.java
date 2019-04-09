package controller;

import java.sql.Date;
import java.util.ArrayList;

import model.Participante;

public class ParticipanteController {
	
	private static final ParticipanteController instance = new ParticipanteController();
	private ArrayList<Participante> participantes;
	
	public ParticipanteController() {
		this.participantes = JdbcController.getInstance().loadParticipantes();
	}
	public void newParticipante(int idParticipante, Date dataCadastro, String nomeParticipante, int idConta, String situacaoParticipante) {
		Participante participante = new Participante(idParticipante, dataCadastro, nomeParticipante, idConta, situacaoParticipante);
		this.participantes.add(participante);
	}
	public ArrayList<Participante> getParticipantes() {
		return this.participantes;
	}
	public static ParticipanteController getInstance() {
		return instance;
	}
	public void deleteParticipante(Participante part) {
		this.participantes.remove(part);
		
	}
	public void editParticipante(Participante part, String nomeParticipante, String situacaoParticipante) {
		participantes.remove(part);
		part.setNomeParticipante(nomeParticipante);
		part.setSituacaoParticipante(situacaoParticipante);
		participantes.add(part);
		
		
	}

	

}
