package duke;

public class Ui {

    public Ui();

    public void showWelcome() {
        System.out.println("Hello from Duke!");
    }

    public void showTaskAdded(Task task) {
        System.out.println(String.format("added: %s", task.toString));
    }

    public void showTaskDeleted() {
        System.out.println("I've deleted this task.");
    }

    public void showTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    public void showExit() {
        System.out.println("Goodbye!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("_____");
    }

}