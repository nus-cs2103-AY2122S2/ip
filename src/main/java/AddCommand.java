public abstract class AddCommand extends Command {
    private final Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public CommandFeedback execute(TaskList taskList) {
        taskList.add(newTask);
        return new CommandFeedback(CommandType.ADD, taskList, newTask);
    }
}
