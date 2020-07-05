import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.*;

public class Principal {
    public static void main(String[] args) {

        LLAdyacencia[] grafos = new LLAdyacencia[100];
        LLAdyacencia[] grafosDirigidos=new LLAdyacencia[100];
        int kGrafos = 1;
        int kGrafosDirigidos=1;

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

                    if (escoger==1){
                        int tamano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de vectores que posee el grafo:"));
                        int cantidadLados = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar:"));

                        LLAdyacencia listaLigada = new LLAdyacencia(tamano);

                        for (int i = 1; i <= cantidadLados; i++) {
                            String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida y llegada por una coma) \n" +
                                    "ejemplo:1,2,25");
                            String[] lado = dato.split(",");
                            if (lado.length<3){
                                JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                                break;
                            }
                            int verticeSalida = Integer.parseInt(lado[0]);
                            int verticeLLegada = Integer.parseInt(lado[1]);
                            int verticeValor= Integer.parseInt((lado[2]));
                            if (verticeLLegada > tamano || verticeSalida > tamano) {
                                JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                                break;
                            }
                            if (verticeValor<=0){
                                JOptionPane.showMessageDialog(null,"el valor del vertice tiene que ser mayor a 0 ");
                                break;
                            }
                            listaLigada.grafoNoDirigido(verticeSalida, verticeLLegada,verticeValor);
                        }
                        grafos[kGrafos] = listaLigada;
                        kGrafos++;
                        JOptionPane.showMessageDialog(null, listaLigada.mostrarGrafoMatriz());
                        break;
                    }
                    if (escoger==2){
                        int tamano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de vectores que posee el grafo:"));
                        int cantidadLados = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar:"));

                        LLAdyacencia listaLigada = new LLAdyacencia(tamano);

                        for (int i = 1; i <= cantidadLados; i++) {
                            String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida,llegada y valor por una coma) \n" +
                                    "ejemplo:1,2,25");
                            String[] lado = dato.split(",");
                            if (lado.length<3){
                                JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                                break;
                            }
                            int verticeSalida = Integer.parseInt(lado[0]);
                            int verticeLLegada = Integer.parseInt(lado[1]);
                            int valorVertice=Integer.parseInt(lado[2]);
                            if (verticeLLegada > tamano || verticeSalida > tamano) {
                                JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                                break;
                            }
                            if (valorVertice<=0){
                                JOptionPane.showMessageDialog(null,"el valor del vertice tiene que ser mayor a 0 ");
                                break;
                            }
                            listaLigada.grafiDirigido(verticeSalida,verticeLLegada,valorVertice);
                        }
                        grafosDirigidos[kGrafosDirigidos] = listaLigada;
                        kGrafosDirigidos++;
                        JOptionPane.showMessageDialog(null, listaLigada.mostrarGrafoMatriz());
                        break;
                    }else {
                        JOptionPane.showMessageDialog(null,"opcion incorrecta");
                        break;
                    }




                case "2":
                    String mostrar = "";
                    for (int i = 1; i < grafos.length; i++) {
                        if (grafos[i] != null) {
                            mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    for (int i = 1; i < grafosDirigidos.length; i++) {
                        if (grafosDirigidos[i] != null) {
                            mostrar += "grafo dirigido #" + i + "\n" + grafosDirigidos[i].mostrarGrafoMatriz() + "\n";
                        }
                    }
                    JOptionPane.showMessageDialog(null, mostrar);

                    break;

                case "3"://recorre en DFS

                    int opcionDFS=Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de grafo al cual va a recorre en DFS:\n" +
                            "1. Grafo no dirigido\n"+
                            "2. Grafo dirigido"));

                    if (opcionDFS==1){
                        mostrar = "Seleccione el grafo al cual quiere representar en DFS\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int grafoDFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        int vectorInicial=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el vector desde el cual se va a iniciar el recorrido DFS, tenga en cuenta que el grafo seleccionado tiene "+grafos[grafoDFS].tamanoGrafo()+"\n vectores"));
                        if (vectorInicial>grafos[grafoDFS].tamanoGrafo()){
                            JOptionPane.showMessageDialog(null,"ingreso un vector que no existe en el grafo");
                            break;
                        }
                        String DFS=grafos[grafoDFS].DFSListaLigadaAdyacencia(vectorInicial);
                        JOptionPane.showMessageDialog(null,DFS);
                        grafos[grafoDFS].resetVisitados();
                        break;
                    }
                    if (opcionDFS==2){
                        mostrar = "Seleccione el grafo dirigido al cual quiere representar en DFS\n";
                        for (int i = 1; i < grafosDirigidos.length; i++) {
                            if (grafosDirigidos[i] != null) {
                                mostrar += "grafo dirigido #" + i + "\n" + grafosDirigidos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int grafoDFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        int vectorInicial=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el vector desde el cual se va a iniciar el recorrido DFS, tenga en cuenta que el grafo seleccionado tiene "+grafosDirigidos[grafoDFS].tamanoGrafo()+"\n vectores"));
                        if (vectorInicial>grafos[grafoDFS].tamanoGrafo()){
                            JOptionPane.showMessageDialog(null,"ingreso un vector que no existe en el grafo");
                            break;
                        }
                        String DFS=grafosDirigidos[grafoDFS].DFSListaLigadaAdyacencia(vectorInicial);
                        JOptionPane.showMessageDialog(null,DFS);
                        grafosDirigidos[grafoDFS].resetVisitados();
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"opcion incorrecta");
                    break;


                case "4"://recorre en BFS

                    int opcionBFS=Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de grafo al cual va a recorre en DFS:\n" +
                            "1. Grafo no dirigido\n"+
                            "2. Grafo dirigido"));
                    if (opcionBFS==1){
                        mostrar = "Seleccione el grafo al cual quiere representar en BFS\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int grafoBFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        int vectorInicial=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el vector desde el cual se va a iniciar el recorrido BFS, tenga en cuenta que el grafo seleccionado tiene "+grafosDirigidos[grafoBFS].tamanoGrafo()+"\n vectores"));
                        if (vectorInicial>grafos[grafoBFS].tamanoGrafo()){
                            JOptionPane.showMessageDialog(null,"ingreso un vector que no existe en el grafo");
                            break;
                        }
                        String BFS=grafos[grafoBFS].BFSListasLigadaAdyacencia(vectorInicial);//cambiar para que el usuario decida desde cual vector
                        JOptionPane.showMessageDialog(null,BFS);
                        grafos[grafoBFS].resetVisitados();
                        break;
                    }
                    if (opcionBFS==2){
                        mostrar = "Seleccione el grafo dirigido al cual quiere representar en BFS\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafosDirigidos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafosDirigidos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int grafoBFS = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        int vectorInicial=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el vector desde el cual se va a iniciar el recorrido BFS, tenga en cuenta que el grafo seleccionado tiene "+grafosDirigidos[grafoBFS].tamanoGrafo()+"\n vectores"));
                        if (vectorInicial>grafos[grafoBFS].tamanoGrafo()){
                            JOptionPane.showMessageDialog(null,"ingreso un vector que no existe en el grafo");
                            break;
                        }
                        String BFS=grafosDirigidos[grafoBFS].BFSListasLigadaAdyacencia(vectorInicial);//cambiar para que el usuario decida desde cual vector
                        JOptionPane.showMessageDialog(null,BFS);
                        grafos[grafoBFS].resetVisitados();
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"opcion incorrecta");
                    break;



                case "5"://añadir un vertice
                    int opcionAñadir=Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de grafo al cual va a añadir un vertice:\n" +
                            "1. Grafo no dirigido\n"+
                            "2. Grafo dirigido"));

                    if (opcionAñadir==1){
                        mostrar = "Seleccione el grafo al cual quiere agregar un vertice\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int agregarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        grafos[agregarVertice].agregarVector();
                        JOptionPane.showMessageDialog(null,grafos[agregarVertice].mostrarGrafoMatriz());
                        int cantidadLados=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar"));
                        int tamano=grafos[agregarVertice].tamanoGrafo();
                        for (int i = 1; i <= cantidadLados; i++) {
                            String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida,llegada  y valor por una coma)");
                            String[] lado = dato.split(",");
                            if (lado.length<3){
                                JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                                break;
                            }
                            int verticeSalida = Integer.parseInt(lado[0]);
                            int verticeLLegada = Integer.parseInt(lado[1]);
                            int verticeValor=Integer.parseInt(lado[2]);
                            if (verticeLLegada > tamano || verticeSalida > tamano) {
                                JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                                break;
                            }
                            if (verticeValor<=0){
                                JOptionPane.showMessageDialog(null,"el valor del vertice tiene que ser mayor a 0 ");
                                break;
                            }
                            grafos[agregarVertice].grafoNoDirigido(verticeSalida,verticeLLegada,verticeValor);
                        }
                        JOptionPane.showMessageDialog(null,grafos[agregarVertice].mostrarGrafoMatriz());
                        break;
                    }
                    if (opcionAñadir==2){
                        mostrar = "Seleccione el grafo dirigido al cual quiere agregar un vertice\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafosDirigidos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafosDirigidos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int agregarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));
                        grafosDirigidos[agregarVertice].agregarVector();
                        JOptionPane.showMessageDialog(null,grafosDirigidos[agregarVertice].mostrarGrafoMatriz());
                        int cantidadLados=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de lados a ingresar"));
                        int tamano=grafosDirigidos[agregarVertice].tamanoGrafo();
                        for (int i = 1; i <= cantidadLados; i++) {
                            String dato = JOptionPane.showInputDialog("ingrese el lado #" + i + " (separe los vertices de salida, llegada y valor por una coma)\n" +
                                    "ejemplo:1,2,25" );
                            String[] lado = dato.split(",");
                            if (lado.length<3){
                                JOptionPane.showMessageDialog(null,"Escribio mal las coordenadas del lado");
                                break;
                            }
                            int verticeSalida = Integer.parseInt(lado[0]);
                            int verticeLLegada = Integer.parseInt(lado[1]);
                            int verticeVolar=Integer.parseInt(lado[2]);
                            if (verticeLLegada > tamano || verticeSalida > tamano) {
                                JOptionPane.showMessageDialog(null, "ingreso un lado invalido, uno de los vectores que lo conforman no existe en el grado");
                                break;
                            }
                            if (verticeVolar<=0){
                                JOptionPane.showMessageDialog(null, "ingreso valor igual o menor a cero por lo cual el lado es invalido");
                                break;
                            }
                            grafosDirigidos[agregarVertice].grafiDirigido(verticeSalida,verticeLLegada,verticeVolar);
                        }
                        JOptionPane.showMessageDialog(null,grafos[agregarVertice].mostrarGrafoMatriz());
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"opcion incorrecta");
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
                    int opcionKruskal=Integer.parseInt(JOptionPane.showInputDialog("Seleccione el tipo de grafo al cual va a añadir un vertice:\n" +
                            "1. Grafo no dirigido\n"+
                            "2. Grafo dirigido"));

                    if (opcionKruskal==1){
                        mostrar = "Seleccione el grafo al cual quiere hallar el spanning tree\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int agregarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));


                        JOptionPane.showMessageDialog(null,grafos[agregarVertice].Kruskal());
                        break;

                    }
                    if (opcionKruskal==2){
                        mostrar = "Seleccione el grafo dirigido al cual quiere hallar el spanning tree\n";
                        for (int i = 1; i < grafos.length; i++) {
                            if (grafosDirigidos[i] != null) {
                                mostrar += "grafo #" + i + "\n" + grafosDirigidos[i].mostrarGrafoMatriz() + "\n";
                            }
                        }
                        int agregarVertice = Integer.parseInt(JOptionPane.showInputDialog(mostrar));

                    }
                    JOptionPane.showMessageDialog(null,"opcion incorrecta");
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
