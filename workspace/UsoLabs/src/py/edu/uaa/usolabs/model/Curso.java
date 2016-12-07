package py.edu.uaa.usolabs.model;

public class Curso {
	
	/**
	 * @return the cod_curso
	 */
	public int getCod_curso() {
		return cod_curso;
	}
	/**
	 * @param cod_curso the cod_curso to set
	 */
	public void setCod_curso(int cod_curso) {
		this.cod_curso = cod_curso;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the cant_alumnos
	 */
	public int getCant_alumnos() {
		return cant_alumnos;
	}
	/**
	 * @param cant_alumnos the cant_alumnos to set
	 */
	public void setCant_alumnos(int cant_alumnos) {
		this.cant_alumnos = cant_alumnos;
	}
	/**
	 * @return the cod_materia
	 */
	public int getCod_materia() {
		return cod_materia;
	}
	/**
	 * @param cod_materia the cod_materia to set
	 */
	public void setCod_materia(int cod_materia) {
		this.cod_materia = cod_materia;
	}
	
	
	private int cod_curso;
	private String descripcion;
	private int cant_alumnos;
	private int cod_materia;
	
}
