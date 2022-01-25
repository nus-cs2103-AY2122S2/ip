class Event extends Task {

    private final String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toString() {
        if (super.getStatus() == 1) {
            return "[E][X] " + super.getName() + " (at: " + time + ")\n";
        } else {
            return "[E][ ] " + super.getName() + " (at: " + time + ")\n";
        }
    }
}
