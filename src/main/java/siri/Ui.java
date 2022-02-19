package siri;

import java.util.ArrayList;
import java.util.Scanner;

//Ui.java reused and edited from Brigette Santoso E0564307
public class Ui {
    protected Scanner scan;

    public Ui() {
        scan = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("     Hi, I'm Siri, a simple list program. \n"
                + "     You can add 3 types of tasks to the list:\n"
                + "     1.todo \n     2.deadline \n     3.event \n\n"
                + "     To add to the list, specify the type of task before it's description.\n"
                + "     Only deadlines and events can accept dates and timings\n\n"
                + "     Examples:\n"
                + "     todo run a mile\n"
                + "     deadline return library book /by Sunday 2359\n"
                + "     event Jack's wedding /at Holiday Inn 1800\n\n"
                + "     What do you wish to take note of today?");
        showLine();
    }

    public void showExit() {
        System.out.println("     Goodbye.\n");
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println("     Tasks to do:");
        int numberOftasks = tasks.size();
        for (int i = 0; i < numberOftasks; i++) {
            String currTask = tasks.get(i).toString();
            System.out.println("     " + (i + 1) + "." + currTask);
        }

    }

    public void showMarkTask(Task task) {
        System.out.println("     This task has been marked:\n       "
                + task.toString() + "\n");
    }

    public void showUnmarkTask(Task task) {
        System.out.println("     This task has been unmarked:\n       "
                + task.toString() + "\n");
    }

    public void showAddTask(Task task, ArrayList<Task> tasks) {
        System.out.println("     This task has been added to your list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n");
    }

    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        System.out.println("     Sure. I've removed this task from the list:\n       "
                + task.toString() + "\n" + "\n"
                + "     Number of task(s) in your list: " + tasks.size() + "\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________" +
                "__________________________________");
    }

    public void showLoadingError(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scan.nextLine();
    }
}