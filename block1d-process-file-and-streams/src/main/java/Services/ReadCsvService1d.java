import java.io.IOException;
import java.util.List;

public interface ReadCsvService1d {
    List<Person1d> readPeopleFromCSV() throws IOException;
}

