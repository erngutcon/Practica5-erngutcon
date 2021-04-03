package Datos;

public class Persona {
	
	public static Persona of() {
		return new Persona("");
	}

	public static Persona ofFormat(String[] formato) {
		return new Persona(formato);
	}

	public static Persona ofName(String nombre) {
		return new Persona(nombre);
	}
	
	private String nombre;

	private Persona(String nombre) {
		super();
		this.nombre = nombre;
	}

	private Persona(String[] formato){
		super();
		this.nombre = formato[0];
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	

}
