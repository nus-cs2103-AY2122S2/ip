package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.InputResponder;

/**
 * MarkCommand is responsible for getting TaskList to mark/unmark the respective task number provided
 * by the user input
 */
public class MarkCommand extends Command {
    private final String[] parsedArr;

    /**
     * Constructs a MarkCommand Class. The parsedArr is determined by the parser,
     * giving out the relevant arguments required to execute mark/unmark.
     * @param tasks TaskList, contains all our Task we create
     * @param inputResponder InputResponder, responsible for printing out messages to the user for any action done
     * @param storage Storage, responsible for saving and loading of tasks into a text file
     * @param parser Parser, helps to parse user inputs to perform subsequent actions
     * @param input String, user input into the program
     * @throws InvalidArgumentException For a correct command with invalid arguments given by the user
     */
    public MarkCommand(TaskList tasks, InputResponder inputResponder, Storage storage, Parser parser, String input)
            throws InvalidArgumentException {
        super(tasks, inputResponder, storage, parser, input);
        this.parsedArr = parser.getParams(Type.MARK, input);
    }

    /**
     * Calls the TaskList to mark or unmark a task number depending on the parsedArr
     * which is the parsed user input
     * @return String, the resulting message
     */
    public String execute() {
        String action = parsedArr[0];
        String taskNum = parsedArr[1];
        TaskList tasks = getTasks();
        InputResponder inputResponder = getInputResponder();
        String outputMsg = null;
        switch (action) {
        case "mark":
            Task markedTask = tasks.markTask(Type.MARK, taskNum);
            outputMsg = inputResponder.showDoneMsg(markedTask);
            break;
        case "unmark":
            Task unmarkedTask = tasks.markTask(Type.UNMARK, taskNum);
            outputMsg = inputResponder.showUndoneMsg(unmarkedTask);
            break;
        default:
            break;
        }
        return outputMsg;
    }
}
