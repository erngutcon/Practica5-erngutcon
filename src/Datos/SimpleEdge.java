package Datos;




public class SimpleEdge {
	
	private static int num =0;
	
	private SimpleVertex source;
	private SimpleVertex target;
	private Double weight;
	private int id;
	
	public static SimpleEdge of() {
		return new SimpleEdge();
	}

	public static SimpleEdge of(SimpleVertex v1, SimpleVertex v2) {
		return new SimpleEdge(v1,v2);
	}

	public static SimpleEdge ofFormat(SimpleVertex v1, SimpleVertex v2, String[] formato) {
		return new SimpleEdge(v1,v2,formato);
	}

	public static SimpleEdge of(SimpleVertex v1, SimpleVertex v2, Double weight) {
		return new SimpleEdge(v1, v2, weight);
	}
	
	public SimpleEdge() {
		this.source = null;
		this.target = null;
		this.weight = 1.;
		id = num++;
		
	} 

	public SimpleEdge(SimpleVertex v1, SimpleVertex v2) {
		this.source = v1;
		this.target = v2;
		this.weight = 1.;
		id = num++;
	}

	
	public SimpleEdge(SimpleVertex v1, SimpleVertex v2, double weight) {
		super();
		this.source = v1;
		this.target = v2;
		this.weight = weight;
		id = num++;
		
	}

	private SimpleEdge(SimpleVertex v1, SimpleVertex v2, String[] formato) {
		this.source = v1;
		this.target = v2;
		this.weight = formato.length==3? Double.parseDouble(formato[2]):1.;		
		id = num++;
	}
	
	public Double getEdgeWeight() {
		return this.weight;
	}
	
	public SimpleVertex getSource(){
		return source;
	}
	
	public SimpleVertex getTarget(){
		return target;
	}
	
	public void setWeight(double weight) {
		this.weight=weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		SimpleEdge other = (SimpleEdge) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("(%s,%s)=%.2f", this.source,this.target,this.weight);
	}
	
	
	
	
	
	

}