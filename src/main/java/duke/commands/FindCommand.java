package duke.commands;

import duke.main.Parser;
import duke.main.TaskList;
import duke.tasks.Task;

public class FindCommand extends Command<String> {
    private final String tabbedLine = "\t----------------------------------------------";

    /**
     * Constructor for FindCommand.
     * When this class is instantiated, it automatically runs runCommand().
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     */
    public FindCommand(TaskList toDoList, String cmd) {
        this.runCommand(toDoList, cmd);
    }

    /**
     * Lists out all the Tasks found according to the given search term
     *
     * @param toDoList the user's List of Tasks
     * @param cmd      the user input to Burp
     */
    public void runCommand(TaskList toDoList, String cmd) {
        // Split into the search terms
        String[] splitCmd = cmd.split("find");
        String searchTerm = splitCmd[1];

        // Create a temporary TaskList to store the search terms found
        TaskList temp = new TaskList();

        // Then, search through the TaskList to find Tasks with that search term
        int listSize = toDoList.size();
        for (int i = 0; i < listSize; i++) {
            Task currentTask = toDoList.get(i);
            String currentTaskInString = currentTask.toString();
            if (currentTaskInString.contains(searchTerm)) {
                temp.add(currentTask);
            }
        }

        // Printing logic
        if (temp.size() > 0) {
            // Print out all search terms found
            System.out.println(tabbedLine);
            System.out.println("\tHere are the matching tasks in your list:");
            for (int i = 0; i < temp.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + temp.get(i).toString());
            }
            System.out.println(tabbedLine);
        } else {
            System.out.println(Parser.formatMsg("There were no terms matching: " + searchTerm));
        }
    }
}
