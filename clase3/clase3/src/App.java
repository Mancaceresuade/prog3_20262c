public class App {
    public static void main(String[] args) throws Exception {
        // matriz cuadrada
        int matriz[][] = {{3,4,5},{1,2,3},{4,3,1}};
        System.out.println(sumaDeDiagonalySumaParesEnColumnaPar(matriz));
    }

    private static int sumaDeDiagonalySumaParesEnColumnaPar(int[][] matriz) {
        int rta = 0; // 1
        rta = rta + sumaDiagonal(matriz); // 2 + 5+6n
        rta = rta + sumaParesEnColumnaPar(matriz); // 4n^2 + 5n + 3
        return rta; // 1
    } // t(n) = 12+11n+4n^2  // conteo de instrucciones
    // demostracion matematica
    // t(n) <= c g(n)
    // 12+11n+4n^2 <= c g(n)
    // termino dominante mas 1
    // 12+11n+4n^2 <= 5n^2
    // divido todo por n^2
    // 12/n^2+11n/n^2+4n^2/n^2 <= 5n^2/n^2
    // 12/n^2+11/n+4  <= 5
    // se cumple a partir de n0 >= 12
    // conclusion t(n) pertenece a O(n^2) para c= 5 y desde n0 >= 12

    private static int sumaParesEnColumnaPar(int[][] matriz) {
        int rta = 0; // 1
        for (int col = 0; col < matriz[0].length; col=col+2) { // 1 + (2(n+1))/2 + n/2
            rta = rta + sumaParesEnColumna(matriz,col); 
            // 2 n/2 + n/2 * r(n)
            // n + n/2 * (5 + 8n )
            // n + 5 n/2 + 4n^2
        }
        return rta; // 1
    } // q(n) = 1+1 + (2(n+1))/2 + n/2 +  n + 5 n/2 + 4n^2 + 1
    // q(n) = 4n^2 + 5n + 3

    private static int sumaParesEnColumna(int[][] matriz, int col) {
        int rta = 0; // 1
        for (int i = 0; i < matriz.length; i++) { // 1 + 2 (n+1) + n = 1+2n+2+n = 3+3n
            rta = rta + devuelveValorSiEsPar(matriz[i][col]); // 2n + n * i(n) = 5n
        }
        return rta; // 1
    } // r(n) = 5 + 8n  => O(n)
    

    private static int devuelveValorSiEsPar(int numero) {
        return numero%2==0 ? numero: 0;
    } // O(1) =>  i(n) = 3

    private static int sumaDiagonal(int[][] matriz) {
        int rta=0; // 1
        for (int i = 0; i < matriz.length; i++) { // 1 + 2(n+1) + n = 1+2n+2+n = 3+3n
            rta = rta + matriz[i][i]; // 3n
        }
        return rta; // 1
    } // s(n) = 5+6n  => O(n)
    



}
