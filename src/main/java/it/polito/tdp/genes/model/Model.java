package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	private GenesDao dao;
	private Graph<Genes,DefaultWeightedEdge>grafo;
	private Map<String,Genes>idMap; 
	
	public Model() {
		dao=new GenesDao ();
		idMap=new HashMap<>();
		this.dao.getAllGenes(idMap);
		
		
		
	}
	
	public void creaGrafo() {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo,this.dao.getVertici(idMap));
		for(Adiacenze a :dao.getArchi(idMap)) {
			if(grafo.containsVertex(a.getG1())&&grafo.containsVertex(a.getG2())) {
				if(a.getG1().getChromosome()==a.getG2().getChromosome()) {
					Graphs.addEdgeWithVertices(grafo, a.getG1() ,a.getG2(),2*a.getPeso());
					
				}
				else {
					Graphs.addEdgeWithVertices(this.grafo, a.getG1(), a.getG2(),a.getPeso());
				}
			}
		}
	}
	public int nVertici() {
		return grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return grafo.edgeSet().size();
	}
	public Graph<Genes, DefaultWeightedEdge> getGrafo(){
		return grafo;
	}
	public List<Genes> getVertici(){
		return new ArrayList<>(this.grafo.vertexSet());
	}
	public List<Adiacenze>getAdiacenti(Genes g){
		
		List<Genes>vicini=Graphs.neighborListOf(grafo, g);
		List<Adiacenze>result=new ArrayList<>();
		for(Genes ge:vicini) {
			result.add(new Adiacenze(ge,this.grafo.getEdgeWeight(this.grafo.getEdge(g, ge))));
		}
		Collections.sort(result);
return result;
	}
}
