package duke.commands;

import duke.main.TaskList;
import duke.main.Ui;

/**
 * ListCommand is a Command.
 * This Command is used to List out all Tasks in the current TaskList.
 */
public class ListCommand extends Command<String> {
    /**
     * Constructor for DeleteCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     */
    public ListCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    /**
     * Lists out all the Tasks in the TaskList.
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     */
    public void runCommand(TaskList toDoList, String cmd) {
        String toPrint = "";
        // Prints out the list of items in the TaskList
        toPrint += "Here's what you have on your list: \n";
        for (int i = 0; i < toDoList.size(); i++) {
            toPrint += "" + (i + 1) + "." + toDoList.get(i).toString() + "\n";
        }
        Ui.setDukeResponse(toPrint);
    }
}
