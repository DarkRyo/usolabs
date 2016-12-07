package py.edu.uaa.usolabs.model;

public class Laboratorio {

	private String cod_laboratorio;
	private String descripcion;
	private String cant_equipos;
	private String nro_lab;

	public String getCod_laboratorio() {
		return cod_laboratorio;
	}

	public void setCod_laboratorio(String cod_laboratorio) {
		this.cod_laboratorio = cod_laboratorio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCant_equipos() {
		return cant_equipos;
	}

	public void setCant_equipos(String cant_equipos) {
		this.cant_equipos = cant_equipos;
	}

	public String getNro_lab() {
		return nro_lab;
	}

	public void setNro_lab(String nro_lab) {
		this.nro_lab = nro_lab;
	}

	@Override
	public String toString() {
		return "Laboratorio [cod_laboratorio=" + cod_laboratorio + ", descripcion=" + descripcion + ", cant_equipos="
				+ cant_equipos + ", nro_lab=" + nro_lab + super.toString() +"]";
	}

}
