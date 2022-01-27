public abstract class Command {
    private String message;
    protected TaskList taskList;

    public Command() {

    }

    public void setMessage(String m) {
        message = m;
    }

    public String getMessage() {
        return message;
    }

    public void setTaskList(TaskList tl) {
        taskList = tl;
    }

    public abstract void executeCommand();
}
