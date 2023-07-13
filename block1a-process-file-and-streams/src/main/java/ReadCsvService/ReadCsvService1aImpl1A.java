package ReadCsvService;

import Entities.Person1a;
import Exceptions.InvalidLineFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadCsvService1aImpl1A implements ReadCsvService1a {
    private static final String CSV_FILE_PATH = "block1-process-file-and-streams/src/main/resources/people.csv";

    @Override
    public List<Person1a> readPeopleFromCSV() throws IOException {
        List<Person1a> people = new ArrayList<>();

        Path filePath = Paths.get(CSV_FILE_PATH);
        List<String> lines = Files.readAllLines(filePath);

        for (String line : lines) {
            try {
                Person1a person = parsePersonFromLine(line);
                people.add(person);
            } catch (InvalidLineFormatException e) {
                String errorMessage = "Error en la línea: " + line + " -> " + e.getMessage();
                people.add(new Person1a(null, null, null, errorMessage));
            }
        }

        List<Person1a> filteredPeople = people.stream()
                .filter(person -> person.getAge() != null && person.getAge() < 25)
                .collect(Collectors.toList());

        return filteredPeople;
    }


    private Person1a parsePersonFromLine(String line) throws InvalidLineFormatException {
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

        return new Person1a(name, Optional.ofNullable(town).orElse(""), age);
    }
}