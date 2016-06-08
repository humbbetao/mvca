package MVCA;

public class Aresta{
        
    private VerticeBuscaProfundidade v1 = null;
    private VerticeBuscaProfundidade v2 = null;
    private Rotulo l = null;

    public Aresta(VerticeBuscaProfundidade v1, VerticeBuscaProfundidade v2, Rotulo l) {
        this.v1 =  v1;
        this.v2 =  v2;
        this.l = l;
    }

    public Aresta(VerticeBuscaProfundidade v1, VerticeBuscaProfundidade v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public VerticeBuscaProfundidade getVertice1() {
        return v1;
    }

    public void setVertice1(VerticeBuscaProfundidade v1) {
        this.v1 = v1;
    }

    public VerticeBuscaProfundidade getVertice2() {
        return v2;
    }

    public void setVertice2(VerticeBuscaProfundidade v2) {
        this.v2 = v2;
    }

    public Rotulo getL() {
        return l;
    }

    public void setL(Rotulo l) {
        this.l = l;
    }

}
