/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author humbe
 */
public class MVCA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Grafo g = new GrafoListaAdjacencia();
        FileReader arq = new FileReader("HDGraph50_12.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha;
        int i = 0;
        int numeroDeVertices = 0;
        int numeroDeLinhas = 0;
        int numeroDeLabels = 0;
        while ((linha = lerArq.readLine()) != null) {
            String[] s = linha.split(" ");
            if (i == 0) {
                numeroDeVertices = Integer.parseInt(s[0]);
                numeroDeLabels = Integer.parseInt(s[1]);
                numeroDeLinhas = numeroDeVertices;
            } else {
                for (int j = 0; j < s.length; j++) {
                    Vertice u = new Vertice(String.valueOf(numeroDeLinhas));
                    Vertice v = new Vertice(String.valueOf(j));
                    g.adicionaVertice(u);
                    g.adicionaVertice(v);
                    if (!s[j].equals(numeroDeLabels)) {
                        g.adicionaAresta(new Aresta(u, v));
                    }
                    numeroDeLinhas--;
                }
            }
            System.out.println(linha);
        }

    }

}
