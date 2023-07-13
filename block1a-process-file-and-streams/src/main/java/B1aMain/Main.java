package B1aMain;

import Entities.Person1a;
import ReadCsvService.ReadCsvService1aImpl1A;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService1aImpl1A readCsvService = new ReadCsvService1aImpl1A();
            List<Person1a> people = readCsvService.readPeopleFromCSV();

            for (Person1a person : people) {
                System.out.println(person.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
