public class Ej {
    public static void main(String[] args) {
        int[][] mat = {{},{},{}};
        recorrer(mat);
    }

    private static void recorrer(int[][] mat) {
        for (int i = 0; i < mat.length; i++) { // 3+3n
            for (int j = 0; j < mat.length; j++) { // 3+3n
                
            }
        }
    } // r(n) = 3+3n + n(3+3n) = 3+6n+3n**2

    private static void recorrer1(int[][] mat) {
    for (int i = 0; i < mat.length; i++) { // 3+3n
        for (int j = i; j < mat.length; j++) { // n + 2 gauss + gass
            
        }
    }
    } // r(n) = 3+3n+2 (n(n+1)/2) + n(n+1)/2 = 
    // gauss = n(n+1)/2  => 10 =>  10(10+1)/2 = 55
    // 1+2+3+4+5+6+7+8+9+10
}
