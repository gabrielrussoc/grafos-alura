package curso1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GrafoBuscaEmLargura {
    private final int INFINITO;
    private final int V;
    private int A;
    private boolean adj[][];

    public GrafoBuscaEmLargura(int V) {
        this.V = V;
        this.A = 0;
        this.adj = new boolean[V][V];
        /* Usaremos infinito para marcar um vértice ainda não alcaçando.
        Podemos utilizar um numero gigantesco como INT_MAX mas não há necessidade
        pois nenhuma distancia é superior a V - 1.
        Em outras palavras, o maior caminho >MAIS CURTO< que qualquer grafo de V vértices pode ter
         tem tamanho no máximo V - 1 */
        this.INFINITO = V;
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

    /* Para conseguir o caminho mais curto, vamos utilizar busca em profundidade.
    É um algoritmo intuitivo, mas formalmente é bem mais complicado de provar.
    Um vetor de visitados é suficiente para o algoritmo, mas em geral queremos calcular distancias.
     */
    public int getTamanhoDoCaminhoMaisCurto(int s, int t) {
        int[] dist = new int[V];
        for(int w = 0; w < V; w++) {
            dist[w] = INFINITO;
        }
        buscaEmLargura(s, dist);

        /* Aqui vamos retornar infinito (= V) caso não exista caminho. Poderia ser -1 ou
         outro valor inválido. */
        return dist[t];
    }

    /* A complexidade desse método também depende da representação. O(V^2) para matrizes e O(V+A) para listas. */
    private void buscaEmLargura(int s, int[] dist) {
        Queue<Integer> fila = new LinkedList<>();
        fila.add(s);
        dist[s] = 0;

        while(!fila.isEmpty()) {
            int u = fila.remove();
            for(int v = 0; v < V; v++) {
                /* Aqui temos um invariante que não é trivial.
                Quando um vértice é visto pela primeira vez (descoberto),
                já temos sua distancia mínima. É impossível descobrir um vértice por um caminho subótimo.
                 */
                if(adj[u][v] && dist[v] == INFINITO) {
                    fila.add(v);
                    dist[v] = dist[u] + 1;
                }
            }
        }
    }

    /* Semelhante a busca em profundidade, vamos guardar o vertice que descobriu (anterior) a cada vertice
     * A unica diferenca é que usamos busca em largura para determinar os anteriores ótimos.  */
    /* Note que o caminho mais curto não é único. Obter todos os caminhos é difícil! */
    public List<Integer> getMenorCaminho(int s, int t) {
        int ant[] = new int[V];
        for(int w = 0; w < V; w++) {
            ant[w] = -1;
        }
        /* Aqui temos um truque: a origem se precede. */
        ant[s] = s;

        /* Fazemos a busca para descobrir os caminhos mais curtos a partir de u.
        Aqui podemos otimizar facilmente para parar quando o destino for atingido.
         */
        buscaEmLarguraComRastro(s, ant);

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

    private void buscaEmLarguraComRastro(int s, int[] ant) {
        Queue<Integer> fila = new LinkedList<>();
        fila.add(s);
        ant[s] = s;

        while(!fila.isEmpty()) {
            int u = fila.remove();
            /* Aqui poderiamos fazer
                if(u == destino) break;
                pois temos certeza de que o destino ja tem o caminho minimo calculo
             */
            for(int v = 0; v < V; v++) {
                if(adj[u][v] && ant[v] == -1) {
                    fila.add(v);
                    ant[v] = u;
                }
            }
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

    public static void main(String[] args) {
        GrafoBuscaEmLargura G = new GrafoBuscaEmLargura(6);
        G.adicionaAresta(0, 1);
        G.adicionaAresta(1, 2);
        G.adicionaAresta(1, 3);
        G.adicionaAresta(2, 4);
        G.adicionaAresta(3, 4);
        G.adicionaAresta(4, 5);

        /*       2
                / \
          0 -- 1   4 -- 5
                \ /
                 3
         */

        System.out.println(G.getTamanhoDoCaminhoMaisCurto(0, 5));
        System.out.println(G.getMenorCaminho(0, 5)); /* Não é único! */

        System.out.println(G);
    }
}
