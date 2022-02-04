package duke;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {

    private StringBuilder print;

    public Ui() {
        print = new StringBuilder();
    }

    public void clearPrint() {
        print.delete(0, print.length());
    }

    public String sendPrint() {
        return print.toString();
    }

    public void addToPrint(String string) {
        print.append(string);
    }

    public void printAllTasks(TaskList tasklist) {
        ArrayList<Task> all = tasklist.getAllTasks();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= all.size(); i++) {
            String s = "";
            Task task = all.get(i-1);
            if (task instanceof ToDo) {
                ToDo todo = (ToDo) task;
                s = todo.toString();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                s = event.toString();
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                s = deadline.toString();
            } else {
                s = task.toString();
            }
            sb.append(i).append(". ").append(s).append('\n');
        }
        sb.append("You have " + all.size() + " tasks! Looks nasty, please rest well.");
        String ret = sb.toString();
        System.out.println(ret);
        addToPrint(ret);
    }

    public void printTaskIsAdded(Task task) {
        StringBuffer sb = new StringBuffer();
        sb.append("Got it. I've added this task: " + "\n");
        if (task instanceof ToDo) {
            ToDo todo = (ToDo) task;
            sb.append(todo);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append(event);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(deadline);
        } else {
            sb.append(task);
        }
        System.out.println(sb.toString());
        addToPrint(sb.toString());
    }

    public void printTaskIsDeleted(TaskList tasklist) {
        StringBuffer sb = new StringBuffer();
        int numOfTasks = tasklist.getLength();
        sb.append("Task is removed. \n Now you have " + numOfTasks + " left!");
        String s = sb.toString();
        System.out.println(s);
        addToPrint(sb.toString());
    }

    public void printMarkTaskAsDone(TaskList tasklist, int ranking) {
        StringBuffer sb = new StringBuffer();
        sb.append("Nice! I've marked this task as done: \n");
        sb.append(tasklist.getAllTasks().get(ranking-1));
        String s = sb.toString();
        System.out.println(s);
        addToPrint(sb.toString());
    }

    public void printUnmarkTaskFromDone(TaskList tasklist, int ranking) {
        StringBuffer sb = new StringBuffer();
        sb.append("OK, I've marked this task as not done yet: \n");
        sb.append(tasklist.getAllTasks().get(ranking-1));
        String s = sb.toString();
        System.out.println(s);
        addToPrint(sb.toString());
    }

    public void printFilteredDeadline(int input) {
        String s = "None of your task is due on this date!";
        System.out.println(s);
        addToPrint(s);
    }

    public void printFilteredDeadline(TaskList filtered_tasklist) {
        StringBuffer sb = new StringBuffer();
        ArrayList<Task> filtered = filtered_tasklist.getAllTasks();
        sb.append("Here you go! \n");
        for (int i = 0; i < filtered.size(); i++) {
            sb.append(filtered.get(i));
            sb.append('\n');
        }
        sb.append("Did you find the task you were looking for?");
        String s = sb.toString();
        System.out.println(s);
        addToPrint(s);
    }

    public void showWelcome() {
        addToPrint("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                "Nice to meet you dear:>\n" +
                "What can I do for you?");
    }

    public String readCommand() {
        Scanner s = new Scanner(System.in);
        String input = "";
        if (s.hasNextLine()) {
            input = s.nextLine();
        }
        return input;
    }

    public void showLine() {
        addToPrint("____________");
    }

    public void showError(String s) {
        addToPrint(s);
    }

    public void printBye() {
        String s = "Bye! See you soon!";
        System.out.println(s);
        addToPrint(s);
    }
}
