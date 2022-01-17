public class Event extends Task {
    private String dateTime;
    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String printStatus() {
        return "[E] " + Task.statusSymbols[super.getStatus()] + " " + this.toString() + " (at: " + this.dateTime + ")";
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
