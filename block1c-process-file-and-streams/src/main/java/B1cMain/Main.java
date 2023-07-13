

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvServiceImpl1c readCsvService = new ReadCsvServiceImpl1c();
            List<Person1c> people = readCsvService.readPeopleFromCSV();

            Optional<Person1c> firstPersonFromMadrid = people.stream()
                    .filter(person -> person.getTown().equals("Madrid"))
                    .findFirst();

            firstPersonFromMadrid.ifPresent(person -> System.out.println(person.toString()));
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }



    }
}