public class NodoLLA {
    int vertice,valor;
    NodoLLA liga=null;

    public NodoLLA(int vertice, int valor) {
        this.vertice = vertice;
        this.valor = valor;
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public NodoLLA getLiga() {
        return liga;
    }

    public void setLiga(NodoLLA liga) {
        this.liga = liga;
    }
}
