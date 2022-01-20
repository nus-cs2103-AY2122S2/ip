class Deadline extends Task {
    private final String time;

    public Deadline(String task, String time) {
        super(task);
        this.time = time;
    }

    public Deadline(String task, boolean complete, String time) {
        super(task, complete);
        this.time = time;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[D][x] " + super.getTaskName() + "(by: " + this.time + ")";
        } else {
            return "[D][ ] " + super.getTaskName() + "(by: " + this.time + ")";
        }
    }
}
