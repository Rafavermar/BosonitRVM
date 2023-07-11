import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ReadCsvService readCsvService = new ReadCsvServiceImpl();
            List<Person> people = readCsvService.readPeopleFromCSV();


            for (Person person : people) {
                System.out.println("Name: " + person.getName());
                System.out.println("Town: " + person.getTown());
                System.out.println("Age: " + person.getAge());
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo csv:" + e.getMessage());
            e.printStackTrace();
        } catch (InvalidLineFormatException e) {
            System.err.println("Error de formato en una linea: " + e.getMessage() );
            e.printStackTrace();
        }
    }
}
