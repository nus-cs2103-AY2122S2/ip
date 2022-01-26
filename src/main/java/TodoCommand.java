public class TodoCommand extends TaskCommand {

    public TodoCommand(String key) {
        super(key);
    }

    @Override
    public void execute(String input, TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task newTask = null;
        String taskDescription = getTaskDescription(input);

        newTask = new Todo(taskDescription);
        updateTaskList(newTask, taskList, storage, ui);
    }
}
