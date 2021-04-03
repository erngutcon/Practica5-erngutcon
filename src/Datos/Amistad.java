package Datos;


public class Amistad {
	
	public static Amistad of() {
		return new Amistad();
	}

	/*public static Amistad ofVertex(Persona c1, Persona c2) {
		return new Amistad(c1,c2);
	}*/

	public static Amistad ofFormat(Persona c1, Persona c2, String[] formato) {
		return new Amistad(c1,c2);
	}

	//public static Amistad ofWeight(Persona c1, Persona c2, Double km) {
	//	return new Amistad(c1, c2, km);
	//}
	
	/*public static Amistad reverse(Amistad c) {
		return new Amistad(c.target, c.source);
	}*/

	private Persona source;
	private Persona target;
	

	private Amistad(Persona c1, Persona c2) {
		this.source = c1;
		this.target = c2;
	}
	
	private Amistad() {
		this.source = null;
		this.target = null;
	} 
	
		
	public Persona getSource(){
		return source;
	}
	
	public Persona getTarget(){
		return target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Amistad other = (Amistad) obj;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Amistad [source=" + source + ", target=" + target + "]";
	}
	
	

}
