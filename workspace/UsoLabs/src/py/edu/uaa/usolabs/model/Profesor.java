package py.edu.uaa.usolabs.model;

public class Profesor extends Persona{
	
	
	public String getCod_profesor() {
		return cod_profesor;
	}
	public void setCod_profesor(String string) {
		this.cod_profesor = string;
	}
	public String getCi_profesor() {
		return ci_profesor;
	}
	public void setCi_profesor(String ci_profesor) {
		this.ci_profesor = ci_profesor;
	}
	public String getNombreYapellido() {
		return nombreYapellido;
	}
	public void setNombreYapellido(String nombreYapellido) {
		this.nombreYapellido = nombreYapellido;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String string) {
		this.edad = string;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String string) {
		this.telefono = string;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String cod_profesor;
	private String ci_profesor;
	private String nombreYapellido;
	private String edad;
	private String direccion;
	private String telefono;
	private String email;

}
