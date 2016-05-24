/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author humbe
 */
public class MVCAAlgorithm {

    GrafoListaAdjacencia arvoreGeradoraDeRotulosMinimos;
    int numeroMaximo = 0;
    String label = null;
    VerticeBuscaProfundidade verticeInicial;
    HashMap<String, Integer> quantoVerticesTem = new HashMap<>();
    int numeroDeVertices;
    GrafoListaAdjacencia g;

    MVCAAlgorithm(GrafoListaAdjacencia g, int numeroDeVertices) {
        this.arvoreGeradoraDeRotulosMinimos = new GrafoListaAdjacencia(g.getVertices());
        this.verticeInicial = new VerticeBuscaProfundidade(g.getVertices().next().getId());
        this.g = g;
    }
//        while this.arvoreGeradoraDeRotulosMinimos.notifyAll()

//                Let H = ( Yc$) be the subgraph of G, which hasn’t
//any edge.
//2. While H is not connected
//3. Find a unused label 1 such that edges with label
//1 cover as many uncovered vertices as possible.
//If there are more than one candidate, select one
//randomly.
//4. Add edges whose labels are 1 into the subgraph
//H.
//5. End (for while).
//6. Find an arbitrary spanning tree of H.
    private void transferirArestas(GrafoListaAdjacencia g, String label, int numeroMaximo, GrafoListaAdjacencia arvoreGeradoraDeRotulosMinimos) {
       Iterator<Aresta<Vertice, Vertice>> arestas = g.getArestas();
        while (arestas.hasNext()) {
            if (arestas.next().getL().getL().equals(label)) {
                Aresta arestaNova = new Aresta(this.arvoreGeradoraDeRotulosMinimos.getVertice(arestas.next().getVertice1().getId()), this.arvoreGeradoraDeRotulosMinimos.getVertice(g.getArestas().next().getVertice1().getId()));
                this.arvoreGeradoraDeRotulosMinimos.adicionaAresta(arestaNova);
            }
        }

    }

    public int contarNumeroDeLabels(int numeroDeVertices) {

        for (int i = 0; i < numeroDeVertices; i++) {
            quantoVerticesTem.put(String.valueOf(i), 0);
        }
        BuscaProfundidade buscaEmProfundidade = new BuscaProfundidade((GrafoListaAdjacencia) arvoreGeradoraDeRotulosMinimos, verticeInicial);
        while (buscaEmProfundidade.inicializaGrafo() != false) {// enquanto nãoe stiver conectado
            Iterator<Aresta<Vertice, Vertice>> arestas = g.getArestas();
            while (arestas.hasNext()) {
                Aresta a = arestas.next();
                int numeroDeRepeticoesDeUmLabel = quantoVerticesTem.get(a.getL().getL());
                quantoVerticesTem.replace(a.getL().getL(), numeroDeRepeticoesDeUmLabel++);
            }
            arestas = g.getArestas();
            while (arestas.hasNext()) {
                 Aresta a = arestas.next();
                if (numeroMaximo < quantoVerticesTem.get(a.getL().getL())) {
                    numeroMaximo = quantoVerticesTem.get(a.getL().getL());
                    label = a.getL().getL();
                }
            }

            transferirArestas(g, label, numeroMaximo,  arvoreGeradoraDeRotulosMinimos);
        }
        int numeroDeLabels = 0;
        ArrayList<Rotulo> a = new ArrayList<>();
        Iterator<Aresta<Vertice, Vertice>> arestas = g.getArestas();
        while (arestas.hasNext()) {
            Aresta aresta = arestas.next();
            if (!a.contains(aresta.getL())) {
                a.add(aresta.getL());
                numeroDeLabels++;
            }
        }
        return numeroDeLabels;
    }

}
