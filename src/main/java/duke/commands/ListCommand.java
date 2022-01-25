package duke.commands;

import duke.main.TaskList;

/**
 * ListCommand is a Command.
 * This Command is used to List out all Tasks in the current TaskList.
 */
public class ListCommand extends Command<String> {
    private final String tabbedLine = "\t----------------------------------------------";

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
        // Prints out the list of items in the TaskList
        System.out.println(tabbedLine);
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + toDoList.get(i).toString());
        }
        System.out.println(tabbedLine);
    }
}
