package duke.commands;

import duke.tasks.TaskList;

/**
 * Represents a <code>FindCommand</code> which is called to find a command matching a specified keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructor for the Find command, which takes in the relevant keyword.
     * @param keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks) {
        String response = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.get(i).getName().contains(keyword)) {
                response += (i + 1) + ". " + tasks.get(i) + "\n";
            }
        }
        if (response.equals("")) {
            response = "Could not find any matching tasks.";
        }
        return "These are the tasks found containing " + keyword + "\n" + response;
    }
}
