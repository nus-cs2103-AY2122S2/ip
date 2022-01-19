public class Todo extends Task {
    public Todo() {
        super();
    }

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        sb.setLength(0);
        sb.append("[T]").append("[").append(this.isDone ? "X] " : " ] ").append(this.taskDescription);

        return sb.toString();
    }
}
