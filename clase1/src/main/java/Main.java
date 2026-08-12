import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int filas = 5;
        int columnas = 5;
        int[][] matriz = new int[filas][columnas];

        cargarMatriz(matriz);
        mostrarMatriz(matriz);

    }

    private static void mostrarMatriz(int[][] matriz) {
        int aux = matriz.length;
        for (int i = 0; i < aux; i++) { // 1 + 2(n+1) + n
            for (int j = 0; j < matriz[i].length; j++) { // 1 + 2(n+1) + n
                System.out.print(matriz[i][j] + "\t"); // 3n
                calcularAlgo();
            }
        }
    } // Conteo de instrucciones
    // 1 + 2n+2 + n + n ( 1 + 2n+2 + n + 3n ) 
    // 1 + 2n+ 2 + n + n + 2n**2 + 2n + n**2 + 3n**2 
    // t(n) = 6n**2 + 6n + 3
    // termino dominante +1 7
    // 6n**2 + 6n + 3 <= 7n**2
    // divido todo por 6n**2
    // 6n**2/n**2 + 6n/n**2 + 3/n**2 <= 7n**2/n**2
    // 6 + 6/n + 3/n**2 <= 7
    // f(n) perteneca a O(n**2)  para c = 7 y desde n0 > 8

    private static void calcularAlgo() {
        for(int i=0; i<10; i++ ){
            System.out.println(i);
        }
    } // complejidad constante

    public static void cargarMatriz(int[][] matriz) {
        Random random = new Random();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = random.nextInt(15) + 1;
            }
        }
    }
}
