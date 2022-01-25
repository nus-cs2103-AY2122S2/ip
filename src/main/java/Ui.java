import java.util.Scanner;

public class Ui {
    public static void printGreeting() {
        printMessage(Message.GREETING);
    }

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public static void printGoodBye() {
        printMessage(Message.GOOD_BYE);
    }

    public static void printLine() {
        System.out.println(Message.HORIZONTAL_LINE);
    }

    public static void printMessage(String message) {
        System.out.println(Message.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(Message.HORIZONTAL_LINE);
    }

    public void printConfirmMark(Task currentTask) {
        printMessage(Message.CONFIRM_MARK + Message.LINE_PREFIX + Message.LINE_SEPARATOR + Message.LINE_PREFIX
                + currentTask.toString());
    }

    public void printConfirmUnmark(Task currentTask) {
        printMessage(Message.CONFIRM_UNMARK + Message.LINE_PREFIX + Message.LINE_SEPARATOR + Message.LINE_PREFIX
                + currentTask.toString());
    }

    public void printConfirmDelete(Task deletedTask, TaskList tasks) {
        String message = Message.LINE_PREFIX + "Noted. I've removed this task:" + Message.LINE_SEPARATOR;
        message += Message.LINE_PREFIX + deletedTask.toString() + Message.LINE_SEPARATOR;
        message += Message.LINE_PREFIX + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        printMessage(message);
    }

    public void printConfirmAdd(Task currentTask, TaskList tasks) {
        String message = Message.LINE_PREFIX + "Got it. I've added this task:" + Message.LINE_SEPARATOR;
        message += Message.LINE_PREFIX + currentTask.toString() + Message.LINE_SEPARATOR;
        message += Message.LINE_PREFIX + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list."
                + Message.LINE_SEPARATOR;
        printMessage(message);
    }
}
