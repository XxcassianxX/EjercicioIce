module ApexStore {
    // Estructuras de datos para la comunicación
    struct SolicitudPago {
        string idOrden;
        double monto;
        string metodoPago;
    };

    struct ResultadoPago {
        string idOrden;
        bool exitoso;
        string mensajeReferencia;
    };

    // Interfaz requerida por las pasarelas para notificar al contexto (Asíncrono)
    interface INotificadorContexto {
        void notificarTransaccionExitosa(ResultadoPago resultado);
    };

    // Interfaz provista unificada (Patrón Strategy)
    interface IEstrategiaPago {
        // Se pasa un proxy del notificador para el callback asíncrono
        void procesarPago(SolicitudPago solicitud, INotificadorContexto* notificador);
    };
};