public class Event extends Task {


    private String dueDate;

    public Event(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dueDate +")";
    }
}
