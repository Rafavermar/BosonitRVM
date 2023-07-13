import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService1d readCsvService = new ReadCsvServiceImpl1d();
            List<Person1d> people = readCsvService.readPeopleFromCSV();

            Optional<Person1d> firstPersonFromBarcelona = people.stream()
                    .filter(person -> "Barcelona".equals(person.getTown()))
                    .findFirst();

            firstPersonFromBarcelona.ifPresent(person -> System.out.println(person.toString()));
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
