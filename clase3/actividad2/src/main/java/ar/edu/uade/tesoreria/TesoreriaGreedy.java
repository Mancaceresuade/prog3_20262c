package ar.edu.uade.tesoreria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Sistema de Tesorería que implementa un algoritmo voraz (Greedy)
 * para la compra de moneda extranjera minimizando la cantidad de comprobantes utilizados.
 *
 * Componentes del Algoritmo Voraz:
 * 1. Conjunto de Candidatos (C): Lista de comprobantes disponibles en tesorería (Monedas, Cheques, Bonos).
 * 2. Función de Selección: Selecciona en cada paso el comprobante disponible de MAYOR valor (criterio local óptimo).
 * 3. Función de Factibilidad: Verifica si el comprobante seleccionado no supera el monto restante por cubrir (valor <= montoRestante).
 * 4. Función Solución: Se verifica si el montoRestante llega a 0 (monto cubierto exactamente).
 * 5. Función Objetivo: Minimizar |S| (cardinalidad del conjunto de comprobantes seleccionados).
 */
public class TesoreriaGreedy {

    private static final double EPSILON = 0.000001; // Tolerancia para comparaciones en punto flotante
    private final List<Comprobante> comprobantesDisponibles;

    public TesoreriaGreedy() {
        this.comprobantesDisponibles = new ArrayList<>();
    }

    /**
     * Agrega un comprobante individual a la tesorería.
     */
    public void agregarComprobante(Comprobante comprobante) {
        if (comprobante != null) {
            this.comprobantesDisponibles.add(comprobante);
        }
    }

    /**
     * Agrega una colección de comprobantes a la tesorería.
     */
    public void agregarComprobantes(Collection<Comprobante> comprobantes) {
        if (comprobantes != null) {
            this.comprobantesDisponibles.addAll(comprobantes);
        }
    }

    /**
     * Agrega múltiples comprobantes del mismo tipo y valor a la tesorería.
     *
     * @param tipo Tipo de comprobante (Moneda, Cheque, Bono)
     * @param valor Valor monetario unitario
     * @param cantidad Cantidad de unidades a ingresar
     * @param prefijoId Prefijo para generar identificadores únicos
     */
    public void agregarComprobantesPorLote(TipoComprobante tipo, double valor, int cantidad, String prefijoId) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        for (int i = 1; i <= cantidad; i++) {
            String id = String.format("%s-%d", prefijoId, i);
            this.comprobantesDisponibles.add(new Comprobante(id, tipo, valor));
        }
    }

    public List<Comprobante> getComprobantesDisponibles() {
        return Collections.unmodifiableList(comprobantesDisponibles);
    }

    public double getMontoTotalDisponible() {
        return comprobantesDisponibles.stream()
                .mapToDouble(Comprobante::getValor)
                .sum();
    }

    /**
     * Ejecuta el algoritmo voraz (Greedy) para seleccionar la menor cantidad posible
     * de comprobantes que sumen el monto requerido para comprar moneda extranjera.
     *
     * @param montoSolicitado Monto total en moneda local a convertir.
     * @param debitarDeTesoreria Si es true y la compra es exitosa, se retiran los comprobantes de la tesorería.
     * @return ResultadoCompra con los comprobantes seleccionados y detalles de la operación.
     */
    public ResultadoCompra comprarMonedaExtranjera(double montoSolicitado, boolean debitarDeTesoreria) {
        if (montoSolicitado <= 0) {
            throw new IllegalArgumentException("El monto solicitado debe ser mayor a cero.");
        }

        // 1. CONJUNTO DE CANDIDATOS: Copia de los comprobantes disponibles
        List<Comprobante> candidatos = new ArrayList<>(this.comprobantesDisponibles);

        // 2. ORDENAMIENTO (Estrategia voraz): Ordenar de MAYOR a MENOR valor
        Collections.sort(candidatos);

        List<Comprobante> seleccionados = new ArrayList<>();
        double montoRestante = montoSolicitado;
        double montoAcumulado = 0.0;

        // 3. ITERACIÓN VORAZ: Tomar el comprobante más grande que sea factible
        for (Comprobante candidato : candidatos) {
            if (montoRestante <= EPSILON) {
                break; // Se cubrió la totalidad del monto
            }

            // FUNCIÓN DE FACTIBILIDAD: ¿El comprobante cabe en el monto restante?
            if (candidato.getValor() <= (montoRestante + EPSILON)) {
                seleccionados.add(candidato);
                montoAcumulado += candidato.getValor();
                montoRestante -= candidato.getValor();
            }
        }

        // 4. FUNCIÓN SOLUCIÓN: Verificar si alcanzamos el monto exacto
        boolean esExitoso = Math.abs(montoRestante) <= EPSILON;

        // Si se solicitó debitar y la compra fue exitosa, removemos los comprobantes del stock
        if (debitarDeTesoreria && esExitoso) {
            for (Comprobante seleccionado : seleccionados) {
                this.comprobantesDisponibles.remove(seleccionado);
            }
        }

        return new ResultadoCompra(montoSolicitado, montoAcumulado, seleccionados, esExitoso);
    }

    /**
     * Sobrecarga sin debitar automáticamente de la tesorería (modo simulación/consulta).
     */
    public ResultadoCompra comprarMonedaExtranjera(double montoSolicitado) {
        return comprarMonedaExtranjera(montoSolicitado, false);
    }
}
