package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.util.TaskList;
import taskmaster.task.Task;

/*
 * This class inherits from the Command class.
 * It encapsulates Commands that deletes a Task
 * in the task list.
 */

public class DeleteCommands extends Commands {
    private final TaskList TASKLIST;

    /**
     * Constructor for DeleteCommands
     *
     * @param command Type of command.
     * @param taskList Task list that command is going to be added in.
     */

    public DeleteCommands(String command, TaskList taskList) {
        super(command);
        this.TASKLIST = taskList;
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     */

    private void parseCommand() {
        String[] stringIntoParts = this.command.split(" ");

        try {
            if (stringIntoParts.length == 1) {
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 1\n");
            } else if (stringIntoParts.length > 2) {
                //Handle the case of having more than 2 inputs
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg delete 3\n");
            }

            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (TASKLIST.isNumberOutOfRange(index)) {
                throw new DukeExceptions("BRAT ! Your index is out of range! "
                                            + "Number has to in the range of the list\n");
            }

            deleteTask(index);

        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg mark 1, unmark 2\n");

        } catch (DukeExceptions e) {
            //Out of task range is thrown if the second input is out of range
            System.out.println(e.getMessage());
        }
    }

    /**
     * Helper function to delete task at the specified index
     * in the task list.
     *
     * @param index Index of the task in the task list.
     */

    private void deleteTask(int index) {
        Task selectedTask = TASKLIST.get(index - 1);
        System.out.println("YES! I've removed this task and soon I'll remove you as well!:\n");
        printTask(selectedTask);
        TASKLIST.delete(index - 1);
        TASKLIST.printCurrentSize();
    }

    /**
     * Helper function to print out the display message when
     * the task has been successfully added.
     *
     * @param task Task that has been successfully added.
     */

    private void printTask(Task task) {
        System.out.println("    " + task.toString());
    }

    /**
     * Execute Command.
     */

    public void execute() {
        parseCommand();
    }


}
