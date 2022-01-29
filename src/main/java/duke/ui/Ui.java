package duke.ui;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {



    public Ui() {
    }


    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    public void showWelcome() {
        System.out.println("YO WHASSUPPP I'm Productivitisation\n" + "What can I do for you today?");
    }

    public void showBye() {
        System.out.println("  " + "That's all?? lame!!!");
    }

    public void addDeadline(Deadline freshDeadline, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshDeadline + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }

    public void addEvent(Event freshEvent, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshEvent + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }

    public void addTodo(ToDo freshTodo, TaskList list) {
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }
}
