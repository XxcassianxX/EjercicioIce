package taller1_nastul_valdez_cifuentes;

import ApexStoreIce.IEstrategiaPago;
import ApexStoreIce.SolicitudPago;
import ApexStoreIce.ResultadoPago;
import com.zeroc.Ice.Current;

class EstrategiaPSEI implements IEstrategiaPago {
    @Override
    public ResultadoPago procesarPago(SolicitudPago solicitud, Current current) {
        System.out.println("[PSE] Procesando débito bancario para la orden: " + solicitud.ordenId);
        return new ResultadoPago(solicitud.ordenId, true, "PSE-TXN-4421", "Débito bancario exitoso");
    }
}
