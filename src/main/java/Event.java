public class Event extends Task {
    String prefix = "[D]";
    String postfix;

    Event(String name, String date) throws EmptyDescriptionException {
        super(name);
        try {
            this.postfix = "("
                    + new StringBuilder(date).insert(2, ":").toString()
                    + ")";
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("");
        }
    }

    @Override
    public String toString() {
        return prefix + super.toString() + " " + postfix;
    }
}
