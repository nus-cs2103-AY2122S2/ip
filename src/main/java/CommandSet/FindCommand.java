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

    // stores the value of no tasks.
    private static final int EMPTY = 0;

    /**
     * checks for tasks containing the word given by user.
     *
     * @param word the user input.
     * @param taskList the list of user tasks.
     */
    public static void findTasksContaining(String word, TaskList taskList) {
        word = word.substring(FIND.length()).trim();
        TaskList tasksContainingWord = taskList.getTasksContaining(word);

        if (tasksContainingWord.numOfTasks() == EMPTY) {
            Ui.printNoTasksFound(word);
        } else {
            Ui.printMessage("The following tasks contain, " + word + ":" + tasksContainingWord.toString());
        }
    }

}
