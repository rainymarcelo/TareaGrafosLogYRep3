public class Cola {
    private int primero,ultimo,n;
    private int[] V;

    public Cola(int m){
        n=m;
        primero=ultimo=0;
        V=new int[n];
    }

    public boolean esVacia(){
        return primero==ultimo;
    }

    public boolean esLlena(){
        return primero==0 && ultimo==n;
    }

    public void enColar(int d){
        if (esLlena()){
            return;
        }
        if (ultimo==n){
            for (int i=primero+1;i<=n;i++){
                V[i-primero]=V[i];
            }
            ultimo-=primero;
            primero=0;
        }
        ultimo++;
        V[ultimo]=d;
    }

    public int desenColar(){
        if (esVacia()){
            return 0;
        }
        primero++;
        return V[primero];
    }


}
