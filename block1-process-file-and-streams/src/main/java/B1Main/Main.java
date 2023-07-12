import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService readCsvService = new ReadCsvServiceImpl();
            List<Person> people = readCsvService.readPeopleFromCSV();

            for (Person person : people) {
                System.out.println(person.toString());
                if (person.getErrorMessage() != null && !person.getErrorMessage().isEmpty()) {
                    System.out.println(person.getErrorMessage());
                }
                System.out.println();
            }


        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
/**
public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService readCsvService = new ReadCsvServiceImpl();
            List<Person> people = readCsvService.readPeopleFromCSV();

            people = people.stream()
                    .filter(person -> person.getAge() != null && person.getAge() < 25)
                    .collect(Collectors.toList());

            for (Person person : people) {
                String name = person.getName() != null ? person.getName() : "unknown";
                String town = person.getTown() != null ? person.getTown() : "unknown";
                String age = person.getAge() != null ? person.getAge().toString() : "unknown";

                System.out.println("Name: " + name + ". Town: " + town + ". Age: " + age);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

**/
