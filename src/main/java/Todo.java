import java.io.FileWriter;
import java.io.IOException;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public void writeToFile(FileWriter fw) throws IOException {
        fw.write("T ; " + isDone + " ; " + description + System.lineSeparator());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
