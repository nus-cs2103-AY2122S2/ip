class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(boolean isDone, String description) {
        super(description);
        if (isDone) {
            setDone();
        } else {
            setUndone();
        }
    }
}