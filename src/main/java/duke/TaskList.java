package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
/**
 Class to process the Tasklist of the chatbot
 */
public class TaskList {

    /**
     * Prints the arraylist for the user of the chatbot
     *
     * @param storeList arraylist to be printed
     * @return String of the contents of the list
     *
     */
    public static String printTheList(ArrayList<Task> storeList) {
        StringBuffer sb = new StringBuffer();
        for (Task s : storeList) {
            sb.append(s.toString());
            sb.append("\n");
        }

        String str = sb.toString();
        return str;
    }

    /**
     * Deletes a specific item in the tasklist
     *
     * @param command command of which task to delete
     *
     * @return String of response
     *
     */
    public static String deleteTask(String command, ArrayList<Task> storeList) {
        if (command.length() <= 7) {
            return "Master, you wished wrongly. Remember you have to wish in this format " + "\n" +
                    "delete tasknumber. Please wish again";
        } else {
            try {
                Task t = storeList.get(Integer.parseInt(command.substring(7)) + 1);
                storeList.remove(Integer.parseInt(command.substring(7)));
                return "Yes master. The task " + t.toString() + " has been removed " + "\n" + "Now you have "
                        + storeList.size() + " tasks left master";
            } catch (IndexOutOfBoundsException e) {
                return "Master, you only have " + storeList.size() + " tasks now." + "\n" +
                        "Please choose another task number";
            }
        }
    }

    /**
     * Unmark a task status in the task list
     *
     * @param command command by user which specify which task
     * @param storeList tasklist to be unmarked from
     * @return String of response
     *
     */
    public static String unmarkCommand(String command, ArrayList<Task> storeList) {
        int taskNumber1 = Integer.parseInt(command.substring(7));
        if (taskNumber1 > storeList.size()) {
            return "Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. " + "\n" + "Please wish again";
        } else if (taskNumber1 <= 0) {
            return "Master, I am only a genie. Please choose a number between 1 and " + storeList.size();
        } else {
            Task tobeUnmark = storeList.get(taskNumber1 - 1);
            tobeUnmark.setUnmark();
            return "You probably need more genies to help you. This task has been marked as not done" +
                    tobeUnmark.toString();
        }
    }

    /**
     * Mark a task status in the task list
     *
     * @param command command by user which specify which task
     * @param storeList tasklist to be marked from
     * @return String of response
     *
     */
    public static String markCommand(String command, ArrayList<Task> storeList) {
        int taskNumber = Integer.parseInt(command.substring(5));
        if (taskNumber > storeList.size()) {
            return "Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. " + "\n" + "Please wish again";
        } else if (taskNumber <= 0) {
            return "Master, I am only a genie. Please choose a number between 1 and " + storeList.size();
        } else {
            Task tobeMark = storeList.get(taskNumber - 1);
            tobeMark.setMark();
           return "You could have gotten me to help you. This task has been marked done" +
                   tobeMark.toString();
        }
    }

    /**
     * Add event type task to tasklist
     *
     * @param command command by user which specify task description
     * @param storeList tasklist to be added to
     * @return String of response
     *
     */
    public static String addEvent(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 6) {
            return "Master, you wished wrongly." + "\n" + "Remember you have to wish in this format " +
                    "event task /at TIME." + "\n" + "Please wish again";
        } else {
            String newtask = command.substring(6, slash - 1);
            String attime = command.substring(slash + 1);
            Event e = new Event(newtask, attime);
            storeList.add(e);
            return "Added to my brain master:" + e.toString() + " Currently I have " +
                    storeList.size() + " things in my brain";
        }
    }

    /**
     * Add Todo type task to tasklist
     *
     * @param command command by user which specify task description
     * @param storeList tasklist to be added to
     * @return String of response
     *
     */
    public static String addTodo(String command, ArrayList<Task> storeList) {
        if (command.length() < 5) {
            return "Master, I have all the knowledge in the world but I do not know what you want to do," + "\n" +
                    " Please wish again in the format todo task";
        } else {
            String newtask = command.substring(5);
            Todo t = new Todo(newtask);
            storeList.add(t);
            return "Added to my brain master:" + "\n" + t.toString() + "\n" + "Currently I have "  +
                    storeList.size() + " things in my brain";
        }
    }

    /**
     * Add Deadline type task to tasklist
     *
     * @param command command by user which specify task description
     * @param storeList tasklist to be added to
     * @return Nothing
     *
     */
    public static String addDeadline(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 9) {
            return "Master, you wished wrongly. Remember you have to wish in this format " +
                    "deadline task /by DD/MM/YYYY TIME. " + "\n" + "Please wish again";
        } else {
            String newtask = command.substring(9, slash - 1);
            if (newtask.equals("")) {
                return "Master, you wished wrongly. Remember you have to wish in this format " +
                        "deadline task /by DD/MM/YYYY TIME. " + "\n" + "Please wish again";
            } else {
                String endtime = command.substring(slash + 1);
                Deadline d = new Deadline(newtask, endtime);
                storeList.add(d);
                return "Added to my brain master:" + "\n" + d.toString() + "Currently I have " +
                        storeList.size() + " things in my brain";
            }
        }
    }

    public static String findTask(String command, ArrayList<Task> storeList) {
        String itemToFind = command.substring(5);
        int sizeOfList = storeList.size();
        ArrayList<Task> tobePrinted = new ArrayList<>();
        for (int i = 0; i < sizeOfList; i++) {
            if (storeList.get(i).getDescription().contains(itemToFind)) {
                tobePrinted.add(storeList.get(i));
            } else {

            }
        }
        if (tobePrinted.size() == 0) {
            return "Im sorry master, you have no matching tasks";
        } else {
            StringBuffer sb = new StringBuffer();
            for (Task s : tobePrinted) {
                sb.append(s.toString());
                sb.append(" ");
            }

            String str = sb.toString();
            return str;
        }
    }
}
