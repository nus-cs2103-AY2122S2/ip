import java.util.Arrays;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public static Todo of (String[] description) throws InvalidArgumentException {
        if(description.length == 1) {
            throw new InvalidArgumentException();
        }
        String name = String.join(" ", Arrays.copyOfRange(description, 1, description.length));
        return new Todo(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
