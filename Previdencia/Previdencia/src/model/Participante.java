package model;

import java.sql.Date;
import java.sql.Statement;

import controller.JdbcController;

public class Participante {
	
	private long idParticipante;
	private Date dataCriacao;
	private String nomeParticipante;
	private long idConta;
	
	public Participante(int idParticipante, Date dataCriacao, String nomeParticipante, int idConta) {
		this.idParticipante = idParticipante;
		this.dataCriacao = dataCriacao;
		this.nomeParticipante = nomeParticipante;
		this.idConta = idConta;
		
	}

	public long getIdParticipante() {
		return this.idParticipante;
	}
	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setNomeParticipante(String nomeParticipante) {
		this.nomeParticipante = nomeParticipante;
	}
	public String getNomeParticipante() {
		return this.nomeParticipante;
	}
	public long getIdConta() {
		return this.idConta;
	}
	@Override
	public String toString() {
		return "ID: "+this.idParticipante+" Nome Participante: "+this.nomeParticipante+" Data de Criacao: "+this.dataCriacao+" ID Conta: "+this.idConta;
	}

}
