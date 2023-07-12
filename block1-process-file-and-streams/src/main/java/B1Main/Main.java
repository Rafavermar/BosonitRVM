import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService readCsvService = new ReadCsvServiceImpl();
            List<Person> people = readCsvService.readPeopleFromCSV();

            for (Person person : people) {
                System.out.println("Name: " + person.getName() + " | Town: " + person.getTown() + " | Age: " + person.getAge());
                if (person.getErrorMessage() != null) {
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

