public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + at + ")";
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
        System.out.println(super.markAsDoneFeedback + this);
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
        System.out.println(super.markAsUndoneFeedback + this);
    }
}