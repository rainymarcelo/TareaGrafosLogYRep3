import java.util.ArrayList;
import java.util.List;

public class LLAdyacencia {
    /*  MARCELO DE LA HOZ SIERRA CC 1037669479
     */

    private NodoLLA[] lista;
    private int[] visitado;

    public  LLAdyacencia(NodoLLA[] vector){
        lista=vector;
    }

    public LLAdyacencia(int vectores) {
        lista = new NodoLLA[vectores + 1];
        visitado = new int[vectores + 1];
        System.out.println(lista.length);
        for (int i = 1; i < vectores + 1; i++) {
            NodoLLA p = new NodoLLA(i, 0);
            lista[i] = p;
        }
    }//funciona

    public void grafiDirigido(int salida, int llegada, int valor) {
        NodoLLA nodoSalida = new NodoLLA(llegada, valor);
        if (lista[salida].getLiga() == null) {
            lista[salida].setLiga(nodoSalida);
        } else {
            NodoLLA q = lista[salida];
            NodoLLA p = lista[salida].getLiga();
            while (p != null) {
                if (llegada < p.getVertice()) {
                    q.setLiga((nodoSalida));
                    nodoSalida.setLiga(p);
                    return;
                }
                if (llegada == p.getVertice()) {
                    return;
                }
                if (p.getLiga() == null) {
                    p.setLiga(nodoSalida);
                    return;
                }
            }
        }
    }//funciona

    public void grafoNoDirigido(int salida, int llegada, int valor) {
        NodoLLA nodoSalida = new NodoLLA(llegada, valor);
        if (lista[salida].getLiga() == null) {
            lista[salida].setLiga(nodoSalida);
            grafoNoDirigido(llegada, salida, valor);
        } else {
            NodoLLA q = lista[salida];
            NodoLLA p = lista[salida].getLiga();
            while (p != null) {
                if (llegada < p.getVertice()) {
                    q.setLiga(nodoSalida);
                    nodoSalida.setLiga(p);
                    grafoNoDirigido(llegada, salida, valor);
                    return;
                }
                if (llegada == p.getVertice()) {
                    return;
                }
                if (p.getLiga() == null) {
                    p.setLiga(nodoSalida);
                    grafoNoDirigido(llegada, salida, valor);
                    return;
                }
                q = p;
                p = p.getLiga();
            }
        }
    }//funciona

    public String mostrarGrafoMatriz() {
        String grafo = "";
        for (int i = 1; i < lista.length; i++) {
            int[] fila = new int[lista.length];
            NodoLLA p = lista[i].getLiga();
            while (p != null) {
                fila[p.getVertice()] = p.getValor();
                p = p.getLiga();
            }
            for (int j = 1; j < lista.length; j++) {
                grafo += " " + fila[j] + " ";
            }
            grafo += "\n";
        }
        return grafo;
    }//funciona

    public String DFSListaLigadaAdyacencia(int vector) {
        String listaDFS = " " + vector;
        visitado[vector] = 1;
        NodoLLA p = lista[vector];
        while (p != null) {
            int w = p.getVertice();
            if (visitado[w] == 0) {
                listaDFS += DFSListaLigadaAdyacencia(w);
            }
            p = p.getLiga();
        }
        return listaDFS;
    }//funciona con no dirigidos

    public String BFSListasLigadaAdyacencia(int vector) {
        String listaBFS = " ";
        int w;
        NodoLLA p;
        Cola cola = new Cola(100);
        visitado[vector] = 1;
        cola.enColar(vector);
        while (!cola.esVacia()) {
            vector = cola.desenColar();
            listaBFS += vector + " ";
            p = lista[vector];
            while (p != null) {
                w = p.getVertice();
                if (visitado[w] == 0) {
                    visitado[w] = 1;
                    cola.enColar(w);
                }
                p = p.getLiga();
            }
        }
        return listaBFS;
    }//funciona con no dirigidos

    public void resetVisitados() {
        visitado = new int[lista.length];
    }

    public void agregarVector() {
        NodoLLA[] nuevaLista = new NodoLLA[lista.length + 1];
        for (int i = 1; i < lista.length; i++) {
            nuevaLista[i] = lista[i];
        }
        NodoLLA nodoLLA = new NodoLLA(lista.length, 0);
        nuevaLista[lista.length] = nodoLLA;
        lista = nuevaLista;
    }

