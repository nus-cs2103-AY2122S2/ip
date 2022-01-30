public class Event extends WordListItem{
    static private final String SYMBOL = "[E]";
    private String datetime;

    public Event(String description, String datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + " (at: " + this.datetime + ")";
    }
}
