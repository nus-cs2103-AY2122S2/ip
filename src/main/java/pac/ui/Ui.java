package pac.ui;

import pac.PacException;
import pac.task.Task;
import pac.task.TaskList;

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

    public String showExit(){
        return ("Goodbye! See you soon. :)");
    }

    public String showList(TaskList tasks){
        int i = 1;
        StringBuilder response = new StringBuilder();

        if(tasks.isEmpty()) {
            response.append("There are no tasks left to complete.")
                    .append(System.getProperty("line.separator"));
        } else {
            for (Task task : tasks.getTasks()) {
                response.append(i).append(". ").append(task.toString())
                        .append(System.getProperty("line.separator"));
                i += 1;
            }
        }

        return response.toString();
    }

    public String showFind(TaskList matchingTasks) {
        int i = 1;
        StringBuilder response = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            response.append("here are no tasks containing the keyword.")
                    .append(System.getProperty("line.separator"));
        } else {
            for (Task task : matchingTasks.getTasks()) {
                response.append(i).append(". ").append(task.toString())
                        .append(System.getProperty("line.separator"));
                i += 1;
            }
        }

        return response.toString();
    }

    public String showMark(Task task) {
        StringBuilder response = new StringBuilder();

        if (task.isMarked()) {
            response.append("Task is already marked as done.")
                    .append(System.getProperty("line.separator")).append(task.toString())
                    .append(System.getProperty("line.separator"));
        } else {
            response.append("Task is marked as done.")
                    .append(System.getProperty("line.separator")).append(task.toString())
                    .append(System.getProperty("line.separator"));
        }

        return response.toString();
    }

    public String showUnmark(Task task) {
        StringBuilder response = new StringBuilder();

        if (!task.isMarked()) {
            response.append("Task is already marked as not done.")
                    .append(System.getProperty("line.separator")).append(task.toString())
                    .append(System.getProperty("line.separator"));
        } else {
            response.append("Task is marked as not done.")
                    .append(System.getProperty("line.separator")).append(task.toString())
                    .append(System.getProperty("line.separator"));
        }

        return response.toString();
    }

    public String showAddTask(Task task, TaskList tasks) {
        StringBuilder response = new StringBuilder();

        response.append("added: ").append(task.toString())
                .append(System.getProperty("line.separator")).append("You have ")
                .append(tasks.getSize()).append(" tasks in your list.")
                .append(System.getProperty("line.separator"));

        return response.toString();
    }

    public String showDelete(Task task, TaskList tasks, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Task has been deleted: ").append(task.toString())
                .append(System.getProperty("line.separator"))
                .append("You have ").append(size).append(" tasks in your list")
                .append(System.getProperty("line.separator"));
        return response.toString();
    }

    public String showReschedule(Task task) {
        StringBuilder response = new StringBuilder();

        response.append("rescheduled: ").append(task.toString())
                .append(System.getProperty("line.separator"));

        return response.toString();
    }

    public String showLoadingError() {
        return "Something went wrong while reading the data file.";
    }

    public String showPacError(PacException e) {
        return e.getMessage();
    }

    public String showIOError(IOException e) {
        return e.getMessage();
    }

    public String showFormatError() {
        return "Invalid Command Format";
    }

    public String showIndexOutOfBoundsError(IndexOutOfBoundsException e) {
        return "Sorry! Invalid Index.";
    }
}
