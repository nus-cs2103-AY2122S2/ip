public class Deadline extends Task{
    private final String dateTime;

    String getDateTime() {
        return dateTime;
    }

    Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDateTime() + ")";
    }
}
