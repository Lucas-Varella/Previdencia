package model;

import java.sql.Date;

public class Conta {
	
	private int idConta;
	private Date dataCadastro;
	private double saldoPortabilidade = 0;
	private double saldoContribuicoesAdicionais = 0;
	private double saldoContribuicoesNormais = 0;
	
	public Conta() {
		
	}
	public void modSaldoPortabilidade(double valor) {
		this.saldoPortabilidade += valor;
	}
	public double getSaldoPortabilidade() {
		return this.saldoPortabilidade;
	}
	public void modSaldoContribuicoesAdicionais(double valor) {
		this.saldoContribuicoesAdicionais += valor;
	}
	public double getSaldoContribuicoesAdicionais() {
		return this.saldoContribuicoesAdicionais;
	}
	public void modSaldoContribuicoesNormais(double valor) {
		this.saldoContribuicoesNormais += valor;
	}
	public double getSaldoContribuicoesNormais() {
		return this.saldoContribuicoesNormais;
	}
	public Date getDataCadastro() {
		return this.dataCadastro;
	}
	public int getIdConta() {
		return this.idConta;
	}

}
