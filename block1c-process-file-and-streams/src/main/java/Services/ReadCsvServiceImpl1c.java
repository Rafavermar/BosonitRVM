import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadCsvServiceImpl1c implements ReadCsvService1c {
    private static final String CSV_FILE_PATH = "block1-process-file-and-streams/src/main/resources/people.csv";

    @Override
    public List<Person1c> readPeopleFromCSV() throws IOException {
        List<Person1c> people = new ArrayList<>();

        Path filePath = Paths.get(CSV_FILE_PATH);
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            try {
                Person1c person = parsePersonFromLine(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                String errorMessage = "Error en la línea: " + line + " -> " + e.getMessage();
                people.add(new Person1c(null, null, null, errorMessage));
            }
        }

        List<Person1c> filteredPeople = people.stream()
                .filter(person -> person.getAge() != null && person.getAge() < 25)
                .collect(Collectors.toList());

        Optional<Person1c> firstPersonFromMadrid = filteredPeople.stream()
                .filter(person -> person.getTown().equals("Madrid"))
                .findFirst();

        return firstPersonFromMadrid.map(Collections::singletonList).orElse(filteredPeople);
    }


    private Person1c parsePersonFromLine(String line) throws InvalidLineFormatException {
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

        return new Person1c(name, Optional.ofNullable(town).orElse(""), age);
    }
}