    public int tamanoGrafo() {
        return lista.length;
    }

    public LLAdyacencia eliminarVector(int vector) {
        NodoLLA[] nuevaLista = new NodoLLA[lista.length - 1];
        int k = 1;
        for (int i = 1; i < lista.length; i++) {
            if (i != vector) {
                NodoLLA q = lista[i];
                NodoLLA p = lista[i].getLiga();
                while (p != null) {
                    if (p.getVertice() == vector) {
                        q.setLiga(p.getLiga());
                    }
                    if (p.getVertice()>vector){
                        p.setVertice(p.getVertice()-1);
                    }
                    q = p;
                    p = p.getLiga();
                }
                nuevaLista[k] = lista[i];
                k++;
            }
        }
        LLAdyacencia llAdyacencia=new LLAdyacencia(nuevaLista);
        return llAdyacencia;
    }

    public void eliminarLadoNoDirigido(int salida, int llegada) {
        NodoLLA q = lista[salida];
        NodoLLA p = lista[salida].getLiga();
        while (p != null) {
            if (p.getVertice() == llegada) {
                q.setLiga(p.getLiga());
                eliminarLadoNoDirigido(llegada, salida);
                return;
            }
            q = p;
            p = p.getLiga();
        }
    }

    public String Kruskal() {
        List<int[]> s2 = new ArrayList<>();
        for (int i = 0; i < tamanoGrafo(); i++) {
            int[] t = new int[tamanoGrafo()];
            for (int j = 0; j < tamanoGrafo(); j++) {
                if (i == j) {
                    t[j] = j;
                } else {
                    t[j] = 0;
                }
            }
            s2.add(t);
        }
        String kruska = "{";
        Lado[] lados = organizarPorValor();
        int c = 0;
        int k = 1;
        while (k < tamanoGrafo() - 1) {
            Lado p = lados[c];
            int v = p.getSalida();
            int w = p.getLlegada();
            int i = s2.get(v)[w];
            int j = s2.get(w)[v];
            if (i != j || i + j == 0) {
                if (i == 0 && j == 0) {
                    kruska += "(" + v + "," + w + "),";
                    s2.get(v)[v] = v;
                    s2.get(v)[w] = w;
                    for (int n = 1; n < tamanoGrafo(); n++) {
                        if (s2.get(w)[n] != 0) {
                            s2.get(v)[n] = s2.get(w)[n];
                        }
                    }
                    for (int n = 1; n < tamanoGrafo(); n++) {
                        s2.set(w, s2.get(v));
                    }
                    k = k + 1;
                }
            }
            c += 1;
        }
        kruska += "}";
        return kruska;
    }

    public boolean arbolLibre() {
        for (int i = 1; i < tamanoGrafo(); i++) {
            int[] fila = new int[tamanoGrafo()];
            NodoLLA p = lista[i].getLiga();
            while (p != null) {
                fila[p.getVertice()] = p.getVertice();
                p = p.getLiga();
            }
            for (int j = 1; j < tamanoGrafo(); j++) {
                if (j!=i){
                    int[] fila2=new int[tamanoGrafo()];
                    NodoLLA q = lista[j].getLiga();
                    while (q != null) {
                        if (q.getVertice() == i && fila[j] != j) {
                            return false;
                        }
                        fila2[j]=q.getVertice();
                        q = q.getLiga();
                    }
                }
            }
        }
        return true;
    }

    private Lado[] organizarPorValor() {
        List<Lado> listaLados = new ArrayList<>();
        for (int i = 1; i < tamanoGrafo(); i++) {
            NodoLLA p = lista[i].getLiga();
            while (p != null) {
                Lado lado = new Lado(i, p.getVertice(), p.getValor());
                listaLados.add(lado);
                p = p.getLiga();
            }
        }
        Lado[] vectorLado = new Lado[listaLados.size()];
        for (int i = 0; i < listaLados.size(); i++) {
            vectorLado[i] = listaLados.get(i);
        }
        for (int i = 0; i < vectorLado.length; i++) {
            for (int j = 0; j < vectorLado.length - i - 1; j++) {
                if (vectorLado[j].getValor() > vectorLado[j + 1].getValor()) {
                    Lado aux = vectorLado[j + 1];
                    ;
                    vectorLado[j + 1] = vectorLado[j];
                    vectorLado[j] = aux;
                }
            }
        }
        return vectorLado;
    }

}
