package duke;
import java.util.ArrayList;

public class Ui {

    private static String indent = " ";

    public static String gotItStatement() {
        return("Got it. I've added this task\n");
    }

    public static String entryMessage() {
        return(addNewLine() + "Hello I'm Duke\nWhat can I do for you today?" + addNewLine());
    }

    public static String addTaskMessage(Task task) {
        return (gotItStatement() + printTask(task) + addNewLine());
    }

    public static String byeMessage() {
        return("Bye, see you next time" + addNewLine());
    }

    public static String addNewLine() {
        return("\n");
    }

    public static String markAsDone(Task task) {
        return(task + "\n");
    }
    public static String markAsNotDone(Task task) {
        return(task + "\n");
    }

    public static String printAllTasks(ArrayList<Task> listOfTasks) {
        String taskString = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            taskString += indent + currIndex + ". " + currTask + "\n";
        }
        return taskString + addNewLine();
    }

    public static String deleteMessage(Task task, int size) {
        return(task + addNewLine());

    }

    /**
     * function to print message on running find
     * @param listOfTasks list of tasks that match the description
     */
    public static String findMessage(ArrayList<Task> listOfTasks) {
        String taskString = "";
        for (Task task : listOfTasks) {
            taskString += indent + task;
        }
        return("Noted. I have found these tasks matching your description\n" + taskString + addNewLine());

    }

    public static String showMessage(String message) {
        return(message);
    }


    public static String printTask(Task task) {
        return(indent + task);
    }

    public static String printSize(int size) {
        return("Now you have " + size + " tasks in the list\n");

    }
}
