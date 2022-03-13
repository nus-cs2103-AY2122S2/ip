package tesseract.command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.task.Deadline;
import tesseract.task.Event;
import tesseract.task.Task;
import tesseract.task.Todo;

/**
 * Represent a command to create a new task.
 * @author Fan Jue
 * @version 0.2.0
 * @since 0.1.0
 */
public class CreateTaskCommand extends Command {
    private static final String TODO_COMMAND = "todo";
    private static final String DDL_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String BY = "/by";
    private static final String AT = "/at";

    /** The type of task to be created */
    protected String taskType;
    /** The full command in an array */
    protected String[] cmdArr;

    CreateTaskCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.taskType = cmdArr[0];
        this.cmdArr = cmdArr;
    }

    /**
     * Execute the command on the system to create a new task.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        Task newTask;
        String description = generateDescription(cmdArr);
        int cmdLen = cmdArr.length;
        switch (taskType) {
        case EVENT_COMMAND:
            newTask = new Event(description, cmdArr[cmdLen - 1]);
            break;
        case DDL_COMMAND:
            newTask = new Deadline(description, cmdArr[cmdLen - 1]);
            break;
        case TODO_COMMAND:
            newTask = new Todo(description);
            break;
        default:
            // report error, this statement should not be reached
            return ui.admitBug();
        }

        taskList.addTask(newTask);
        storage.needUpdate();
        return ui.addTaskRes(newTask.toString(), taskList.size());
    }

    /** Generate the description of the task from the command String[] */
    String generateDescription(String[] arr) {
        int cmdLen = arr.length;
        String description = "";
        for (int i = 1; i < cmdLen - 1; i++) {
            if (arr[i].equals(AT) || arr[i].equals(BY)) {
                return description.substring(0, description.length() - 1);
            }
            description += arr[i] + " ";
        }
        description += arr[cmdLen - 1];
        return description;
    }
}
