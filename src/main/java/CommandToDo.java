public class CommandToDo extends Command {
    private final String name;

    public CommandToDo(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new TaskToDo(this.name);
        tasks.add(task);
        storage.save(tasks);
        ui.showTaskAdded();
    }
}
