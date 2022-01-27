public class Deadline extends Task {
    protected DateTime dateTime;

    public Deadline(String description, String time) throws UltoiException {
        super(description);
        try {
            this.dateTime = new DateTime(time);
        } catch (UltoiException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime.toString() + ")";
    }
}
