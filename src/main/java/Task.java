import java.util.ArrayList;

public class Task {
    private boolean marked;
    private String description;
    private String charId;
    private String time;

    Task(String description, String charId, String time) {
        this.marked = false;
        this.description = description;
        this.charId = charId;
        this.time = time;
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
            System.out.println(output + toString());
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

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[" + getMark() + "] " + getUserInput();
    }
}