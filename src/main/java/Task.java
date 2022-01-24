import java.util.List;
import java.util.ArrayList;

public abstract class Task {
    private String name;
    private int status;
    public String type;
    protected static String[] statusSymbols = new String[]{"[ ]", "[✓]"};

    public Task(String name) {
        this.name = name;
        this.status = 0;
    }

    public static Task addNewToDo(String input) {
        return new Todo(input);
    };

    public String getType() {
        return this.type;
    }

    public static Task addNewDeadline(String input, String date) {
        return new Deadline(input, date);
    }

    public static Task addNewEvent(String input, String dateTime) {
        return new Event(input, dateTime);
    }

    public String getName() { return this.name; }

    public int getStatus() { return this.status; }
    public abstract String printStatus();

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
