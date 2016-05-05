package mvca;

public class GrafoFactory {

    public static Grafo constroiGrafo(Representacao r) {
        switch (r) {
            case LISTA_ADJACENCIA:
                return new GrafoListaAdjacencia();
            case MATRIZ_ADJACENCIA:
                return null;
//				return new GrafoMatrizAdjacencia();
        }
        return null;
    }
}
