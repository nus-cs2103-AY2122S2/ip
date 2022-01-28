package jose;

import java.io.PrintStream;
import jose.task.*;

/**
 * The class that handles the UI
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "-----------------------------------------";
    private final PrintStream out;

    /**
     * The default constructor also initializes the PrintStream.
     */
    public Ui() {
        this.out = new PrintStream(System.out);
    }

    /**
     * Shows the welcome message.
     */
    public void showGreeting() {
        out.println("Hola! soy José\nQué puedo hacer por ti? UwU");
    }

    /**
     * Says goodbye.
     */
    public void showExitMessage() {
        out.println(DIVIDER + LS + "Adiós. Espero volver a verte pronto!" + LS + DIVIDER);
    }

    /**
     * Shows the task that was just marked.
     *
     * @param task A task.
     */
    public void showMarkMessage(Task task) {
        out.println(DIVIDER + LS + "¡Bonito! He marcado esta tarea como hecha:" + LS + task + LS + DIVIDER);
    }

    /**
     * Shows the task that was just unmarked.
     *
     * @param task A task.
     */
    public void showUnmarkMessage(Task task) {
        out.println(DIVIDER + LS + "Bien, he marcado esta tarea como aún no realizada:" +
                LS + task + LS + DIVIDER);
    }

    /**
     * Shows the task that was just added to the task list.
     *
     * @param task A task.
     */
    public void showAddMessage(Task task) {
        out.println(DIVIDER + LS + "Entendido. he añadido esta tarea:" + LS + task);
    }

    /**
     * Shows the task that was just deleted from the task list.
     *
     * @param task A task.
     */
    public void showDeleteMessage(Task task) {
        out.println(DIVIDER + LS + "Señalado. He eliminado esta tarea:" + LS + task);
    }

    /**
     * Shows the number of tasks in the given task list.
     *
     * @param tasks A task list.
     */
    public void showRemainingTasks(TaskList tasks) {
        out.println("Ahora tienes " + tasks.getSize() + " tareas en la lista." + LS + DIVIDER);
    }

    /**
     * Displays a list of tasks in the given task list
     *
     * @param tasks A task list.
     */
    public void showList(TaskList tasks) {
        out.println(DIVIDER + LS + "Aquí están las tareas en su lista:");
        for (int i = 0; i < tasks.getSize(); i++) {
            out.println(i + 1 + ": " + tasks.getTask(i));
        }
        out.println(DIVIDER);
    }
}
