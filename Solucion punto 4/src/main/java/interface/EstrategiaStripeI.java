public class EstrategiaStripeI extends ApexStore._IEstrategiaPagoDisp {
    @Override
    public void procesarPago(ApexStore.SolicitudPago solicitud, ApexStore.INotificadorContextoPrx notificador, Ice.Current current) {
        System.out.println("[Stripe] Autorizando tarjeta de crédito para orden: " + solicitud.idOrden);
        
        ApexStore.ResultadoPago resultado = new ApexStore.ResultadoPago();
        resultado.idOrden = solicitud.idOrden;
        resultado.exitoso = true;
        resultado.mensajeReferencia = "Stripe Auth OK";

        notificador.notificarTransaccionExitosa(resultado);
    }
}