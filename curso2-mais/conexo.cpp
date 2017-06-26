#include <iostream>
#include <vector>
using namespace std;
using graph = vector<vector<int>>;

/* Dado um grafo, decide se ele eh conexo.
 *
 * Um grafo eh dito conexo se existe um caminho entre quaisquer par de vertices.
 * */ 

/* declaracao */
void dfs(int u, graph &G, bool *vis);


/* A ideia eh bem simples: fazer uma busca em profundidade a partir de um vertice qualquer
 * e ver se todos os vertices sao visitados.
 *
 * A seguinte observacao garante a corretude do algoritmo:
 * Um grafo eh conexo se e so se existe um caminho do vertice 0 para todos os vertices.
 *
 * Prova:
 * (=>) Se o grafo eh conexo, entao existe um caminho do vertice 0 para todos por definicao
 * (<=) Se existe um caminho do vertice 0 para dois vertices, digamos u e v, entao o caminho
 * u, ..., 0, ..., v eh um caminho entre os vertices u e v.
 * */
bool eh_conexo(graph &G) {
    int n = G.size();
    bool vis[n];
    /* inicialmente ninguem foi visitado */
    for(int u = 0; u < n; u++) vis[u] = false;
    dfs(0, G, vis);
    for(int u = 0; u < n; u++) 
        if(!vis[u]) 
            return false;
    return true;
}

/* Busca em profundidade simples. */
void dfs(int u, graph &G, bool *vis) {
    vis[u] = true;
    for(int v : G[u])
        if(!vis[v])
            dfs(v, G, vis);
}

void adiciona_aresta(graph &G, int u, int v) {
    G[u].push_back(v);
    G[v].push_back(u);
}

int main() {

    /* Grafo completo de 4 vertices eh conexo */
    graph G(4);
    for(int u = 0; u < 4; u++)
        for(int v = 0; v < 4; v++) 
            G[u].push_back(v);
    
    cout << (eh_conexo(G) ? "Conexo" : "Desconexo") << endl;

    /* Grafo desconexo
     * 0 -- 2
     *  
     * 1 -- 3 */
    G = graph(4);
    adiciona_aresta(G, 0, 2);
    adiciona_aresta(G, 1, 3);
    
    cout << (eh_conexo(G) ? "Conexo" : "Desconexo") << endl;
}

