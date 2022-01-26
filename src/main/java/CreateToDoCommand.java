/**
 * Responsible for the functionality needed when creating a ToDoo task.
 */
public class CreateToDoCommand extends Command{

    /**
     * ToDo task created.
     */
    private ToDo toDo;

    /**
     * Constructor to create CreateToDoCommand.
     * Creates a ToDo task.
     *
     * @param description string describing the task.
     */
    public CreateToDoCommand(String description) {
        super(CommandType.CREATE_TODO);
        toDo = new ToDo(description);
    }

    /**
     * Adds the ToDo task to task list, saves task list to file and
     * print out the response message via user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chatbot.
     * @param storage storage used by chatbot.
     * @throw DukeException File I/O exception thrown when saving file.
     */
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(toDo);
        storage.saveToFile(taskList);
        ui.showMessage("Got it! I've added this ToDo task:\n " + toDo + "\n"
                + taskList.sizeDescription());
    }
}
