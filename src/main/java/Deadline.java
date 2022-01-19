import java.util.StringTokenizer;

public class Deadline extends Task {
    String prefix = "[D]";
    String postfix;

    Deadline(String name, String date) {
        super(name);
        this.postfix = "("
                + new StringBuilder(date).insert(2, ":").toString()
                + ")";
    }

    @Override
    public String toString() {
        return prefix + super.toString() + " " + postfix;
    }
}
