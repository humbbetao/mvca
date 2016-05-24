package mvca;

public class Aresta<U extends Vertice, V extends Vertice> {

    private U v1 = null;
    private V v2 = null;
    private Rotulo l = null;

    public Aresta(U v1, V v2, Rotulo l) {
        this.v1 = v1;
        this.v2 = v2;
        this.l = l;
    }

    public Aresta(U v1, V v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Vertice getVertice1() {
        return v1;
    }

    public void setVertice1(U v1) {
        this.v1 = v1;
    }

    public Vertice getVertice2() {
        return v2;
    }

    public void setVertice2(V v2) {
        this.v2 = v2;
    }

    public Rotulo getL() {
        return l;
    }

    public void setL(Rotulo l) {
        this.l = l;
    }

}
