public class Deadline extends WordListItem{
    static private final String SYMBOL = "[D]";
    private String datetime;

    public Deadline(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    public String getDatetime() {
        return this.datetime;
    }

    static public String getSymbol() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (by: " + this.datetime + ")";
    }
}
