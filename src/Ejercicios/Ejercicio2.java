package Ejercicios;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm.Coloring;
import org.jgrapht.graph.SimpleGraph;

import Datos.SimpleEdge;
import Datos.SimpleVertex;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Style;
import us.lsi.common.Files2;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class Ejercicio2 {
	
	public static void main(String[] args) {
		Graph<SimpleVertex, SimpleEdge> g = leeGrupos("../Practica 5/Ficheros5/PI5Ej2DatosEntrada.txt");
		
		String fout = "../Practica 5/Ficheros5/PI5Ej2bDatosSalida.gv";
		
		//APARTADO A
		
		GreedyColoring<SimpleVertex, SimpleEdge> alg = new GreedyColoring<>(g);
		Coloring<SimpleVertex> coloreado = alg.getColoring();
		System.out.println("Nº de franjas horarias necesarias: " + coloreado.getNumberColors());
		
		//APARTADO B
		
		List<Set<SimpleVertex>> composicion = coloreado.getColorClasses();
		System.out.println("Grupos a impartirse en paralelo según franja horaria:");
		for(int i=0; i<composicion.size(); i++) {
			System.out.println("Franja nº "+(i+1)+": "+composicion.get(i));
		}
		
		Map<SimpleVertex, Integer> mapColores = coloreado.getColors();
		Graphs2.toDot(g, fout,
				v->v.getNombre(),
				e->"",
				v-> GraphColors.getColor(mapColores.get(v)),
				e-> GraphColors.getStyle(Style.solid));
	}
	
	
	
	public static Graph<SimpleVertex, SimpleEdge> leeGrupos(String fichero){
		List<String> linea = Files2.linesFromFile(fichero);
		Graph<SimpleVertex, SimpleEdge> g =
				new SimpleGraph<>(SimpleVertex::of, SimpleEdge::of, false);
		
		for(int i=0; i<linea.size(); i++) {
			
			String[] elementos = linea.get(i).split(":");
			String[] elementos2 = elementos[1].split(",");	
			
			for(int j=0; j<elementos2.length; j++) {
				
				SimpleVertex v1 = SimpleVertex.ofName(elementos2[j].trim());
				SimpleVertex v2 = SimpleVertex.ofName(elementos2[(j+1)%elementos2.length].trim());
				
				g.addVertex(v1);
				g.addVertex(v2);
				
				
				g.addEdge(v1, v2);
		}
	}
		return g;
		
	}
		
		
			
	}
	


/*private static Graph<SimpleVertex, SimpleEdge> leeGrupos(String fichero){
		Graph<SimpleVertex, SimpleEdge> g =
				new SimpleGraph<>(SimpleVertex::of, SimpleEdge::of, false);
		for(String s: Files2.linesFromFile(fichero)) {
			String[] tokens = s.split(":");
			SimpleVertex v1 = SimpleVertex.ofName(tokens[0].trim());
			SimpleVertex v2 = SimpleVertex.ofName(tokens[1].trim());
			g.addVertex(v1);
			g.addVertex(v2);
			g.addEdge(v1, v2);
		}
		return g;*/
		
	
	/*private static SimpleVertex parseaLinea2(List<String> linea) {
		for(int i=0; i<linea.size(); i++) {
		String[] elementos = linea.get(i).split(":");
		String[] elementos2 = elementos[1].split(",");
		}
		
		}*/