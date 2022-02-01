package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.InputResponder;

/**
 * DeleteCommand is responsible for getting TaskList to delete a specific task given the
 * task number
 */
public class DeleteCommand extends Command {
    private final String[] parsedArr;
    /**
     * Constructs a DeleteCommand Class. The parsedArr is determined by the parser,
     * giving out the relevant arguments required to execute delete.
     * @param tasks TaskList, contains all our Task we create
     * @param inputResponder InputResponder, responsible for printing out messages to the user for any action done
     * @param storage Storage, responsible for saving and loading of tasks into a text file
     * @param parser Parser, helps to parse user inputs to perform subsequent actions
     * @param input String, user input into the program
     * @throws InvalidArgumentException For a correct command with invalid arguments given by the user
     */
    public DeleteCommand(TaskList tasks, InputResponder inputResponder, Storage storage, Parser parser, String input)
            throws InvalidArgumentException {
        super(tasks, inputResponder, storage, parser, input);
        this.parsedArr = parser.getParams(Type.DELETE, input);
    }

    /**
     * Delete the task according to parsedArr which is the parsed user input. The new state of the
     * tasks is saved to the data directory by storage after deletion of task and inputResponder shows the
     * relevant message.
     * @return String, the resulting message
     */
    public String execute() {
        TaskList tasks = getTasks();
        Storage storage = getStorage();
        InputResponder inputResponder = getInputResponder();
        String taskNum = parsedArr[0];
        Task deletedTask = tasks.deleteTask(taskNum);
        String msgOutputFromSave = storage.saveTasks(tasks);
        return inputResponder.showDeleteMsg(deletedTask, tasks.numTasksLeft()) + msgOutputFromSave;
    }
}
