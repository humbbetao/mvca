package mvca;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author humbe
 */
public class BuscaProfundidade implements Algoritmo {

    private GrafoListaAdjacencia g;
    private VerticeBuscaProfundidade s;

    public BuscaProfundidade(GrafoListaAdjacencia g, VerticeBuscaProfundidade verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null) {
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo " + g.toString() + ". "
                    + "Utilize um vértice válido como argumento do construtor da classe " + this.getClass().getName());
        } else {
            this.g = g;
            this.s = verticeInicial;
        }
    }

    //TODO Exercicio 4.1 - Implementar o algoritmo de inicializacao da busca em profundidade
    public boolean inicializaGrafo() {
        int tempo;
        boolean recomecou = false;
        int r = 0;
        VerticeBuscaProfundidade u = new VerticeBuscaProfundidade();
        Iterator<Aresta<Vertice, Vertice>> arestas = g.getArestas();
        while (arestas.hasNext()) {
            Aresta a = arestas.next();
            u = (VerticeBuscaProfundidade) a.getVertice1();
            u.setCor(new CorVertice(Cor.Branco));
            u.setPai(null);

        }
        tempo = 0;
        Iterator<VerticeBuscaProfundidade> vertices = (Iterator<VerticeBuscaProfundidade>) g.getVertices();
        while (vertices.hasNext()) {
            VerticeBuscaProfundidade vertice = vertices.next();
            if (recomecou == true && r > 0) {
                return true;
            }
            if (vertice.getCor().getCor() == Cor.Branco) {
                r = 1;
                u = (VerticeBuscaProfundidade) vertice;
                executar(u, tempo);
            }

        }
        return false;
    }

    //TODO Exercicio 4.2 - Implementar a busca em profundidade
    public void executar(VerticeBuscaProfundidade u, int tempo) {
        tempo++;
        u.setTempoDescoberta(tempo);
        u.setCor(new CorVertice(Cor.Cinza));
        Iterator<Vertice> verticesAdj = g.getVerticesAdjacentes(u);
        while (verticesAdj.hasNext()) {
            VerticeBuscaProfundidade v = (VerticeBuscaProfundidade) verticesAdj.next();
            if (v.getCor().getCor() == Cor.Branco) {
                v.setPai(u);
                executar(u, tempo);
            }
        }
        u.setCor(new CorVertice(Cor.Preto));
        tempo++;
        u.setTempoFinalizacao(tempo);
    }

    //TODO Exercicio 4.3 - Implementar a impressao do grafo
    public void imprimeGrafo(Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g, VerticeBuscaProfundidade s, VerticeBuscaProfundidade v) {

    }

    @Override
    public void executar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
