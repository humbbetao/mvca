/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca;

/**
 *
 * @author humbe
 * @param <V>
 * @param <R>
 */
public class VerticeRotulo<V extends Vertice, R extends Rotulo > {
    private V v;
    private R r;

    public VerticeRotulo() {
    }

    public VerticeRotulo(V v, R r) {
        this.v = v;
        this.r = r;
    }

    public Vertice getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

    public Rotulo getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }
    
}
