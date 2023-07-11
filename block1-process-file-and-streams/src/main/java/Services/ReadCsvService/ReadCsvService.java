import java.io.IOException;
import java.util.List;
public interface ReadCsvService {
    List<Person> readPeopleFromCSV() throws IOException, InvalidLineFormatException;
}
