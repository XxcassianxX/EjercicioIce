package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.IEstrategiaPago;
import ApexStoreIce.SolicitudPago;
import ApexStoreIce.ResultadoPago;
import com.zeroc.Ice.Current;
import ApexStoreIce.ProcesadorPagosContextoI;

class ProcesadorPagosContextoServant implements ProcesadorPagosContextoI {

    private final IEstrategiaPago stripe = new EstrategiaStripeI();
    private final IEstrategiaPago pse = new EstrategiaPSEI();
    private final IEstrategiaPago cripto = new EstrategiaCriptoI();

    @Override
    public void iniciarPagoOrden(SolicitudPago solicitud, Current current) {
        System.out.println("\n[ProcesadorPagosContexto] Recibida orden " + solicitud.ordenId + " | Método: " + solicitud.metodoPago);

        IEstrategiaPago estrategiaSeleccionada;
        switch (solicitud.metodoPago.toUpperCase()) {
            case "STRIPE": estrategiaSeleccionada = stripe; break;
            case "PSE":    estrategiaSeleccionada = pse; break;
            case "CRIPTO": estrategiaSeleccionada = cripto; break;
            default: throw new IllegalArgumentException("Método de pago no soportado: " + solicitud.metodoPago);
        }

        // Invocación polimórfica a la estrategia
        ResultadoPago resultado = estrategiaSeleccionada.procesarPago(solicitud, current);

        // Notificación de la transacción exitosa hacia el contexto
        notificarTransaccionExitosa(resultado, current);
    }

    @Override
    public void notificarTransaccionExitosa(ResultadoPago resultado, Current current) {
        System.out.println("[ProcesadorPagosContexto] Notificación recibida para orden: " + resultado.ordenId);
        persistirTransaccionPostgres(resultado);
    }

    private void persistirTransaccionPostgres(ResultadoPago resultado) {
        System.out.println("[DB PostgreSQL] PERSISTIENDO TRANSACCIÓN EN BD: Orden=" + resultado.ordenId + 
                           " | TxnId=" + resultado.codigoTransaccion + " | Estado=CONFIRMADO");
    }
}