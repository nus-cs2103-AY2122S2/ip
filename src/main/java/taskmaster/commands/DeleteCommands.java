package taskmaster.commands;

import taskmaster.exception.TaskmasterExceptions;
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

    private String parseCommand(TaskList taskList) throws TaskmasterExceptions {
        //Split the string using the whitespace delimiter to make identifying each component easier.
        String[] stringIntoParts = this.command.split(" ");

        //Handle the case of having only 1 input & the case of having more than 2 inputs.
        if (stringIntoParts.length == 1 || stringIntoParts.length > 2) {
            throw new TaskmasterExceptions("What?! You are to enter only 2 inputs. Eg delete 3\n");
        }

        try {
            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (taskList.isNumberOutOfRange(index)) {
                throw new TaskmasterExceptions("BRAT ! Your index is out of range! "
                        + "Number has to in the range of the list\n");
            }

            return deleteTask(index, taskList);

        } catch (NumberFormatException nfe) {
            throw new TaskmasterExceptions("What? Second input has to be an integer! Eg mark 1, unmark 2\n");
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
        //initial size of the task list to check whether task has been successfully deleted.
        int initialSize = taskList.getCurrentSize();
        Task selectedTask = taskList.get(index - 1);
        result += "YES! I've removed this task and soon I'll remove you as well!:\n";
        result += printTask(selectedTask) + "\n";
        taskList.delete(index - 1);
        //Assert statement to ensure deletion is successful.
        assert taskList.getCurrentSize() == initialSize - 1 : "Task list should contain one less element "
                + "after deletion";
        result += taskList.printCurrentSize();
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
     *
     * @param ui The User interface.
     *
     * @param storage The file that is storing the task information.
     *
     * @return Returns a string confirmation that the task has been executed.
     *
     * @throws TaskmasterExceptions Throws an exception if execution fails.
     */

    @Override
    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws TaskmasterExceptions {
        return parseCommand(taskList);
    }

}
