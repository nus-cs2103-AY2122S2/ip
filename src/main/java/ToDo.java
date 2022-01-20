class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    public ToDo(String task, boolean complete) {
        super(task, complete);
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[T][x] " + super.getTaskName();
        } else {
            return "[T][ ] " + super.getTaskName();
        }
    }
}
