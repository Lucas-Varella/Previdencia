package model;

import java.sql.Date;
import java.sql.SQLException;

import controller.JdbcController;

public class Conta {
	
	private int idConta;
	private Date dataCadastro;
	private double saldoPortabilidade = 0;
	private double saldoContribuicoesAdicionais = 0;
	private double saldoContribuicoesNormais = 0;
	
	public Conta(int idConta, Date dataCadastro, double saldoPortabilidade, double saldoContribuicoesAdicionais, double saldoContribuicoesNormais) {
		this.idConta = idConta;
		this.dataCadastro = dataCadastro;
		this.saldoPortabilidade = saldoPortabilidade;
		this.saldoContribuicoesAdicionais = saldoContribuicoesAdicionais;
		this.saldoContribuicoesNormais = saldoContribuicoesNormais;
	}
	public void modSaldoPortabilidade(double valor) {
		this.saldoPortabilidade += valor;
		JdbcController.getInstance().modSaldoPortabilidade(this.idConta, this.saldoPortabilidade);
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
