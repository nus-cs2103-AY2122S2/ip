import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate by;

    Deadline(String name, String by) {
        super(name);
        if (dateValidator(by)) {
            this.by = LocalDate.parse(by);
        } else {
            this.by = LocalDate.now();
        }
    }

    private Boolean dateValidator(String date) {
        try {
            LocalDate.parse(date);
        } catch(DateTimeParseException e) {
            return false;
        }
        return true;
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
        return String.format("[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")");
    }
}
