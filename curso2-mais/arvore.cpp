#include <iostream>
#include <vector>
using namespace std;
using graph = vector<vector<int>>;

/* Dado um grafo, decide se ele eh uma arvore.
 *
 * Um grafo eh dito simples se nao tem arestas paralelas nem laços (self loops).
 *
 * Um grafo eh chamado de arvore se eh simples, conexo e sem ciclos.
 * Existe MUITAS propriedades em arvores, mas as principais sao:
 *
 * - Uma arvore tem N-1 arestas (sendo N o numero de vertices).
 * - O caminho entre dois vertices numa arvore eh unico.
 * - Nao tem ciclos (vem direto da definicao, mas pode vir como propriedade se definirmos de outras formas).
 *   - Essa propriedade garante que toda arvore eh um grafo bipartido.
 * - Eh conexa (tambem vem da definicao, mas pode vir como propriedade se definirmos de outras formas).
 * 
 * Arvore sao muito bem representadas com listas de adjacencias pois tem pouquissimas arestas.
 * */ 

/* declaracao */
bool dfs(int u, int p, graph &G, bool *vis);

bool eh_arvore(graph &G) {
    int n = G.size();
    bool vis[n];

    /* inicialmente ninguem foi visitado */
    for(int u = 0; u < n; u++) 
        vis[u] = false;

    /* Temos um truque: passamos o anterior do primeiro vertice como -1 */
    if(!dfs(0, -1, G, vis))
        return false;

    /* Nao esquecer de verificar se o grafo eh conexo! */
    for(int u = 0; u < n; u++) 
        if(!vis[u]) 
            return false;

    return true;
}

/* Vamos usar a busca em profundidade para procurar um circuito. 
 * Para tanto, vamos guardar o vertice que nos descobriu. 
 * Este vertice eh o unico que ja pode ter sido visitado.
 *
 * Para entender bem o porque disso, eh necessario entender muito bem qual eh
 * a saida da busca em profundidade: a chamada arvore de busca em profundidade.
 *
 * O professor Paulo Feofiloff explica muito bem o algoritmo, mas ele eh um tanto quanto sofisticado.
 * https://www.ime.usp.br/~pf/algoritmos_para_grafos/aulas/dfs.html
 * */
bool dfs(int u, int p, graph &G, bool *vis) {
    vis[u] = true;
    for(int v : G[u]) {
        if(!vis[v]) { 
            if(!dfs(v, u, G, vis))
                return false;
        } else if (v != p) {
            return false;
        }
    }
    return true;
}

void adiciona_aresta(graph &G, int u, int v) {
    G[u].push_back(v);
    G[v].push_back(u);
}

int main() {

    /* Grafo completo de 4 vertices nao eh arvore */
    graph G(4);
    for(int u = 0; u < 4; u++)
        for(int v = 0; v < 4; v++) 
            G[u].push_back(v);
    
    cout << (eh_arvore(G) ? "Arvore" : "Nao eh arvore") << endl;

    /* Arvore
     *        3
     *        |
     *    1 - 0 - 2
     * */
    G = graph(4);
    adiciona_aresta(G, 0, 1);
    adiciona_aresta(G, 0, 2);
    adiciona_aresta(G, 0, 3);

    cout << (eh_arvore(G) ? "Arvore" : "Nao eh arvore") << endl;

    /* Grafo desconexo nao eh arvore ! 
     *
     * 0 -- 1
     *
     * 2 -- 3
     * */
    G = graph(4);
    adiciona_aresta(G, 0, 1);
    adiciona_aresta(G, 2, 3);

    cout << (eh_arvore(G) ? "Arvore" : "Nao eh arvore") << endl;
}

