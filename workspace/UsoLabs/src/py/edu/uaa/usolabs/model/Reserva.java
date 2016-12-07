package py.edu.uaa.usolabs.model;
 

public class Reserva {
	
	private String cod_reserva;
	private String FechaReserva;
	private Laboratorio cod_laboratorio;
	private String cod_materia;
	private String cod_curso;
	private String cod_profesor;
	private String HoraInicio;
	private String HoraFin;
	private String cod_instructor;
	
	
	
	public String getFechaReserva() {
		return FechaReserva;
	}
	public void setFechaReserva(String fechaReserva) {
		FechaReserva = fechaReserva;
	}
	public Laboratorio getCod_laboratorio() {
		return cod_laboratorio;
	}
	public void setCod_laboratorio(Laboratorio cod_laboratorio) {
		this.cod_laboratorio = cod_laboratorio;
	}
	public String getCod_materia() {
		return cod_materia;
	}
	public void setCod_materia(String cod_materia) {
		this.cod_materia = cod_materia;
	}
	public String getCod_profesor() {
		return cod_profesor;
	}
	public void setCod_profesor(String cod_profesor) {
		this.cod_profesor = cod_profesor;
	}
	public String getHoraInicio() {
		return HoraInicio;
	}
	public void setHoraInicio(String horaInicio) {
		HoraInicio = horaInicio;
	}
	public String getHoraFin() {
		return HoraFin;
	}
	public void setHoraFin(String horaFin) {
		HoraFin = horaFin;
	}
	public String getCod_instructor() {
		return cod_instructor;
	}
	public void setCod_instructor(String cod_instructor) {
		this.cod_instructor = cod_instructor;
	}
	public String getCod_curso() {
		return cod_curso;
	}
	public void setCod_instructor(Instructor instructor) {
		// TODO Auto-generated method stub
		
	}
	public void setCod_curso(String string) {
		// TODO Auto-generated method stub
		
	}
	public String getCod_reserva() {
		return cod_reserva;
	}
	public void setCod_reserva(String cod_reserva) {
		this.cod_reserva = cod_reserva;
	}
	
	

	
	
	
	
}
