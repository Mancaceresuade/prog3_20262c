public class Ejemplo2 {
    public static void main(String[] args) {

    }

    public static int resolverHanoi(int n, char origen, char aux, char destino) {
        if (n == 1) {
            System.out.println("Mover disco 1: " + origen + " -> " + destino);
            return 1;
        }
        int mov = 0;
        mov += resolverHanoi(n - 1, origen, destino, aux);
        System.out.println("Mover disco " + n + ": " + origen + " -> " + destino);
        mov += 1;
        mov += resolverHanoi(n - 1, aux, origen, destino);
        return mov;
    }
    // ¿ complejidad ?
    // a = 2
    // b = 1
    // k = 0
    // metodo sustraccion
    // opcion 3
    // O(a ** n div b)  = O (2 ** n div 1) = O( 2 ** n)
}
