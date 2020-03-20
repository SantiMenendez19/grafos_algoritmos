package desarrollo;

public class Arista {
	private int peso;
	private int nodoOrigen;
	private int nodoDestino;
	
	public Arista(int nodo1,int nodo2,int peso) {
		nodoOrigen = nodo1;
		nodoDestino = nodo2;
		this.peso = peso;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getNodoOrigen() {
		return nodoOrigen;
	}

	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}

	public int getNodoDestino() {
		return nodoDestino;
	}

	public void setNodoDestino(int nodoDestino) {
		this.nodoDestino = nodoDestino;
	}
	
	
}
