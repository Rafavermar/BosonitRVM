import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvServiceImpl implements ReadCsvService {
    private static final String CSV_FILE_PATH = "people.csv";

    @Override
    public List<Person> readPeopleFromCSV() throws IOException, InvalidLineFormatException {
        List<Person> people = new ArrayList<>();

        Path filePath = Paths.get(CSV_FILE_PATH);
        List<String> lines = Files.readAllLines(filePath);

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);

            try {
                Person person = parsePersonFromLine(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                throw new InvalidLineFormatException("Invalid format at line " + i + ": " + line, e);
            }
        }

        return people;
    }

    private Person parsePersonFromLine(String line) throws InvalidLineFormatException {
        String[] fields = line.split(":");

        if (fields.length < 1 || fields.length > 3) {
            throw new InvalidLineFormatException("Invalid number of fields", line);
        }

        String name = fields[0];
        String town = fields.length >= 2 ? fields[1] : "";
        int age = fields.length == 3 ? parseAge(fields[2]) : 0;

        return new Person(name, town, age);
    }

    private int parseAge(String ageStr) throws InvalidLineFormatException {
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            throw new InvalidLineFormatException("Invalid age format", e);
        }
    }


}
