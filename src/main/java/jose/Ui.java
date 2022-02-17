package jose;

import java.util.ArrayList;

import jose.task.Task;

/**
 * The class that handles the UI
 */
public class Ui {
    private static final String LS = System.lineSeparator();

    /**
     * Shows the help message.
     *
     * @return A helpful string.
     */
    public String getHelpMessage() {
        return "Here's some commands hombre" + LS
                + "list: shows un lista of tasks" + LS
                + "mark/unmark [task no.]: marks/unmarks task" + LS
                + "priority [task no.] [low/medium/high]: changes task prioridad" + LS
                + "delete [task no.]: eliminado el task" + LS
                + "find [phrase]: gets tasks containing wordido" + LS
                + "todo [desc]: creates todo" + LS
                + "deadline [desc] /by [yyyy-MM-dd HHmm]: creates deadline" + LS
                + "event [desc] /at [yyyy-MM-dd HHmm]: creates event" + LS
                + "bye: Jose says bye";
    }

    /**
     * Says goodbye.
     *
     * @return A sadge string.
     */
    public String getExitMessage() {
        return "Adios. Backpack backpack swiper no swiping!";
    }

    /**
     * Shows the task that was just marked.
     *
     * @param task A task.
     * @return A string.
     */
    public String getMarkMessage(Task task) {
        return "Bonito! He marcado esta tarea como hecha:" + LS + task;
    }

    /**
     * Shows the task that was just unmarked.
     *
     * @param task A task.
     * @return A string.
     */
    public String getUnmarkMessage(Task task) {
        return "Bien, he marcado esta tarea como aun no realizada:" + LS + task;
    }

    /**
     * Shows the task that just had its priority changed.
     *
     * @param task A task.
     * @return A string.
     */
    public String getPriorityMessage(Task task) {
        return "Hombre changido da priority op:" + LS + task;
    }

    /**
     * Shows the task that was just added to the task list.
     *
     * @param task A task.
     * @return A string.
     */
    public String getAddMessage(Task task, TaskList tasks) {
        return "Entendido. He anadido esta tarea:" + LS + task + LS + getRemainingTasks(tasks);
    }

    /**
     * Shows the task that was just deleted from the task list.
     *
     * @param task A task.
     * @return A string.
     */
    public String getDeleteMessage(Task task, TaskList tasks) {
        return "Senalado. He eliminado esta tarea:" + LS + task + LS + getRemainingTasks(tasks);
    }

    /**
     * Shows the number of tasks in the given task list.
     *
     * @param tasks A task list.
     * @return A string representing the remaining tasks.
     */
    public String getRemainingTasks(TaskList tasks) {
        return "Ahora tienes " + tasks.getSize() + " tareas en la lista.";
    }

    /**
     * Displays a list of tasks in the given task list.
     *
     * @param tasks A task list.
     * @return A string representation of the list of tasks.
     */
    public String getList(ArrayList<Task> tasks) {
        StringBuilder list = new StringBuilder("Aqui estan las tareas en su lista:" + LS);

        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(": ").append(tasks.get(i)).append(LS);
        }

        return list.toString();
    }
}
