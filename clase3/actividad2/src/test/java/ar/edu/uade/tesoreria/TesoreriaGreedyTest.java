package ar.edu.uade.tesoreria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesoreriaGreedyTest {

    private TesoreriaGreedy tesoreria;

    @BeforeEach
    void setUp() {
        tesoreria = new TesoreriaGreedy();
    }

    @Test
    @DisplayName("Debe minimizar la cantidad de comprobantes priorizando los de mayor valor (Greedy)")
    void testMinimizaCantidadComprobantes() {
        // Disponemos de 1 bono de 1000, 1 cheque de 500 y 15 monedas de 100
        tesoreria.agregarComprobante(new Comprobante("B-1000", TipoComprobante.BONO, 1000.0));
        tesoreria.agregarComprobante(new Comprobante("CH-500", TipoComprobante.CHEQUE, 500.0));
        tesoreria.agregarComprobantesPorLote(TipoComprobante.MONEDA, 100.0, 15, "M-100");

        // Para pagar 1500:
        // Estrategia voraz debe elegir Bono(1000) + Cheque(500) -> 2 comprobantes en vez de 15 monedas
        ResultadoCompra resultado = tesoreria.comprarMonedaExtranjera(1500.0);

        assertTrue(resultado.esExitoso());
        assertEquals(1500.0, resultado.getMontoCubierto(), 0.001);
        assertEquals(2, resultado.getCantidadComprobantes());
        assertEquals(TipoComprobante.BONO, resultado.getComprobantesUtilizados().get(0).getTipo());
        assertEquals(TipoComprobante.CHEQUE, resultado.getComprobantesUtilizados().get(1).getTipo());
    }

    @Test
    @DisplayName("Debe seleccionar combinaciones de Bonos, Cheques y Monedas correctamente")
    void testCombinacionMixta() {
        tesoreria.agregarComprobante(new Comprobante("B-2000", TipoComprobante.BONO, 2000.0));
        tesoreria.agregarComprobante(new Comprobante("CH-300", TipoComprobante.CHEQUE, 300.0));
        tesoreria.agregarComprobante(new Comprobante("M-50", TipoComprobante.MONEDA, 50.0));

        ResultadoCompra resultado = tesoreria.comprarMonedaExtranjera(2350.0);

        assertTrue(resultado.esExitoso());
        assertEquals(2350.0, resultado.getMontoCubierto(), 0.001);
        assertEquals(3, resultado.getCantidadComprobantes());
        assertEquals(1, resultado.getCantidadPorTipo().get(TipoComprobante.BONO));
        assertEquals(1, resultado.getCantidadPorTipo().get(TipoComprobante.CHEQUE));
        assertEquals(1, resultado.getCantidadPorTipo().get(TipoComprobante.MONEDA));
    }

    @Test
    @DisplayName("Debe reportar como no exitoso si los fondos son insuficientes o no exactos")
    void testFondosInsuficientes() {
        tesoreria.agregarComprobante(new Comprobante("B-1000", TipoComprobante.BONO, 1000.0));
        tesoreria.agregarComprobante(new Comprobante("CH-200", TipoComprobante.CHEQUE, 200.0));

        ResultadoCompra resultado = tesoreria.comprarMonedaExtranjera(1500.0);

        assertFalse(resultado.esExitoso());
        assertEquals(1200.0, resultado.getMontoCubierto(), 0.001);
        assertEquals(300.0, resultado.getMontoRestante(), 0.001);
        assertEquals(2, resultado.getCantidadComprobantes());
    }

    @Test
    @DisplayName("Debe debitar comprobantes de la tesorería cuando se solicita")
    void testDebitarComprobantes() {
        tesoreria.agregarComprobante(new Comprobante("B-1000", TipoComprobante.BONO, 1000.0));
        tesoreria.agregarComprobante(new Comprobante("CH-500", TipoComprobante.CHEQUE, 500.0));
        tesoreria.agregarComprobante(new Comprobante("M-100", TipoComprobante.MONEDA, 100.0));

        assertEquals(3, tesoreria.getComprobantesDisponibles().size());

        ResultadoCompra resultado = tesoreria.comprarMonedaExtranjera(1500.0, true);

        assertTrue(resultado.esExitoso());
        assertEquals(1, tesoreria.getComprobantesDisponibles().size());
        assertEquals("M-100", tesoreria.getComprobantesDisponibles().get(0).getId());
    }

    @Test
    @DisplayName("Debe lanzar excepción si el monto solicitado es menor o igual a cero")
    void testMontoInvalidoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> tesoreria.comprarMonedaExtranjera(0));
        assertThrows(IllegalArgumentException.class, () -> tesoreria.comprarMonedaExtranjera(-100));
    }
}
