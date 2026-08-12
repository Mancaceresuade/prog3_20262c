import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facturacion {
    public static void main(String[] args) {
        HashMap<Integer,Cliente> clientes = cargarClientes();
        List<Factura> facturas = cargarFacturas();

        List<ResumenCliente> resumen = calcularResumen(clientes, facturas);

        for (ResumenCliente r : resumen) {
            System.out.println(r);
        }
    }

    private static HashMap<Integer,Cliente> cargarClientes() {
        HashMap<Integer,Cliente> clientes = new HashMap<>();
        clientes.put(1,new Cliente(1, "Juan Perez"));
        clientes.put(2,new Cliente(2, "Maria Gomez"));
        clientes.put(3,new Cliente(3, "Carlos Ruiz"));
        return clientes;
    }

    private static List<Factura> cargarFacturas() {
        List<Factura> facturas = new ArrayList<>();
        facturas.add(new Factura(101, 1, 1500.0));
        facturas.add(new Factura(102, 2, 2300.5));
        facturas.add(new Factura(103, 1, 800.0));
        facturas.add(new Factura(104, 3, 500.0));
        facturas.add(new Factura(105, 2, 1200.0));
        facturas.add(new Factura(106, 1, 300.0));
        return facturas;
    }

    private static List<ResumenCliente> calcularResumen(HashMap<Integer,Cliente> clientes, List<Factura> facturas) {
        List<ResumenCliente> resumen = new ArrayList<>();
        
        return resumen;
    } 
}
