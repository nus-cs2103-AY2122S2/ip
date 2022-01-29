package duke.ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.util.Scanner;

/**
 * Ui class
 */
public class Ui {

    /**
     * Constructor for Ui
     */
    public Ui() {
    }

    /**
     * read next input
     * @return String from input
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * System out loading error
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * System out welcome
     */
    public void showWelcome() {
        System.out.println("YO WHASSUPPP I'm Productivitisation\n" + "What can I do for you today?");
    }

    /**
     * System out for exit program
     */
    public void showBye() {
        System.out.println("  " + "That's all?? lame!!!");
    }

    /**
     * System out for addition of deadline
     * @param freshDeadline deadline task added
     * @param list current list of task
     */
    public void addDeadline(Deadline freshDeadline, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshDeadline + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * System out for addition of event
     * @param freshEvent event task added
     * @param list current list of task
     */
    public void addEvent(Event freshEvent, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshEvent + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }

    /**
     * System out for addition of todo
     * @param freshTodo todo task added
     * @param list current list of task
     */
    public void addTodo(ToDo freshTodo, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }
}
