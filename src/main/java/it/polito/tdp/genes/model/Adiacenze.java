package it.polito.tdp.genes.model;

public class Adiacenze implements Comparable<Adiacenze>{
private Genes g1;
private Genes g2;
private Double peso;
public Adiacenze(Genes g1, Genes g2, Double peso) {
	super();
	this.g1 = g1;
	this.g2 = g2;
	this.peso = peso;
}

public Adiacenze(Genes g1, Double peso) {
	super();
	this.g1 = g1;
	this.peso = peso;
}

public Genes getG1() {
	return g1;
}
public void setG1(Genes g1) {
	this.g1 = g1;
}
public Genes getG2() {
	return g2;
}
public void setG2(Genes g2) {
	this.g2 = g2;
}
public Double getPeso() {
	return peso;
}
public void setPeso(Double peso) {
	this.peso = peso;
}
@Override
public String toString() {
	return this.getG1()+" "+this.getPeso();
}
@Override
public int compareTo(Adiacenze e) {
	
	if(this.getPeso()-e.getPeso()>0)
		return -1;
	else
		return 1;
	
	
}

}
