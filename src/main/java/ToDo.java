import java.util.Map;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(Map<String,String> args) {
        this(args.get("description"));
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
