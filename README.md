Los pasos a seguir son los siguientes

1. slice2java --output-dir app/src/main/java app/src/main/slice/ApexStore.ice
## PowerShell
2. .\gradlew compileJava

3. . Identificar la ubicación exacta del archivo ice.jar
Verifica dónde se encuentra el archivo JAR de la librería de ZeroC ICE.

Opción A (Caché local de Gradle):

C:\Users\<TuUsuario>\.gradle\caches\modules-2\files-2.1\com.zeroc\ice\3.7.11\<hash>\ice-3.7.11.jar

Opción B (Instalación local en Windows):

C:\Program Files\ZeroC\Ice-3.7.11\lib\ice-3.7.11.jar

4. Ejecutar el Servidor ICE (Terminal 1)
## PowerShell

java -cp "app/build/classes/java/main;C:\Users\<TuUsuario>\.gradle\caches\modules-2\files-2.1\com.zeroc\ice\3.7.11\<hash>\ice-3.7.11.jar" taller1_nastul_valdez_cifuentes.ServidorApexStore

5. Ejecutar el Cliente ICE (Terminal 2)
## PowerShell
java -cp "app/build/classes/java/main;C:\Users\<TuUsuario>\.gradle\caches\modules-2\files-2.1\com.zeroc\ice\3.7.11\<hash>\ice-3.7.11.jar" taller1_nastul_valdez_cifuentes.ClienteCheckout

======================================================================
1. COMPILACIÓN DE ARCHIVOS SLICE (slice2java)
======================================================================
$ slice2java ApexStore.ice
-> Compilación exitosa. Clases generadas en el paquete ApexStoreIce/

======================================================================
2. CONSOLA DEL SERVIDOR (ServidorApexStore)
======================================================================
$ java ServidorApexStore
====================================================
>>> Servidor ApexStore ICE Activo en el puerto 10000...
====================================================

[Servicio Checkout] Petición HTTP recibida para la orden: ORD-101
[ProcesadorPagosContexto] Recibida orden ORD-101 | Método: STRIPE
  [Estrategia Stripe] Procesando Tarjeta de Crédito para la orden: ORD-101
[ProcesadorPagosContexto] Notificación recibida para orden: ORD-101
[DB PostgreSQL] PERSISTIENDO TRANSACCIÓN EN BD: Orden=ORD-101 | TxnId=STRIPE-TXN-9981 | Estado=CONFIRMADO

[Servicio Checkout] Petición HTTP recibida para la orden: ORD-102
[ProcesadorPagosContexto] Recibida orden ORD-102 | Método: PSE
  [Estrategia PSE] Procesando Débito Bancario para la orden: ORD-102
[ProcesadorPagosContexto] Notificación recibida para orden: ORD-102
[DB PostgreSQL] PERSISTIENDO TRANSACCIÓN EN BD: Orden=ORD-102 | TxnId=PSE-TXN-4421 | Estado=CONFIRMADO

[Servicio Checkout] Petición HTTP recibida para la orden: ORD-103
[ProcesadorPagosContexto] Recibida orden ORD-103 | Método: CRIPTO
  [Estrategia Cripto] Procesando Blockchain Wallet para la orden: ORD-103
[ProcesadorPagosContexto] Notificación recibida para orden: ORD-103
[DB PostgreSQL] PERSISTIENDO TRANSACCIÓN EN BD: Orden=ORD-103 | TxnId=BTC-TXN-0x7F9B | Estado=CONFIRMADO

======================================================================
3. CONSOLA DEL CLIENTE (ClienteCheckout)
======================================================================
$ java ClienteCheckout
=== INICIANDO PRUEBAS DE EXTREMO A EXTREMO (APEXSTORE) ===
Respuesta Servidor: 200 OK - Orden ORD-101 procesada exitosamente.

Respuesta Servidor: 200 OK - Orden ORD-102 procesada exitosamente.

Respuesta Servidor: 200 OK - Orden ORD-103 procesada exitosamente.

=== PRUEBAS FINALIZADAS EXITOSAMENTE ===