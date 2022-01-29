public class Todo extends WordListItem{
    static private final String SYMBOL = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
