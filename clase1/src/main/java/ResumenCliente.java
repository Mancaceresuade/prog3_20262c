public class ResumenCliente {
    private int idCliente;
    private String nombre;
    private double totalImportes;

    public ResumenCliente(int idCliente, String nombre, double totalImportes) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.totalImportes = totalImportes;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTotalImportes() {
        return totalImportes;
    }

    @Override
    public String toString() {
        return "Cliente " + idCliente + " - " + nombre + " - Total facturado: $" + totalImportes;
    }
}
