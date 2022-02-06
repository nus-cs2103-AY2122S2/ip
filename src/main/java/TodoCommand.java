/**
 * TodoCommand class is a Command that contains instructions
 * to run when user wants to add a new Todo task.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructor of TodoCommand.
     *
     * @param body Name of the task.
     */
    public TodoCommand(String body) {
        this.description = body;
    }

    /**
     * Creates a new Todo task and store it
     * into the task list and feedback to the user.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @throws JeffException When file cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        Task currTask = new Todo(description);
        tasks.add(currTask);
        ui.showAdded(currTask.toString(), tasks.size());
        storage.save(tasks);
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
