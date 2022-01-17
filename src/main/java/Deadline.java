public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String printStatus() {
        return "[D] " + Task.statusSymbols[super.getStatus()] + " " + this.toString() + " (by: " + date + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}