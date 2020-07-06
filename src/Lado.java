public class Lado {
    /*  MARCELO DE LA HOZ SIERRA CC 1037669479
     */
    private int salida,llegada,valor;

    public Lado(int salida, int llegada, int valor) {
        this.salida = salida;
        this.llegada = llegada;
        this.valor = valor;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getLlegada() {
        return llegada;
    }

    public void setLlegada(int llegada) {
        this.llegada = llegada;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
