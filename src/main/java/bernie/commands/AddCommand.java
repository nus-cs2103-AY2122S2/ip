package bernie.commands;

import bernie.enums.Type;
import bernie.exceptions.BernieException;
import bernie.exceptions.InvalidArgumentException;
import bernie.parser.Parser;
import bernie.storage.Storage;
import bernie.tasks.Task;
import bernie.tasks.TaskList;
import bernie.ui.UiHandler;

public class AddCommand extends Command {
    String[] parsedArr;
    Type type;

    public AddCommand(TaskList tasks, UiHandler uiHandler, Storage storage, Parser parser, String input)
            throws BernieException, InvalidArgumentException {
        super(tasks, uiHandler, storage, parser, input);
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
     * Bernie will decide what kind of task is to be created. Bernie splits the input accordingly,
     * to get the parameters required to create the type of task. The creation and adding of task will be
     * handled by the TaskList. New state of tasks is saved to the data directory
     * after we add a task.
     */
    public void execute() {
        Task newTask = null;
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
        storage.saveTasks(tasks);
        uiHandler.showAddedMsg(newTask, tasks.numTasksLeft());
    }
}
