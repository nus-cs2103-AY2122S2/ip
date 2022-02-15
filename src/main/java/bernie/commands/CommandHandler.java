package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.TaskList;
import bernie.ui.InputResponder;

/**
 * CommandHandler determines which Command to execute given the user input. Parser helps to
 * parse the user input to help CommandHandler determine the respective Command.
 */
public class CommandHandler {
    private TaskList tasks;
    private InputResponder inputResponder;
    private Storage storage;
    private Parser parser;

    /**
     * Constructs the commandHandler to handle user inputs
     */
    public CommandHandler() {
        this.tasks = new TaskList();
        this.inputResponder = new InputResponder();
        this.storage = new Storage();
        this.parser = new Parser(tasks);
    }

    public InputResponder getInputResponder() {
        return inputResponder;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Commands are performed according to the input, done by creating the respective commands and
     * executing it. Parser helps to determine the Type given the user input. inputResponder displays to
     * the user the relevant message according to their input.
     * Exceptions are caught and printed out for the user.
     * @param input String, user input
     * @return String, the resulting message
     */
    public String handle(String input) {
        Type type = parser.parseType(input);
        String executeOutputMsg = null;
        try {
            switch (type) {
            case LIST:
                if (tasks.isEmpty()) {
                    executeOutputMsg = inputResponder.showListTasksMsg();
                } else {
                    executeOutputMsg = inputResponder.showListTasksMsg(tasks);
                }
                break;
            case BYE:
                executeOutputMsg = inputResponder.showLeaveMsg();
                break;
            case EMPTY:
                String emptyInputMsg = "Say something???";
                throw new BernieException(emptyInputMsg);
            case MARK:
                MarkCommand markCommand = new MarkCommand(tasks, inputResponder, storage, parser, input);
                executeOutputMsg = markCommand.execute();
                break;
            case DELETE:
                DeleteCommand deleteCommand = new DeleteCommand(tasks, inputResponder, storage, parser, input);
                executeOutputMsg = deleteCommand.execute();
                break;
            case ADD:
                AddCommand addCommand = new AddCommand(tasks, inputResponder, storage, parser, input);
                executeOutputMsg = addCommand.execute();
                break;
            case FIND:
                FindCommand findCommand = new FindCommand(tasks, inputResponder, storage, parser, input);
                executeOutputMsg = findCommand.execute();
                break;
            case REMIND:
                executeOutputMsg = inputResponder.showDeadlinesMsg(tasks);
                break;
            default:
                break;
            }
            return executeOutputMsg;
        } catch (BernieException e) {
            String bernieErrorMsg = inputResponder.showErrorMsg(e.getMessage());
            return bernieErrorMsg;
        } catch (Exception e) {
            String generalErrorMsg = inputResponder.showErrorMsg(e.getMessage());
            return generalErrorMsg;
        }
    }

    /**
     * This fn is called by Storage to trigger commandHandler to get
     * TaskList to initialise the task on load.
     * @param line String, the line read from the text file created previously if
     *             the user has used the app before.
     */
    public void initTaskOnLoad(String line) {
        // gets the args required to create the task on load
        String[][] args = parser.parseFileLine(line);
        String[] taskArgs = getTaskArgsForInit(args);
        String type = getTypeForInit(args);
        String isDone = getIsDone(args);
        tasks.initTask(taskArgs, type, isDone);
    }

    private String getIsDone(String[][] args) {
        final int doneStatusArrIndex = 2;
        final int doneStatusIndex = 0;
        String isDone = args[doneStatusArrIndex][doneStatusIndex];
        return isDone;
    }

    private String getTypeForInit(String[][] args) {
        final int typeArrIndex = 1;
        final int typeIndex = 0;
        String type = args[typeArrIndex][typeIndex];
        return type;
    }

    private String[] getTaskArgsForInit(String[][] args) {
        final int taskArgsIndex = 0;
        String[] taskArgs = args[taskArgsIndex];
        return taskArgs;
    }
}
