package curso1;

/**
 * curso1.Grafo representado com matriz de adjacência
 *
 * Vantagens dessa representação:
 * 	- Simples
 * 	- Fácil evitar arestas paralelas e laços (self loops)
 * 	- Fácil remover arestas
 * 	- Boa para grafos densos (ex: grafo com 10 vértices e 80 arestas)
 *
 * Desvantagens:
 *  - Ruim para grafos esparsos (ex: um grafo com 100 vértices e 99 arestas)
 *
 */
public class Grafo {
	private final int V;
	private int A;
	private boolean adj[][];
	private int g[];

	/* O grafo tem V vértices {0, 1, ..., V-1} */
	public Grafo(int V) {
		this.V = V;
		this.A = 0;
		this.adj = new boolean[V][V];
		this.g = new int[V]; /* Grau do vertice := numero de arestas que incide no vertice */
	}
	
	public void adicionaAresta(int u, int v) {
		/* Não vamos permitir arestas paralelas (duas arestas entre o mesmo par de vértices) */
		if(!adj[u][v]) {
			A++;
			/* Aqui pode há um pequeno problema com arestas do tipo (u,u) - self loops ou laços.
			 * A definição de grau diz que o grau é o número de arestas que incide no vértice e estamos
			 * aumentando o grau em +2. Em várias aplicações self loops não são necessários, então sugiro
			 * não permití-los também. */
			g[u]++;
			g[v]++;
			adj[u][v] = adj[v][u] = true;
		}
		/* Pode ser útil retornar um booleano para dizer se a aresta foi inserida ou não */
	}
	
	public void removeAresta(int u, int v) {
		/* Esse método é completamente análogo ao anterior */
		if(adj[u][v]) {
			A--;
			g[u]--;
			g[v]--;
			adj[u][v] = adj[v][u] = false;
		}
	}

	/* Grau do vertice := numero de arestas que incide no vertice */
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
