package ar.edu.uade.tesoreria;

/**
 * Clase principal de demostración del sistema de tesorería con algoritmo Greedy.
 *
 * Enunciado:
 * "Un sistema de tesorería dispone de comprobantes de distinto tipo (monedas, cheques, bonos),
 * cada uno con un valor específico. Hay que comprar moneda extranjera minimizando la cantidad
 * de comprobantes utilizados."
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("    SISTEMA DE TESORERÍA - COMPRA DE MONEDA EXTRANJERA (GREEDY)      ");
        System.out.println("======================================================================\n");

        // -------------------------------------------------------------
        // CASO DE USO 1: Compra estándar minimizando comprobantes
        // -------------------------------------------------------------
        System.out.println(">>> ESCENARIO 1: Selección óptima voraz con Bonos, Cheques y Monedas");
        TesoreriaGreedy tesoreria1 = new TesoreriaGreedy();

        // Cargar comprobantes con distintos valores
        // Bonos (valores altos)
        tesoreria1.agregarComprobante(new Comprobante("BONO-5000-A", TipoComprobante.BONO, 5000.0));
        tesoreria1.agregarComprobante(new Comprobante("BONO-2000-A", TipoComprobante.BONO, 2000.0));
        tesoreria1.agregarComprobante(new Comprobante("BONO-2000-B", TipoComprobante.BONO, 2000.0));
        tesoreria1.agregarComprobante(new Comprobante("BONO-1000-A", TipoComprobante.BONO, 1000.0));

        // Cheques (valores medios)
        tesoreria1.agregarComprobante(new Comprobante("CHQ-500-1", TipoComprobante.CHEQUE, 500.0));
        tesoreria1.agregarComprobante(new Comprobante("CHQ-500-2", TipoComprobante.CHEQUE, 500.0));
        tesoreria1.agregarComprobante(new Comprobante("CHQ-200-1", TipoComprobante.CHEQUE, 200.0));
        tesoreria1.agregarComprobante(new Comprobante("CHQ-100-1", TipoComprobante.CHEQUE, 100.0));

        // Monedas (valores fraccionarios / bajos)
        tesoreria1.agregarComprobantesPorLote(TipoComprobante.MONEDA, 50.0, 4, "MON-50");
        tesoreria1.agregarComprobantesPorLote(TipoComprobante.MONEDA, 20.0, 5, "MON-20");
        tesoreria1.agregarComprobantesPorLote(TipoComprobante.MONEDA, 10.0, 10, "MON-10");

        double montoSolicitado1 = 8750.0; // Equivale a comprar ej. 10 USD a cotización 875
        System.out.printf("Monto a cubrir para comprar moneda extranjera: $%.2f%n", montoSolicitado1);
        System.out.printf("Total disponible en tesorería: $%.2f (en %d comprobantes)%n%n",
                tesoreria1.getMontoTotalDisponible(),
                tesoreria1.getComprobantesDisponibles().size());

        ResultadoCompra resultado1 = tesoreria1.comprarMonedaExtranjera(montoSolicitado1);
        resultado1.imprimirResumen();

        // -------------------------------------------------------------
        // CASO DE USO 2: Ejecución con débito de stock
        // -------------------------------------------------------------
        System.out.println(">>> ESCENARIO 2: Compra con débito real del inventario de tesorería");
        TesoreriaGreedy tesoreria2 = new TesoreriaGreedy();
        tesoreria2.agregarComprobante(new Comprobante("BONO-1000", TipoComprobante.BONO, 1000.0));
        tesoreria2.agregarComprobante(new Comprobante("CHQ-500", TipoComprobante.CHEQUE, 500.0));
        tesoreria2.agregarComprobante(new Comprobante("CHQ-200", TipoComprobante.CHEQUE, 200.0));
        tesoreria2.agregarComprobante(new Comprobante("MON-50-A", TipoComprobante.MONEDA, 50.0));
        tesoreria2.agregarComprobante(new Comprobante("MON-50-B", TipoComprobante.MONEDA, 50.0));

        System.out.println("Comprobantes disponibles antes de la compra: " + tesoreria2.getComprobantesDisponibles().size());
        ResultadoCompra resultado2 = tesoreria2.comprarMonedaExtranjera(1550.0, true);
        resultado2.imprimirResumen();
        System.out.println("Comprobantes restantes en tesorería: " + tesoreria2.getComprobantesDisponibles().size());
        System.out.println("Disponibles: " + tesoreria2.getComprobantesDisponibles() + "\n");

        // -------------------------------------------------------------
        // CASO DE USO 3: Fondos insuficientes / no alcanzables exactamente
        // -------------------------------------------------------------
        System.out.println(">>> ESCENARIO 3: Monto imposible de completar de forma exacta");
        TesoreriaGreedy tesoreria3 = new TesoreriaGreedy();
        tesoreria3.agregarComprobante(new Comprobante("BONO-1000", TipoComprobante.BONO, 1000.0));
        tesoreria3.agregarComprobante(new Comprobante("CHQ-200", TipoComprobante.CHEQUE, 200.0));
        tesoreria3.agregarComprobante(new Comprobante("MON-50", TipoComprobante.MONEDA, 50.0));

        ResultadoCompra resultado3 = tesoreria3.comprarMonedaExtranjera(1120.0);
        resultado3.imprimirResumen();
    }
}
