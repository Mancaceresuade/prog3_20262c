package ar.edu.uade.tesoreria;

import java.util.Objects;

/**
 * Representa un comprobante de pago individual con su tipo, identificador y valor específico.
 */
public class Comprobante implements Comparable<Comprobante> {

    private final String id;
    private final TipoComprobante tipo;
    private final double valor;

    public Comprobante(String id, TipoComprobante tipo, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor del comprobante debe ser positivo.");
        }
        this.id = Objects.requireNonNull(id, "El identificador no puede ser nulo.");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de comprobante no puede ser nulo.");
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public TipoComprobante getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    /**
     * Orden natural: Descendente por valor (criterio voraz / greedy).
     * Si dos comprobantes tienen el mismo valor, se ordenan por su ID para garantizar consistencia.
     */
    @Override
    public int compareTo(Comprobante otro) {
        int comparacionValor = Double.compare(otro.valor, this.valor); // Descendente
        if (comparacionValor != 0) {
            return comparacionValor;
        }
        return this.id.compareTo(otro.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comprobante that = (Comprobante) o;
        return Double.compare(that.valor, valor) == 0 &&
               Objects.equals(id, that.id) &&
               tipo == that.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, valor);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (#%s) - $%.2f", tipo, tipo.getDescripcion(), id, valor);
    }
}
