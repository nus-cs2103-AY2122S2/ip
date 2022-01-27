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
     * @return Nothing
     *
     */
    public static void printTheList(ArrayList<Task> storeList) {
        int sizeOfList = storeList.size();
        System.out.println("Everything in my blue brain now:");
        for (int i = 1; i <= sizeOfList; i++) {
            Task t = storeList.get(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }

    /**
     * Deletes a specific item in the tasklist
     *
     * @param command command of which task to delete
     *
     * @return Nothing
     *
     */
    public static void deleteTask(String command, ArrayList<Task> storeList) {
        if (command.length() <= 7) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "delete tasknumber. Please wish again");
        } else {
            try {
                Task t = storeList.get(Integer.parseInt(command.substring(7)));
                System.out.println("Yes master. The task " + t.toString() + " has been removed");
                storeList.remove(Integer.parseInt(command.substring(7)));
                System.out.println("Now you have " + storeList.size() + " tasks left master");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Master, you only have " + storeList.size() + " tasks now. Please choose another task number");
            }
        }
    }

    /**
     * Unmark a task status in the task list
     *
     * @param command command by user which specify which task
     * @param storeList tasklist to be unmarked from
     * @return Nothing
     *
     */
    public static void unmarkCommand(String command, ArrayList<Task> storeList) {
        int taskNumber1 = Integer.parseInt(command.substring(7));
        if (taskNumber1 > storeList.size()) {
            System.out.println("Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. Please wish again");
        } else if (taskNumber1 <= 0) {
            System.out.println("Master, I am only a genie. Please choose a number between 1 and " + storeList.size());
        } else {
            Task tobeUnmark = storeList.get(taskNumber1 - 1);
            tobeUnmark.setUnmark();
            System.out.println("You probably need more genies to help you. This task has been marked as not done");
            System.out.println(tobeUnmark.toString());
        }
    }

    /**
     * Unmark a task status in the task list
     *
     * @param command command by user which specify which task
     * @param storeList tasklist to be marked from
     * @return Nothing
     *
     */
    public static void markCommand(String command, ArrayList<Task> storeList) {
        int taskNumber = Integer.parseInt(command.substring(5));
        if (taskNumber > storeList.size()) {
            System.out.println("Master, you do not have that many tasks, you currently only have "
                    + storeList.size() + " tasks. Please wish again");
        } else if (taskNumber <= 0) {
            System.out.println("Master, I am only a genie. Please choose a number between 1 and " + storeList.size());
        } else {
            Task tobeMark = storeList.get(taskNumber - 1);
            tobeMark.setMark();
            System.out.println("You could have gotten me to help you. This task has been marked done");
            System.out.println(tobeMark.toString());
        }
    }

    /**
     * Add event type task to tasklist
     *
     * @param command command by user which specify task description
     * @param storeList tasklist to be added to
     * @return Nothing
     *
     */
    public static void addEvent(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 6) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "deadline task /at dateofevent. Please wish again");
        } else {
            String newtask = command.substring(6, slash - 1);
            String attime = command.substring(slash + 1);
            Event e = new Event(newtask, attime);
            storeList.add(e);
            System.out.println("Added to my brain master:");
            System.out.println(e.toString());
            System.out.println("Currently I have " + storeList.size() + " things in my brain");
        }
    }

    /**
     * Add Todo type task to tasklist
     *
     * @param command command by user which specify task description
     * @param storeList tasklist to be added to
     * @return Nothing
     *
     */
    public static void addTodo(String command, ArrayList<Task> storeList) {
        if (command.length() < 5) {
            System.out.println("Master, I have all the knowledge in the world but I do not know what you want to do," +
                    " Please wish again in the format todo task");
        } else {
            String newtask = command.substring(5);
            Todo t = new Todo(newtask);
            storeList.add(t);
            System.out.println("Added to my brain master:");
            System.out.println(t.toString());
            System.out.println("Currently I have " + storeList.size() + " things in my brain");
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
    public static void addDeadline(String command, ArrayList<Task> storeList) {
        int slash = command.indexOf("/");
        if (slash == -1 || slash <= 9) {
            System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                    "deadline task /by dateofdeadline. Please wish again");
        } else {
            String newtask = command.substring(9, slash - 1);
            if (newtask.equals("")) {
                System.out.println("Master, you wished wrongly. Remember you have to wish in this format " +
                        "deadline task /by dateofdeadline. Please wish again");
            } else {
                String endtime = command.substring(slash + 1);
                Deadline d = new Deadline(newtask, endtime);
                storeList.add(d);
                System.out.println("Added to my brain master:");
                System.out.println(d.toString());
                System.out.println("Currently I have " + storeList.size() + " things in my brain");
            }
        }
    }

    public static void findTask(String command, ArrayList<Task> storeList) {
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
            System.out.println("Im sorry master, you have no matching tasks");
        } else {
            System.out.println("Master, I have found these in my brain");
            for (int j = 0; j < tobePrinted.size(); j++) {
                System.out.println((j + 1) + ". " + tobePrinted.get(j).toString());
            }
        }
    }
}
