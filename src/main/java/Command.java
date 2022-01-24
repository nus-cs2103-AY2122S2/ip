import java.util.Map;

public class Command {
    private final CommandType type;
    private final Map<String, String> params;

    public Command(CommandType type, Map<String, String> params) {
        this.type = type;
        this.params = params;
    }

    public CommandType getType() {
        return type;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
