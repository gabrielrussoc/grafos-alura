# Grafos

Material para um curso de grafos da Alura.

## Curso 1

Para um primeiro curso, a representação de um grafo e a busca em largura
 e profundidade já parecem ser bastante material. Na minha opinião, pode se pensar em incluir
 mais aplicações diretas desses algoritmos, mas não mais que isso.
 
 Um conhecimento de estruturas de dados, algoritmos e complexidade é 

### Ordem sugerida:
- **Classe Grafo**: conceitos básicos de grafos, como vértices, 
arestas e grau de vértices. Usa a respresentação de matriz de adjacência.

- **Classe Digrafo**: conceitos básicos de digrafos. A única diferença é
que arestas agora tem direção e temos graus de entrada e saída. 

- **Classe GrafoLista**: outra representação para grafos.

- **Classe GrafoBuscaEmProfundidade**: apresenta a busca em profundidade
e uma aplicação simples: a existência de caminho entre vértices.

- **Classe GrafoBuscaEmLargura**: apresenta a busca em largura e uma
aplicação simples: o caminho mais curto entre vértices.

*Todos os códigos têm muitos comentários sobre os algoritmos e representações*.

## Curso 2

O segundo curso já assume que o aluno conhece os conceitos básicos de grafos e
entende bem as buscas (principalmente em profundidade).

É até curioso notar que inicialmente a busca em largura parece muito mais poderosa
por trabalhar com distâncias e utilizar estruturas de dados. Entretanto, a maioria
dos algoritmos avançados utilizam busca em profundidade.

Estou usando C++ para esses códigos pois tenho mais fluência. Não deve ser difícil converter
para Java. Estou usando uma representação super simplória (uma lista de listas) para o grafo.

###Ordem Surgerida

- **conexo.cpp**: verifica se um grafo eh conexo.

- **bipartido.cpp**: verifica se um grafo eh bipartido.

- **arvore.cpp**: verifica se um grafo eh uma arvore.

- **dijkstra.cpp**: algoritmo de Dijkstra (caminho minimo em grafos com pesos nas arestas) 

*Todos os códigos tem muitos comentários sobre os algoritmos*

