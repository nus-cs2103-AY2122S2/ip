public class Deadline extends WordListItem{
    static private final String SYMBOL = "[D]";
    private String datetime;

    public Deadline(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (by: " + this.datetime + ")";
    }
}
