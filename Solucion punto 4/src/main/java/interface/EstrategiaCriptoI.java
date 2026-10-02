public class EstrategiaCriptoI extends ApexStore._IEstrategiaPagoDisp {
    @Override
    public void procesarPago(ApexStore.SolicitudPago solicitud, ApexStore.INotificadorContextoPrx notificador, Ice.Current current) {
        System.out.println("[Cripto] Iniciando procesamiento en Blockchain Wallet para orden: " + solicitud.idOrden);
        
        // Simulación de procesamiento en la pasarela externa
        ApexStore.ResultadoPago resultado = new ApexStore.ResultadoPago();
        resultado.idOrden = solicitud.idOrden;
        resultado.exitoso = true;
        resultado.mensajeReferencia = "Hash TX: 0xABC123...";

        // Notificación de vuelta al contexto, sin tocar la BD directamente
        System.out.println("[Cripto] Notificando resultado al contexto...");
        notificador.notificarTransaccionExitosa(resultado);
    }
}