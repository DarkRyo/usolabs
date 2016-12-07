package py.edu.uaa.usolabs.model;

public abstract class Persona {
	

	@Override
	public String toString(){
		return "Persona [nombre=" + ", CI" + "]";
	}
	
	private String nombre;
	private String CI;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCI() {
		return CI;
	}
	public void setCI(String cI) {
		CI = cI;
	}

	

}
