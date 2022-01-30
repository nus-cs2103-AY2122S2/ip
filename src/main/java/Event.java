public class Event extends Task {
    String prefix = "[E]";
    String postfix;

    Event(String name, String date) {
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
