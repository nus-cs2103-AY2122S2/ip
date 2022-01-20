public class Deadline extends Task {
    protected String dateAndTime;

    public Deadline(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + super.description + "(by:" + this.dateAndTime + ")";
    }
}
