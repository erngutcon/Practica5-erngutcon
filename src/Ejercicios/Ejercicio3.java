package Ejercicios;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import Datos.Persona;
//import Datos.SimpleEdge;
import Datos.SimpleVertex;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.SimpleEdge;

public class Ejercicio3 {

	public static void main(String[] args) {
		Graph<SimpleVertex, SimpleEdge<SimpleVertex>> g = leeAsignaturas("../Practica 5/Ficheros5/PI5Ej3DatosEntrada.txt");
		String fout = "../Practica 5/Ficheros5/PI5Ej3bDatosSalida.gv";
		
		
		
		//APARTADO A
		
		System.out.println("Apartado A");
		TopologicalOrderIterator<SimpleVertex, SimpleEdge<SimpleVertex>> toi = 
				new TopologicalOrderIterator<>(g);
				List<String> asig=new ArrayList<String>();
				toi.forEachRemaining(v->asig.add(v.getNombre()));
		System.out.println("Una de las posibles ordenaciones válidas es la siguiente:");
		for(int i = 0; i<asig.size(); i++) {
			System.out.println("   "+asig.get(i) );
		}
				
		//APARTADO B
		
		Set<String> origen = new HashSet<String>();
		g.vertexSet().stream().filter(x->g.inDegreeOf(x)==0).forEach(x->origen.add(x.getNombre()));
		
		System.out.println("\nApartado B");
		System.out.println("Las asignaturas que no requieren aprobar otra antes son: ");
		origen.stream().forEach(x-> System.out.println(""+x));
		
		Graphs2.toDot(g, fout,
				v->v.getNombre(),
				e->String.format("%s", ""),
				v->GraphColors.getColorIf(Color.blue, Color.black, 
						origen.contains(v.getNombre())),
				e-> GraphColors.getStyle(Style.solid)
				);
		
		//APARTADO C
		
		System.out.println("\nApartado C");
		System.out.println("Test 1 – El alumno puede cursar las siguientes asignaturas: ");
		Set<String> aprobadas = Set.of("Asignatura_01", "Asignatura_02", "Asignatura_03", 
				"Asignatura_04", "Asignatura_05");
		Set<String> proximas = ej3C("../Practica 5/Ficheros5/PI5Ej3DatosEntrada.txt", "../Practica 5/Ficheros5/PI5Ej3C1DatosSalida.gv", aprobadas);
		System.out.println( proximas);
		
		
		System.out.println("Test 2 – El alumno puede cursar las siguientes asignaturas: ");
		aprobadas = Set.of("Asignatura_01", "Asignatura_02", "Asignatura_03", 
				"Asignatura_04", "Asignatura_05", "Asignatura_06", "Asignatura_07", "Asignatura_08", "Asignatura_11");
		proximas = ej3C("../Practica 5/Ficheros5/PI5Ej3DatosEntrada.txt", "../Practica 5/Ficheros5/PI5Ej3C2DatosSalida.gv", aprobadas);
		System.out.println( proximas);
	}
	
	public static Set<String> ej3C(String fin, String foutc, Set<String> aprobadas){
		
		Graph<SimpleVertex, SimpleEdge<SimpleVertex>> g = leeAsignaturas(fin);
		Set<String> proximas = new HashSet<String>();
		g.vertexSet().stream().filter(x-> {if(aprobadas.contains(x.getNombre()))
			return false;
		int i = 0; 
		for(String s: aprobadas){
			SimpleVertex v1 = SimpleVertex.ofName(s);
			if(g.containsEdge(v1,x))
				i++;
		} return g.inDegreeOf(x)==i;
		}).forEach(x-> proximas.add(x.getNombre()));
		
		//Map<SimpleVertex, Integer> resc = new HashMap<>();
		
		Map<SimpleVertex, Color> resv = new HashMap<>();
		Object[] array = g.vertexSet().toArray();
		for(int i=0; i<array.length; i++) {
			SimpleVertex set = (SimpleVertex) array[i];
			if(aprobadas.contains(set.getNombre())) 
				resv.put(set, Color.blue);
			else if(proximas.contains(set.getNombre())) 
				resv.put(set, Color.yellow);
				else 
			resv.put(set, Color.black);
		
			
		}
		
		Graphs2.toDot(g, foutc,
				v->v.getNombre(),
				e->String.format("%s", ""),
				v->GraphColors.getColor(resv.get(v)),
				e-> GraphColors.getStyle(Style.solid)
				);
	return proximas;
	}
	
	public static Graph<SimpleVertex, SimpleEdge<SimpleVertex>> leeAsignaturas(String fichero){
		List<String> linea = Files2.linesFromFile(fichero);
		SimpleDirectedGraph<SimpleVertex, SimpleEdge<SimpleVertex>> g =
				new SimpleDirectedGraph<>(SimpleVertex::of, SimpleEdge::of, false);
		for(int i=0; i<linea.size(); i++) {
			String[] elementos = linea.get(i).split(":");
			String elementos2 = elementos[1].replaceAll("[{}]", "").trim();
			String[] elementos3 = elementos2.split(",");
			int j = 0;
			SimpleVertex v = SimpleVertex.ofName(elementos[0]);
			g.addVertex(v);
				while(j<elementos3.length) {
				if(!elementos3[j].equals("")) {
				SimpleVertex v1 = SimpleVertex.ofName(elementos3[j]);
				SimpleEdge<SimpleVertex> e = SimpleEdge.of(v1, v);	
				g.addEdge(v1, v, e);
				}
				j++;
				}
		
	}
		return g;

}}
