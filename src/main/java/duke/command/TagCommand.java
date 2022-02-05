package duke.command;

import duke.exception.DukeException;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;
import duke.task.Task;

public class TagCommand extends Command {
    /**
     * The tag name to tag the task with.
     */
    private String tag;
    /**
     * The task to be tagged.
     */
    private int taskNumber;
    /**
     * To store any exceptions that were thrown during the parsing of the command.
     */
    private DukeException exception;

    /**
     * Initializes a new tag command.
     *
     * @param fullCommand
     */
    public TagCommand(String fullCommand) {
        super(fullCommand);
        String[] fullCommandSplit = fullCommand.split(" ");

        if (fullCommandSplit.length != 3) {
            this.exception = new DukeException(
                    "Please enter the command in the following format (eg. 'tag 1 #tagName')"
            );
            return;
        }

        try {
            this.taskNumber = Integer.parseInt(fullCommandSplit[1]);
            this.tag = fullCommandSplit[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.exception = new DukeException("Please input an item number when tagging tasks (eg. 'tag 1 #tagName')");
        } catch (NumberFormatException e) {
            this.exception = new DukeException("Please only input integers when tagging tasks (eg. 'tag 1 #tagName");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.exception != null) {
            throw this.exception;
        } else if (this.taskNumber <= 0 || this.taskNumber > tasks.getSize()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task task = tasks.getByNumber(this.taskNumber);
        task.addTag(this.tag);
        String output = ui.printReturn(
                String.format(
                        "Successfully tagged task %d with #%s",
                        this.taskNumber,
                        this.tag));

        return output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
