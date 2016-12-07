package py.edu.uaa.usolabs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import py.edu.uaa.usolabs.model.Instructor;
import py.edu.uaa.usolabs.model.Laboratorio;
import py.edu.uaa.usolabs.model.Reserva;

public class ReservaDao {
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/usolabs";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "@lumno123";
	private LaboratorioDao laboratorioDao = new LaboratorioDao();
	private InstructorDao instructorDao = new InstructorDao();
	// private ReservaDao reservaDao = new ReservaDao();

	///////////////////////////////////////////////////////////////////////

	public List<Reserva> recuperarReserva() {
		Connection dbConnection = null;
		Statement statement = null;

		String query = "SELECT * FROM reserva";
		List<Reserva> reservas = new ArrayList<Reserva>();

		try {
			dbConnection = getDBConnection();
			ResultSet rs = dbConnection.createStatement().executeQuery(query);
			while (rs.next()) {
				Reserva res = new Reserva();

				// res.setCod_reserva(rs.getString(1));
				res.setCod_materia(rs.getString(2));
				res.setFechaReserva(rs.getString(3));
				res.setHoraInicio(rs.getString(4));
				res.setHoraFin(rs.getString(5));
				res.setCod_curso(rs.getString(6));
				// instructor
				String codinstrucctor = rs.getString(7);
				if (codinstrucctor != null) {
					Instructor instructor = this.instructorDao.recuperarInstructor(codinstrucctor);
					res.setCod_instructor(instructor);
				}
				// laboratorio
				String codlaboratorio = rs.getString(8);
				if (codlaboratorio != null) {
					Laboratorio laboratorio = this.laboratorioDao.recuperarLaboratorio(codlaboratorio);
					// ERROR res.setCod_laboratorio(laboratorio);
				}
				reservas.add(res);
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

		return reservas;

	}

	///////////////////////////////////////////////////////////////////////

	public boolean insertarReserva(Reserva reserva) throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO reserva (cod_reserva, fecha, cod_curso, hora_inicio, hora_fin, cod_instructor, cod_laboratorio) VALUES (?,?,?,?,?,?,?)";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			// del Ins que se recibe como parametro se obtienen
			// las propiedades que se tienen que insertar en la base de datos

			preparedStatement.setString(1, reserva.getCod_reserva());
			// preparedStatement.setString(2, reserva.getCod_materia());
			preparedStatement.setString(2, reserva.getCod_curso());
			// preparedStatement.setString(4, reserva.getCod_profesor());
			// FechaReserva
			if (reserva.getFechaReserva() != null) {
				preparedStatement.setString(5, reserva.getFechaReserva());
			} else {
				preparedStatement.setNull(5, Types.CHAR);
			}
			// Hora Inicio
			if (reserva.getHoraInicio() != null) {
				preparedStatement.setString(3, reserva.getHoraInicio());
			} else {
				preparedStatement.setNull(3, Types.CHAR);
			}
			// Hora Fin
			if (reserva.getHoraFin() != null) {
				preparedStatement.setString(4, reserva.getHoraFin());
			} else {
				preparedStatement.setString(4, reserva.getHoraFin());
			}

			// preparedStatement.setString(6, reserva.getCod_instructor());
			//
			// preparedStatement.setString(7, reserva.getCod_laboratorio());

			// INSTRUCTOR

			if (reserva.getCod_instructor() != null) {
				preparedStatement.setString(6, reserva.getCod_instructor());
			} else {
				preparedStatement.setNull(6, Types.CHAR);
			}

			// LABORATORIO

			if (reserva.getCod_laboratorio() != null) {
				preparedStatement.setString(7, reserva.getCod_laboratorio().getDescripcion());
			} else {
				preparedStatement.setNull(7, Types.CHAR);
			}

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Se ha insertado correctamente los datos :D");
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

	///////////////////////////////////////////////////////////////////////

	public boolean eliminarReserva(Reserva reserva) {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		String deleteSQL = "DELETE from reserva WHERE cod_reserva = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(deleteSQL);

			preparedStatement.setString(1, reserva.getCod_reserva());
			
//			preparedStatement.setString(2, reserva.getCod_curso());
//			
//			// FechaReserva
//			if (reserva.getFechaReserva() != null) {
//				preparedStatement.setString(5, reserva.getFechaReserva());
//			} else {
//				preparedStatement.setNull(5, Types.CHAR);
//			}
//			// Hora Inicio
//			if (reserva.getHoraInicio() != null) {
//				preparedStatement.setString(3, reserva.getHoraInicio());
//			} else {
//				preparedStatement.setNull(3, Types.CHAR);
//			}
//			// Hora Fin
//			if (reserva.getHoraFin() != null) {
//				preparedStatement.setString(4, reserva.getHoraFin());
//			} else {
//				preparedStatement.setString(4, reserva.getHoraFin());
//			}
//
//		
//			// INSTRUCTOR
//
//			if (reserva.getCod_instructor() != null) {
//				preparedStatement.setString(6, reserva.getCod_instructor());
//			} else {
//				preparedStatement.setNull(6, Types.CHAR);
//			}
//
//			// LABORATORIO
//
//			if (reserva.getCod_laboratorio() != null) {
//				preparedStatement.setString(7, reserva.getCod_laboratorio().getDescripcion());
//			} else {
//				preparedStatement.setNull(7, Types.CHAR);
//			}

			// execute delete SQL stetement
			preparedStatement.executeUpdate();
			System.out.println("Registro borrado de forma correcta");
			return true;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////

	public boolean actualizarReserva(Reserva reserva) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		// los parametros del where son en base a la necesidad de la logica de
		// negocio.
		String updateSql = "UPDATE reserva set fecha = ?, hora_inicio = ?, hora_fin = ?, cod_instructor = ?,  WHERE cod_reserva = ?";
		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(updateSql);
			// FechaReserva
			if (reserva.getFechaReserva() != null) {
				preparedStatement.setString(1, reserva.getFechaReserva());
			} else {
				preparedStatement.setNull(1, Types.CHAR);
			}

			// Hora Inicio
			if (reserva.getHoraInicio() != null) {
				preparedStatement.setString(2, reserva.getHoraInicio());
			} else {
				preparedStatement.setNull(2, Types.CHAR);
			}

			// Hora Fin
			if (reserva.getHoraFin() != null) {
				preparedStatement.setString(3, reserva.getHoraFin());
			} else {
				preparedStatement.setString(3, reserva.getHoraFin());
			}
			preparedStatement.setString(4, reserva.getCod_instructor());
			// ERROR preparedStatement.setString(5,
			// reserva.getCod_laboratorio());

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}

		}
		return false;
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
}
