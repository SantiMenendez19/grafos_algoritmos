package desarrollo;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private int nroNodo;
	private List<Arista> enlaces;
	
	public Nodo(int nro) {
		this.nroNodo = nro;
		this.enlaces = new ArrayList<Arista>();
	}

	public int getNroNodo() {
		return nroNodo;
	}

	public void setNroNodo(int nroNodo) {
		this.nroNodo = nroNodo;
	}

	public List<Arista> getEnlaces() {
		return enlaces;
	}

	public void setEnlaces(List<Arista> enlaces) {
		this.enlaces = enlaces;
	}
	
	public void agregarEnlace(int nodo1,int nodo2,int peso) {
		enlaces.add(new Arista(nodo1,nodo2,peso));
	}
	
}
