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


public class InstructorDao {
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";

	
	@SuppressWarnings("null")
	public Instructor recuperarInstructor(String codinstrucctor) {
		Connection dbConnection = null;
		PreparedStatement statement = null;

		String query = "SELECT * FROM instructor where nombreapellido = ?";
		//ArrayList<Instructor> instructores = new ArrayList<Instructor>();
		
		try {
			dbConnection = getDBConnection();
			statement.setString(1, codinstrucctor);
			ResultSet rs = statement.executeQuery(query);
			if (rs.next()) {
				Instructor ins = new Instructor();
				
				ins.setCi_ins(rs.getString(1));
				ins.setNombreApellido_ins(rs.getString(2));
				ins.setEdad_ins(rs.getString(3));
				ins.setDireccion_ins(rs.getString(4));
				ins.setCod_ciudad_ins(rs.getString(5));
				ins.setTelefono_ins(rs.getString(6));
				ins.setCelular_ins(rs.getString(7));
				ins.setEmail_ins(rs.getString(8));
				
				return ins;
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
	public List<Instructor> recuperarInstructores(){
		Connection dbConnection = null;
		Statement statement = null;
		String query = "SELECT * FROM instructor";
		
		List<Instructor> instructores = new ArrayList<Instructor>();
		try{
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				Instructor instructor = new Instructor();
				
				instructor.setNombreApellido_ins(rs.getString(1));
//				instructor.setEdad_ins(rs.getString(2));
//				instructor.setDireccion_ins(rs.getString(3));
//				instructor.setCod_ciudad_ins(rs.getString(4));
//				instructor.setTelefono_ins(rs.getString(5));
//				instructor.setCelular_ins(rs.getString(6));
//				instructor.setEmail_ins(rs.getString(7));
//				
				instructores.add(instructor);
			}
			
			
		}catch (Exception e){
			System.out.println(e.getMessage());
		} finally{
			try{
					statement.close();
					dbConnection.close();
				
			}catch (Exception e2){
				System.out.println(e2.getMessage());
			}
		}
		return instructores;
		

	}
 
	////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean insertarInstructor(Instructor Instructor) throws SQLException {
 
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
 
		String insertTableSQL = "INSERT INTO instructor"
				+ "(cod_instructor, nombreapellido, edad, direccion, cod_ciudad, telefono, celular, email) VALUES"
				+ "(?,?,?,?,?,?,?,?)";
 
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);
 
			//del Ins que se recibe como parametro se obtienen 
			//las propiedades que se tienen que insertar en la base de datos
			
			preparedStatement.setString(1, Instructor.getCi_ins());
			preparedStatement.setString(2, Instructor.getNombreApellido_ins());
			preparedStatement.setString(3, Instructor.getEdad_ins());
			preparedStatement.setString(4, Instructor.getDireccion_ins());
			preparedStatement.setString(5, Instructor.getCod_ciudad_ins());
			preparedStatement.setString(6, Instructor.getTelefono_ins());
			preparedStatement.setString(7, Instructor.getCelular_ins());
			preparedStatement.setString(8, Instructor.getEmail_ins());
			
			
			
 
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
		
			System.out.println("Se ha insertado correctamente en la tabla instructor");
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
