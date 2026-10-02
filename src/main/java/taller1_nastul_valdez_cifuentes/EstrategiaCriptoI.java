package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.IEstrategiaPago;
import ApexStoreIce.SolicitudPago;
import ApexStoreIce.ResultadoPago;
import com.zeroc.Ice.Current;

class EstrategiaCriptoI implements IEstrategiaPago {
    @Override
    public ResultadoPago procesarPago(SolicitudPago solicitud, Current current) {
        System.out.println("[Cripto] Procesando blockchain wallet para la orden: " + solicitud.ordenId);
        // Ya no invoca la BD directamente; retorna el resultado al contexto
        return new ResultadoPago(solicitud.ordenId, true, "BTC-TXN-0x7F9B", "Transacción confirmada en Blockchain");
    }
}