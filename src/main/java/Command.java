public abstract class Command {
    protected TaskList taskList;

    public abstract String execute();

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }
}
