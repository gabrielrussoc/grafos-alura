#include <iostream>
#include <vector>
using namespace std;
using graph = vector<vector<int>>;

/* Dado um grafo, decide se ele eh bipartido.
 * Um grafo eh dito bipartido se pode ser particionado em dois
 * conjuntos independentes nao-vazios de vertices 
 *
 * Em outras palavras, um grafo eh bipartido se podemos pintar todos os vertices usando
 * apenas duas cores, onde dois vertices adjacentes nao compartilham a mesma cor. 
 *
 * Eh possivel provar que um grafo eh bipartido se e so se nao contem um circuito impar.
 * */ 

/* apenas declaracao para organizar melhor */
bool pinta(int u, graph &G, int *color, int k);

/* A funcao decide se um grafo G eh bipartido. Estamos supondo que G eh conexo por simplicidade. Caso nao seja, precisamos
 * checar se cada componente eh bipartida.
 * */
bool eh_bipartido(graph &G) {
    int n = G.size();
    int color[n];
    /* Inicialmente todo vertice eh incolor */
    for(int u = 0; u < n; u++) color[u] = -1;
    return pinta(0, G, color, 0);
}

/* O algoritmo eh bem simples: pintamos um vertice com a cor k e todos os vizinhos com a cor !k.
 * Se no processo algum vizinho tem a mesma cor, a funcao devolve falso.
 * Aplicacao direta de busca em profundidade. 
 *
 * Nao eh dificil perceber que se a funcao devolve true, entao color[] tem uma bicoloracao.
 * O problema eh quando ela devolve falso. Por que o algoritmo funciona? Eh possivel apelar para intuicao,
 * mas a prova eh um tanto quanto sofisticada. Deixo o link do professor Paulo Feofiloff sobre o assunto:
 * https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/bipartite.html */
bool pinta(int u, graph &G, int *color, int k) {
    color[u] = k;
    for(int v : G[u]) {
        if(color[v] == color[u]) return false;
        /* Importante chamar so para os vizinhos nao-coloridos! */
        if(color[v] == -1) {
            if(!pinta(v, G, color, !k)) return false;
        }
    }
    return true;
}

void adiciona_aresta(graph &G, int u, int v) {
    G[u].push_back(v);
    G[v].push_back(u);
}

int main() {

    /* Grafo completo de 4 vertices nao eh bipartido */
    graph G(4);
    for(int u = 0; u < 4; u++)
        for(int v = 0; v < 4; v++) 
            G[u].push_back(v);
    
    cout << (eh_bipartido(G) ? "Bipartido" : "Nao eh bipartido") << endl;

    /* Grafo bipartido
     * 0 -- 2
     *    /
     *   /
     * 1 -- 3 */
    G = graph(4);
    adiciona_aresta(G, 0, 2);
    adiciona_aresta(G, 1, 2);
    adiciona_aresta(G, 1, 3);
    
    cout << (eh_bipartido(G) ? "Bipartido" : "Nao eh bipartido") << endl;
}

