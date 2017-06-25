package curso1; /**
 * curso1.Grafo representado com matriz de adjacência
 *
 * Vantagens dessa representação:
 * 	- Ótima para grafos esparsos pois gasta quantidade de espaço proporcional
 * 	ao número de arestas
 *
 * Desvantagens:
 *  - Complexa (usa uma lista de listas)
 *  - Difícil evitar arestas paralelas (custa caro decidir se uma aresta já existe)
 *  - Difícil evitar laços
 *  - Difícil remover arestas
 *
 *  Apesar de parecer que só tem desvantagens, vale lembrar que no mundo real a maioria dos grafos
 *  é esparso. Por exemplo, eu montei um grafo da Wikipédia que tem cerca de 1 milhão de vértices
 *  e 5 milhões de arestas.
 *  Se o grafo da Wikipédia fosse denso, ele teria algo na ordem de 1 trilhão de arestas!
 */

import java.util.ArrayList;
import java.util.List;

public class GrafoLista {
	private final int V;
	private int A;
	private int g[];
	private List<List<Integer>> adj;
	
	public GrafoLista(int V) {
		this.V = V;
		/* É bom utilizar o construtor do ArrayList que tem a capacidade inicial, pois sabemos
		exatamente qual é o tamanho da lista: o número de vértices. */
		this.adj = new ArrayList<>(V);
		for(int i = 0; i < V; i++) {
			adj.add(new ArrayList<>());
		}
	}
	
	public void adicionaAresta(int u, int v) {
		/* Esse método é muito caro!
		 * Ele varre a lista inteiro de um vértice.
		 * Um exemplo que explicita isso é:
		 *
		 * adicionaAresta(0, 1);
		 * adicionaAresta(0, 2);
		 * ...
		 * adicionaAresta(0, n);
		 *
		 * Ele varre uma lista de tamanho 1, depois de 2, depois de 3...
		 * Isso dá um custo de 1 + 2 + 3 + ... + n =~ n^2 !
		 *
		 * Se for realmente necessário garantir a ausência de arestas paralelas, pode se usar
		 * outra estrura de dados, como um TreeSet, que tem operações log n e não permite repetições */
		if(!adj.get(u).contains(v)) {
			A++;
			adj.get(u).add(v);
			adj.get(v).add(u);
		}
	}

	public void removeAresta(int u, int v) {
		/* Esse método é mais caro ainda! Além de varrer a lista, remover também é custoso. */
		if(adj.get(u).contains(v)) {
			A--;
			/* Aqui foi necessário wrappar os vértice no objeto Integer pois existe um método
			 * que recebe um índice para remoção. */
			adj.get(u).remove(new Integer(v));
			adj.get(v).remove(new Integer(u));
		}

	}

	/* VEJA A MODIFICACAO NO MEIO DO TO STRING EM RELACAO A MATRIZ */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Numero de vértices: " + V + "\n");
		stringBuilder.append("Numero de arestas: " + A + "\n");
		for(int u = 0; u < V; u++) {
			stringBuilder.append(u + ":");
			/* Fica mais fácil e barato iterar na lista de adjacência nessa representação */
			for(int v : adj.get(u)) {
				stringBuilder.append(" " + v);
			}
			stringBuilder.append('\n');
		}
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		GrafoLista G = new GrafoLista(3);
		G.adicionaAresta(1, 2);
		G.adicionaAresta(0, 1);
		G.adicionaAresta(0, 2);
		G.removeAresta(0, 2);
		G.removeAresta(0, 1);

		System.out.println(G);
	}
}
