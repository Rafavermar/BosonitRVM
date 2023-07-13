

import Services.ReadCsvService.ReadCsvService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvServiceImpl1b readCsvService = new ReadCsvServiceImpl1b();
            List<Person1b> people = readCsvService.readPeopleFromCSV();

            for (Person1b person : people) {
                System.out.println(person.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }

    }
}