package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.IEstrategiaPago;
import ApexStoreIce.SolicitudPago;
import ApexStoreIce.ResultadoPago;
import com.zeroc.Ice.Current;

class EstrategiaStripeI implements IEstrategiaPago {
    @Override
    public ResultadoPago procesarPago(SolicitudPago solicitud, Current current) {
        System.out.println("[Stripe] Procesando tarjeta de crédito para la orden: " + solicitud.ordenId);
        return new ResultadoPago(solicitud.ordenId, true, "STRIPE-TXN-9981", "Pago aprobado con tarjeta");
    }
}