import java.util.Scanner;

public class Ui {

    Scanner scanner = new Scanner(System.in);

    /**
     * Constructs an instance of Ui to manage the user interactions required by Duke
     * application
     */
    public Ui() {
    }

    public void welcome() {
        System.out.println("Welcome to Duke, your friendly task manager!\n What do you want to do today?");
    }

    public void goodBye() {
        System.out.println("Sayonara!! Hope to see you again soon hehe! :-)");
    }

    public void addMessage(Task task, int size) {
        System.out.println("Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    public void deleteMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    public void markMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    public void unmarkMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task.toString());
    }

    public void showLine() {
        System.out.println("---------------------------------------------------------------");
    }

    public String readCommand() {
        String command = scanner.nextLine();
        return command;
    }

    public void showError(String errorMessage) {
        System.out.println("Uh oh... We ran into an error: " + errorMessage);
    }

    public void listTasks(TaskList tasks) {
        for (int i = 1; i <= tasks.length(); i++) {
            System.out.println(i + "." + tasks.at(i - 1).toString());
        }
    }

}
