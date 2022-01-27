public class Event extends Task {
    private final String time;

    Event(String task, String time) {
        super(task, "E");
        this.time = time;
    }

    @Override
    String saveFormat() {
        return super.saveFormat() + "/" + this.time;
    }

    @Override
    public String toString() {
        return super.toString() + "(at: " + this.time + ")";
    }

}
