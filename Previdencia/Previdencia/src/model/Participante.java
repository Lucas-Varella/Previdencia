package model;

import java.sql.Date;
import java.sql.Statement;

import controller.JdbcController;

public class Participante {
	
	private int idParticipante;
	private Date dataCriacao;
	private String nomeParticipante;
	private int idConta;
	private String situacaoParticipante;
	
	public Participante(int idParticipante, Date dataCriacao, String nomeParticipante, int idConta, String situacaoParticipante) {
		this.idParticipante = idParticipante;
		this.dataCriacao = dataCriacao;
		this.nomeParticipante = nomeParticipante;
		this.idConta = idConta;
		this.situacaoParticipante = situacaoParticipante;
		
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
	public void setSituacaoParticipante(String situacaoParticipante) {
		this.situacaoParticipante = situacaoParticipante;
	}
	public String getSituacaoParticipante() {
		return this.situacaoParticipante;
	}
	public int getIdConta() {
		return this.idConta;
	}
	@Override
	public String toString() {
		return "ID: "+this.idParticipante+" Nome Participante: "+this.nomeParticipante+" Data de Criacao: "+this.dataCriacao+" ID Conta: "+this.idConta+" Situacao: "+this.situacaoParticipante;
	}

}
