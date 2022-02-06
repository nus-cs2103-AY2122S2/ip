package commandset;

import helper.TaskList;
import helper.Ui;

/**
 * <h1>FindCommand</h1>
 * <p>
 * FindCommands checks if any of the tasks in the list of tasks match
 * the user input word.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class FindCommand extends Command {

    // stores the command for find.
    private static final String FIND = "find";

    /**
     * checks for tasks containing the word given by user.
     *
     * @param word the user input.
     * @param taskList the list of user tasks.
     */
    public static void findTasksContaining(String word, TaskList taskList) {
        word = word.substring(FIND.length()).trim();
        TaskList tasksContainingWord = taskList.getTasksContaining(word);
        if (tasksContainingWord.numOfTasks() == 0) {
            Ui.printNoTasksFound(word);
        } else {
            Ui.printMessage(tasksContainingWord.toString());
        }

    }
}
