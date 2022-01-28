package jose;

import java.io.PrintStream;
import java.util.ArrayList;

import jose.task.*;

public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "---------------------------------------";
    private final PrintStream out;

    public Ui() {
        this.out = new PrintStream(System.out);
    }

    public void showGreeting() {
        out.println("Hola! soy José\nQué puedo hacer por ti? UwU");
    }

    public void showExitMessage() {
        out.println(DIVIDER + LS + "Adiós. Espero volver a verte pronto!" + LS + DIVIDER);
    }

    public void showMarkMessage(Task task) {
        out.println(DIVIDER + LS + "¡Bonito! He marcado esta tarea como hecha:" + LS + task + LS + DIVIDER);
    }

    public void showUnmarkMessage(Task task) {
        out.println(DIVIDER + LS + "Bien, he marcado esta tarea como aún no realizada:" +
                LS + task + LS + DIVIDER);
    }

    public void showAddMessage(Task task) {
        out.println(DIVIDER + LS + "Entendido. he añadido esta tarea:" + LS + task);
    }

    public void showDeleteMessage(Task task) {
        out.println(DIVIDER + LS + "Señalado. He eliminado esta tarea:" + LS + task);
    }

    public void showRemainingTasks(TaskList tasks) {
        out.println("Ahora tienes " + tasks.getSize() + " tareas en la lista." + LS + DIVIDER);
    }

    public void showList(ArrayList<Task> tasks) {
        out.println(DIVIDER + LS + "Aquí están las tareas en su lista:");
        for (int i = 0; i < tasks.size(); i++) {
            out.println(i + 1 + ": " + tasks.get(i));
        }
        out.println(DIVIDER);
    }
}
