package Ejercicios;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.TopologicalOrderIterator;

import Datos.SimpleEdge;
import Datos.SimpleVertex;
import us.lsi.common.Files2;

public class Ejercicio3 {

	public static void main(String[] args) {
		Graph<SimpleVertex, SimpleEdge> g = leeAsignaturas("../Practica 5/Ficheros5/PI5Ej3DatosEntrada.txt");
		
		TopologicalOrderIterator<SimpleVertex, SimpleEdge> toi = 
				new TopologicalOrderIterator<>(g);
				List<SimpleVertex> asig=new ArrayList<>();
				toi.forEachRemaining(v->asig.add(v));
		System.out.println("Una de las posibles ordenaciones válidas es la siguiente:\n" + asig );
				
		
		
	}
	public static Graph<SimpleVertex, SimpleEdge> leeAsignaturas(String fichero){
		Graph<SimpleVertex, SimpleEdge> g =
				new SimpleDirectedGraph<>(SimpleVertex::of, SimpleEdge::of, false);
		for(String s: Files2.linesFromFile(fichero)) {
			String[] tokens = s.split(":");
			SimpleVertex v1 = SimpleVertex.ofName(tokens[0].trim());
			SimpleVertex v2 = SimpleVertex.ofName(tokens[1].trim());
			g.addVertex(v1);
			g.addVertex(v2);
			g.addEdge(v1, v2);
		}
		return g;
		
	}

}
