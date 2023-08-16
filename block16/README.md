# README: Arquitectura de Microservicios con Eureka y API Gateway
## Introducción
En la arquitectura de microservicios, las aplicaciones se descomponen en servicios independientes que interactúan entre sí. Esta guía se centra en cómo estos servicios comunican y descubren entre sí utilizando herramientas como Eureka y API Gateway en el ecosistema Spring Cloud.

## Descripción de Microservicios
- Servicio de clientes (clienteService): Gestiona la información de clientes. Interactúa con otros servicios para proporcionar datos relacionados con clientes.
- Servicio de Viajes (ViajeService): Encargado de toda la información relacionada con viajes.
- Servicio de Tickets (TicketService): Genera tickets basándose en información combinada de clienteService y ViajeService.
- Comunicación Entre Servicios: RestTemplate

Los servicios se comunican entre sí a través de llamadas HTTP, para lo cual utilizamos la clase RestTemplate de Spring. RestTemplate facilita la consumición de servicios web RESTful, proporcionando métodos para enviar solicitudes HTTP y recuperar la respuesta.

Por ejemplo, si el TicketService necesita obtener información de un cliente específico, usaría RestTemplate para hacer una llamada HTTP al clienteService.

## Eureka: Descubrimiento de Servicios
Eureka actúa como un servidor de registro y descubrimiento. Los microservicios se registran en él y cuando un servicio necesita comunicarse con otro, consulta a Eureka para obtener la dirección.

__Resolver el Problema de DNS:__ eureka.instance.preferIpAddress=true


En algunas redes, la resolución de nombres de host a través de DNS puede ser problemática. Para superar este problema y hacer que Eureka use direcciones IP en lugar de nombres de host, se configura la propiedad eureka.instance.preferIpAddress=true. Esto significa que, cuando un servicio se registra en Eureka, utiliza su dirección IP en lugar del nombre de host. Como resultado, las llamadas entre servicios utilizando RestTemplate son más fiables, ya que se basan en direcciones IP directas.

## API Gateway: Punto Único de Entrada
API Gateway sirve como punto de entrada para todas las solicitudes, proporcionando funciones adicionales como balanceo de carga, autenticación y filtrado. Las solicitudes de los clientes primero pasan por el API Gateway antes de ser redirigidas al servicio apropiado.

## Flujo de Trabajo:
Un cliente hace una solicitud para generar un ticket al API Gateway.
Basándose en la URL, el API Gateway redirige la solicitud al TicketService.
TicketService, para generar el ticket, necesita información de otros servicios. Usa RestTemplate para hacer llamadas HTTP a estos servicios.
Pero primero, RestTemplate consulta a Eureka para encontrar la dirección IP del servicio al que necesita llamar (gracias a eureka.instance.preferIpAddress=true).
Con la dirección IP obtenida, RestTemplate realiza la llamada al servicio requerido.
Una vez recopilada toda la información necesaria, TicketService genera el ticket y envía una respuesta al cliente a través del API Gateway.

## Procedimiento para Crear un Ticket
Para comprender mejor el flujo del sistema, veamos un escenario típico de cómo un cliente crea un ticket. A continuación, los pasos y endpoints correspondientes:

1º. Crear un Cliente:

Endpoint: http://localhost:8080/cliente
En este paso, se registra el cliente proporcionando detalles relevantes. El clienteService procesa y guarda esta información.



2º. Crear un Viaje:

Endpoint: http://localhost:8080/trip
Se introducen detalles como el destino, la fecha y otros datos del viaje. El ViajeService se encarga de crear un registro para este viaje.


3º. Asignar un Cliente a un Viaje:

Endpoint: http://localhost:8080/trip/addPassenger/1/2
Este es un paso crucial donde se vincula un cliente con un viaje específico. En este caso, el cliente con ID 1 es asignado al viaje con ID 2.


4º. Generar el Ticket:

Endpoint: http://localhost:8080/tickets/generateTicket/2/1
Con la información previamente establecida, ahora se genera un ticket. En este caso, para el cliente con ID 1 y el viaje con ID 2. El TicketService toma esta información y crea un ticket correspondiente.


5º. Consultar la Cantidad de Pasajeros de un Viaje (Opcional):

Endpoint: http://localhost:8080/trip/passenger/count/1
Este endpoint permite consultar cuántos pasajeros están asignados al viaje con ID 1. Es una función adicional para mantener un seguimiento de la ocupación del viaje.


## Construir y ejecutar Docker Compose

- En la terminal:

docker-compose build
docker-compose up

- Se debe actualizar la propiedad eureka.client.service-url.defaultZone en los archivos properties de los servicios para usar el nombre del servicio eureka-server en lugar de localhost:
  eureka.client.service-url.defaultZone=http://eureka-server:8761/eureka/

