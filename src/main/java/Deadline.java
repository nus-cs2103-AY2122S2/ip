import java.util.StringTokenizer;

public class Deadline extends Task {
    private final String postfix;

    Deadline(String name, String date) {
        super(name);
        this.postfix = DateManager.formatDate(date);
    }

    public String getPrefix() {
        return "D";
    }

    public String getPostfix() {
        return this.postfix;
    }

    @Override
    public String toString() {
        String prefix = "[D]";
        return prefix + super.toString() + " " + postfix;
    }
}
