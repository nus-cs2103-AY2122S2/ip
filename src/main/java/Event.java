public class Event extends Task {

    protected String at;
    private static boolean tipShown = false;

    public Event(String description) {
        super(description);
        if (!tipShown) {
            System.out.println("Tip: You can specify event location");
            System.out.println("eg. event party /at My House\n");
            tipShown = true;
        }
    }

    public Event(String description, String at) {
        super(description);
        this.at = at;
        tipShown = true;
    }

    @Override
    public String toString() {
        if (at != null) {
            return "[E]" + super.toString() + " (at: " + at + ")";
        } else {
            return "[E]" + super.toString();
        }
    }
}
