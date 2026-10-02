public class ClienteCheckout {
    public static void main(String[] args) {
        try (Ice.Communicator communicator = Ice.Util.initialize(args)) {
            
            // 1. Crear el adaptador para el Notificador (Callback)
            Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("CallbackAdapter", "default -p 10000");
            Ice.Object contextoServant = new ProcesadorPagosContextoI();
            Ice.ObjectPrx contextoProxyBase = adapter.add(contextoServant, Ice.Util.stringToIdentity("NotificadorContexto"));
            ApexStore.INotificadorContextoPrx notificadorPrx = ApexStore.INotificadorContextoPrxHelper.uncheckedCast(contextoProxyBase);
            adapter.activate();

            // 2. Obtener Proxy de la estrategia deseada (Simulando conexión a EstrategiaCripto)
            // En un entorno real, estos proxies se resuelven dinámicamente según el método de pago
            Ice.ObjectPrx baseCripto = communicator.stringToProxy("EstrategiaCripto:default -p 10001");
            ApexStore.IEstrategiaPagoPrx criptoPrx = ApexStore.IEstrategiaPagoPrxHelper.checkedCast(baseCripto);

            if (criptoPrx == null) throw new Error("Proxy de estrategia inválido");

            // 3. Crear solicitud
            ApexStore.SolicitudPago solicitud = new ApexStore.SolicitudPago();
            solicitud.idOrden = "ORD-998877";
            solicitud.monto = 150.50;
            solicitud.metodoPago = "CRIPTO";

            // 4. Invocar el pago asíncronamente enviando el callback
            System.out.println("[Checkout] Iniciando pago de la orden: " + solicitud.idOrden);
            criptoPrx.procesarPago(solicitud, notificadorPrx);

            communicator.waitForShutdown();
        }
    }
}