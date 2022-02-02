package jose;

import java.util.ArrayList;

import jose.task.Task;

/**
 * The class that handles the UI
 */
public class Ui {
    private static final String LS = System.lineSeparator();

    /**
     * Shows the welcome message.
     */
    public String showGreeting() {
        return "Hola! soy Jose" + LS + "Que puedo hacer por ti? UwU";
    }

    /**
     * Says goodbye.
     */
    public String showExitMessage() {
        return "Adios. Espero volver a verte pronto!";
    }

    /**
     * Shows the task that was just marked.
     *
     * @param task A task.
     */
    public String showMarkMessage(Task task) {
        return "Bonito! He marcado esta tarea como hecha:" + LS + task;
    }

    /**
     * Shows the task that was just unmarked.
     *
     * @param task A task.
     */
    public String showUnmarkMessage(Task task) {
        return "Bien, he marcado esta tarea como aun no realizada:" + LS + task;
    }

    /**
     * Shows the task that was just added to the task list.
     *
     * @param task A task.
     */
    public String showAddMessage(Task task) {
        return "Entendido. he anadido esta tarea:" + LS + task + LS;
    }

    /**
     * Shows the task that was just deleted from the task list.
     *
     * @param task A task.
     */
    public String showDeleteMessage(Task task) {
        return "Senalado. He eliminado esta tarea:" + LS + task + LS;
    }

    /**
     * Shows the number of tasks in the given task list.
     *
     * @param tasks A task list.
     */
    public String showRemainingTasks(TaskList tasks) {
        return "Ahora tienes " + tasks.getSize() + " tareas en la lista.";
    }

    /**
     * Displays a list of tasks in the given task list
     *
     * @param tasks A task list.
     */
    public String showList(ArrayList<Task> tasks) {
        String list = "Aqui estan las tareas en su lista:" + LS;

        for (int i = 0; i < tasks.size(); i++) {
            list += i + 1 + ": " + tasks.get(i) + LS;
        }

        return list;
    }
}
