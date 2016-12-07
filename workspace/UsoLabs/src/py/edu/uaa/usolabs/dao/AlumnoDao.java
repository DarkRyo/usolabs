package py.edu.uaa.usolabs.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.uaa.usolabs.model.Alumno;
import py.edu.uaa.usolabs.model.Instructor;


public class AlumnoDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";

	
	public List<Alumno> recuperarAlumno() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM alumno";
		List<Alumno> alumnos = new ArrayList<Alumno>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				Alumno alu = new Alumno();
				
				alu.setCod_alumno(rs.getString(1));
				alu.setNombre(rs.getString(2));
				alu.setEdad(rs.getString(3));
				alu.setCod_carrera(rs.getString(4));
				alu.setDireccion(rs.getString(5));
				alu.setCod_ciudad(rs.getString(6));
				alu.setTelefono(rs.getString(7));
				alu.setCelular(rs.getString(8));
				alu.setEmail(rs.getString(9));
				
				alumnos.add(alu);
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
		
		return alumnos;

	}
 
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean insertarAlumno(Alumno Alumno) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO alumno"
				+ "(cod_alumno, nombreapellido, edad, cod_carrera, direccion, cod_ciudad, telefono, celular, email, ci_alumno) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			//del Ins que se recibe como parametro se obtienen 
			//las propiedades que se tienen que insertar en la base de datos
			
			preparedStatement.setString(1, Alumno.getCi_alumno());
			preparedStatement.setString(2, Alumno.getNombreYapellido());
			preparedStatement.setString(3, Alumno.getEdad());
			preparedStatement.setString(4, Alumno.getCod_carrera());
			preparedStatement.setString(5, Alumno.getDireccion());
			preparedStatement.setString(6, Alumno.getCod_ciudad());
			preparedStatement.setString(7, Alumno.getTelefono());
			preparedStatement.setString(8, Alumno.getCelular());
			preparedStatement.setString(9, Alumno.getEmail());
			preparedStatement.setString(10, Alumno.getCi_alumno());			
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		
			System.out.println("Se ha insertado correctamente en la tabla Alumno");
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
