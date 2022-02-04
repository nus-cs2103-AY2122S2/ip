public class AddTodoCommand extends Command {
    private final ToDo newTodo;

    AddTodoCommand(ToDo newTodo) {
        this.newTodo = newTodo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks = tasks.add(newTodo);
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
