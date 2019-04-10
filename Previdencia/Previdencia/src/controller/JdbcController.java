package controller;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Conta;
import model.Contribuicao;
import model.Participante;

public class JdbcController {
	
	private static final JdbcController instance = new JdbcController();
	Connection con;
	
	public JdbcController() {
		jdbcConnect();
	}
	
	public void jdbcConnect() {
		
		//registra JDBC driver
		try {
			Driver d = (Driver)Class.forName("com.filemaker.jdbc.Driver").newInstance();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:filemaker://localhost:2399/Previdencia","Admin", "admin");
			this.con = con;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public Connection getConnection() {
		return con;
	}
	public static JdbcController getInstance() {
		return instance;
	}

	public long createConta() {
		try {
			Statement st = con.createStatement();
			st.execute("INSERT INCO CONTA VALUES (0, 0, 0)");
			ResultSet rs = st.executeQuery("SELECT TOP 1 idConta FROM CONTA ORDER BY dataCadastro DESC");
			return rs.getLong(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	public void modSaldoPortabilidade(long idConta, double saldoPortabilidade) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE CONTA SET saldoPortabilidade ="+saldoPortabilidade+" WHERE idConta ="+idConta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet executeQuery(String query) {
		Statement st;
		try {
			st = con.createStatement();
			return st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public long insertParticipante(String nomeParticipante, String situacaoParticipante) {
		try {
			
			Statement st = con.createStatement();
			st.execute("INSERT INTO CONTA (saldoPortabilidade, saldoContribuicoesAdicionais, saldoContribuicoesNormais) VALUES (0, 0, 0)");
			ResultSet rs = st.executeQuery("SELECT MAX(idConta) FROM CONTA");
			int idConta = 0;
			if(rs.next()) {
				idConta = rs.getInt(1);
			}
			
			st.execute("INSERT INTO PARTICIPANTE (nomeParticipante, idConta, situacaoParticipante) VALUES ('"+nomeParticipante+"',"+idConta+", '"+situacaoParticipante+"')");
			ResultSet rsPart = st.executeQuery("SELECT * FROM PARTICIPANTE WHERE IDCONTA = "+idConta);
			if(rsPart.next()) {
				int idParticipante = rsPart.getInt("idParticipante");
				Date dataCriacao = rsPart.getDate("dataCriacao");
				MainController.getInstance().newParticipante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
			}
			
			return idConta;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//retorna um arrayList atualizado de Participantes
	public ArrayList<Participante> loadParticipantes() {
		ArrayList<Participante> parts = new ArrayList<>();
		int idParticipante;
		Date dataCriacao;
		String nomeParticipante;
		int idConta;
		String situacaoParticipante;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PARTICIPANTE");
			while(rs.next()) {
				idParticipante = rs.getInt("idParticipante");
				dataCriacao = rs.getDate("dataCriacao");
				nomeParticipante = rs.getString("nomeParticipante");
				idConta = rs.getInt("idConta");
				situacaoParticipante = rs.getString("situacaoParticipante");
				Participante part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
				parts.add(part);
			}
			return parts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parts;
		
	}

	public void removeParticipante(Participante part) {
		try {
			Statement st = con.createStatement();
			st.execute("DELETE FROM PARTICIPANTE WHERE idParticipante = "+part.getIdParticipante());
			st.execute("DELETE FROM CONTA WHERE idConta = "+part.getIdConta());
			ParticipanteController.getInstance().deleteParticipante(part);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void editParticipante(Participante part, String nomeParticipante, String situacaoParticipante) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE PARTICIPANTE SET nomeParticipante ='"+nomeParticipante+"', situacaoParticipante = '"+situacaoParticipante+"' WHERE idParticipante ="+part.getIdParticipante());
			loadParticipantes();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Conta findContaById(int idConta) {
		Conta conta = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM CONTA WHERE idConta ="+idConta);
			if(rs.next()) {
				Date dataCadastro = rs.getDate("dataCadastro");
				double saldoPortabilidade = rs.getDouble("saldoPortabilidade");
				double saldoContribuicoesAdicionais = rs.getDouble("saldoContribuicoesAdicionais");
				double saldoContribuicoesNormais = rs.getDouble("saldoContribuicoesNormais");
				conta = new Conta(idConta, dataCadastro, saldoPortabilidade, saldoContribuicoesAdicionais, saldoContribuicoesNormais);
				return conta;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conta;
	}

	public Participante findParticipanteByContaId(int idConta) {
		Participante part = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Participante WHERE idConta ="+idConta);
			if(rs.next()) {
				int idParticipante = rs.getInt("idParticipante");
				Date dataCriacao = rs.getDate("dataCriacao");
				String nomeParticipante = rs.getString("nomeParticipante");
				String situacaoParticipante = rs.getString("situacaoParticipante");
				part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
				return part;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return part;
	}
	//Atualiza valor de saldo de acordo com contribui��o
	public int contribuir(Conta conta, String tipoContribuicao, Double valor) {
		try {
			Statement st = con.createStatement();
			switch(tipoContribuicao) {
			case "PORTABILIDADE" :
				st.execute("UPDATE CONTA SET saldoPortabilidade ="+(conta.getSaldoPortabilidade()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			case "ADICIONAL" :
				st.execute("UPDATE CONTA SET saldoContribuicoesAdicionais ="+(conta.getSaldoContribuicoesAdicionais()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			case "NORMAL" :
				st.execute("UPDATE CONTA SET saldoContribuicoesNormais ="+(conta.getSaldoContribuicoesNormais()+valor)+" WHERE idConta = "+conta.getIdConta());		
				return 0;
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public boolean resgatar(Conta conta, String tipoResgate, double valor, int numeroParcelas) {
		Participante part = findParticipanteByContaId(conta.getIdConta());
		try {
			Statement st = con.createStatement();
				switch(tipoResgate) {
				case "PORTABILIDADE" :
					if(conta.getSaldoPortabilidade() < valor) {
						return false;
					}
					st.execute("UPDATE CONTA SET saldoPortabilidade ="+(conta.getSaldoPortabilidade()-valor)+" WHERE idConta = "+conta.getIdConta());	
					st.execute("INSERT INTO RESGATE (idConta, valorResgate, tipoResgate, numeroParcelas) VALUES("+conta.getIdConta()+","+valor+",'"+tipoResgate+"',"+numeroParcelas+")");
					return true;
				case "ADICIONAL" :
					if(conta.getSaldoContribuicoesAdicionais() < valor) {
						return false;
					}
					st.execute("UPDATE CONTA SET saldoContribuicoesAdicionais ="+(conta.getSaldoContribuicoesAdicionais()-valor)+" WHERE idConta = "+conta.getIdConta());		
					st.execute("INSERT INTO RESGATE (idConta, valorResgate, tipoResgate, numeroParcelas) VALUES("+conta.getIdConta()+","+valor+",'"+tipoResgate+"',"+numeroParcelas+")");
					return true;
				case "NORMAL" :
					if(conta.getSaldoContribuicoesNormais() < valor) {
						return false;
					}
					st.execute("UPDATE CONTA SET saldoContribuicoesNormais ="+(conta.getSaldoContribuicoesNormais()-valor)+" WHERE idConta = "+conta.getIdConta());		
					st.execute("INSERT INTO RESGATE (idConta, valorResgate, tipoResgate, numeroParcelas) VALUES("+conta.getIdConta()+","+valor+",'"+tipoResgate+"',"+numeroParcelas+")");
					return true;
				case "TOTAL" :
					
				}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}

	public Participante findParticipanteById(int idParticipante) {
		Participante part = null;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Participante WHERE idParticipante ="+idParticipante);
			if(rs.next()) {
				int idConta = rs.getInt("idConta");
				Date dataCriacao = rs.getDate("dataCriacao");
				String nomeParticipante = rs.getString("nomeParticipante");
				String situacaoParticipante = rs.getString("situacaoParticipante");
				part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta, situacaoParticipante);
				return part;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return part;
	}
	public boolean validateIdadeConta(int idConta) {
		try {
			Statement st = con.createStatement();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			date.setYear(date.getYear()-3);
			ResultSet rs = st.executeQuery("SELECT * FROM CONTA WHERE idConta = "+idConta);
			if(rs.next()) {
				if(rs.getDate("dataCadastro").before(date)) {
					return true;
				}
				return false;
			} 
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean validateResgateNormal(int idConta) {
		try {
			Statement st = con.createStatement();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			long time = System.currentTimeMillis();
			Date date = new Date(time);
			date.setYear(date.getYear()-2);
			ResultSet rs = st.executeQuery("SELECT * FROM RESGATE WHERE idConta = "+idConta+" AND tipoResgate = 'NORMAL'" );
			if(rs.next()) {
				if(rs.getDate("dataCadastro").before(date)) {
					return true;
				}
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	
		
}
