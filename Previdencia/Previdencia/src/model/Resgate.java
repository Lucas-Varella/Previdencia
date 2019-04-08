package model;

import java.sql.Date;

public class Resgate {
	
	private long idResgate;
	private Date dataResgate;
	private long idConta;
	private double valor;
	private String tipoResgate;
	private long numeroParcelas;
	
	public Resgate(long idConta, double valor, String tipoResgate, long numeroParcelas) {
		this.idConta = idConta;
		this.valor = valor;
		this.tipoResgate = tipoResgate;
		this.numeroParcelas = numeroParcelas;
	}
	public long getIdResgate() {
		return this.idResgate;
	}
	public Date getDataResgate() {
		return this.dataResgate;
	}
	public long getIdConta() {
		return this.idConta;		
	}
	public double getValor() {
		return this.valor;
	}
	public String getTipoResgate() {
		return this.tipoResgate;
	}
	public long getNumeroParcelas() {
		return this.numeroParcelas;
	}
	

}
