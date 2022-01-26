public class TodoCommand extends Command {

    protected Task todo;

    public TodoCommand(String description) {
        this.todo = new Todo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.add(todo);
            storage.store(tasks);
            ui.showResponseMessage("todo");
            ui.showTaskMessage(todo);
            ui.printTasksCountMessage(tasks);
        } catch (DukeException e) {
            // e.printStackTrace();
        }
    }

}
