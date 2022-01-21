class ToDo extends Task {

    public ToDo(String task) {
        super(task, "T");
    }

    public ToDo(String task, boolean complete) {
        super(task, "T", complete);

    }

    public String getTaskTime() {
        return "";
    }

    @Override
    public String toString() {
        if (super.isCompleted()) {
            return "[" + super.getType() + "][x] " + super.getTaskName();
        } else {
            return "[" + super.getType() + "][ ] " + super.getTaskName();
        }
    }
}
