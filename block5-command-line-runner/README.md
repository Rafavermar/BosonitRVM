# BosonitRVM

# Comando ejecutado
PS C:\Users\rafael.vera\IdeaProjects\BosonitRVM\block3-maven-package\target> java -jar block3-maven-package-1.0-SNAPSHOT.jar


## CommandLineRunner
Nombre proyecto Maven: block5-command-line-runner
Tiempo estimado: 4 horas.
1) Realizar programa con tres funciones que se deberán ejecutar al arrancar el programa. Una mostrará el texto “Hola desde clase inicial”,
otra escribirá “Hola desde clase secundaria” y la tercera función pondrá “Soy la tercera clase”. Se deberá utilizar @Postconstruct en la primera función y
la interface CommandLineRunner en los dos siguientes. 

__¿En qué orden se muestran los mensajes? ¿Por qué?__

   Se muestra primero el mensaje desde la clase inicial,
   seguido del mensaje desde la clase secundaria y 
   por último el mensaje desde la tercer la clase.

   Soprendentemente al no añadir la anotacion @PostConstruct a la función inicial,
   éste no se muestra en consola. Mostrandose solo la funcion Secundaria y Terciaria.

   La anotación @PostConstruct sobre una función indica a spring, que esa función se ejecutará
   una vez antes de la inicialización del  primer Bean.

/**
apuntando al directorio target
java -jar block5-command-line-runner-1.0-SNAPSHOT.jar valor1 valor2 valor3
**/

2) Modificar la tercera función para que imprima los valores pasados como parámetro al ejecutar el programa.
