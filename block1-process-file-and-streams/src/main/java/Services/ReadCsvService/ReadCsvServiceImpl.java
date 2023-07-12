import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ReadCsvServiceImpl implements ReadCsvService {
    private static final String CSV_FILE_PATH = "block1-process-file-and-streams/src/main/resources/people.csv";

    @Override
    public List<Person> readPeopleFromCSV() throws IOException {
        List<Person> people = new ArrayList<>();

        Path filePath = Paths.get(CSV_FILE_PATH);
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            try {
                Person person = parsePersonFromLine(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                String errorMessage = "Error en la línea: " + line + " -> " + e.getMessage();

                String[] fields = line.split(":");
                String name = fields[0].trim();
                String town = null;
                Integer age = null;
                if (fields.length > 1) {
                    town = fields[1].trim();
                }
                if (fields.length > 2) {
                    String ageStr = fields[2].trim();
                    if (ageStr.matches("\\d+")) {
                        age = Integer.parseInt(ageStr);
                    }
                }

                people.add(new Person(name, town, age, errorMessage));
            }
        }


        return people;
    }

    private Person parsePersonFromLine(String line) throws InvalidLineFormatException {
        String[] fields = line.split(":");

        if (fields.length != 3) {
            throw new InvalidLineFormatException("La línea no tiene el formato correcto");
        }

        String name = fields[0].trim();
        String town = fields[1].trim();
        String ageStr = fields[2].trim();

        if (name.isBlank()) {
            throw new InvalidLineFormatException("El nombre es obligatorio");
        }
        if (!ageStr.matches("\\d+")) {
            throw new InvalidLineFormatException("El formato de edad no es válido");
        }

        int age = Integer.parseInt(ageStr);

        return new Person(name, Optional.ofNullable(town).orElse(""), age);
    }
}