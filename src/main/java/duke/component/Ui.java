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

/**
 * A class to handle interactions with the user.
 */
public class Ui {
    /**
     * Prints Greeting message.
     */
    public String printGreeting() {
        return printMessage(GREETING);
    }

    /**
     * Reads input from user.
     *
     * @param sc Scanner class
     * @return String userInput
     */
    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * Prints goodbye message.
     */
    public String printGoodBye() {
        return printMessage(GOOD_BYE);
    }

    /**
     * Prints a message between two horizontal lines
     *
     * @param message String
     */
    public String printMessage(String message) {
        return message;
    }

    /**
     * Prints a confirmation after marking task as Done.
     *
     * @param currentTask Task that been marked
     */
    public String printConfirmMark(Task currentTask) {
        return printMessage(CONFIRM_MARK + LINE_PREFIX + LINE_SEPARATOR + LINE_PREFIX + currentTask);
    }

    /**
     * Prints a confirmation after unmarking task as Done.
     *
     * @param currentTask Task that been unmarked
     */
    public String printConfirmUnmark(Task currentTask) {
        return printMessage(CONFIRM_UNMARK + LINE_PREFIX + LINE_SEPARATOR + LINE_PREFIX + currentTask);
    }

    /**
     * Prints a confirmation after deleting task.
     * @param deletedTask Task that been deleted
     * @param numberOfTasks Integer that shows number of tasks in list.
     */
    public String printConfirmDelete(Task deletedTask, int numberOfTasks) {
        String message = LINE_PREFIX + "Noted. I've removed this task:" + LINE_SEPARATOR;
        message += LINE_PREFIX + deletedTask + LINE_SEPARATOR;
        message += LINE_PREFIX + "Now you have " + numberOfTasks + " tasks in the list.";
        return printMessage(message);
    }

    /**
     * Prints a confirmation after adding task.
     * @param currentTask Task that been added
     * @param numberOfTasks Integer that shows number of tasks in list.
     */
    public String printConfirmAdd(Task currentTask, int numberOfTasks) {
        String message = LINE_PREFIX + "Got it. I've added this task:" + LINE_SEPARATOR;
        message += LINE_PREFIX + currentTask.toString() + LINE_SEPARATOR;
        message += LINE_PREFIX + "Now you have " + numberOfTasks + " tasks in the list.";
        return printMessage(message);
    }
}
