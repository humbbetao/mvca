package mvca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MVCA {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        GrafoListaAdjacencia g = new GrafoListaAdjacencia();
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
            if (numeroDeLinhas == 0) {
                numeroDeLinhas = numeroDeVertices;
                MVCAAlgorithm m = MVCAAlgorithm(g);
            }
            System.out.println(linha);
            i++;
        }
    }

    private static MVCAAlgorithm MVCAAlgorithm(GrafoListaAdjacencia g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
