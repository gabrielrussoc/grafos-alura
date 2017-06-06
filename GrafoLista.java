import java.util.ArrayList;
import java.util.List;

public class GrafoLista {
	private final int V;
	private List<List<Integer>> adj;
	
	public GrafoLista(int numeroDeVertices) {
		this.V = numeroDeVertices;
		this.adj = new ArrayList<>();
		for(int i = 0; i < numeroDeVertices; i++) {
			adj.add(new ArrayList<>());
		}
	}
	
	public void adicionaAresta(int u, int v) {
		adj.get(u).add(v);
		adj.get(v).add(u);
	}
	
	public boolean temCaminho(int s, int t) {
		boolean vis[] = new boolean[V];
		buscaEmProfundidade(s, vis);
		return vis[t];
	}
	
	private void buscaEmProfundidade(int u, boolean[] vis) {
		vis[u] = true;
		for(int v : adj.get(u)) {
			if(!vis[v]) {
				buscaEmProfundidade(v, vis);
			}
		}
	}
	
	public static void main(String[] args) {
		Grafo G = new Grafo(3);
		G.adicionaAresta(0, 1);
		G.adicionaAresta(0, 2);
		G.adicionaAresta(1, 2);
		
		System.out.println(G);
	}
}
