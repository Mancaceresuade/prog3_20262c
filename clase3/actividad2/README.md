# Sistema de Tesorería - Compra de Moneda Extranjera (Algoritmo Voraz / Greedy)

## 📋 Enunciado del Problema
> *"Un sistema de tesorería dispone de comprobantes de distinto tipo (monedas, cheques, bonos), cada uno con un valor específico. Hay que comprar moneda extranjera minimizando la cantidad de comprobantes utilizados."*

---

## 💡 Análisis Teórico de la Técnica Voraz (Greedy)

El problema se modela como una variante del problema de cambio de divisas / selección de elementos con criterio voraz:

1. **Conjunto de Candidatos ($C$):** Todos los comprobantes disponibles en la tesorería (cada uno caracterizado por su tipo y su valor monetario).
2. **Función de Selección (Criterio Voraz):** En cada paso se elige el comprobante disponible de **mayor valor nominal** que no haya sido seleccionado previamente.
3. **Función de Factibilidad:** Verifica que el comprobante seleccionado tenga un valor menor o igual al monto restante por pagar (`valor <= montoRestante`).
4. **Función Objetivo:** Minimizar la cantidad total de comprobantes seleccionados ($|S|$).
5. **Función Solución:** Determina que la meta fue alcanzada cuando el monto restante por cubrir es $0.

### Complejidad Temporal
- **Ordenamiento de comprobantes:** $O(n \log n)$, donde $n$ es la cantidad total de comprobantes disponibles.
- **Iteración voraz de selección:** $O(n)$, ya que se realiza una pasada lineal sobre la lista ordenada.
- **Complejidad total:** $O(n \log n)$.

---

## 🏛️ Estructura del Proyecto

```
actividad2/
├── pom.xml
├── README.md
└── src/
    ├── main/java/ar/edu/uade/tesoreria/
    │   ├── TipoComprobante.java   # Enum: BONO, CHEQUE, MONEDA
    │   ├── Comprobante.java       # Entidad comprobante (tipo, id, valor, Comparable)
    │   ├── TesoreriaGreedy.java   # Lógica del algoritmo voraz y gestión de stock
    │   ├── ResultadoCompra.java   # Objeto resultado con reporte y desglose
    │   └── Main.java              # Demostración con múltiples escenarios
    └── test/java/ar/edu/uade/tesoreria/
        └── TesoreriaGreedyTest.java # Pruebas unitarias con JUnit 5
```

---

## 🚀 Ejecución

### Ejecutar la aplicación principal:
```bash
mvn compile exec:java
```

### Ejecutar las pruebas unitarias:
```bash
mvn test
```
