import java.time.LocalDate;
import java.util.ArrayList;

public class Task {
    private boolean marked;
    private String description;
    private String charId;
    private LocalDate date;

    Task(String description, String charId, LocalDate date) {
        this.marked = false;
        this.description = description;
        this.charId = charId;
        this.date = date;
    }

    public void markTask(boolean mark) {
        this.marked = mark;
        String output;

        if (mark) {
            output = "Nice! I've marked this task as done:\n";
        } else {
            output = "OK, I've marked this task as not done yet:\n";
        }
        System.out.println(output + toString());
    }

    public char getMark() {
        return this.marked ? 'X' : ' ';
    }

    public String getUserInput() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getMark() + "] " + getUserInput();
    }
}