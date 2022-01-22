public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public Deadline(String name, boolean isDone,String dateTime) {
        super(name, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateTime);
    }

    @Override
    public String toData() {
        return "D|" + super.toData() + "|" + dateTime;
    }
}
