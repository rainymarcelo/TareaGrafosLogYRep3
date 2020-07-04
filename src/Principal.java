import javax.swing.*;
import java.awt.*;

public class Principal {
    public static void main(String[] args) {

        LLAdyacencia[] grafos = new LLAdyacencia[100];
        int kGrafos = 1;

        String menu = "1. Construir grafo\n" +
                "2. Mostrar el grafo como matriz (incluyendo los ceros) sin construir la matriz\n" +
                "3. Recorrer en DFS\n" +
                "4. Recorrer en BFS\n" +
                "5. Agregar un nuevo vértice al grafo con sus correspondientes lados\n" +
                "6. Eliminar un vértice con sus correspondientes lados\n" +
                "7. Eliminar un lado del grafo\n" +
                "8. Construir y mostrar el spanning tree\n" +
                "9. Determinar si es un árbol libre\n" +
                "10. Determinar si tiene puntos de articulación o no. En caso de que tenga mostrar cuáles son.\n" +
                "11. Salir";

        String opcion1 = "";

        do {
            opcion1 = JOptionPane.showInputDialog(menu);
            switch (opcion1) {

                case "1"://construlle un grafo

                    int escoger=Integer.parseInt(JOptionPane.showInputDialog("1. Grafo no dirigido\n" +
                            "2. Grafo dirigido"));

                    do {
                        switch (escoger){
                            case 1:


                                escoger=100;
                                break;

                            case 2:

                                escoger=100;
                                break;
                        }

                    }while (escoger!=100);

                    int tamano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de vectores que posee el grafo:"));
                    int cantidadLados = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar:"));

                    LLAdyacencia listaLigada = new LLAdyacencia(tamano);

                    for (int i = 1; i <= cantidadLados; i++) {
                        String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida y llegada por una coma) \n" +
                                "ejemplo:1,2");
                        String[] lado = dato.split(",");
                        if (lado.length<2){
                            JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                            break;
                        }
                        int verticeSalida = Integer.parseInt(lado[0]);
                        int verticeLLegada = Integer.parseInt(lado[1]);
                        if (verticeLLegada > tamano || verticeSalida > tamano) {
                            JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                            break;
                        }
                        listaLigada.grafoNoDirigido(verticeSalida, verticeLLegada);
                    }
                    grafos[kGrafos] = listaLigada;
                    kGrafos++;
                    JOptionPane.showMessageDialog(null, listaLigada.mostrarGrafoMatriz());
                    break;


                case "2":
                    String mostrar = "";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    JOptionPane.showMessageDialog(null, mostrar);

                    break;

                case "3"://recorre en DFS
                    mostrar = "Seleccione el grafo al cual quiere representar en DFS\n";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    int grafoDFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                    String DFS=grafos[grafoDFS].DFSListaLigadaAdyacencia(1);//cambiar para que el usuario decida desde cual vector
                    JOptionPane.showMessageDialog(null,DFS);
                    grafos[grafoDFS].resetVisitados();
                    break;

                case "4"://recorre en BFS
                    mostrar = "Seleccione el grafo al cual quiere representar en DFS\n";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    int grafoBFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                    String BFS=grafos[grafoBFS].BFSListasLigadaAdyacencia(1);//cambiar para que el usuario decida desde cual vector
                    JOptionPane.showMessageDialog(null,BFS);
                    grafos[grafoBFS].resetVisitados();
                    break;

                case "5"://añadir un vertice
                    mostrar = "Seleccione el grafo al cual quiere agregar un vertice\n";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    int agregarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                    grafos[agregarVertice].agregarVector();
                    JOptionPane.showMessageDialog(null,grafos[agregarVertice].mostrarGrafoMatriz());
                    cantidadLados=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar"));
                    tamano=grafos[agregarVertice].tamanoGrafo();
                    for (int i = 1; i <= cantidadLados; i++) {
                        String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida y llegada por una coma)");
                        String[] lado = dato.split(",");
                        if (lado.length<2){
                            JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                            break;
                        }
                        int verticeSalida = Integer.parseInt(lado[0]);
                        int verticeLLegada = Integer.parseInt(lado[1]);
                        if (verticeLLegada > tamano || verticeSalida > tamano) {
                            JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                            break;
                        }
                        grafos[agregarVertice].grafoNoDirigido(verticeSalida,verticeLLegada);
                    }
                    JOptionPane.showMessageDialog(null,grafos[agregarVertice].mostrarGrafoMatriz());

                    break;

                case"6"://eliminar vertice
                    mostrar = "Seleccione el grafo al cual quiere agregar un vertice\n";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    int eliminarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                    int vectirEliminado=Integer.parseInt((JOptionPane.showInputDialog("Escribo cual vertice desea eliminar:")));

                    if (vectirEliminado>grafos[eliminarVertice].tamanoGrafo()){
                        JOptionPane.showMessageDialog(null,"El vector que desea eliminar no existe");
                        break;
                    }

                    grafos[eliminarVertice].eliminarVector(vectirEliminado);

                    JOptionPane.showMessageDialog(null,grafos[eliminarVertice].mostrarGrafoMatriz());

                    break;

                case"7"://eliminar lado
                    mostrar = "Seleccione el grafo al cual quiere agregar un vertice\n";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    int eliminaLado = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                    String ladoEliminado=JOptionPane.showInputDialog("Ingrese el lado a eliminar(separe los vertices con una coma)");
                    String[] datoEliminado=ladoEliminado.split(",");
                    int eliminado=Integer.parseInt(datoEliminado[0]);
                    int eliminado1=Integer.parseInt(datoEliminado[1]);
                    int tamanoElimiar=grafos[eliminaLado].tamanoGrafo();
                    if (eliminado>tamanoElimiar || eliminado1>tamanoElimiar){
                        JOptionPane.showMessageDialog(null,"uno de los vectores ingresados no existe");
                        break;
                    }
                    if (tamanoElimiar<2){
                        JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                        break;
                    }
                    grafos[eliminaLado].eliminarLadoNoDirigido(eliminado,eliminado1);

                    JOptionPane.showMessageDialog(null,grafos[eliminaLado].mostrarGrafoMatriz());

                    break;

                case"8"://construir y mostrar el spaning tree

                    break;

                case "9":
                    break;

                case "10":
                    break;

                case "11":
                    break;
            }

        } while (!opcion1.equals("11"));
    }
}
