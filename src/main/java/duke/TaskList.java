package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList {

    public static void printTheList(ArrayList<Task> storeList) {
        int sizeOfList = storeList.size();
        System.out.println("Everything in my blue brain now:");
        for (int i = 1; i <= sizeOfList; i++) {
            Task t = storeList.get(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }

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
}
