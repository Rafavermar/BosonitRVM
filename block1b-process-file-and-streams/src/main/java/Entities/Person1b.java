import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person1b {
    private String name;
    private String town;
    private Integer age;
    private String errorMessage;

    public Person1b(String name, String town, Integer age) {
        this.name = name != null ? name : "";
        this.town = town != null ? town : "unknown";
        this.age = age != null && age > 0 ? age : null;
        this.errorMessage = null;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". Town: " + (town != null ? town : "unknown") + ". Age: " + (age != null ? age : "unknown");
    }

}