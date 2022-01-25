import java.time.LocalDate;

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

    public void markTask(boolean mark, boolean show) {
        this.marked = mark;
        String output;

        if (show) {
            if (mark) {
                output = "Nice! I've marked this task as done:\n";
            } else {
                output = "OK, I've marked this task as not done yet:\n";
            }
            new Ui().echo(output + toString());
        }
    }

    public char getMark() {
        return this.marked ? 'X' : ' ';
    }

    public String getUserInput() {
        return this.description;
    }

    public String getCharId() {
        return this.charId;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[" + getMark() + "] " + getUserInput();
    }
}