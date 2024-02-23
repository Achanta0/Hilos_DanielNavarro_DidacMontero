package procesos_hilos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

//La clase MultiplicadorDeMatrices realiza la multiplicación de matrices y proporciona métodos para manipularlas.
class MultiplicadorDeMatrices {
 private int[][] matrizResultado;
 int[][] matriz1;
 int[][] matriz2;
 private int filas1, columnas1, filas2, columnas2;

 // Constructor que inicializa las matrices y verifica si las dimensiones son válidas para la multiplicación.
 public MultiplicadorDeMatrices(int filas1, int columnas1, int filas2, int columnas2) {
     // Inicialización de dimensiones y matrices.
     this.filas1 = filas1;
     this.columnas1 = columnas1;
     this.filas2 = filas2;
     this.columnas2 = columnas2;

     // Verificación de la validez de las dimensiones para la multiplicación de matrices.
     if (columnas1 != filas2) {
         System.err.println("Error: El número de columnas en la primera matriz debe ser igual al número de filas en la segunda matriz.");
         System.exit(1);
     }

     // Inicialización de las matrices.
     matrizResultado = new int[filas1][columnas2];
     matriz1 = new int[filas1][columnas1];
     matriz2 = new int[filas2][columnas2];
 }

 // Método para llenar una matriz desde la consola.
 public void llenarMatrizDesdeConsola(int[][] matriz, String nombreMatriz) {
     System.out.println("Ingrese valores para la matriz " + nombreMatriz + ":");
     Scanner scanner = new Scanner(System.in);
     for (int i = 0; i < matriz.length; i++) {
         for (int j = 0; j < matriz[0].length; j++) {
             System.out.print("Ingrese el valor para el elemento [" + i + "][" + j + "]: ");
             matriz[i][j] = scanner.nextInt();
         }
     }
 }

 // Método para llenar una matriz desde un archivo.
 public void llenarMatrizDesdeArchivo(int[][] matriz, String nombreMatriz, String nombreArchivo) {
     try {
         Scanner scanner = new Scanner(new File(nombreArchivo));
         System.out.println("Leyendo valores para la matriz " + nombreMatriz + " desde el archivo " + nombreArchivo);
         for (int i = 0; i < matriz.length; i++) {
             for (int j = 0; j < matriz[0].length; j++) {
                 matriz[i][j] = scanner.nextInt();
             }
         }
         scanner.close();
     } catch (FileNotFoundException e) {
         System.err.println("Error: No se ha encontrado " + nombreArchivo);
         System.exit(1);
     }
 }

 // Método para realizar la multiplicación de matrices.
 public void multiplicarMatrices() {
     for (int i = 0; i < filas1; i++) {
         for (int j = 0; j < columnas2; j++) {
             for (int k = 0; k < columnas1; k++) {
                 matrizResultado[i][j] += matriz1[i][k] * matriz2[k][j];
             }
         }
     }
 }

 // Método para imprimir la matriz resultado.
 public void imprimirMatrizResultado() {
     System.out.println("Matriz Resultado:");
     for (int i = 0; i < filas1; i++) {
         for (int j = 0; j < columnas2; j++) {
             System.out.print(matrizResultado[i][j] + " ");
         }
         System.out.println();
     }
 }

 // Método para guardar la matriz resultado en un archivo.
 public void guardarMatrizResultadoEnArchivo(String nombreArchivo) {
     try {
         FileWriter escritor = new FileWriter(nombreArchivo);
         for (int i = 0; i < filas1; i++) {
             for (int j = 0; j < columnas2; j++) {
                 escritor.write(matrizResultado[i][j] + " ");
             }
             escritor.write("\n");
         }
         escritor.close();
         System.out.println("Matriz Resultado guardada en " + nombreArchivo);
     } catch (IOException e) {
         System.err.println("Error: No se puede escribir en el archivo - " + nombreArchivo);
     }
 }
}

//La clase principal que contiene el método main para ejecutar el programa.
public class matriz_dna_dmu {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);

     // Entrada de dimensiones de las matrices desde el usuario.
     System.out.print("Ingrese el número de filas para la primera matriz: ");
     int filas1 = scanner.nextInt();
     System.out.print("Ingrese el número de columnas para la primera matriz: ");
     int columnas1 = scanner.nextInt();

     System.out.print("Ingrese el número de filas para la segunda matriz: ");
     int filas2 = scanner.nextInt();
     System.out.print("Ingrese el número de columnas para la segunda matriz: ");
     int columnas2 = scanner.nextInt();

     // Creación de un objeto MultiplicadorDeMatrices con las dimensiones especificadas.
     MultiplicadorDeMatrices multiplicadorMatrices = new MultiplicadorDeMatrices(filas1, columnas1, filas2, columnas2);

     // Selección del método de entrada para las matrices desde el usuario.
     System.out.println("Elija el método de entrada para las matrices:");
     System.out.println("1. Entrada desde consola");
     System.out.println("2. Entrada desde archivo");

     int metodoEntrada = scanner.nextInt();

  // Llenado de las matrices según el método de entrada seleccionado.
     if (metodoEntrada == 1) {
         multiplicadorMatrices.llenarMatrizDesdeConsola(multiplicadorMatrices.matriz1, "primera");
         multiplicadorMatrices.llenarMatrizDesdeConsola(multiplicadorMatrices.matriz2, "segunda");
     } else if (metodoEntrada == 2) {
         String archivo = "matriz_dna_dmu.txt";
         multiplicadorMatrices.llenarMatrizDesdeArchivo(multiplicadorMatrices.matriz1, "primera", archivo);
         
         // Cambia el nombre del archivo para la segunda matriz
         archivo = "matriz_dna_dmu.txt";
         multiplicadorMatrices.llenarMatrizDesdeArchivo(multiplicadorMatrices.matriz2, "segunda", archivo);
     } else {
         System.err.println("Método de entrada no válido seleccionado.");
         System.exit(1);
     }


     // Realización de la multiplicación de matrices.
     multiplicadorMatrices.multiplicarMatrices();

     // Impresión de la matriz resultado.
     multiplicadorMatrices.imprimirMatrizResultado();

     // Pregunta al usuario si desea guardar la matriz resultado en un archivo.
     System.out.print("¿Quiere guardar la matriz resultado en un archivo? (si/no): ");
     String guardarEnArchivo = scanner.next();
     if (guardarEnArchivo.equalsIgnoreCase("si")) {
         String archivoResultado = "matriz_dmu_dna_resultado.txt";
         multiplicadorMatrices.guardarMatrizResultadoEnArchivo(archivoResultado);
     }

     // Pregunta al usuario si desea continuar con una nueva multiplicación.
     System.out.print("¿Quiere continuar con una nueva multiplicación? (si/no): ");
     String continuarMultiplicacion = scanner.next();
     if (continuarMultiplicacion.equalsIgnoreCase("si")) {
         main(args); // Llamada recursiva para una nueva multiplicación
     } else {
         System.out.println("Programa terminado.");
     }
     
     // Cierre del scanner.
     scanner.close();
 }
}
