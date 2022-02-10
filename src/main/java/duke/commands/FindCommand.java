package duke.commands;

import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;
import duke.tasks.Task;


public class FindCommand extends Command<String> {
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
            String dukeResponse = "";
            // Print out all search terms found
            dukeResponse += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < temp.size(); i++) {
                dukeResponse += "" + (i + 1) + "." + temp.get(i).toString() + "\n";
            }
            dukeResponse += "\n";
            Ui.setDukeResponse(dukeResponse);
        } else {
            Ui.setDukeResponse(Parser.formatMsg("There were no terms matching: " + searchTerm));
        }
    }
}
