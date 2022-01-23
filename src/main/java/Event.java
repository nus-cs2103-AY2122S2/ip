public class Event extends Task {
    String at;
    public Event(String activity, String at) {
        super(activity, "E");
        this.at = at;
    }

    @Override
    public void getStatus() {
        if (this.status == 0) {
            System.out.println("[" + type + "][ ] " + activity + " (at " + at + " )");
        } else {
            System.out.println("[" + type + "][X] " + activity + " (at " + at + " )");
        }
    }
}
