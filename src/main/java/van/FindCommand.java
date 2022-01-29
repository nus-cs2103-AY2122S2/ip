package van;

import java.util.ArrayList;

/**
 * Abstracts commands that finds particular keywords in existing tasks
 */
public class FindCommand implements Command {

    public String keyword;

    /**
     * Creates FindCommand object containing the keyword to be found
     *
     * @param keyword keyword to be found
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Calls the methods needed in order to find a keyword
     *
     * @param ui Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        ArrayList<Task> currentTasks = taskList.getList();
        ArrayList<Task> results = new ArrayList<Task>();
        for (int i = 0; i < currentTasks.size(); i++) {
            String[] words = Parser.parseDescription(currentTasks.get(i));
            for (int j = 0; j < words.length; j++) {
                if(keyword.equals(words[j])) {
                    results.add(currentTasks.get(i));
                }
            }
        }
        ui.printResults(results, keyword);
        return false;
    }

}
