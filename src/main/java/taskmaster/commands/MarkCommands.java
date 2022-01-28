package taskmaster.commands;

import taskmaster.exception.DukeExceptions;
import taskmaster.task.Task;
import taskmaster.util.TaskList;


/*
 * This class inherits from the Command class.
 * It encapsulates Commands that marks or un-marks
 * an existing task in the task list.
 */

public class MarkCommands extends Commands {
    private TaskList taskList;

    /**
     * Constructor for MarkCommands.
     *
     * @param command Type of command.
     * @param taskList Task list that command is going to be added in.
     */

    public MarkCommands(String command, TaskList taskList) {
        super(command);
        this.taskList = taskList;
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     */

    private void parseCommand() {
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];

        try {
            if (stringIntoParts.length == 1) {
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
            } else if (stringIntoParts.length > 2) {
                //Handle the case of having more than 2 inputs
                throw new DukeExceptions("What?! You are to enter only 2 inputs. Eg mark 1, unmark 2, delete 3\n");
            }

            //Handle error if the second input is not an integer
            //Gets the index of the task in the task list
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (taskList.isNumberOutOfRange(index)) {
                throw new DukeExceptions("BRAT ! Your index is out of range! Number has to in the range of the list\n");
            }

            if (firstWord.equals("mark")) {
                mark(index);
            } else if (firstWord.equals("unmark")) {
                unmark(index);
            }

        } catch (NumberFormatException nfe) {
            System.out.println("What? Second input has to be an integer! Eg mark 1, unmark 2\n");

        } catch (DukeExceptions e) {
            //Out of task range is thrown if the second input is out of range
            System.out.println(e.getMessage());
        }
    }

    /**
     *  Marks the task in the task list with the specified
     *  index.
     *
     * @param index Index of task that is to be marked.
     */

    private void mark(int index) {
        Task selectedTask = taskList.get(index - 1);
        System.out.println("Aye! I've marked this task as completed:\n");
        taskList.mark(index - 1);
        printTask(selectedTask);
    }

    /**
     *  Un-marks the task in the task list with the specified
     *  index.
     *
     * @param index Index of task that is to be un-marked.
     */

    private void unmark(int index) {
        Task selectedTask = taskList.get(index - 1);
        System.out.println("Aye kid! I've marked this task as uncompleted:\n");
        taskList.unmark(index - 1);
        printTask(selectedTask);
    }

    /**
     * Prints the task.
     * @param task Task whose information is to be printed.
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
