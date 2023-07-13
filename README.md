# BosonitRVM

# Comando ejecutado
PS C:\Users\rafael.vera\IdeaProjects\BosonitRVM\block3-maven-package\target> java -jar block3-maven-package-1.0-SNAPSHOT.jar


## Empaquetar proyecto Maven
Nombre proyecto Maven: block3-maven-package
Tiempo estimado: 3 horas

Crear un programa simple que imprima por consola: Hello world!.
Compilar nuestro proyecto con Maven desde IntelliJ. Esto nos deberá crear un fichero JAR, en el directorio ‘target’. Conseguir ejecutar el programa java desde línea de comandos con la instrucción:
java –jar XXX.jar
Hint: Para poder ejecutar nuestro JAR, hay que incluir este plugin en el fichero pom.xml.


Esto es porque para que Java sepa dónde está nuestro main, dentro del fichero JAR, hay que especificarlo de algún modo. Este plugin indica en qué clase está nuestra función main. En el ejemplo, es la clase Prueba que está en el paquete “com.bosonit.prueba”.

Recordar que Java deberá estar en el path de windows.
