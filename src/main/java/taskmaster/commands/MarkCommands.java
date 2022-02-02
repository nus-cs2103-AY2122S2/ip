package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.task.Task;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;


/*
 * This class inherits from the Command class.
 * It encapsulates Commands that marks or un-marks
 * an existing task in the task list.
 */

public class MarkCommands extends Commands {

    /**
     * Constructor for MarkCommands.
     *
     * @param command Type of command.
     */

    public MarkCommands(String command) {
        super(command);
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     */

    private String parseCommand(TaskList taskList) throws DukeExceptions {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];
        String result = "";

        if (stringIntoParts.length == 1) {
            throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
        } else if (stringIntoParts.length > 2) {
            //Handle the case of having more than 2 inputs
            throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
        }

        //Handle error if the second input is not an integer
        //Gets the index of the task in the task list
        try {
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (taskList.isNumberOutOfRange(index)) {
                throw new DukeExceptions("BRAT ! Your index is out of range! Number has to in the range of the list\n");
            }
            if (firstWord.equals("mark")) {
                result += mark(index, taskList);
            } else if (firstWord.equals("unmark")) {
                result += unmark(index, taskList);
            }
            return result;
        } catch (NumberFormatException nfe) {
            throw new DukeExceptions("What? Second input has to be an integer! Eg mark 1, unmark 2\\n");
        }
    }

    /**
     *  Marks the task in the task list with the specified
     *  index.
     *
     * @param index Index of task that is to be marked.
     */

    private String mark(int index, TaskList taskList) {
        Task selectedTask = taskList.get(index - 1);
        String result = "Aye! I've marked this task as completed:\n";
        taskList.mark(index - 1);
        return result + printTask(selectedTask);
    }

    /**
     *  Un-marks the task in the task list with the specified
     *  index.
     *
     * @param index Index of task that is to be un-marked.
     */

    private String unmark(int index, TaskList taskList) {
        Task selectedTask = taskList.get(index - 1);
        String result = "Aye kid! I've marked this task as uncompleted:\n";
        taskList.unmark(index - 1);
        return result + printTask(selectedTask);
    }

    /**
     * Prints the task.
     * @param task Task whose information is to be printed.
     */

    private String printTask(Task task) {
        return "    " + task.toString();
    }

    /**
     * Execute the command.
     * @param ui The User interface.
     * @param storage The file that is storing the task information.
     * @return Returns a string confirmation that the task has been executed.
     * @throws DukeExceptions Throws an exception if execution fails.
     */
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeExceptions {
        return parseCommand(taskList);
    }


}
