import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person1d {
    private String name;
    private String town;
    private Integer age;
    private String errorMessage;

    public Person1d(String name, String town, Integer age) {
        this.name = name != null ? name : "";
        this.town = town != null ? town : "";
        this.age = age != null ? age : 0;
        this.errorMessage = null;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". Town: " + (town != null ? town : "unknown") + ". Age: " + (age != null ? age : "unknown");
    }

}