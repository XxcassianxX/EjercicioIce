public class ProcesadorPagosContextoI extends ApexStore._INotificadorContextoDisp {
    @Override
    public void notificarTransaccionExitosa(ApexStore.ResultadoPago resultado, Ice.Current current) {
        System.out.println("[Contexto] Recibiendo notificación asíncrona...");
        if(resultado.exitoso) {
            System.out.println("[Contexto] Transacción " + resultado.idOrden + " exitosa. Detalle: " + resultado.mensajeReferencia);
            // Aquí el contexto asume la responsabilidad de llamar a la Base de Datos
            System.out.println("[Contexto] Invocando persistirTransaccionPostgres() sobre DB_PostgreSQL_Transacciones...");
        } else {
            System.out.println("[Contexto] Transacción fallida, actualizando estado de la orden.");
        }
    }
}