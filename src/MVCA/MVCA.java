package MVCA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
        int k = 0;
        while ((linha = lerArq.readLine()) != null) {
            String[] s = linha.split(" ");

            if (i == 0) {
                numeroDeVertices = Integer.parseInt(s[0]);
                numeroDeLabels = Integer.parseInt(s[1]);
                numeroDeLinhas = numeroDeVertices;
            } else {
                numeroDeLinhas--;
                g.rotulos = new ArrayList<>();
                for (int j = 0; j < s.length; j++) {
                    VerticeBuscaProfundidade u = new VerticeBuscaProfundidade(String.valueOf(numeroDeLinhas));
                    VerticeBuscaProfundidade v = new VerticeBuscaProfundidade(String.valueOf(j));
                    g.adicionaVertice(u);
                    g.adicionaVertice(v);
                    Rotulo l = new Rotulo(s[j]);
                    if (!g.rotulos.contains(l)) {
                        g.rotulos.add(l);
                        g.numeroDeRotulos++;
                    }
                    if (!s[j].equals(String.valueOf(numeroDeLabels)) && !s[j].equals("")) {
                        Aresta nova = new Aresta(u, v, l);
                        g.adicionaAresta(nova);
//                        System.out.println("OI" + g.getArestas().next());
                    }
                }
            }

            if (numeroDeLinhas == 0) {
                k++;
                numeroDeLinhas = numeroDeVertices;
                MVCAAlgorithm m = new MVCAAlgorithm(g, numeroDeVertices);
                System.out.println("Esse numero e o do grafo : " + m.contarNumeroDeLabels(numeroDeVertices, numeroDeLabels));
                numeroDeLinhas = numeroDeVertices;
            }
            System.out.println(linha);
            i++;
        }
    }
}
