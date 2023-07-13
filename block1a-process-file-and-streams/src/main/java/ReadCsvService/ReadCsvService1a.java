package ReadCsvService;

import Entities.Person1a;

import java.io.IOException;
import java.util.List;

public interface ReadCsvService1a {
    List<Person1a> readPeopleFromCSV() throws IOException;
}
