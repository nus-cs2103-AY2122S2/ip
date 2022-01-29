import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        fw.write("D ; " + isDone + " ; " + description + " ; " + by + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}