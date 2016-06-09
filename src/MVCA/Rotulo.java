/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCA;

/**
 *
 * @author humbe
 */
class Rotulo {

    private String l = null;

    Rotulo(String l) {
        this.l = new String(l);
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "Rotulo{" + "l=" + l + '}';
    }

}
