public class Event extends Task {
    private final String postfix;

    Event(String name, String date) {
        super(name);
        this.postfix = DateManager.formatDate(date);
    }

    public String getPrefix() {
        return "E";
    }

    public String getPostfix() {
        return this.postfix;
    }

    @Override
    public String toString() {
        String prefix = "[E]";
        return prefix + super.toString() + " " + postfix;
    }
}
