# BosonitRVM

# Comando ejecutado
PS C:\Users\rafael.vera\IdeaProjects\BosonitRVM\block3-maven-package\target> java -jar block3-maven-package-1.0-SNAPSHOT.jar


## CommandLineRunner
Nombre proyecto Maven: block5-command-line-runner
Tiempo estimado: 4 horas.
1) Realizar programa con tres funciones que se deberán ejecutar al arrancar el programa. Una mostrará el texto “Hola desde clase inicial”, otra escribirá “Hola desde clase secundaria” y la tercera función pondrá “Soy la tercera clase”. Se deberá utilizar @Postconstruct en la primera función y la interface CommandLineRunner en los dos siguientes. ¿En qué orden se muestran los mensajes? ¿Por qué?
   Por ejemplo:


   @SpringBootApplication
   public class Main {

   @Bean
   CommandLineRunner ejecutame()
   {
   return p ->
   {
   System.out.println("Linea a ejecutar cuando arranque");
   };
   }
   }

2) Modificar la tercera función para que imprima los valores pasados como parámetro al ejecutar el programa.
