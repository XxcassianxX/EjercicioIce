module ApexStoreIce {

    // ESTRUCTURAS DE DATOS (Representan la información transferida)
    struct SolicitudPago {
        string ordenId;
        double monto;
        string metodoPago; // "STRIPE", "PSE", "CRIPTO"
        string detallesCliente;
    };

    struct ResultadoPago {
        string ordenId;
        bool exito;
        string codigoTransaccion;
        string mensaje;
    };

    // CONTRATO UNIFICADO (Patrón Strategy - Punto 3)
    interface IEstrategiaPago {
        ResultadoPago procesarPago(SolicitudPago solicitud);
    };

    // CONTRATO DEL CONTEXTO (Coordinación y Persistencia)
    interface ProcesadorPagosContextoI {
        void notificarTransaccionExitosa(ResultadoPago resultado);
        void iniciarPagoOrden(SolicitudPago solicitud);
    };

    // CONTRATO FRONTEND (Punto de entrada al Backend)
    interface ServicioCheckoutI {
        string gestionarComprasHttp(SolicitudPago solicitud);
    };
};