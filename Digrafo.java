/**
 * Digrafo representado com matriz de adjacência
 * Essencialmente igual a um grafo, mas agora as arestas tem direções.
 * Uma aresta direciona também é chamada de arco.
 * Alguns autores definem pelo outro lado: uma aresta é um par de arcos anti-simétricos (um vai outro volta).
 * Pode ser útil pensar que um grafo é um tipo especial de digrafo.
 */
public class Digrafo {
	private final int V;
	private int A;
	private boolean adj[][];

	/* Agora temos grau de entrada e saída. O conceito só de grau não faz mais sentido */
	private int gIn[], gOut[];

	public Digrafo(int V) {
		this.V = V;
		this.A = 0;
		this.adj = new boolean[V][V];
		this.gIn = new int[V];
		this.gOut = new int[V];
	}
	
	public void adicionaArco(int u, int v) {
		if(!adj[u][v]) {
			A++;
			gOut[u]++;
			gIn[v]++;
			adj[u][v] = true; /* Veja que não fazemos nas duas direções */
		}
	}
	
	public void removeArco(int u, int v) {
		if(adj[u][v]) {
			A--;
			gOut[u]--;
			gIn[v]--;
			adj[u][v] = false; /* Veja que não fazemos nas duas direções */
		}
	}

	public int getGrauEntrada(int u) {
		return gIn[u];
	}

	public int getGrauSaida(int u) {
		return gOut[u];
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Numero de vértices: " + V + "\n");
		stringBuilder.append("Numero de arestas: " + A + "\n");
		for(int u = 0; u < V; u++) {
			stringBuilder.append(u + ":");
			for(int v = 0; v < V; v++) {
				if(adj[u][v]) {
					stringBuilder.append(" " + v);
				}
			}
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		Digrafo G = new Digrafo(3);
		G.adicionaArco(0, 1);
		G.adicionaArco(0, 2);
		G.adicionaArco(1, 2);
		G.removeArco(0, 1);
		
		System.out.println(G);
	}
}
