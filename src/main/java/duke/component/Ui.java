package duke.component;

import java.util.Scanner;

import duke.task.Task;

import static duke.constant.Message.GOOD_BYE;
import static duke.constant.Message.GREETING;
import static duke.constant.Message.HORIZONTAL_LINE;
import static duke.constant.Message.LINE_PREFIX;
import static duke.constant.Message.LINE_SEPARATOR;
import static duke.constant.Message.CONFIRM_MARK;
import static duke.constant.Message.CONFIRM_UNMARK;

public class Ui {
    public static void printGreeting() {
        printMessage(GREETING);
    }

    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    public static void printGoodBye() {
        printMessage(GOOD_BYE);
    }

    public static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(HORIZONTAL_LINE);
    }

    public void printConfirmMark(Task currentTask) {
        printMessage(CONFIRM_MARK + LINE_PREFIX + LINE_SEPARATOR + LINE_PREFIX + currentTask.toString());
    }

    public void printConfirmUnmark(Task currentTask) {
        printMessage(CONFIRM_UNMARK + LINE_PREFIX + LINE_SEPARATOR + LINE_PREFIX + currentTask.toString());
    }

    public void printConfirmDelete(Task deletedTask, TaskList tasks) {
        String message = LINE_PREFIX + "Noted. I've removed this task:" + LINE_SEPARATOR;
        message += LINE_PREFIX + deletedTask.toString() + LINE_SEPARATOR;
        message += LINE_PREFIX + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        printMessage(message);
    }

    public void printConfirmAdd(Task currentTask, TaskList tasks) {
        String message = LINE_PREFIX + "Got it. I've added this task:" + LINE_SEPARATOR;
        message += LINE_PREFIX + currentTask.toString() + LINE_SEPARATOR;
        message += LINE_PREFIX + "Now you have " + tasks.getNumberOfTasks() + " tasks in the list.";
        printMessage(message);
    }
}
