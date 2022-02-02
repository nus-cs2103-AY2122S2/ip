public class CommandUnmark extends Command{
    private final int taskNum;

    public CommandUnmark(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.unmark(this.taskNum);
        storage.save(tasks);
        ui.showTaskUnmarked();
    }
}
