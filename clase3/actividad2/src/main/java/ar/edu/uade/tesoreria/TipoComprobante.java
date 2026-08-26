package ar.edu.uade.tesoreria;

/**
 * Representa los diferentes tipos de comprobantes disponibles en la tesorería.
 */
public enum TipoComprobante {
    BONO("Bono"),
    CHEQUE("Cheque"),
    MONEDA("Moneda");

    private final String descripcion;

    TipoComprobante(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
