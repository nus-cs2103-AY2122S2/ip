package bobby;

import java.util.ArrayList;

public class Ui {

    public static String showWelcome() {
        return "Bobby greets you. Bobby is here to help.";
    }

    /**
     * Prints the tasks currently in the list of tasks.
     * @param taskArray ArrayList containing current tasks to be printed.
     */
    public static String printList(ArrayList<Task> taskArray) {
        int count = 0;
        String result = "Here is what you told Bobby:" + System.lineSeparator();
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            count = i + 1;
            result = result + count + "." + t.toString() + System.lineSeparator();
        }
        assert count == taskArray.size();
        return result;
    }

    public static String printExit() {
        return "Bobby bids you farewell.";
    }

    public static String taskDone(Task t) {
        return "Bobby applauds you. This task is done:\n" + t.toString();
    }

    public static String taskNotDone(Task t) {
        return "Bobby will remember that this task is not yet done:\n" + t.toString();
    }

    public static String findError() {
        return "Bobby can only search using 1 keyword.";
    }

    public static String dateFormatError() {
        return "Invalid date format. Please use YYYY-MM-DD.";
    }

    public static String invalidInput() {
        return "Bobby did not understand you. Please use valid inputs.";
    }
    public static String printAddedTask(Task t, ArrayList<Task> taskArray) {
        return "Bobby heard: " + t.toString() + System.lineSeparator() + "Bobby remembers "
                + taskArray.size() + " tasks(s).";

    }

    public static String printDeletedTask(Task t, ArrayList<Task> taskArray) {
        return "Bobby has forgotten this task:\n" + t.toString() + System.lineSeparator()
                + "Bobby remembers " + taskArray.size() + " task(s).";
    }

    public static String printFoundTasks(ArrayList<Task> taskArray) {
        String result = "Bobby found these task(s):" + System.lineSeparator();
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            int count = i + 1;
            result = result + count + "." + t.toString() + System.lineSeparator();
        }
        return result;
    }

    public static String printUndo() {
        return "Bobby has forgotten your previous change.";
    }

    public static String printUndoError() {
        return "Bobby cannot Undo any further.";
    }

    public static String printNoTasksFound() {
        return "Bobby could not find any matching tasks.";
    }
}
