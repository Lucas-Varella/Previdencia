package controller;

import java.sql.*;
import java.util.ArrayList;

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
	public void createParticipante(Date dataNascimento, String nomeParticipante) {
		try {
			Statement st = con.createStatement();
			//st.execute("INSERT longO PARTICIPANTE VALUES (")
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
	public long insertParticipante(String nomeParticipante) {
		try {
			Statement st = con.createStatement();
			st.execute("INSERT INTO CONTA (saldoPortabilidade, saldoContribuicoesAdicionais, saldoContribuicoesNormais) VALUES (0, 0, 0)");
			ResultSet rs = st.executeQuery("SELECT idConta FROM CONTA ORDER BY dataCadastro DESC");
			int idConta = 0;
			if(rs.next()) {
				idConta = rs.getInt("idConta");
			}
			
			st.execute("INSERT INTO PARTICIPANTE (nomeParticipante, idConta) VALUES ('"+nomeParticipante+"',"+idConta+")");
			ResultSet rsPart = st.executeQuery("SELECT * FROM PARTICIPANTE WHERE IDCONTA = "+idConta);
			if(rsPart.next()) {
				int idParticipante = rsPart.getInt("idParticipante");
				Date dataCriacao = rsPart.getDate("dataCriacao");
				MainController.getInstance().newParticipante(idParticipante, dataCriacao, nomeParticipante, idConta);
			}
			
			return idConta;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void test() {
		try {
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM CONTA");
			while(rs.next()) {
				System.out.println(rs.getInt("idConta"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Participante> loadParticipantes() {
		ArrayList<Participante> parts = new ArrayList<>();
		int idParticipante;
		Date dataCriacao;
		String nomeParticipante;
		int idConta;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM PARTICIPANTE");
			while(rs.next()) {
				idParticipante = rs.getInt("idParticipante");
				dataCriacao = rs.getDate("dataCriacao");
				nomeParticipante = rs.getString("nomeParticipante");
				idConta = rs.getInt("idConta");
				Participante part = new Participante(idParticipante, dataCriacao, nomeParticipante, idConta);
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

	public void editParticipante(Participante part, String text) {
		try {
			Statement st = con.createStatement();
			st.execute("UPDATE PARTICIPANTE SET nomeParticipante ='"+text+"' WHERE idParticipante ="+part.getIdParticipante());
			ParticipanteController.getInstance().editParticipante(part,text);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
		
}
