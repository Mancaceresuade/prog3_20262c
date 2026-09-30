import java.util.ArrayList;

public class FuerzaBrutaCombinaciones {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3};
        generarCombinacionesFuerzaBruta(numeros);
    }

    public static void generarCombinacionesFuerzaBruta(int[] numeros) {
        int n = numeros.length;
        int total = (int) Math.pow(2, n); // 2^n combinaciones posibles

        for (int mascara = 0; mascara < total; mascara++) {
            ArrayList<Integer> combinacion = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                // Verificamos si el bit i está activado en la máscara
                if ((mascara & (1 << i)) != 0) {
                    combinacion.add(numeros[i]);
                }
            }
            System.out.println(combinacion);
        }
    }
}
