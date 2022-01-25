import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public void showLine() {
        String line = "*_______________________________________________________________*";
        System.out.println(line);
    }

    public void showWelcome() {
        this.showLine();
        String welcomeMessage = "Hello! I'm IstjBot. \n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        this.showLine();
    }

    public String readCommand() {
        String fullCommand = sc.nextLine();
        return fullCommand;
    }

    public void showTaskAdded(int tasksSize, String addedTask) {
        String messageStart = "As an IstjBot, I will add this task right now. \n";
        // May refactor messageLast?
        String messageLast = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        System.out.println(messageStart + addedTask + "\n" + messageLast);
    }

    public void showTaskMarked(String task) {
        String message = "As an IstjBot, I've marked this task as done: \n" + task;
        System.out.println(message);
    }

    public void showTaskUnmarked(String task) {
        String message = "As an IstjBot, I've unmarked this task: \n" + task;
        System.out.println(message);
    }

    public void showTaskDeleted(int tasksSize, String deletedTask) {
        String messageStart = "As an IstjBot, I've removed this task: \n";
        String messageLast = "Now you have " + tasksSize + " ";
        String plural = tasksSize > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        System.out.println(messageStart + deletedTask + "\n" + messageLast);
    }

    public void showTasksByDate(String searchList) {
        String messageStart = "As an IstjBot, I present you the task(s) with that date.";
        messageStart += searchList.isEmpty() ? "" : "\n";
        System.out.println(messageStart + searchList);
    }

    public void showTasks(String list) {
        String messageStart = "As an IstjBot, I present you the task(s) in your list:";
        messageStart += list.isEmpty() ? "" : "\n";
        System.out.println(messageStart + list);
    }

    public void showBye() {
        System.out.println("Bye, I, IstjBot, will be organizing your tasks until you come back.");
        sc.close();
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
