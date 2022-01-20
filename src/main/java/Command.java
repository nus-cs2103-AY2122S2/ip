import java.util.HashMap;
import java.util.Map;

public class Command {
    private String name;
    private String argument;
    private Map<String, String> kwargs;

    public Command(String name) {
        this(name, "", new HashMap<>());
    }

    public Command(String name, String arg) {
        this(name, arg, new HashMap<>());
    }

    public Command(String name, String arg, Map<String, String> kwargs) {
        this.name = name;
        this.argument = arg;
        this.kwargs = kwargs;
    }

    public String getName() {
        return this.name;
    }

    public String getArgs() {
        return this.argument;
    }

    public Map<String, String> getKwargs() {
        return this.kwargs;
    }
}
