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
import py.edu.uaa.usolabs.model.Profesor;


public class ProfesorDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";

	
	public List<Profesor> recuperarProfesor() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM profesor";
		List<Profesor> profesores = new ArrayList<Profesor>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				
				Profesor pro = new Profesor();
				pro.setCod_profesor(rs.getString(1));
				pro.setCi_profesor(rs.getString(2));
				pro.setNombreYapellido(rs.getString(3));
				pro.setEdad(rs.getString(4));
				pro.setDireccion(rs.getString(5));
				pro.setTelefono(rs.getString(6));
				pro.setEmail(rs.getString(7));
	
				profesores.add(pro);
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
		
		return profesores;

	}
 
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean insertarProfesor(Profesor profesor) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO profesor"
				+ "(cod_profesor, nombreapellido, edad, direccion, cod_ciudad, telefono, celular, email) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			//del Ins que se recibe como parametro se obtienen 
			//las propiedades que se tienen que insertar en la base de datos
			
			preparedStatement.setString(1, profesor.getCi_profesor());
			
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		
			System.out.println("Se ha insertado correctamente en la tabla profesor");
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
