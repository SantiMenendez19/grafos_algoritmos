package desarrollo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class GrafoNDP {
	private int cantNodos;
	private int cantAristas;
	private List<Nodo> nodos;
	private List<Arista> aristas;
	private int[][] matrizAdyacencia;

	public GrafoNDP(String arch) throws IOException {
		Scanner sc = new Scanner(new File(arch));
		cantNodos = sc.nextInt();
		cantAristas = sc.nextInt();
		nodos = new ArrayList<Nodo>();
		aristas = new ArrayList<Arista>();
		matrizAdyacencia = new int[cantNodos][cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			nodos.add(new Nodo(i + 1));
		}
		for (int i = 0; i < cantAristas; i++) {
			int nodoO = sc.nextInt();
			int nodoD = sc.nextInt();
			int peso = sc.nextInt();
			nodos.get(nodoO - 1).agregarEnlace(nodoO, nodoD, peso);
			nodos.get(nodoD - 1).agregarEnlace(nodoD, nodoO, peso);
			aristas.add(new Arista(nodoO, nodoD, peso));
		}

		for (Arista a : aristas) {
			matrizAdyacencia[a.getNodoOrigen() - 1][a.getNodoDestino() - 1] = a.getPeso();
			matrizAdyacencia[a.getNodoDestino() - 1][a.getNodoOrigen() - 1] = a.getPeso();
		}

		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				if (matrizAdyacencia[i][j] == 0 && i != j) {
					matrizAdyacencia[i][j] = 999999;
				}
			}
		}
		sc.close();
	}

	public GrafoNDP(int cant, int cantaristas) {
		this.cantNodos = cant;
		this.cantAristas = cantaristas;
		this.nodos = new ArrayList<Nodo>();
		this.aristas = new ArrayList<Arista>();
		for (int i = 0; i < cantNodos; i++) {
			nodos.add(new Nodo(i + 1));
		}
		matrizAdyacencia = new int[cantNodos][cantNodos];
	}

	public void mostrarGrafo() {
		for (Nodo n : nodos) {
			System.out.println(n.getNroNodo());
			for (Arista a : n.getEnlaces()) {
				System.out.println(a.getNodoOrigen() + " " + a.getNodoDestino() + " " + a.getPeso());
			}
		}
	}

	public void mostrarMatriz() {
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				System.out.print(matrizAdyacencia[i][j] + " ");
			}
			System.out.println();
		}
	}

	public GrafoNDP kruscal() {
		GrafoNDP arbol = new GrafoNDP(this.getCantNodos(), this.getCantNodos() - 1);
		int cant = this.getCantNodos() - 1, i = 0;
		int[] referencias = new int[this.getCantNodos()];
		for (int j = 0; j < this.getCantNodos(); j++) {
			referencias[j] = j;
		}
		List<Arista> aristasOrd = this.aristas;
		Collections.sort(aristasOrd, new Comparator<Arista>() {
			@Override
			public int compare(Arista o1, Arista o2) {
				return o1.getPeso() - o2.getPeso();
			}
		});

		arbol.agregarArista(aristasOrd.get(i));
		referencias[aristasOrd.get(i).getNodoOrigen() - 1] = aristasOrd.get(i).getNodoDestino() - 1;
		referencias[aristasOrd.get(i).getNodoDestino() - 1] = aristasOrd.get(i).getNodoDestino() - 1;
		cant--;
		while (cant > 0) {
			i++;
			if (referencias[referencias[aristasOrd.get(i).getNodoOrigen() - 1]] == referencias[aristasOrd.get(i)
					.getNodoDestino() - 1]) {
				System.out.println("cierra ciclo");
			} else {
				arbol.agregarArista(aristasOrd.get(i));
				referencias[aristasOrd.get(i).getNodoOrigen() - 1] = aristasOrd.get(i).getNodoDestino() - 1;
				referencias[aristasOrd.get(i).getNodoDestino() - 1] = aristasOrd.get(i).getNodoDestino() - 1;
				cant--;
			}
		}
		return arbol;
	}

	private void agregarArista(Arista arista) {
		this.aristas.add(arista);
		this.nodos.get(arista.getNodoOrigen() - 1).agregarEnlace(arista.getNodoOrigen(), arista.getNodoDestino(),
				arista.getPeso());
		this.nodos.get(arista.getNodoDestino() - 1).agregarEnlace(arista.getNodoDestino(), arista.getNodoOrigen(),
				arista.getPeso());
		matrizAdyacencia[arista.getNodoOrigen() - 1][arista.getNodoDestino() - 1] = arista.getPeso();
		matrizAdyacencia[arista.getNodoDestino() - 1][arista.getNodoOrigen() - 1] = arista.getPeso();
	}

	private int getCantNodos() {
		return this.cantNodos;
	}

	public static void main(String[] args) throws IOException {
		GrafoNDP g1 = new GrafoNDP("grafo3.in");
//		g1.mostrarGrafo();
//		g1.mostrarMatriz();
		g1.kruscal().mostrarMatriz();;
	}

}
