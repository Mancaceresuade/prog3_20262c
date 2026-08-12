public class EjemploBusquedaBinaria {
    public static void main(String[] args) {
        int[] arreglo = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};

        int objetivo = 13;
        int resultado = busquedaBinaria(arreglo, objetivo, 0, arreglo.length - 1);
        System.out.println("Buscando " + objetivo + " -> indice: " + resultado);

        objetivo = 4;
        resultado = busquedaBinaria(arreglo, objetivo, 0, arreglo.length - 1);
        System.out.println("Buscando " + objetivo + " -> indice: " + resultado);
    }

    public static int busquedaBinaria(int[] arreglo, int objetivo, int inicio, int fin) {
        if (inicio > fin) {
            return -1;
        }

        int medio = inicio + (fin - inicio) / 2;

        for (int i = 0; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length; j++) {
                // hacer algo
<            }
        }

        if (arreglo[medio] == objetivo) {
            return medio;
        } else if (arreglo[medio] > objetivo) {
            return busquedaBinaria(arreglo, objetivo, inicio, medio - 1);
        } else {
            return busquedaBinaria(arreglo, objetivo, medio + 1, fin);
        }
    }
    // ¿ complejidad ?
    // metodo division, porque divide por 2
    // a = 1
    // b = 2
    // k = 0
    // opcion de la formula 2
    // O(n**k log(n)) = O(n**0 log(n)) = O (1 log(n)) = O(log(n))

}
