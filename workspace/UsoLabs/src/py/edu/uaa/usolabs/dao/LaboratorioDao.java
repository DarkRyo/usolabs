package py.edu.uaa.usolabs.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import py.edu.uaa.usolabs.model.Instructor;
import py.edu.uaa.usolabs.model.Laboratorio;


public class LaboratorioDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";

	
	public Laboratorio recuperarLaboratorio(String codlaboratorio) {
		Connection dbConnection = null;
		
		PreparedStatement statement = null;

		String query = "SELECT * FROM laboratorio where cod_laboratorio = ?";
		//
		
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement(query);
			statement.setString(1, codlaboratorio);
			ResultSet rs =statement.executeQuery(query);
			
			
			while (rs.next()) {
				Laboratorio lab = new Laboratorio();
				
				lab.setCod_laboratorio(rs.getString(1));
				lab.setDescripcion(rs.getString(2));
				lab.setCant_equipos(rs.getString(3));
				lab.setNro_lab(rs.getString(4));
				
				
				return lab;
			}			
		}

		catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
	 
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		return null;

	}
	
	
	public List<Laboratorio> recuperarLaboratorio(){
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM laboratorio";
		List<Laboratorio> laboratorios = new ArrayList<Laboratorio>();
		
		try{
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()){
				Laboratorio labora = new Laboratorio();
				
				labora.setCod_laboratorio(rs.getString(1));
				labora.setDescripcion(rs.getString(2));
				labora.setCant_equipos(rs.getString(3));
				labora.setNro_lab(rs.getString(4));
				
				laboratorios.add(labora);
			}
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		} finally{
			try {
				if (statement != null) {
					statement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
		
		
		
		return laboratorios;
	}
 
	////////////////////////////////////////////////////////////////////////////////////////
	
	/**public boolean insertarLaboratorio (Laboratorio laboratorio) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO laboratorio"
				+ "(cod_laboratorio, descripcion, cant_equipos, numero_lab) VALUES"
				+ "(?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			//del Ins que se recibe como parametro se obtienen 
			//las propiedades que se tienen que insertar en la base de datos
			
			preparedStatement.setString(1, laboratorio.getCod_laboratorio());
			preparedStatement.setString(2, laboratorio.getDescripcion());
			preparedStatement.setString(3, laboratorio.getCant_equipos());
		
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		
			System.out.println("Se ha insertado correctamente en la tabla laboratorio");
			return true;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
			return false;
			
 
		} finally {
 
			if (preparedStatement != null) {
				preparedStatement.close();
			}
 
			if (dbConnection != null) {
				dbConnection.close();
			}
 
		}
 
	}
	**/
 
	private static Connection getDBConnection() {
 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(DB_DRIVER);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		return dbConnection;
 
	}
	
}
