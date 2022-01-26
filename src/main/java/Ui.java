import java.util.Locale;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    public Ui(Scanner sc) {
        this.sc = sc;
    }
    private final String logo =
            "─██████████████───██████████████─██████████████───██████████████───████████──████████─\n" +
            "─██░░░░░░░░░░██───██░░░░░░░░░░██─██░░░░░░░░░░██───██░░░░░░░░░░██───██░░░░██──██░░░░██─\n" +
            "─██░░██████░░██───██░░██████░░██─██░░██████░░██───██░░██████░░██───████░░██──██░░████─\n" +
            "─██░░██──██░░██───██░░██──██░░██─██░░██──██░░██───██░░██──██░░██─────██░░░░██░░░░██───\n" +
            "─██░░██████░░████─██░░██──██░░██─██░░██████░░████─██░░██████░░████───████░░░░░░████───\n" +
            "─██░░░░░░░░░░░░██─██░░██──██░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██─────████░░████─────\n" +
            "─██░░████████░░██─██░░██──██░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n" +
            "─██░░██────██░░██─██░░██──██░░██─██░░██────██░░██─██░░██────██░░██───────██░░██───────\n" +
            "─██░░████████░░██─██░░██████░░██─██░░████████░░██─██░░████████░░██───────██░░██───────\n" +
            "─██░░░░░░░░░░░░██─██░░░░░░░░░░██─██░░░░░░░░░░░░██─██░░░░░░░░░░░░██───────██░░██───────\n" +
            "─████████████████─██████████████─████████████████─████████████████───────██████───────\n";
    private final String line1 = "\t====================================================================\n";
    private final String line2 = "\t====================================================================";
    private final String greeting = "\tHowdy! I'm Bobby\t\t(｡◕‿‿◕｡)\n\tWhat can I do for you?";
    private final String goodbye = "\tBye! Hope to see you again soon! (｡^‿‿^｡)";
    private final String invalid = "\tInvalid command!   (╯°□°)╯︵ ┻━┻ ︵ ╯(°□° ╯)";

    public void printLoadingError() {
        System.out.println("Error in loading file");
    }

    public void printLongLine() {
        System.out.print(line1);
    }

    public void printLongLine(int type) {
        if (type == 1)
            System.out.print(line1);
        else
            System.out.println(line2);
    }

    public void printLogo() {
        System.out.println(logo);
    }

    public void printGreeting() {
        printLongLine(1);
        System.out.println(greeting);
        printLongLine(2);
    }

    public void goodbyeMessage() {
        printLongLine(1);
        System.out.println(goodbye);
    }

    public void invalidMessage() {
        printLongLine(1);
        System.out.println(invalid);
    }

    public String readCommand() {
        return sc.nextLine().toLowerCase(Locale.ROOT);
    }

    public void printTaskList(TaskList tasks) {
        Task currTask;
        if (tasks.isEmpty()) {
            System.out.println("\tWow you are very free now! Enjoy~ ༼ つ ◕_◕ ༽つ");
        } else {
            System.out.println("\tI've sorted and put the any deadlines/events to the top for you :)");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            currTask = tasks.getIndex(i);
            int index = i + 1;
            System.out.println("\t" + index + "." + currTask);
        }
    }

    public void markMessage(Task task) {
        System.out.println("\tFinally... I've marked this task as done:");
        System.out.println("\t  " + task);
    }

    public void unmarkMessage(Task task) {
        System.out.println("\tCould you be any more lazy? I've marked this task as not done yet:");
        System.out.println("\t  " + task);
    }

    public void todoMessage(Task todo) {
        System.out.println("\tOK you better do this today, or else... (ㆆ _ ㆆ) Added task:");
        System.out.println("\t  " + todo);
    }

    public void deadlineMessage(Task deadline) {
        System.out.println("\tOh boy, another deadline? (ㆆ _ ㆆ) Added task:");
        System.out.println("\t  " + deadline);
    }

    public void eventMessage(Task event) {
        System.out.println("\tLet's see... A new event! Added task:");
        System.out.println("\t  " + event);
    }

    public void deleteMessage(Task task) {
        System.out.println("\tAlright I'm deleting this task:");
        System.out.println("\t  " + task);
    }

    public void printNumTasks(TaskList tasks) {
        System.out.println("\t" + "Now you have " + tasks.getSize() + " in the list.");
    }
}
