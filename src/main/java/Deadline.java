import java.util.Arrays;

public class Deadline extends Task {
    private final String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline of(String[] description) throws InvalidArgumentException {
        if(description.length == 1) {
            throw new InvalidArgumentException();
        }
        int index = Arrays.asList(description).indexOf("/by");
        String name = String.join(" ", Arrays.copyOfRange(description, 1, index));
        String by = String.join(" ", Arrays.copyOfRange(description, index + 1, description.length));
        return new Deadline(name, by);
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(by: " + by + ")");
    }
}
