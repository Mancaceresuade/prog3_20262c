public class Arbol<T extends Comparable<T>> implements IArbol<T> {
    Nodo<T> raiz;

    public static void main(String[] args) {
        Arbol<Integer> arbol = new Arbol<>();
        int[] valores = {5, 3, 8, 1, 4, 7, 9, 2, 6, 0};
        for (int valor : valores) {
            arbol.agregarElemento(valor);
        }
        System.out.println("Altura del arbol: " + arbol.calcularAltura());
    }

    @Override
    public void agregarElemento(T elemento) {
        raiz = agregar(raiz, elemento);
    }

    private Nodo<T> agregar(Nodo<T> nodo, T elemento) {
        if (nodo == null) {
            Nodo<T> nuevo = new Nodo<>();
            nuevo.elemento = elemento;
            return nuevo;
        }

        int comparacion = elemento.compareTo(nodo.elemento);
        if (comparacion < 0) {
            nodo.izq = agregar(nodo.izq, elemento);
        } else if (comparacion > 0) {
            nodo.der = agregar(nodo.der, elemento);
        } else {
            return nodo;
        }

        return rebalancear(nodo);
    }

    private Nodo<T> rebalancear(Nodo<T> nodo) {
        int factor = altura(nodo.izq) - altura(nodo.der);

        if (factor > 1) {
            if (altura(nodo.izq.izq) < altura(nodo.izq.der)) {
                nodo.izq = rotarIzquierda(nodo.izq);
            }
            return rotarDerecha(nodo);
        }

        if (factor < -1) {
            if (altura(nodo.der.der) < altura(nodo.der.izq)) {
                nodo.der = rotarDerecha(nodo.der);
            }
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private Nodo<T> rotarDerecha(Nodo<T> nodo) {
        Nodo<T> nuevaRaiz = nodo.izq;
        nodo.izq = nuevaRaiz.der;
        nuevaRaiz.der = nodo;
        return nuevaRaiz;
    }

    private Nodo<T> rotarIzquierda(Nodo<T> nodo) {
        Nodo<T> nuevaRaiz = nodo.der;
        nodo.der = nuevaRaiz.izq;
        nuevaRaiz.izq = nodo;
        return nuevaRaiz;
    }

    @Override
    public int calcularAltura() {
        return altura(raiz);
    }

    private int altura(Nodo<T> nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(altura(nodo.izq), altura(nodo.der));
    }
}

class Nodo<T> {
    T elemento;
    Nodo<T> izq;
    Nodo<T> der;
}
