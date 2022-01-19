public class Deadline extends Task {
    String type = "[D]";
    String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return super.description + "(" + date + ")";
    }

    @Override
    public String track() {
        return this.type;
    }
}
