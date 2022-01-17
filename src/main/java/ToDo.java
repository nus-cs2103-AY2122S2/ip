public class ToDo extends Task{

    public ToDo(String taskName) {
        super (taskName);
    }

    @Override
    public String toString() {
        String done = getStatus() ? "[X]" : "[ ]";
        return "[T]" + done + getTaskName();
    }

}
