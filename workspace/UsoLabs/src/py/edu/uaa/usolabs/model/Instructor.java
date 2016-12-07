package py.edu.uaa.usolabs.model;

public class Instructor {

	@Override
	public String toString() {
		return "Instructor [edad_ins=" + edad_ins + ", direccion_ins=" + direccion_ins + ", cod_ciudad_ins="
				+ cod_ciudad_ins + ", telefono_ins=" + telefono_ins + ", celular_ins=" + celular_ins + ", email_ins="
				+ email_ins + super.toString() + "]";
	}

	public String getEdad_ins() {
		return edad_ins;
	}

	public void setEdad_ins(String edad_ins) {
		this.edad_ins = edad_ins;
	}

	public String getDireccion_ins() {
		return direccion_ins;
	}

	public void setDireccion_ins(String direccion_ins) {
		this.direccion_ins = direccion_ins;
	}

	public String getCod_ciudad_ins() {
		return cod_ciudad_ins;
	}

	public void setCod_ciudad_ins(String cod_ciudad_ins) {
		this.cod_ciudad_ins = cod_ciudad_ins;
	}

	public String getTelefono_ins() {
		return telefono_ins;
	}

	public void setTelefono_ins(String telefono_ins) {
		this.telefono_ins = telefono_ins;
	}

	public String getCelular_ins() {
		return celular_ins;
	}

	public void setCelular_ins(String celular_ins) {
		this.celular_ins = celular_ins;
	}

	public String getEmail_ins() {
		return email_ins;
	}

	public void setEmail_ins(String email_ins) {
		this.email_ins = email_ins;
	}

	
	 public String getCi_ins() {
		return ci_ins;
	}

	public void setCi_ins(String ci_ins) {
		this.ci_ins = ci_ins;
	}

	public String getNombreApellido_ins() {
		return nombreApellido_ins;
	}

	public void setNombreApellido_ins(String nombreApellido_ins) {
		this.nombreApellido_ins = nombreApellido_ins;
	}

	public String getCodInstructor() {
		return codInstructor;
	}

	public void setCodInstructor(String codInstructor) {
		this.codInstructor = codInstructor;
	}

	private String codInstructor;
	private String ci_ins;
	 private String nombreApellido_ins;
	private String edad_ins;
	private String direccion_ins;
	private String cod_ciudad_ins;
	private String telefono_ins;
	private String celular_ins;
	private String email_ins;
	// public void add(Instructor instructor) {
	// // TODO Auto-generated method stub
	//
	// }

}
