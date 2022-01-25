import java.time.LocalDate;

public class Task {
    protected boolean marked;
    protected String description;
    private LocalDate date;

    Task(String description, LocalDate date) {
        this.marked = false;
        this.description = description;
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