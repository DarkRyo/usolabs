package py.edu.uaa.usolabs.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import py.edu.uaa.usolabs.model.Curso;
import py.edu.uaa.usolabs.model.Instructor;
import py.edu.uaa.usolabs.model.Profesor;


public class CursoDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";

	
	public List<Curso> recuperarCurso() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM curso";
		List<Curso> cursos = new ArrayList<Curso>();
		
		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				
				Curso cur = new Curso();
				cur.setCod_curso(rs.getInt(1));
				cur.setDescripcion(rs.getString(2));
				cur.setCant_alumnos(rs.getInt(3));
				cur.setCod_materia(rs.getInt(4));
	
				cursos.add(cur);
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
		
		return cursos;

	}
 
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean insertarCursos(Curso cursos) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO curso"
				+ "(cod_curso, descripcion, cantalumnos, cod_materia) VALUES"
				+ "(?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			//del Ins que se recibe como parametro se obtienen 
			//las propiedades que se tienen que insertar en la base de datos
			
			preparedStatement.setInt(1, cursos.getCod_curso());
			
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		
			System.out.println("Se ha insertado correctamente en la tabla curso");
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
