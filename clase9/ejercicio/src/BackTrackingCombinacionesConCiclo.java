import java.util.ArrayList;

/**
 * Misma lógica que BackTrackingCombinaciones.java, pero escrita CON UN CICLO.
 *
 * En la versión original, las dos opciones de cada nivel (incluir / no incluir)
 * están escritas a mano, una debajo de la otra.
 * Acá las dos opciones se guardan en un arreglo y el for las recorre:
 * adentro del for hay UNA sola llamada recursiva, que se ejecuta una vez por opción.
 *
 * Sigue siendo TIPO 1: las opciones de cada nivel son siempre las mismas
 * ({incluir, no incluir}), no dependen de lo que se eligió antes.
 * El ciclo es solo otra forma de escribirlo.
 */
public class BackTrackingCombinacionesConCiclo {

    // Las opciones de CADA nivel: siempre las mismas dos
    private static final boolean[] OPCIONES = {true, false};   // true = incluir, false = no incluir

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3};
        ArrayList<Integer> combinacionActual = new ArrayList<>();
        imprimirCombinaciones(numeros, combinacionActual, 0);
    }

    private static void imprimirCombinaciones(int[] numeros, ArrayList<Integer> combinacionActual, int posicion) {
        if (posicion == numeros.length) {           // hoja: ya se decidió sobre todos los números
            imprimir(combinacionActual);
            return;
        }

        for (boolean incluir : OPCIONES) {          // 2 vueltas: primero incluir, después no incluir
            if (incluir) {
                combinacionActual.add(numeros[posicion]);                     // aplicar la decisión
            }

            imprimirCombinaciones(numeros, combinacionActual, posicion + 1);  // UNA sola llamada

            if (incluir) {
                combinacionActual.remove(combinacionActual.size() - 1);       // deshacer (retroceso)
            }
        }
    }

    private static void imprimir(ArrayList<Integer> combinacionActual) {
        for (Integer numero : combinacionActual) {
            System.out.print(numero);
        }
        System.out.println(" ");
    }
}

/*
 * Comparación lado a lado (un nivel del árbol):
 *
 *   ORIGINAL (2 llamadas a mano)            CON CICLO (1 llamada en un for)
 *   ------------------------------          ----------------------------------
 *   agregar(numero);                        for (incluir : {true, false}) {
 *   recursion(posicion + 1);                    if (incluir) agregar(numero);
 *   quitar_ultimo();                            recursion(posicion + 1);
 *   recursion(posicion + 1);                    if (incluir) quitar_ultimo();
 *                                           }
 *
 * Una diferencia chica: en el original el "quitar" queda pegado ANTES de la
 * segunda llamada; en el ciclo, cada vuelta deshace lo suyo al terminar.
 * El resultado es el mismo: las 8 combinaciones, en el mismo orden.
 *
 * Salida:
 *   123
 *   12
 *   13
 *   1
 *   23
 *   2
 *   3
 *   (línea vacía = el conjunto vacío)
 */
