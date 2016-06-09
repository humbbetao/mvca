/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import static java.util.Collections.max;
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
    ArrayList<Integer> quantosVerticesTem = new ArrayList<>();

    int numeroDeVertices;
    GrafoListaAdjacencia g;

    MVCAAlgorithm(GrafoListaAdjacencia g, int numeroDeVertices) {
        this.arvoreGeradoraDeRotulosMinimos = new GrafoListaAdjacencia(g.getVertices());
        this.verticeInicial = new VerticeBuscaProfundidade(g.getVertices().next().getId());
        this.g = g;
        System.out.println(g.getArestas().next());
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
    private void transferirArestas(String label, int numeroMaximo, GrafoListaAdjacencia arvoreGeradoraDeRotulosMinimos) {
        Iterator<Aresta> arestas = g.getArestas();
        int i = 0;
        while (arestas.hasNext()) {
            i++;
//            System.out.println("Passsou");
            Aresta antiga = arestas.next();
            if (antiga.getL() != null) {
//                System.out.println(i);
                if (antiga.getL().getL().equals(label)) {
                    Aresta arestaNova = new Aresta(this.arvoreGeradoraDeRotulosMinimos.getVertice(antiga.getVertice1().getId()), this.arvoreGeradoraDeRotulosMinimos.getVertice(antiga.getVertice1().getId()));
                    this.arvoreGeradoraDeRotulosMinimos.adicionaAresta(arestaNova);
//                    System.out.println("Eh Igual");
                }
            }
        }
//        System.out.println(i);

    }

    public int contarNumeroDeLabels(int numeroDeVertices, int numeroDeLabels) {
        System.out.println("Comecou a Executar o Algoritmo");
        for (int i = 0; i < numeroDeLabels; i++) {
            quantosVerticesTem.add(0);
        }
        for (int i = 0; i < numeroDeVertices; i++) {
            quantoVerticesTem.put(String.valueOf(i), 0);
        }
        BuscaProfundidade buscaEmProfundidade = new BuscaProfundidade(arvoreGeradoraDeRotulosMinimos, verticeInicial);
        while (buscaEmProfundidade.inicializaGrafo() == true) {// enquanto nãoe stiver conectado
//            System.out.println("Passou");
            int j = 0;
            Iterator<Aresta> arestas = g.getArestas();
            while (arestas.hasNext()) {
//                System.out.println(a);
                Aresta a = arestas.next();
                j++;
//                System.out.println(j);
                if (a.getL() != null) {
//                    System.out.println(a.getL().toString());
//                    int numeroDeRepeticoesDeUmLabel = quantosVerticesTem.get(1);
//                    System.out.println(a.getL().getL() + "ROtulo de agora");
                    int numeroDeRepeticoesDeUmLabel = quantosVerticesTem.get(Integer.parseInt(a.getL().getL()));
//                    System.out.println(numeroDeRepeticoesDeUmLabel + "Oi");
//                    System.out.println(Integer.parseInt(a.getL().getL()) + "ROtulo de agora");
                    quantosVerticesTem.set(Integer.parseInt(a.getL().getL()), (numeroDeRepeticoesDeUmLabel++));
//                    System.out.println("Contando os rotulos");
//                        .replace(a.getL().getL(), numeroDeRepeticoesDeUmLabel++);
                }
            }
//            arestas = g.getArestas();
//            while (arestas.hasNext()) {
//                Aresta a = arestas.next();
////                if (numeroMaximo < quantoVerticesTem.get(a.getL().getL())) {
////                    numeroMaximo = quantoVerticesTem.get(a.getL().getL());
//                label = a.getL().getL();
////                }

        }
        System.out.println(quantosVerticesTem.toString());
        for (int j = 0; j < numeroDeLabels; j++) {
            if (numeroMaximo < quantosVerticesTem.get(j)) {
                numeroMaximo = quantosVerticesTem.get(j);
            }
            label = String.valueOf(j);
        }
        System.out.println(quantosVerticesTem.toString());

        ArrayList<Integer> novo = quantosVerticesTem;
        numeroMaximo = max(quantosVerticesTem);
        System.out.println("O Numero maximo de labels nessa eh o " + numeroMaximo);
        transferirArestas(label, numeroMaximo, arvoreGeradoraDeRotulosMinimos);

        int numeroDeLabel = 0;
        Iterator<Aresta> arestasNoGrafoOriginal = g.getArestas();
        Iterator<Aresta> arestasNoGrafoNovo = arvoreGeradoraDeRotulosMinimos.getArestas();
        ArrayList<String> novo2 = new ArrayList<>();
//        preciso comecar a verificar quanrtas arestas diferentes tem nesse negocio
        while (arestasNoGrafoOriginal.hasNext()) {

            Aresta aresta = arestasNoGrafoOriginal.next();
            if (aresta.getL() != null) {
                if (!novo2.contains(aresta.getL().getL())) {
                    novo2.add(aresta.getL().getL());
                    numeroDeLabel++;
                }
            }
        }
        return numeroDeLabel;
    }
}
