package mvca;

/**
 *
 * @author humbe
 */
public class BuscaProfundidade implements Algoritmo {

    private Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
    private VerticeBuscaProfundidade s;

    public BuscaProfundidade(Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g, VerticeBuscaProfundidade verticeInicial) {
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
        while (g.getVertices().hasNext()) {
            u = g.getVertices().next();
            u.setCor(new CorVertice(Cor.Branco));
            u.setPai(null);

        }
        tempo = 0;
        while (g.getVertices().hasNext()) {
            if (recomecou == true && r > 0) {
                return true;
            }
            if (g.getVertices().next().getCor().getCor() == Cor.Branco) {
                r = 1;
                u = g.getVertices().next();
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
        while (g.getVerticesAdjacentes(u).hasNext()) {
            VerticeBuscaProfundidade v = g.getVerticesAdjacentes(u).next();
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
