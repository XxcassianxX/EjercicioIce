package taller1_nastul_valdez_cifuentes;

public class ServidorApexStore {
    public static void main(String[] args) {
        try (com.zeroc.Ice.Communicator communicator = com.zeroc.Ice.Util.initialize(args)) {
            com.zeroc.Ice.ObjectAdapter adapter = 
                communicator.createObjectAdapterWithEndpoints("ApexStoreAdapter", "default -p 10000");

            // Registro del Servant de Checkout en el Adaptador ICE
            adapter.add(new ServicioCheckoutServant(), com.zeroc.Ice.Util.stringToIdentity("ServicioCheckout"));
            adapter.activate();

            System.out.println("====================================================");
            System.out.println(">>> Servidor ApexStore ICE Activo en el puerto 10000...");
            System.out.println("====================================================");
            communicator.waitForShutdown();
        }
    }
}
