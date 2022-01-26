/**
 * Responsible for the functionality needed when marking a task.
 */
public class MarkCommand extends Command{

    /**
     * 1-based index of the task in the list.
     */
    private int point;

    /**
     * Constructor to create a Mark Command.
     *
     * @param point 1-based index of the task in the list.
     */
    public MarkCommand(int point) {
        super(CommandType.MARK);
        this.point = point;
    }

    /**
     * Marks the task as done, saves changes to file and prints ui message.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chatbot.
     * @param storage storage used by chatbot.
     * @throws DukeException exception thrown when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(point - 1);
        task.markAsDone();
        storage.saveToFile(taskList);
        ui.showMessage("Great! I have marked this task as done:\n " + task);
    }
}
