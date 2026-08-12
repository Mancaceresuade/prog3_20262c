public class Ejemplo {
    public static void main(String[] args) {
        int[] numeros = {3,4,5,7};
        
        System.out.println(sumarNumeros(numeros,numeros.length));
    }

    private static int sumarNumeros(int[] numeros, int i) {
        if(i==0) return numeros[i];
        return numeros[i] + sumarNumeros(numeros, i-1) ;
    }
    // ¿ Complejidad algoritmica ?
    // a = 1
    // b = 1
    // k = 0
    // Segunda opcion en formula
    // O(n**k+1) = O(n**0+1) = O(n)  => t(n) pertenece a O(n)

    // apila en memoria
    // carga sumarNumeros(3)
    // carga sumarNumeros(2)
    // carga sumarNumeros(1)
    // carga sumarNumeros(0)
    // descarga i = 3 + sumarNumeros(1)
    // descarga i = 3 + sumarNumeros(1)
    // descarga i = 3 + sumarNumeros(1)
    
}
