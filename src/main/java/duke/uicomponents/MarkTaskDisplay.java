package duke.uicomponents;

/**
 * Shows the menu when the user marks a task.
 */
public class MarkTaskDisplay{
    /**
     * Runs the mark task display.
     *
     * @param task The task that was recently marked.
     */
    public void run(String task) {
        System.out.println("Alright, I've marked the duke.task: ");
        System.out.println(task);
        System.out.println("Good job by the way!");
    }
}
