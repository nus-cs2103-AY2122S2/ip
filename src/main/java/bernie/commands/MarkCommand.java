package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

/**
 * MarkCommand is responsible for getting TaskList to mark/unmark the respective task number provided
 * by the user input
 */
public class MarkCommand extends Command {
    String[] parsedArr;

    /**
     * Constructs a MarkCommand Class. The parsedArr is determined by the parser,
     * giving out the relevant arguments required to execute mark/unmark.
     * @param tasks TaskList, contains all our Task we create
     * @param uiHandler UiHandler, responsible for printing out messages to the user for any action done
     * @param storage Storage, responsible for saving and loading of tasks into a text file
     * @param parser Parser, helps to parse user inputs to perform subsequent actions
     * @param input String, user input into the program
     * @throws InvalidArgumentException For a correct command with invalid arguments given by the user
     */
    public MarkCommand(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input)
            throws InvalidArgumentException {
        super(tasks, uiHandler, storage, parser, input);
        this.parsedArr = parser.getParams(Type.MARK, input);
    }

    /**
     * Calls the TasList to mark or unmark a task number depending on the parsedArr
     * which is the parsed user input
     */
    public void execute() {
        String action = parsedArr[0];
        String taskNum = parsedArr[1];
        if (action.equals("mark")) {
            Task markedTask = tasks.markTask(Type.MARK, taskNum);
            uiHandler.showDoneMsg(markedTask);
        } else if (action.equals("unmark")) {
            Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
            uiHandler.showUndoneMsg(unmarkedTask);
        }
    }
}
