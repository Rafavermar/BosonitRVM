# BosonitRVM



## Carga de propiedades

Nombre proyecto Maven: block5-properties
Tiempo estimado: 4 horas.
1) Realizar aplicación que tenga en el fichero ‘application.properties’ los siguientes valores:
   greeting=Hello world!
   my.number=100

El programa debe imprimir por consola:
El valor de greeting es: (valor de ‘greeting’)
El valor de my.number es: (valor de ‘my.number’)

Intentar leer el valor ‘new.property’, que no existe en application.properties. Deberá asignar a la variable el texto ‘new.property no tiene valor”.
Imprime por consola:
El valor de new.property es: (valor de ‘new.property’)

Poner la variable ‘new.property’ dentro del S.O.
Lanzar aplicación y ver como la presenta.

Comando en powerShell:

__setx new_property "no tiene valor"__

2) Hacer el ejercicio anterior, pero cambiando el fichero application.properties por “application.yml” (adaptando el formato) Deberían obtenerse los mismos resultados que anteriormente.
   Yml es abreviatura de YAML:
   Propiedades de Spring Boot: https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

3) Buscar la manera de leer la variable de entorno del Sistema Operativo “MYURL” mostrarla al arrancar el programa (con un CommandLineRunner).
4) Mostrar la variable MYURL2 que por defecto tenga un valor igual a “NO_tengo_valor” pero que si existe en el S.O. muestre lo que haya en el S.O.
