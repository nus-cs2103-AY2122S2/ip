class Event extends Task {
    private final String time;

    public Event(String task, String time) {
        super(task, "E");
        this.time = time;
    }

    public Event(String task, boolean complete, String time) {
        super(task, "E", complete);
        this.time = time;
    }

    public String getTaskTime() {
        return this.time;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[" + super.getType() + "][x] " + super.getTaskName() + " (at: " + this.time + ")";
        } else {
            return "[" + super.getType() + "][ ] " + super.getTaskName() + " (at: " + this.time + ")";
        }
    }
}
