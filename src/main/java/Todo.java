public class Todo {

    private String todo;
    private boolean completed;

    public Todo(String todo) {
        this.todo = todo;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        StringBuilder status = new StringBuilder();
        if (this.completed) {
            status.append("[X] ");
        } else {
            status.append("[ ] ");
        }
        return status.append(this.todo).toString();
    }
}
