public class TodoCommand extends Command {
    private final Todo todo;
    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(todo);
        ui.showAdd(todo, taskList);
        storage.updateList(taskList);
    }
}
