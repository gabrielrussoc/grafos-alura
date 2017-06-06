/**
 * Grafo representado com matriz de adjacência
 *
 * Vantagens dessa representação:
 * 	- Simples
 * 	- Fácil evitar arestas paralelas
 * 	- Fácil remover arestas
 * 	- Boa para grafos densos (ex: grafo com 10 vértices e 80 arestas)
 *
 * Desvantagens:
 *  - Ruim para grafos esparsos (ex: um grafo com 2 arestas e 100 vértices)
 *
 */
public class Grafo {
	private final int V;
	private int A;
	private boolean adj[][];
	private int g[];
	
	public Grafo(int V) {
		this.V = V;
		this.A = 0;
		this.adj = new boolean[V][V];
		this.g = new int[V]; /* Grau do vertice := numero de arestas que incide no vertice */
	}
	
	public void adicionaAresta(int u, int v) {
		if(!adj[u][v]) {
			A++;
			/* Veja que se permitissemos arestas paralelas, temos um impacto imediato nos graus.
			* O mesmo vale para laços (self loops). */
			g[u]++;
			g[v]++;
			adj[u][v] = adj[v][u] = true;
		}
	}
	
	public void removeAresta(int u, int v) {
		if(adj[u][v]) {
			A--;
			g[u]--;
			g[v]--;
			adj[u][v] = adj[v][u] = false;
		}
	}

	public int getGrau(int u) {
		return g[u];
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
		Grafo G = new Grafo(3);
		G.adicionaAresta(0, 1);
		G.adicionaAresta(0, 2);
		G.adicionaAresta(1, 2);
		G.removeAresta(0, 1);
		
		System.out.println(G);
	}
}
