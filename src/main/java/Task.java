import java.util.ArrayList;

public class Task implements makeCompactable {
    String task;
    Boolean done;
    String initials;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String string;

        if (this.done) {
            string = "[X] " + task;
        } else {
            string = "[ ] " + task;
        }

        return string;
    }

    public ArrayList<String> makeCompact() {
        ArrayList<String> out = new ArrayList<>();

        out.add(this.initials);
        if (done) {
            out.add("1");
        } else {
            out.add("0");
        }
        out.add(task);
        return out;
    }
}
