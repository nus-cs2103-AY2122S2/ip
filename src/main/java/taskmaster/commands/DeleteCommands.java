package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.task.Task;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;

/*
 * This class inherits from the Command class.
 * It encapsulates Commands that deletes a Task
 * in the task list.
 */

public class DeleteCommands extends Commands {

    /**
     * Constructor for DeleteCommands
     *
     * @param command Type of command.
     */

    public DeleteCommands(String command) {
        super(command);
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     */

    private String parseCommand(TaskList taskList) throws DukeExceptions {
        String[] stringIntoParts = this.command.split(" ");

        if (stringIntoParts.length == 1) {
            throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 1\n");
        } else if (stringIntoParts.length > 2) {
            //Handle the case of having more than 2 inputs
            throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 3\n");
        }

        try {
            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (taskList.isNumberOutOfRange(index)) {
                throw new DukeExceptions("BRAT ! Your index is out of range! "
                        + "Number has to in the range of the list\n");
            }

            return deleteTask(index, taskList);

        } catch (NumberFormatException nfe) {
            throw new DukeExceptions("What? Second input has to be an integer! Eg mark 1, unmark 2\n");
        }
    }

    /**
     * Helper function to delete task at the specified index
     * in the task list.
     *
     * @param index Index of the task in the task list.
     */

    private String deleteTask(int index, TaskList taskList) {
        String result = "";
        Task selectedTask = taskList.get(index - 1);
        result += "YES! I've removed this task and soon I'll remove you as well!:\n";
        result += printTask(selectedTask) + "\n";
        taskList.delete(index - 1);
        result += taskList.returnCurrentSize();
        return result;
    }

    /**
     * Helper function to print out the display message when
     * the task has been successfully added.
     *
     * @param task Task that has been successfully added.
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

    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws DukeExceptions {
        return parseCommand(taskList);
    }

}
