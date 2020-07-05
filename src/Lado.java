public class Lado {
    private int salida,llegada,valor;
    private Lado lado;

    public Lado(int salida, int llegada, int valor, Lado lado) {
        this.salida = salida;
        this.llegada = llegada;
        this.valor = valor;
        this.lado = lado;
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

    public Lado getLado() {
        return lado;
    }

    public void setLado(Lado lado) {
        this.lado = lado;
    }
}
