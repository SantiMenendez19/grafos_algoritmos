package desarrollo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class GrafoPD {
	private int cantNodos;
	private int cantAristas;
	private List<Nodo> nodos;
	private List<Arista> aristas;
	private int[][] matrizAdyacencia;

	public GrafoPD(String arch) throws IOException {
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
			aristas.add(new Arista(nodoO, nodoD, peso));
		}

		for (Arista a : aristas) {
			matrizAdyacencia[a.getNodoOrigen() - 1][a.getNodoDestino() - 1] = a.getPeso();
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

	public int[] dijkstra(int nodoInicio, int nodoFinal) {
		PriorityQueue<Integer> colaPrioridad = new PriorityQueue<Integer>();
		int[] distancias = new int[cantNodos];
		int[] padre = new int[cantNodos];
		boolean[] visto = new boolean[cantNodos];
		for (int i = 0; i < cantNodos; i++) {
			distancias[i] = Integer.MAX_VALUE;
			padre[i] = -1;
			visto[i] = false;
		}
		distancias[nodoInicio - 1] = 0;
		colaPrioridad.add(nodoInicio - 1);
		while (!colaPrioridad.isEmpty()) {
			int u = colaPrioridad.poll();
			visto[u] = true;
			for (int i = 0; i < cantNodos; i++) {
				if (matrizAdyacencia[u][i] != 0) {
					if (distancias[i] > distancias[u] + matrizAdyacencia[u][i]) {
						distancias[i] = distancias[u] + matrizAdyacencia[u][i];
						padre[i] = u;
						colaPrioridad.add(i);
					}
				}
			}
		}
		verCaminoCorto(padre, nodoFinal - 1);
		return distancias;
//		for(int i = 0 ; i< cantNodos ; i++) {
//			System.out.print(distancias[i] + " ");
//		}
//		for(int i = 0 ; i< cantNodos ; i++) {
//			System.out.print(padre[i] + " ");
//		}
	}

	public void verCaminoCorto(int[] destino, int nodoFinal) {
		if (destino[nodoFinal] != -1) {
			verCaminoCorto(destino, destino[nodoFinal]);
		}
		System.out.print((nodoFinal + 1) + " ");
	}

	public void floydWarshall() {
		int[][] mat = this.matrizAdyacencia;
		boolean[][] caminos = new boolean[this.cantNodos][this.cantNodos];
		
		for(int i = 0 ; i < this.cantNodos ; i++) {
			for(int j = 0 ; j < this.cantNodos ; j++) {
				if(mat[i][j] != 999999 && mat[i][j] != 0) {
					caminos[i][j] = true;
				}
			}
		}

		for (int i = 0; i < this.cantNodos; i++) {
			for (int j = 0; j < this.cantNodos; j++) {
				for (int k = 0; k < this.cantNodos; k++) {
					int aux1 = mat[i][j];
					int aux2 = mat[i][k] + mat[k][j];
					int res = Math.min(aux1, aux2);
					mat[i][j] = res;
					caminos[i][j] = (caminos[i][j] || (caminos[i][k] && caminos[k][j]));
				}
			}
		}

		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				System.out.print(caminos[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		GrafoPD g1 = new GrafoPD("grafo2.in");
//		g1.mostrarGrafo();
		g1.mostrarMatriz();
//		g1.dijkstra(1, 2);
		g1.floydWarshall();
	}
}
