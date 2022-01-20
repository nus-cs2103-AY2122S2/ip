class Event extends Task {
    private final String time;

    public Event(String task, String time) {
        super(task);
        this.time = time;
    }

    public Event(String task, boolean complete, String time) {
        super(task, complete);
        this.time = time;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[E][x] " + super.getTaskName() + "(at: " + this.time + ")";
        } else {
            return "[E][ ] " + super.getTaskName() + "(at: " + this.time + ")";
        }
    }
}
