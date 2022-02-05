package duke.command;
import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.gui.Ui;

/**
 * AddCommand represents the user's actions of adding Todo, Event & Deadline tasks.
 */
public class AddCommand extends Command {

    private String description;

    /**
     * Initializes the AddCommand with the CommandType Enum & a task description.
     * @param commandType User requested CommandType Enum
     * @param description Task description
     */
    public AddCommand(CommandType commandType, String description) {
        super(commandType);
        this.description = description;
    }

    /**
     * The logic to execute the AddCommand
     * @param taskList The TaskList object containing existing tasks.
     * @param ui The Ui object for interacting with the user.
     * @param storage The Storage object for saving & loading tasks.
     * @throws DukeException Error if the user specifies an invalid task index.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        // check if the user's input is just whitespaces
        if (description.trim().length() == 0) {
            throw new DukeException("I'm sorry, your todo command is missing a task description. Please try again.");
        }

        Task task;
        String dukeResponse = "";

        switch (super.commandType) {
        case TODO:
            task = new Todo(description, false);
            taskList.add(task);
            dukeResponse = ui.showText("Noted. I've added this task: ") + ui.showTask(task.toString());
            break;
        case EVENT:
            // Fallthrough
        case DEADLINE:
            String[] taskDescriptionStrings = description.split(super.commandType.getRegex());
            String taskDescriptionText = taskDescriptionStrings[0].strip();
            String taskDescriptionTime = taskDescriptionStrings[1].strip();

            // check if the user's input is just whitespace
            if (taskDescriptionText.strip().length() == 0) {
                throw new DukeException("I'm sorry, your " + commandType
                        + " command is missing a task description. Please try again.");
            } else if (taskDescriptionTime.strip().length() == 0) {
                throw new DukeException("I'm sorry, your " + commandType
                        + " command is missing a time. Please try again.");
            }

            try {
                if (commandType.equals(CommandType.EVENT)) {
                    task = new Event(taskDescriptionText, taskDescriptionTime, false);
                } else {
                    task = new Deadline(taskDescriptionText, taskDescriptionTime, false);
                }

                taskList.add(task);
                dukeResponse = ui.showText("Noted. I've added this task: ") + ui.showText(task.toString());
            } catch (DukeException e) {
                dukeResponse = ui.showError(e.getMessage());
            }
            break;
        case BYE:
            // Fallthrough
        case FIND:
            // Fallthrough
        case LIST:
            // Fallthrough
        case DELETE:
            // Fallthrough
        case MARK:
            // Fallthrough
        case UNMARK:
            // Fallthrough
        default:
            throw new DukeException("unexpected command!");
        }

        storage.updateFileContents(taskList);
        return dukeResponse;
    }

    /**
     * A getter method to indicate if the chat session with Duke is active.
     * @return boolean indicating if the chat session is active or not.
     */
    @Override
    public boolean isActive() {
        return super.active;
    }

}
