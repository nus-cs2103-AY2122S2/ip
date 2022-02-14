package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import java.io.IOException;
import funbox.util.Parser;

/**
 * Deal with handling command for Mark.
 */
public class TagCommand extends Command {
    private String description;
    private Parser parser;
    private final String errorMessage = "`tag` command usage: tag <index> <tag>";

    /**
     * The constructor for TagCommand.
     *
     * @param description The description of the task which contains the index and tag.
     */
    public TagCommand(String description) {
        super(false);
        this.description = description;
        this.parser = new Parser();
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Execute the command to mark the tasks in the list.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If (index - 1) > taskList.getSize() or (index - 1) < 0.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions, IOException {
        String result = "";

        String[] resultsArr = parser.formatCommands(description);

        if (resultsArr.length < 2 || !(isInteger(resultsArr[0]))) {
            throw new FunBoxExceptions(errorMessage);
        }

        int index = Integer.parseInt(resultsArr[0]);

        boolean isGreaterThanList = (index - 1) > taskList.getSize();
        boolean isNegative = (index - 1) < 0;

        if (isGreaterThanList) {
            throw new FunBoxExceptions("The index entered is larger than the list!");
        }

        if (isNegative) {
            throw new FunBoxExceptions("The index cannot be negative!");
        }

        int currIndex = index - 1;
        String tag = parser.concatFormat(resultsArr, 1);
        taskList.addTagToTask(currIndex, tag);
        storage.addTagToTask(currIndex, tag);

        return taskList.getTask(ui, currIndex);
    }
}
