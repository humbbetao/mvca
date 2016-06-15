/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCA;

import java.util.ArrayList;
import java.util.Arrays;
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
    ArrayList<String> labelsAceitos = new ArrayList<String>();
    int numeroDeVertices;
    GrafoListaAdjacencia g;

    MVCAAlgorithm(GrafoListaAdjacencia g, int numeroDeVertices) {
        this.arvoreGeradoraDeRotulosMinimos = new GrafoListaAdjacencia(g.getVertices());
        this.verticeInicial = new VerticeBuscaProfundidade(g.getVertices().next().getId());
        this.g = g;
//        System.out.println(g.getArestas().next());
    }

    private void transferirArestas(String label, int numeroMaximo, GrafoListaAdjacencia arvoreGeradoraDeRotulosMinimos) {
        Iterator<Aresta> arestas = g.getArestas();
        int i = 0;
        while (arestas.hasNext()) {
            i++;
//            System.out.println("Passsou");
            Aresta antiga = arestas.next();
//            if (antiga.getL() != null) {
//                System.out.println(i);
            if (antiga.getL().getL().equals(label)) {
                System.out.println(antiga.toString());
                Aresta arestaNova = new Aresta(new VerticeBuscaProfundidade(antiga.getVertice1().getId()), new VerticeBuscaProfundidade(antiga.getVertice2().getId()), new Rotulo(antiga.getL().getL()));
                this.arvoreGeradoraDeRotulosMinimos.adicionaAresta(arestaNova);
//                System.out.println("Eh Igual");
//                }
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
            int j = 0;
            quantosVerticesTem = new ArrayList<>();
            for (int i = 0; i < numeroDeLabels; i++) {
                quantosVerticesTem.add(0);
            }
            Iterator<Aresta> arestas = g.getArestas();
//            Iterator<Aresta> arestaDoGrafoNovo = arvoreGeradoraDeRotulosMinimos.getArestas();
            while (arestas.hasNext()) {
//                while (arestaDoGrafoNovo.hasNext()) {
//                    Aresta arestaNova = arestaDoGrafoNovo.next();
                Aresta a = arestas.next();

                j++;
                String nova = a.getL().getL();
                if (a.getL() != null && !(labelsAceitos.contains(nova))) {
                    int numeroDeRepeticoesDeUmLabel = quantosVerticesTem.get(Integer.parseInt(a.getL().getL()));
                    numeroDeRepeticoesDeUmLabel++;
                    quantosVerticesTem.set(Integer.parseInt(a.getL().getL()), numeroDeRepeticoesDeUmLabel);
                }

            }
            numeroMaximo = 0;
            arestas = g.getArestas();
            System.out.println(Arrays.toString(quantosVerticesTem.toArray()));
            while (arestas.hasNext()) {
                Aresta a = arestas.next();
                if (numeroMaximo < quantosVerticesTem.get(Integer.parseInt(a.getL().getL()))) {
                    numeroMaximo = quantosVerticesTem.get(Integer.parseInt(a.getL().getL()));
                    label = a.getL().getL();
                }
            }
//            System.out.println("Esse eh o label dessa iteracao " + label);
            transferirArestas(label, numeroMaximo, arvoreGeradoraDeRotulosMinimos);
            buscaEmProfundidade = new BuscaProfundidade(arvoreGeradoraDeRotulosMinimos, verticeInicial);
            labelsAceitos.add(label);
            System.out.println("labelAceitos" + labelsAceitos.toString());
        }
        ArrayList<Integer> novo = quantosVerticesTem;

        System.out.println(Arrays.toString(quantosVerticesTem.toArray()));
        numeroMaximo = max(quantosVerticesTem);

        System.out.println(
                "O Numero maximo de labels nessa eh o " + numeroMaximo);

        int numeroDeLabel = 0;
        Iterator<Aresta> arestasNoGrafoOriginal = g.getArestas();
        Iterator<Aresta> arestasNoGrafoNovo = arvoreGeradoraDeRotulosMinimos.getArestas();
        ArrayList<String> novo2 = new ArrayList<>();

        while (arestasNoGrafoNovo.hasNext()) {
            Aresta aresta = arestasNoGrafoNovo.next();
            System.out.println(aresta);
            if (aresta.getL() != null) {

                if (!novo2.contains(aresta.getL().getL())) {
                    novo2.add(aresta.getL().getL());
                    numeroDeLabel++;
                    System.out.println("eh igual ao do grafo");
                }
            }
        }
        return numeroDeLabel;
    }
}
