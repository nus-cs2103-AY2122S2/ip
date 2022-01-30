package duke.uicomponents;

/**
 * Shows the menu when the user unmarks a task.
 */
public class UnmarkedTaskDisplay{
    /**
     * Runs the unmark task display.
     *
     * @param task The task that was recently unmarked.
     */
    public void run(String task) {
        System.out.println("Alright, I've unmarked the duke.task: ");
        System.out.println(task);
        System.out.println("Hope you complete it soon!");
    }
}
