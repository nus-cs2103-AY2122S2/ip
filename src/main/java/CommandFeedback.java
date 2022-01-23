public class CommandFeedback {
    public final CommandType cType;
    public final TaskList taskList;
    public final Task task;

    public CommandFeedback(CommandType cType) {
        this(cType, null, null);
    }

    public CommandFeedback(CommandType cType, TaskList taskList) {
        this(cType, taskList, null);
    }

    public CommandFeedback(CommandType cType, Task task) {
        this(cType, null, task);
    }

    public CommandFeedback(CommandType cType, TaskList taskList, Task task) {
        this.cType = cType;
        this.taskList = taskList;
        this.task = task;
    }

}

