package Ejercicios;

import java.util.Comparator;


import java.util.HashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import Datos.Amistad;
import Datos.Persona;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Lists2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio1 {

	public static void main(String[] args) {
	ej1a ("../Practica 5/Ficheros5/PI5Ej1DatosEntrada.txt",
			"../Practica 5/Ficheros5/PI5Ej1aDatosSalida.gv");
	ej1b ("../Practica 5/Ficheros5/PI5Ej1DatosEntrada.txt",
			"../Practica 5/Ficheros5/PI5Ej1bDatosSalida.gv");
	ej1c ("../Practica 5/Ficheros5/PI5Ej1DatosEntrada.txt",
			"../Practica 5/Ficheros5/PI5Ej1cDatosSalida.gv");
	ej1d ("../Practica 5/Ficheros5/PI5Ej1DatosEntrada.txt",
			"../Practica 5/Ficheros5/PI5Ej1dDatosSalida.gv");

	}
	public static void ej1a (String fin, String fout) {
		
		SimpleGraph<Persona,Amistad> g = GraphsReader.newGraph(fin, 
				Persona::ofFormat, 
				Amistad::ofFormat, 
				Graphs2::simpleGraph);
	
		List<Persona> nof = Lists2.empty();
		List<Persona> yof = Lists2.empty();
		
		Set<Persona> c = g.vertexSet();
		Stream<Persona> s = c.stream();
		OptionalInt max;
		max = s.mapToInt(x->g.degreeOf(x)).max();
		int max2 = max.getAsInt();
		Stream<Persona> s2 = c.stream();
		s2.forEach(v-> {
			if (g.degreeOf(v)==0) nof.add(v);
			else if(g.degreeOf(v)==max2) yof.add(v);
		});
		
		System.out.println("APARTADO A");
		System.out.println("Los miembros con 0 amigos son: " + nof);
		System.out.println("Los miembros con "+max2+" amigos son: " + yof);
		
		Graphs2.toDot(g, fout,
				v ->String.format("%s", v + "(" + g.degreeOf(v) + "amigos)"),
				e->String.format("%s", ""),
				v->GraphColors.getColor(colores(v,g)),
				e-> GraphColors.getStyle(Style.solid));
	}
	
	public static Color colores(Persona p, Graph<Persona, Amistad> g) {
		Color res = null;
			if(g.degreeOf(p)>=3) {
				res = Color.red;
		}
			else if (g.degreeOf(p)==0){
				res = Color.gray;
			}
			else {
				res = Color.black;
		}
			return res;	
		
		
		}
	
	
	public static <V,E> void ej1b (String fin, String fout) {
		
		Graph<Persona,Amistad> g = GraphsReader.newGraph(fin, 
				Persona::ofFormat, 
				Amistad::ofFormat, 
				Graphs2::simpleGraph);
	
		
		Persona from = Persona.ofName("Juan");
		Persona to = Persona.ofName("Ramiro");
		
		ShortestPathAlgorithm<Persona, Amistad> a = new DijkstraShortestPath<Persona,Amistad>(g);
		GraphPath<Persona,Amistad> gp =  a.getPath(from,to);
		
		Predicate<Persona> p_v = v->gp.getVertexList().contains(v);
		Predicate<Amistad> p_e = e->gp.getEdgeList().contains(e);
		
		
		Graphs2.toDot(g, fout,
				v ->String.format("%s", v + "(" + g.degreeOf(v) + "amigos)"),
				e->String.format("%s", ""),
				v->GraphColors.getColorIf(Color.blue, Color.gray, p_v.test(v)),
				e-> GraphColors.getColorIf(Color.blue, Color.gray, p_e.test(e)));
		
		List<Persona> lista = gp.getVertexList();
		System.out.println("APARTADO B");
		System.out.println("La lista mas corta entre "+from+" y "+ to +" es: " + lista);
		
		
	}
	
	public static void ej1c(String fin, String fout) {
		
		Graph<Persona,Amistad> g = GraphsReader.newGraph(fin, 
				Persona::ofFormat, 
				Amistad::ofFormat, 
				Graphs2::simpleGraph);
		
		var alg = new ConnectivityInspector<>(g);
		List<Set<Persona>> componentes = alg.connectedSets();
		
		Map<Persona, Integer> resc = new HashMap<>();
		
		System.out.println("APARTADO C");
		System.out.println("Existen "+componentes.size() +" grupos. Su composición es: " );
		
		Map<Persona, Color> resv = new HashMap<>();
		for(int i=0; i<componentes.size(); i++) {
			Set<Persona> set = componentes.get(i);
			Color color = Color.values()[i];
			System.out.println("Grupo :"+ color+ "("+ set.size()+"usuario):\n"
					+set);
			for (Persona p : set) {
				resv.put(p, Color.values()[i]);
				resc.put(p, i);
			}
		}
		
		Map<Amistad, Color> rese = new HashMap<>();
		for (Amistad e: g.edgeSet()) {
			if (resc.get(e.getSource()).equals(resc.get(e.getTarget()))) {
				rese.put(e, resv.get(e.getSource()));
			}
		}
		
		Graphs2.toDot(g, fout,
				v ->v.getNombre(),
				e->String.format("%s", ""),
				v->GraphColors.getColor(resv.get(v)),
				e-> GraphColors.getColor(rese.get(e)));
		
		
		
				
	}
	
	public static void ej1d(String fin, String fout) {
		
		Graph<Persona,Amistad> g = GraphsReader.newGraph(fin, 
				Persona::ofFormat, 
				Amistad::ofFormat, 
				Graphs2::simpleGraph);
		
		var vCover = new GreedyVCImpl<>(g);
		Set<Persona> cuestionarios = vCover.getVertexCover();
		
		Predicate<Persona> p_v = p-> cuestionarios.contains(p);
		Predicate<Amistad> p_e = a -> cuestionarios.contains(a.getSource())&&
									cuestionarios.contains(a.getTarget());
		
		Graphs2.toDot(g, fout,
				v ->v.getNombre(),
				e->String.format("%s", ""),
				v->GraphColors.getColorIf(Color.blue, Color.gray, p_v.test(v)),
				e-> GraphColors.getColorIf(Color.blue, Color.gray, p_e.test(e)));
		
		System.out.println("APARTADO D");
		System.out.println("Se enviarán "+cuestionarios.size()+" cuestionarios a los siguientes miembros: " + cuestionarios);
		
		
	}
	
	}


