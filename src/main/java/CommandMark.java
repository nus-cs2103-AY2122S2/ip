public class CommandMark extends Command{
    private final int taskNum;

    public CommandMark(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.mark(this.taskNum);
        storage.save(tasks);
        ui.showTaskMarked();
    }
}
