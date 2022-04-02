package command;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;
import tsundere.Storage;
import tsundere.TsundereException;

/**
 * Add a new task into tasklist and storage saves it.
 */
public class AddCommand extends Command {
    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    private static final int MIN_SPACE_NUM = 2;
    protected Type type;
    protected String body;
    /**
     * Creates a new AddCommand.
     *
     * @param s string to determine Type of command.
     * @param body string description of the task.
     */
    public AddCommand(String s, String body) {
        switch (s) {
        case "TODO":
            this.type = Type.TODO;
            break;
        case "DEADLINE":
            this.type = Type.DEADLINE;
            break;
        case "EVENT":
            this.type = Type.EVENT;
            break;
        default:
        }
        this.body = body;
    }

    /**
     * Executes the add command to add the task into the tasklist and stores it using the Storage.
     *
     * @param t TaskList for managing and adding tasks.
     * @param s Storage for saving to file.
     * @throws TsundereException for incorrect input format.
     */
    public String execute(TaskList t, Storage s) throws TsundereException {
        switch (this.type) {
        case TODO:
            t.addTask(new ToDo(body));
            break;
        case DEADLINE:
            t.addTask(checkFormatDeadline());
            break;
        case EVENT:
            t.addTask(checkFormatEvent());
            break;
        default:
        }

        s.saveFile(t.tasksToString());
        return ("New task! You better do it.\n" + t.getTaskStr(t.getCount())
                + "\nYou have " + t.getCount() + " task(s) to do you lazy bum!");

    }

    /**
     * Determines if the class is ExitCommand.
     *
     * @return false always because it is not an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if the input format is correct then return new Deadline
     *
     * @return a new Deadline
     * @throws TsundereException if format is incorrect
     */
    public Deadline checkFormatDeadline() throws TsundereException {
        String[] splitStr;
        splitStr = body.split("/by");
        if (splitStr.length < MIN_SPACE_NUM) {
            throw new TsundereException("Hmph you baka, gimme a correct format."
                    + " For example, deadline sleep/by 2019-01-15");
        }

        return new Deadline(splitStr[0], splitStr[1]);
    }

    /**
     * Checks if the input format is correct then return new Event
     *
     * @return a new Event
     * @throws TsundereException if format is incorrect
     */
    public Event checkFormatEvent() throws TsundereException {
        String[] splitStr;
        splitStr = body.split("/at");
        if (splitStr.length < MIN_SPACE_NUM) {
            throw new TsundereException("Hmph you baka, gimme a correct format. "
                    + "For example, event sleep/at 2019-01-15");
        }
        return new Event(splitStr[0], splitStr[1]);
    }
}
