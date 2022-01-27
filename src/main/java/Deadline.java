import java.util.Arrays;
import java.util.List;

public class Deadline extends Task {
    private final String by;

    Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline of(String[] description) throws InvalidArgumentException {
        return Deadline.of(Arrays.asList(description));
    }

    public static Deadline of(List<String> description) throws InvalidArgumentException {
        if(description.size() == 1) {
            throw new InvalidArgumentException();
        }
        int index = description.indexOf("/by");
        String name = String.join(" ", description.subList(1, index));
        String by = String.join(" ", description.subList(index + 1, description.size()));
        return new Deadline(name, by);
    }

    @Override
    public String toStorageString() {
        String status = getStatus()? "X" : ".";
        return String.format(status + " deadline " + getName() + " /by " + by);
    }

    @Override
    public String toString() {
        return String.format("[D]" + super.toString() + "(by: " + by + ")");
    }
}
