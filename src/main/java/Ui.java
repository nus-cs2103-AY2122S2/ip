import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void closeScanner() {
        sc.close();
    }

    public void showLogo() {
        String logo = " ____     ___    _____\n"
            + "|  _ \\   / _ \\  |  ___|\n"
            + "| |_| | | |_| | | |\n"
            + "|  __/  | | | | | |___\n"
            + "|_|     |_| |_| |_____|\n";
        System.out.println(logo);
    }

    public void showWelcome() {
        System.out.println("Hello there! I'm Pac, your very own Personal Assistant ChatBot.");
        System.out.println("How may I help you?");
    }

    public void showLine() {
        System.out.println("-----------------");
    }

    public void showExit(){
        System.out.println("Goodbye! See you soon. :)");
    }

    public void showList(TaskList tasks){
        int i = 1;

        if(tasks.isEmpty()) {
            System.out.println("There are no tasks left to complete");
        }

        for (Task task : tasks.getTasks()) {
            System.out.println(i++ +". " + task);
        }
    }

    public void showMark(Task task) {
        System.out.println("Task is marked as done.");
        System.out.println(task);
    }

    public void showUnmark(Task task) {
        System.out.println("Task is marked as not done.");
        System.out.println(task);
    }

    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("added: " + task);
        System.out.println("You have " + tasks.getSize() + " tasks in your list.");
    }

    public void showDelete(Task task, TaskList tasks) {
        int size = tasks.getSize() - 1;
        System.out.println("Task has been deleted: " + task);
        System.out.println("You have " + size + " tasks in your list.");
    }

    public void showLoadingError() {
        System.out.println("Something went wrong while reading the data file.");
    }

    public void showPacError(PacException e) {
        System.out.println(e.getMessage());
    }

    public void showIOError(IOException e) {
        System.out.println(e.getMessage());
    }
}
