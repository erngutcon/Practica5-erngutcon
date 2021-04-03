package Datos;

public class SimpleVertex implements Comparable<SimpleVertex>{
	
	public static SimpleVertex of() {
		return new SimpleVertex("");
	}
	
	public static SimpleVertex ofFormat(String[] formato) {
		return new SimpleVertex(formato);
	}
	public static SimpleVertex ofName(String nombre) {
		return new SimpleVertex(nombre);
	}
	
	private final String nombre;
	
	private SimpleVertex(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	private SimpleVertex(String[] formato){
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
		SimpleVertex other = (SimpleVertex) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	public int compareTo(SimpleVertex o) {
		return getNombre().compareTo(o.getNombre());
	}
	
	
	

}
