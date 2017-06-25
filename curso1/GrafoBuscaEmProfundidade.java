package curso1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrafoBuscaEmProfundidade {
    private final int V;
    private int A;
    private boolean adj[][];

    public GrafoBuscaEmProfundidade(int V) {
        this.V = V;
        this.A = 0;
        this.adj = new boolean[V][V];
    }

    public void adicionaAresta(int u, int v) {
        if(!adj[u][v]) {
            A++;
            adj[u][v] = adj[v][u] = true;
        }
    }

    public void removeAresta(int u, int v) {
        if(adj[u][v]) {
            A--;
            adj[u][v] = adj[v][u] = false;
        }
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

    /**
     * Primeira tentativa de decidir se existe um caminho entre dois vértices.
     * Só funciona se u == v ou se u é adjacente a v.
     * Em qualquer outro caso, temos um loop infinito.
     */
    public boolean temCaminho_v1(int s, int t) {
        if(s == t) return true;
        for(int w = 0; w < V; w++) {
            if(adj[s][w] && temCaminho_v1(w, t))
                return true;
        }
        return false;
    }

    /**
     * Segunda tentativa de decidir se existe um caminho entre dois vértices.
     * Só funciona se o grafo não tem ciclos (árvores) pois estamos guardando
     * apenas o vértice anterior.
     */
    public boolean temCaminho_v2(int u, int v) {
        return temCaminho_v2(u, v, -1);
    }

    private boolean temCaminho_v2(int u, int v, int ant) {
        if(u == v) return true;
        for(int w = 0; w < V; w++) {
            if(w != ant && adj[u][w] && temCaminho_v2(w, v, u))
                return true;
        }
        return false;
    }

    /**
     * Terceira tentativa de decidir se existe um caminho entre dois vértices.
     * Funciona em todos os casos, mas é importante observar que estamos gastando mais memória.
     */
    public boolean temCaminho_v3(int u, int v) {
        boolean[] vis = new boolean[V];
        temCaminho_v3(u, vis);
        return vis[v];
    }

    /* Esse aqui seria o código final da Busca em Profundidade. Ela tem diversos detalhes complexos para um iniciante.
     * Por ora, é importante falar de complexidade.
     *
     * A complexidade desse método é O(V^2) pois estamos trabalhando com matrizes de adjacência.
     * Num grafo esparso, com 1 milhão de vértices e 5 milhões de arestas, esse método é inviável.
     *
     * Na representação de listas, a complexidade fica O(V+A). Apesar de A poder ser tão grande quanto V^2 em grafos densos,
     * no exemplo esparso a diferença é gritante. Um jeito de pensar é que cada vértice é visitado apenas uma vez e nós olhamos
     * para cada aresta apenas duas vezes. */
    private void temCaminho_v3(int u, boolean[] vis) {
        vis[u] = true;
        for(int v = 0; v < V; v++) {
            if(adj[u][v] && !vis[v])
                temCaminho_v3(v, vis);
        }
        /* Versão para listas:
         * vis[u] = true;
         * for(int v : adj.get(u)) {
         *     if(!vis[v]) {
         *         temCaminho_v3(v, vis);
         *     }
         * }
         * */

    }


    /**
     * Construção do caminho. A ideia é guardar o vértice que "descobriu" cada vértice na busca.
     * IMPORTANTE: O tamanho do caminho é medido pelo numero de ARESTAS.
     * IMPORTANTE: Um caminho NAO repete vértices nem arestas.
     */
    public List<Integer> getCaminho(int s, int t) {
        int ant[] = new int[V];
        for(int w = 0; w < V; w++) {
            ant[w] = -1;
        }
        /* Aqui temos um truque: a origem se precede. */
        ant[s] = s;

        /* Fazemos a busca para descobrir TODOS os caminhos a partir de u. Poderia ser otimizado para parar quando
           o destino é encontrado, mas o código fica mais complicado.
         */
        getCaminho(s, ant);

        /* Se o vértice destino não foi descoberto por ninguém, não temos um caminho
           e portanto devolvemos uma lista vazia.
         */
        if(ant[t] == -1) return new ArrayList<>();

        /* Vamos de fato construir o caminho. Veja que estamos construindo de trás para frente.
            Podemos utilizar uma pilha ou apenas inverter a lista depois.
         */
        List<Integer> caminho = new ArrayList<>();
        int w = t;
        while(w != ant[w]) {
            caminho.add(w);
            w = ant[w];
        }
        caminho.add(s); /* IMPORTANTE! Não podemos esquecer de adicionar a origem */
        Collections.reverse(caminho);
        return caminho;
    }

    private void getCaminho(int u, int[] ant) {
        for(int v = 0; v < V; v++) {
            if(adj[u][v] && ant[v] == -1) {
                ant[v] = u;
                getCaminho(v, ant);
            }
        }
    }


    public static void main(String[] args) {
        GrafoBuscaEmProfundidade G = new GrafoBuscaEmProfundidade(5);
        G.adicionaAresta(0, 1);
        G.adicionaAresta(1, 2);
        G.adicionaAresta(1, 3);
        G.adicionaAresta(1, 4);

        System.out.println(G.temCaminho_v1(0, 1)); //Funciona porque são adjacentes
        System.out.println(G.temCaminho_v2(0, 3)); //Funciona pois não tem circuito

        G.adicionaAresta(4, 2); //adiciona ciclo
        System.out.println(G.temCaminho_v3(0, 4)); //Funciona sempre

        System.out.println(G.getCaminho(0, 4)); //Veja que nesse caso o caminho é válido mas não mínimo
    }
}
