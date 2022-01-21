class Deadline extends Task {
    private final String time;


    public Deadline(String task, String time) {
        super(task, "D");
        this.time = time;
    }

    public Deadline(String task, boolean complete, String time) {
        super(task, "D", complete);
        this.time = time;

    }

    public String getTaskTime() {
        return this.time;
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[" + super.getType() + "][x] " + super.getTaskName() + " (by: " + this.time + ")";
        } else {
            return "[" + super.getType() + "][ ] " + super.getTaskName() + " (by: " + this.time + ")";
        }
    }
}
