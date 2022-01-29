package duke;

class Task {
    enum Type {
        E, T, D
    }

    protected String description;
    protected boolean hasCompleted;

    public Task(String description) {
        this.description = description;
        hasCompleted = false;
    }

    String getStatus () {
        return hasCompleted ? "[X]" : "[ ]";
    }

    void markDone() {
        this.hasCompleted = true;
    }

    void unmarkDone () {
        this.hasCompleted = false;
    }

    public String getDescription(){
        return this.description;
    }

}