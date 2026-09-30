public class NReinas {

    private static int N = 3; // tamaño del tablero

    public static void main(String[] args) {
        int[][] tablero = new int[N][N];
        backtrack(0, N, tablero);
    }

    private static void backtrack(int fila, int n, int[][] tablero) {
        if (fila == n) {
            imprimir(tablero);
            System.out.println("-----------");
            return;
        }

        // Intentar poner la reina en cada columna de la fila actual
        for (int col = 0; col < n; col++) {
            if (esValido(tablero, fila, col, n)) {
                colocarReina(tablero, fila, col);
                backtrack(fila + 1, n, tablero);
                quitarReina(tablero, fila, col); // retroceder
            }
        }
    }

    private static boolean esValido(int[][] tablero, int fila, int col, int n) {
        // Revisar la columna
        for (int i = 0; i < fila; i++) {
            if (tablero[i][col] == 1) return false;
        }
        // Revisar diagonal izquierda
        for (int i = fila - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) return false;
        }
        // Revisar diagonal derecha
        for (int i = fila - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (tablero[i][j] == 1) return false;
        }
        return true;
    }

    private static void colocarReina(int[][] tablero, int fila, int col) {
        tablero[fila][col] = 1;
    }

    private static void quitarReina(int[][] tablero, int fila, int col) {
        tablero[fila][col] = 0;
    }

    private static void imprimir(int[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == 1) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
