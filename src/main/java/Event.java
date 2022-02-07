public class Event extends WordListItem{
    static private final String SYMBOL = "[E]";
    private String datetime;

    public Event(String description, String datetime) {
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
        return SYMBOL + super.toString() + " (at: " + this.datetime + ")";
    }
}
