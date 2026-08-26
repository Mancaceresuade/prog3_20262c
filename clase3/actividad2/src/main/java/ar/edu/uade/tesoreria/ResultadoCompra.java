package ar.edu.uade.tesoreria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsula el resultado de la operación de compra de moneda extranjera
 * utilizando el algoritmo voraz (greedy).
 */
public class ResultadoCompra {

    private final double montoSolicitado;
    private final double montoCubierto;
    private final List<Comprobante> comprobantesUtilizados;
    private final boolean exitoso;

    public ResultadoCompra(double montoSolicitado, double montoCubierto, List<Comprobante> comprobantesUtilizados, boolean exitoso) {
        this.montoSolicitado = montoSolicitado;
        this.montoCubierto = montoCubierto;
        this.comprobantesUtilizados = new ArrayList<>(comprobantesUtilizados);
        this.exitoso = exitoso;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public double getMontoCubierto() {
        return montoCubierto;
    }

    public double getMontoRestante() {
        return Math.max(0.0, montoSolicitado - montoCubierto);
    }

    public List<Comprobante> getComprobantesUtilizados() {
        return Collections.unmodifiableList(comprobantesUtilizados);
    }

    public int getCantidadComprobantes() {
        return comprobantesUtilizados.size();
    }

    public boolean esExitoso() {
        return exitoso;
    }

    /**
     * Retorna la cantidad de comprobantes utilizados agrupados por tipo.
     */
    public Map<TipoComprobante, Integer> getCantidadPorTipo() {
        Map<TipoComprobante, Integer> mapa = new EnumMap<>(TipoComprobante.class);
        for (TipoComprobante tipo : TipoComprobante.values()) {
            mapa.put(tipo, 0);
        }
        for (Comprobante c : comprobantesUtilizados) {
            mapa.put(c.getTipo(), mapa.get(c.getTipo()) + 1);
        }
        return mapa;
    }

    /**
     * Imprime un resumen detallado del resultado de la compra en consola.
     */
    public void imprimirResumen() {
        System.out.println("=================================================");
        System.out.println("          RESUMEN DE COMPRA DE MONEDA           ");
        System.out.println("=================================================");
        System.out.printf("Estado:                 %s%n", exitoso ? "EXITOSO (Monto exacto alcanzado)" : "PARCIAL (Fondos insuficientes/no exactos)");
        System.out.printf("Monto Solicitado:       $%.2f%n", montoSolicitado);
        System.out.printf("Monto Cubierto:         $%.2f%n", montoCubierto);
        System.out.printf("Monto Restante:         $%.2f%n", getMontoRestante());
        System.out.printf("Comprobantes Utilizados: %d%n", getCantidadComprobantes());
        System.out.println("-------------------------------------------------");
        System.out.println("Desglose por tipo de comprobante:");
        Map<TipoComprobante, Integer> porTipo = getCantidadPorTipo();
        for (Map.Entry<TipoComprobante, Integer> entry : porTipo.entrySet()) {
            System.out.printf("  - %-10s: %d%n", entry.getKey().getDescripcion(), entry.getValue());
        }
        System.out.println("-------------------------------------------------");
        System.out.println("Detalle de comprobantes seleccionados (Greedy):");
        if (comprobantesUtilizados.isEmpty()) {
            System.out.println("  (Ningún comprobante seleccionado)");
        } else {
            int index = 1;
            for (Comprobante c : comprobantesUtilizados) {
                System.out.printf("  %2d. %s%n", index++, c);
            }
        }
        System.out.println("=================================================\n");
    }
}
