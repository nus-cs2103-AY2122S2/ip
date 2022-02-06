package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.InputResponder;

/**
 * AddCommand is responsible for getting the TaskList to add the respective Type of tasks
 */
public class AddCommand extends Command {
    private final String[] parsedArr;
    private final Type type;

    /**
     * Constructs an AddCommand Class with the respective Type based on the user input. The parsedArr is
     * determined by the parser based on the Type, giving out the relevant arguments required to
     * create the Type of task.
     * @param tasks TaskList, contains all our Task we create
     * @param inputResponder InputResponder, responsible for printing out messages to the user for any action done
     * @param storage Storage, responsible for saving and loading of tasks into a text file
     * @param parser Parser, helps to parse user inputs to perform subsequent actions
     * @param input String, user input into the program
     * @throws BernieException For invalid command by the user
     * @throws InvalidArgumentException For a correct command with invalid arguments given by the user
     */
    public AddCommand(TaskList tasks, InputResponder inputResponder, Storage storage, Parser parser, String input)
            throws BernieException, InvalidArgumentException {
        super(tasks, inputResponder, storage, parser, input);
        if (parser.isType(Type.TODO, input)) {
            this.parsedArr = parser.getParams(Type.TODO, input);
            this.type = Type.TODO;
        } else if (parser.isType(Type.DEADLINE, input)) {
            this.parsedArr = parser.getParams(Type.DEADLINE, input);
            this.type = Type.DEADLINE;
        } else if (parser.isType(Type.EVENT, input)) {
            this.parsedArr = parser.getParams(Type.EVENT, input);
            this.type = Type.EVENT;
        } else {
            throw new BernieException("Not a valid type of task!");
        }
    }

    /**
     * Executes the AddCommand, calling the TaskList to add the respective task based on the Type
     * determined by the parser. After adding, storage will save the updated tasks and inputResponder shows
     * the message to the user. Type that goes through is only TODO, DEADLINE, EVENT
     * @return String, the resulting message
     */
    public String execute() {
        Task newTask = null;
        TaskList tasks = getTasks();
        Storage storage = getStorage();
        InputResponder inputResponder = getInputResponder();
        switch (type) {
        case TODO:
            newTask = tasks.addTask(parsedArr, Type.TODO);
            break;
        case DEADLINE:
            newTask = tasks.addTask(parsedArr, Type.DEADLINE);
            break;
        case EVENT:
            newTask = tasks.addTask(parsedArr, Type.EVENT);
            break;
        default:
            break;
        }
        String msgOutputFromSave = storage.saveTasks(tasks);
        int numTasksLeft = tasks.numTasksLeft();
        String addOutputMsg = inputResponder.showAddedMsg(newTask, numTasksLeft) + msgOutputFromSave;
        return addOutputMsg;
    }
}
