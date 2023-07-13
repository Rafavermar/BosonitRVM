# BosonitRVM

## 1. Java Development Kit (JDK)
# Lectura de ficheros y filtrado con Stream

Crear un método que reciba la ruta de un fichero CSV (por ejemplo: people.csv) y devuelva una lista de personas. El formato del fichero es el siguiente:
Cada línea del fichero corresponde a una persona (clase Person).
Cada línea puede contener hasta 3 campos separados por dos puntos (:). Los campos serán los siguientes:
name:town:age

El campo name es obligatorio, el resto de campos son opcionales. Nota: cuando no se informe el campo age guardaremos la persona con 0 años. Si hay alguna línea en el fichero en la que el campo age sea 0, consideraremos que esa persona tiene edad desconocida.
Si cualquiera de las líneas no tiene formato correcto el método debe lanzar una excepción InvalidLineFormatException. Esta excepción debe incluir un mensaje descriptivo incluyendo la línea que provocó el error y la causa de la excepción.
Nota: no usamos Stream en este método porque no permite tratar correctamente la excepción InvalidLineFormatException. Stream no se recomienda cuando hay que tratar excepciones.


Requisitos:

Para crear el método que lee el fichero, no usar Stream, usar las clases NIO: https://www.baeldung.com/reading-file-in-java
Para crear el segundo método, usar Stream.
La ruta al fichero se le pasa al programa vía argumentos y será una ruta relativa.
Tratar la excepción InvalidLineFormatException capturándola desde el método que invoca la función, imprimiendo su mensaje e imprimiendo el Stack Trace.

Contenido de un fichero de ejemplo:

Jesús:Logroño:41
Andrés:Madrid:19
Ángel Mari:Valencia:
Laura Saenz::23
Fernando:Zaragoza
Sergio
:Sevilla:36
María Calvo::38
Roberto:Madrid:20
Carlos:Barcelona:37


Ejemplos de líneas incorrectas:

Fernando:Zaragoza  -> Falta el último delimitador de campo (:)
Sergio             -> Faltan dos delimitadores de campo
:Sevilla:36        -> El nombre es obligatorio. Hay 3 espacios en el campo y esto se considera como blank.

