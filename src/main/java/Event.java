import java.util.Arrays;

public class Event extends Task {
    private final String at;

    Event(String name, String at) {
        super(name);
        this.at = at;
    }

    public static Event of (String[] description) throws  InvalidArgumentException {
        if(description.length == 1) {
            throw new InvalidArgumentException();
        }
        int index = Arrays.asList(description).indexOf("/at");
        String name = String.join(" ", Arrays.copyOfRange(description, 1, index));
        String at = String.join(" ", Arrays.copyOfRange(description, index + 1, description.length));
        return new Event(name, at);
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + "(at: " + at + ")");
    }
}
