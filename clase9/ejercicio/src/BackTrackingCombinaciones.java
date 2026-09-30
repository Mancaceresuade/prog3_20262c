import java.util.ArrayList;

public class BackTrackingCombinaciones {
    public static void main(String[] args) throws Exception {
        int[] numeros = {1,2,3};
        ArrayList<Integer> combinacion_actual = new ArrayList<>();
        imprimirCombinaciones(numeros,combinacion_actual,0);
    }

    private static void imprimirCombinaciones(int[] numeros,ArrayList<Integer> combinacion_actual, int posicion) {
        if(posicion==numeros.length) {
            imprimir(combinacion_actual);
            return;
        }
        // incluir en la combinacion
        combinacion_actual_agregar(combinacion_actual,numeros,posicion);
        imprimirCombinaciones(numeros, combinacion_actual, posicion+1);
        // retroceder y no incluir el elemento actual
        combinacion_actual_quitar_ultimo(combinacion_actual);
        imprimirCombinaciones(numeros, combinacion_actual, posicion+1);
    }

    private static void combinacion_actual_quitar_ultimo(ArrayList<Integer> combinacion_actual) {
        combinacion_actual.remove(combinacion_actual.size()-1);
    }

    private static void combinacion_actual_agregar(ArrayList<Integer>  combinacion_actual, int[] numeros, int posicion) {
        combinacion_actual.add(numeros[posicion]);
    }

    private static void imprimir(ArrayList<Integer> combinacion_actual) {        
        for (Integer numero : combinacion_actual) {
            System.out.print(numero);
        }
        System.out.println(" ");
    }    
    

}
