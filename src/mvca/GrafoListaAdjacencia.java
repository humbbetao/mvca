package mvca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class GrafoListaAdjacencia {

    HashMap<Vertice, ArrayList<VerticeRotulo>> grafo = new HashMap<>();
    int numeroDeRotulos = 0;
    ArrayList<Rotulo> rotulos = new ArrayList<Rotulo>();

    GrafoListaAdjacencia(Iterator<Vertice> vertices) {
        while (vertices.hasNext()) {
            adicionaVertice(vertices.next());
        }

    }

    GrafoListaAdjacencia() {
    }

    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        ArrayList<VerticeRotulo> vertices = new ArrayList<VerticeRotulo>();
        vertices = grafo.get(u);
        ArrayList<Vertice> v = new ArrayList<>();
        for (VerticeRotulo vr : vertices) {
            v.add(vr.getV());
        }
        return (Iterator<Vertice>) v;
//
//        if (u != null && grafo.containsKey(u)) {
//            return grafo.get(u).iterator();
//        } else {
//            return null;
//        }

    }

    public Iterator<Vertice> getVertices() {
        return this.grafo.keySet().iterator();
    }

    public Iterator<Aresta<Vertice, Vertice>> getArestas() {
        Set<Aresta<Vertice, Vertice>> i = new HashSet<Aresta<Vertice, Vertice>>();
        for (Entry<Vertice, ArrayList<VerticeRotulo>> adj : grafo.entrySet()) {
            for (VerticeRotulo u : adj.getValue()) {
                i.add(new Aresta<>(adj.getKey(), u.getV()));
            }
        }
        return i.iterator();
    }

    public Vertice getVertice(String idVertice) {
        for (Entry<Vertice, ArrayList<VerticeRotulo>> e : grafo.entrySet()) {
            if (e.getKey().getId().equals(idVertice)) {
                return e.getKey();
            } else {
                ArrayList<VerticeRotulo> adj = e.getValue();
                for (VerticeRotulo v : adj) {
                    if (v.getV().getId().equals(idVertice)) {
                        return v.getV();
                    }
                }
            }
        }
        return null;
    }

    public void adicionaVertice(Vertice verticeNoGrafo,
            VerticeRotulo verticeAdicionado) {
        // verifica se verticeNoGrafo esta no grafo
        Vertice v = getVertice(verticeNoGrafo.getId());
        if (v == null) {
            throw new RuntimeException("O vértice com identificado "
                    + verticeNoGrafo.getId()
                    + " precisa necessariamente estar no grafo.");
        } // else -> vertice esta no grafo !
        else {
            // verifica se o vertice verticeNoGrafo já possui
            // outros vértices adjacentes
            ArrayList<VerticeRotulo> adj = this.grafo.get(v);
            if (adj == null) {
                adj = new ArrayList<>();
                adj.add(verticeAdicionado);
                this.grafo.put(v, adj);
            } else {
                adj.add(verticeAdicionado);
                this.grafo.put(v, adj);
            }
        }
    }

    public void adicionaVertice(Vertice verticeAdicionado) {
        // o vértice já está no grafo ?
        // se não, adiciona o vértice sem pai
        Vertice v = getVertice(verticeAdicionado.getId());
        if (v == null) {
            this.grafo.put(verticeAdicionado, new ArrayList<>());
        }
        // se o vértice já está no grafo, troca a referência
        verticeAdicionado = v;
    }

    public void adicionaAresta(Aresta<Vertice, Vertice> arestaAdicionada) {
        if (arestaAdicionada.getVertice1() == null
                || arestaAdicionada.getVertice2() == null) {
            throw new RuntimeException(
                    "Não é possível adicionar uma aresta com vértice nulos no grafo");
        } else {
            // vertice 1 da aresta já existe no grafo ?
            // se não, cria nova entrada na lista de adjacências
            Vertice v = getVertice(arestaAdicionada.getVertice1().getId());
            if (v == null) {
                // recupera vertice 2
                Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
                // vertice 2 ja esta no grafo ?
                if (v2 != null) {
                    v = arestaAdicionada.getVertice1();
                    ArrayList<VerticeRotulo> adjV2 = new ArrayList<VerticeRotulo>();
                    adjV2.add(new VerticeRotulo(v2, arestaAdicionada.getL()));

                    this.grafo.put(v, adjV2);
                } else {
                    // vertice 2 nao esta no grafo !
                    v = arestaAdicionada.getVertice1();
                    v2 = arestaAdicionada.getVertice2();
                    ArrayList<VerticeRotulo> adjV2 = new ArrayList<VerticeRotulo>();
                    adjV2.add(new VerticeRotulo(v2, arestaAdicionada.getL()));

                    // adiciona listas de adjacencia do vertice 2 e do vertice 1
                    this.grafo.put(v, adjV2);
                    this.grafo.put(v2, new ArrayList<VerticeRotulo>());
                }
                // se vertice 1 esta no grafo, adiciona novo elemento na lista
                // de adjacência
            } else {
                // vértice 2 está no grafo?
                Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
                if (v2 == null) {
                    v2 = arestaAdicionada.getVertice2();
                    // adiciona vertice 2 ao grafo
                    this.grafo.put(v2, new ArrayList<VerticeRotulo>());
                }

                // adiciona vertice 2 a lista de adjacencia do vertice 1
                List<VerticeRotulo> l = this.grafo.get(v);
                l.add(new VerticeRotulo(v2, arestaAdicionada.getL()));
            }
        }
    }
}
