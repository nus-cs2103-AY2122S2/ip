package pac.ui;

import pac.PacException;
import pac.task.Task;
import pac.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * returns String for Pac response
 */
public class Ui {

    /**
     * ui response for exit command
     * @return
     */
    public String showExit(){
        return ("Goodbye! See you soon. :)");
    }

    /**
     * ui response for list command
     * @param tasks
     * @return
     */
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

    /**
     * ui response for find command
     * @param matchingTasks
     * @return
     */
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

    /**
     * ui response for mark command
     * @param task
     * @return
     */
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

    /**
     * ui response for unmark command
     * @param task
     * @return
     */
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

    /**
     * ui response for add command
     * @param task
     * @param tasks
     * @return
     */
    public String showAddTask(Task task, TaskList tasks) {
        StringBuilder response = new StringBuilder();

        response.append("added: ").append(task.toString())
                .append(System.getProperty("line.separator")).append("You have ")
                .append(tasks.getSize()).append(" tasks in your list.")
                .append(System.getProperty("line.separator"));

        return response.toString();
    }

    /**
     * ui response for delete command
     * @param task
     * @param tasks
     * @param size
     * @return
     */
    public String showDelete(Task task, TaskList tasks, int size) {
        StringBuilder response = new StringBuilder();
        response.append("Task has been deleted: ").append(task.toString())
                .append(System.getProperty("line.separator"))
                .append("You have ").append(size).append(" tasks in your list")
                .append(System.getProperty("line.separator"));
        return response.toString();
    }

    /**
     * ui response for reschedule command
     * @param task
     * @return
     */
    public String showReschedule(Task task) {
        StringBuilder response = new StringBuilder();

        response.append("rescheduled: ").append(task.toString())
                .append(System.getProperty("line.separator"));

        return response.toString();
    }

    /**
     * ui response for loading error
     * @return
     */
    public String showLoadingError() {
        return "Something went wrong while reading the data file.";
    }

    /**
     * ui response for pac exception
     * @param e
     * @return
     */
    public String showPacError(PacException e) {
        return e.getMessage();
    }

    /**
     * ui response for IOException
     * @param e
     * @return
     */
    public String showIOError(IOException e) {
        return e.getMessage();
    }

    /**
     * ui response for NumberFormatException
     * @return
     */
    public String showFormatError() {
        return "Invalid Command Format";
    }

    /**
     * ui response for IndexOutOfBoundsException
     * @param e
     * @return
     */
    public String showIndexOutOfBoundsError(IndexOutOfBoundsException e) {
        return "Sorry! Invalid Index.";
    }
}
