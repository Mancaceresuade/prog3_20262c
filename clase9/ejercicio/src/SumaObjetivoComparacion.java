import java.util.ArrayList;
import java.util.List;

/**
 * Subconjuntos que suman un objetivo: fuerza bruta vs. backtracking con poda.
 * Mismo problema, mismas soluciones; lo que cambia es cuánto trabajo hace cada uno.
 */
public class SumaObjetivoComparacion {

    // ------------------------------------------------------------------
    // 1) FUERZA BRUTA: arma TODAS las combinaciones completas y filtra al final
    // ------------------------------------------------------------------
    static int combinacionesArmadas;

    static void fuerzaBruta(int[] numeros, int objetivo) {
        int n = numeros.length;
        int total = 1 << n;                                 // 2^n combinaciones
        for (int mascara = 0; mascara < total; mascara++) {
            combinacionesArmadas++;
            List<Integer> combinacion = new ArrayList<>();
            int suma = 0;
            for (int i = 0; i < n; i++) {
                if ((mascara & (1 << i)) != 0) {
                    combinacion.add(numeros[i]);
                    suma += numeros[i];
                }
            }
            if (suma == objetivo) {                         // recién acá se filtra
                System.out.println("   " + combinacion);
            }
        }
    }

    // ------------------------------------------------------------------
    // 2) BACKTRACKING: construye de a un elemento y puede cortar a mitad de camino
    // ------------------------------------------------------------------
    static int nodosVisitados;

    static void backtracking(int[] numeros, List<Integer> actual, int posicion,
                             int suma, int objetivo, boolean conPoda) {
        nodosVisitados++;                                   // se cuenta cada nodo del árbol
        if (conPoda && suma > objetivo) return;             // PODA: ya me pasé, no sigo bajando
        if (posicion == numeros.length) {                   // hoja: combinación completa
            if (suma == objetivo) System.out.println("   " + actual);
            return;
        }
        // RAMA 1: incluir numeros[posicion]
        actual.add(numeros[posicion]);
        backtracking(numeros, actual, posicion + 1, suma + numeros[posicion], objetivo, conPoda);
        actual.remove(actual.size() - 1);                   // RETROCESO
        // RAMA 2: no incluirlo
        backtracking(numeros, actual, posicion + 1, suma, objetivo, conPoda);
    }

    // ------------------------------------------------------------------
    static void comparar(int[] numeros, int objetivo) {
        System.out.println("==============================================");
        System.out.println("Conjunto " + java.util.Arrays.toString(numeros) + "  objetivo = " + objetivo);

        combinacionesArmadas = 0;
        System.out.println("\nFuerza bruta:");
        fuerzaBruta(numeros, objetivo);
        System.out.println("   -> combinaciones armadas: " + combinacionesArmadas);

        nodosVisitados = 0;
        System.out.println("\nBacktracking SIN poda:");
        backtracking(numeros, new ArrayList<>(), 0, 0, objetivo, false);
        System.out.println("   -> nodos visitados: " + nodosVisitados);

        nodosVisitados = 0;
        System.out.println("\nBacktracking CON poda (suma > objetivo):");
        backtracking(numeros, new ArrayList<>(), 0, 0, objetivo, true);
        System.out.println("   -> nodos visitados: " + nodosVisitados);
        System.out.println();
    }

    public static void main(String[] args) {
        // Ejemplo chico, el de la actividad original: se ve todo a mano
        comparar(new int[]{2, 3, 5}, 5);

        // Ejemplo más grande: acá se nota la poda
        comparar(new int[]{12, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 10);
    }
}
