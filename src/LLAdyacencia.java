public class LLAdyacencia {

    private NodoLLA[] lista;
    private int[] visitado;

    public LLAdyacencia(int vectores) {
        lista=new NodoLLA[vectores+1];
        visitado=new int[vectores+1];
        System.out.println(lista.length);
        for (int i=1;i<vectores+1;i++){
            NodoLLA p=new NodoLLA(i,0);
            lista[i]=p;
        }
    }//funciona

    public void grafiDirigido(int salida,int llegada,int valor){
        NodoLLA nodoSalida=new NodoLLA(llegada,valor);
        if (lista[salida].getLiga()==null){
            lista[salida].setLiga(nodoSalida);
        }
        else {
            NodoLLA q=lista[salida];
            NodoLLA p=lista[salida].getLiga();
            while (p!=null){
                if (llegada<p.getVertice()){
                    q.setLiga((nodoSalida));
                    nodoSalida.setLiga(p);
                    return;
                }
                if (llegada==p.getVertice()){
                    return;
                }
                if (p.getLiga()==null){
                    p.setLiga(nodoSalida);
                    return;
                }
            }
        }
    }

    public void grafoNoDirigido(int salida,int llegada){
        NodoLLA nodoSalida=new NodoLLA(llegada,1);
        if (lista[salida].getLiga()==null){
            lista[salida].setLiga(nodoSalida);
            grafoNoDirigido(llegada,salida);
        }
        else {
            NodoLLA q=lista[salida];
            NodoLLA p=lista[salida].getLiga();
            while (p!=null){
                if (llegada<p.getVertice()){
                    q.setLiga(nodoSalida);
                    nodoSalida.setLiga(p);
                    grafoNoDirigido(llegada,salida);
                    return;
                }
                if (llegada==p.getVertice()){
                    return;
                }
                if (p.getLiga()==null){
                    p.setLiga(nodoSalida);
                    grafoNoDirigido(llegada,salida);
                    return;
                }
                q=p;
                p=p.getLiga();
            }
        }
    }//funciona

    public String mostrarGrafoMatriz(){
        String grafo="";
        for (int i=1;i<lista.length;i++){
            int[] fila=new int[lista.length];
            NodoLLA p=lista[i].getLiga();
            while (p!=null){
                fila[p.getVertice()]=p.getValor();
                p=p.getLiga();
            }
            for (int j=1;j<lista.length;j++){
                grafo+=" "+fila[j]+" ";
            }
            grafo+="\n";
        }
        return grafo;
    }//funciona con no dirigidos

    public String DFSListaLigadaAdyacencia(int vector){
        String listaDFS =" "+vector;
        visitado[vector]=1;
        NodoLLA p=lista[vector];
        while (p!=null){
            int w=p.getVertice();
            if (visitado[w]==0){
                listaDFS+=DFSListaLigadaAdyacencia(w);
            }
            p=p.getLiga();
        }
        return listaDFS;
    }//funciona con no dirigidos

    public String BFSListasLigadaAdyacencia(int vector){
        String listaBFS=" ";
        int w;
        NodoLLA p;
        Cola cola=new Cola(100);
        visitado[vector]=1;
        cola.enColar(vector);
        while (!cola.esVacia()){
            vector=cola.desenColar();
            listaBFS+=vector+" ";
            p=lista[vector];
            while (p!=null){
                w=p.getVertice();
                if (visitado[w]==0){
                    visitado[w]=1;
                    cola.enColar(w);
                }
                p=p.getLiga();
            }
        }
        return listaBFS;
    }//funciona con no dirigidos

    public void resetVisitados(){
        visitado=new int[lista.length];
    }

    public void agregarVector(){
        NodoLLA[] nuevaLista=new NodoLLA[lista.length+1];
        for (int i=1;i<lista.length;i++){
            nuevaLista[i]=lista[i];
        }
        NodoLLA nodoLLA=new NodoLLA(lista.length,0);
        nuevaLista[lista.length]=nodoLLA;
        lista=nuevaLista;
    }

    public int tamanoGrafo(){
        return lista.length;
    }



}
