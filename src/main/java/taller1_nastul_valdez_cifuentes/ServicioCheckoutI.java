package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.SolicitudPago;
import com.zeroc.Ice.Current;
import ApexStoreIce.ServicioCheckoutI;


class ServicioCheckoutServant implements ServicioCheckoutI {

    private final ProcesadorPagosContextoServant contexto = new ProcesadorPagosContextoServant();

    @Override
    public String gestionarComprasHttp(SolicitudPago solicitud, Current current) {
        System.out.println("[Servicio Checkout] Petición HTTP recibida para la orden: " + solicitud.ordenId);
        contexto.iniciarPagoOrden(solicitud, current);
        return "200 OK - Orden " + solicitud.ordenId + " procesada exitosamente.";
    }
}