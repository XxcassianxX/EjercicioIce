package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.*;

public class ClienteCheckout {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {

            // Obtención del proxy remoto
            com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("ServicioCheckout:default -p 10000");
            ServicioCheckoutIPrx checkoutProxy = ServicioCheckoutIPrx.checkedCast(base);

            if (checkoutProxy == null) {
                throw new Error("Proxy de ServicioCheckout inválido o inalcanzable.");
            }

            System.out.println("=== INICIANDO PRUEBAS DE EXTREMO A EXTREMO (APEXSTORE) ===");

            // Simulaciones con las 3 pasarelas
            SolicitudPago orden1 = new SolicitudPago("ORD-101", 250.00, "STRIPE", "Cliente 1 - Tarjeta Crédito");
            SolicitudPago orden2 = new SolicitudPago("ORD-102", 120.00, "PSE",    "Cliente 2 - Débito Bancario");
            SolicitudPago orden3 = new SolicitudPago("ORD-103", 890.00, "CRIPTO", "Cliente 3 - Wallet Bitcoin");

            String resp1 = checkoutProxy.gestionarComprasHttp(orden1);
            System.out.println("Respuesta Servidor: " + resp1 + "\n");

            String resp2 = checkoutProxy.gestionarComprasHttp(orden2);
            System.out.println("Respuesta Servidor: " + resp2 + "\n");

            String resp3 = checkoutProxy.gestionarComprasHttp(orden3);
            System.out.println("Respuesta Servidor: " + resp3 + "\n");

            System.out.println("=== PRUEBAS FINALIZADAS EXITOSAMENTE ===");
        }
    }
}