package taskmaster.commands;

import taskmaster.exception.TaskmasterExceptions;
import taskmaster.task.Task;
import taskmaster.userinterface.UserInterface;
import taskmaster.util.Storage;
import taskmaster.util.TaskList;


/*
 * This class inherits from the Command class.
 * It encapsulates Commands that marks or un-marks
 * an existing task in the task list.
 */

public class TagCommands extends Commands {

    /**
     * Constructor for MarkCommands.
     *
     * @param command Type of command.
     */

    public TagCommands(String command) {
        super(command);
    }

    /**
     * Helper function to help parse the command.
     * Extract the components of the command.
     */

    private String parseCommand(TaskList taskList) throws TaskmasterExceptions {
        //Split the string based on the whitespace delimiter.
        String[] stringIntoParts = this.command.split(" ");
        String firstWord = stringIntoParts[0];

        //Handle the case of having the wrong number of inputs
        if (stringIntoParts.length != 3) {
            throw new TaskmasterExceptions("What?! You are to enter in the inputs in the following format. "
                    + "Eg tag 1 #fun");
        }
        //Handle error if the second input is not an integer
        //Gets the index of the task in the task list
        try {
            int index = Integer.parseInt(stringIntoParts[1]);

            //If index is out of range, throw illegal argument exception
            if (taskList.isNumberOutOfRange(index)) {
                throw new TaskmasterExceptions("BRAT ! Your index is out of range! "
                        + "Number has to in the range of the list\n");
            }
            //Retrieve the tagging portion of the input.
            String tagging = stringIntoParts[2];
            if (!tagging.contains("#")) {
                throw new TaskmasterExceptions("Tagging must include # to indicate it's a tag!");
            }

            String recentlyAddedTagMsg = tagTaskAtIndex(index, taskList, tagging);
            String allTagsAssociatedWithTask = getTagsAssociatedWithTask(index, taskList);

            return recentlyAddedTagMsg + allTagsAssociatedWithTask;

        } catch (NumberFormatException nfe) {
            throw new TaskmasterExceptions("What? Second input has to be an integer! Eg mark 1, unmark 2\\n");
        }
    }

    private String tagTaskAtIndex(int index, TaskList taskList, String tagName) {
        return taskList.get(index - 1).addTag(tagName);
    }

    private String getTagsAssociatedWithTask(int index, TaskList taskList) {
        return taskList.get(index - 1).getAllTags();
    }


    /**
     * Prints the task.
     *
     * @param task Task whose information is to be printed.
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

    public String execute(TaskList taskList, UserInterface ui, Storage storage) throws TaskmasterExceptions {
        return parseCommand(taskList);
    }


}
