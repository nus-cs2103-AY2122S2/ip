public class EventTask extends Task {

    private final String date;

    public EventTask(String name, String date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s %s",
                getStatusIcon(), name, String.format(" (at: %s)", this.date));
    }

}
