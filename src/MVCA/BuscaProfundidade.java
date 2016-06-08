package MVCA;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author humbe
 */
public class BuscaProfundidade {

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
        System.out.println("Inicialiazou a busca em Profundidade");
        int tempo;
        boolean recomecou = false;
        int r = 0;
        VerticeBuscaProfundidade u = new VerticeBuscaProfundidade();
        Iterator<Aresta> arestas = g.getArestas();
        while (arestas.hasNext()) {
            Aresta a = (Aresta) arestas.next();
            u = (VerticeBuscaProfundidade) a.getVertice1();
            u.setCor(new CorVertice(Cor.Branco));
            u.setPai(null);

        }
        tempo = 0;
        Iterator<VerticeBuscaProfundidade> vertices = g.getVertices();
        ArrayList<VerticeBuscaProfundidade> vertices2 = new ArrayList<>();
        while (vertices.hasNext()) {
            Vertice v = vertices.next();
            vertices2.add(new VerticeBuscaProfundidade(v.getId()));
        }

        Iterator<VerticeBuscaProfundidade> vertices3 = g.getVertices();
        while (vertices3.hasNext()) {
//        for (VerticeBuscaProfundidade f : vertices2) {
            VerticeBuscaProfundidade v = (VerticeBuscaProfundidade) vertices3.next();
            if (recomecou == true) {
                System.out.println("Recomecou");
                return true;
            } else if (v.getCor().getCor() == Cor.Branco) {
                recomecou = true;
                u = v;
                executar(u, tempo);
            }

        }
        return false;
    }

    //TODO Exercicio 4.2 - Implementar a busca em profundidade
    public void executar(VerticeBuscaProfundidade u, int tempo) {
        System.out.println("Executando o Algoritmo de Profundidade");
        tempo++;
        u.setTempoDescoberta(tempo);
        u.setCor(new CorVertice(Cor.Cinza));
        Iterator<VerticeRotulo> verticesAdj = g.getVerticesAdjacentes(u);
        while (verticesAdj.hasNext()) {
            VerticeRotulo v = verticesAdj.next();
            if (v.getV().getCor().getCor() == Cor.Branco) {
                v.getV().setPai(u);
                executar(u, tempo);
            }
        }
        u.setCor(new CorVertice(Cor.Preto));
        tempo++;
        u.setTempoFinalizacao(tempo);
    }

}
