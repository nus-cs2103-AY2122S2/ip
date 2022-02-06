public class Event extends Task {
    private final String sym = "E";
    private String dayAndTime;

    Event (String description, String dayAndTime) {
        super(description);
        this.dayAndTime = dayAndTime;
    }

    String getSym() {
        return this.sym;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at:%s)", sym, super.getStatusIcon(), super.getDescription(), this.dayAndTime);
    }
}
