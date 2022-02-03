package gui;

import duke.Task;
import duke.TaskList;

import java.util.ArrayList;

public class Output {

    static public String WRONG_DATE_FORMAT = "Note that dates should be in <<YYYY-MM-DD HHMM>> format";

    public static String startup() {
        String s;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        s = "Hello from\n" + logo;
        s += "\nWhat can i do for you?";
        return s;
    }

    /**
     * prints output when user enters bye
     */
    public static String printBye() {
        return "Bye. I hope to see you sometime soon! :)";
    }

    /**
     * prints when user enters input not recognised by Duke
     */
    public static String printWhatDoesThatMean() {
        return " OOPS!!! I'm sorry, but I don't know what does that mean :-(";
    }

    /**
     * prints when user leaves description of task empty
     */
    public static String printEmptyDescriptionException() {
        return " OOPS!!! The description of a task cannot be empty.";
    }

    /**
     * prints all task currently found in the Tasklist
     * @param tasklist tasklist to print from
     */
    public static String printAllTasks(TaskList tasklist) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < Task.totalTask; i++){
            s += tasklist.tasklist.get(i).toString();
        }
        s += "__________________________________________________________________";
        return s;
    }


    public static String printMatchTasks(ArrayList<Task> tasklist) {
        String s = "Here are the match tasks in your list:\n";
        for (int i = 0; i < tasklist.size(); i++){
           s += tasklist.get(i).toString();
        }
        s += "__________________________________________________________________";
        return s;
    }


    /**
     * prints the task that got removed
     * @param num index of task to remove (zero indexed)
     * @param taskList tasklist to remove task from
     */
    public static String printRemovedThisTask(int num, TaskList taskList) {
        String s = " Noted. I've removed this task: ";
        s += String.format("  [%s][%s] %s\n",taskList.tasklist.get(num).type,
                taskList.tasklist.get(num).getStatus(), taskList.tasklist.get(num).name);
        return s;
    }

    /**
     * prints when task is marked as done
     * @param curr task to be marked as done
     */
    public static String printMarkTaskDone(Task curr) {
       String s = "Nice! I've marked this task as done: ";
       s += String.format("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
       return s;
    }

    /**
     * print when task is marked as not done
     * @param curr task to be marked as not done
     */
    public static String printMarkTaskNotDone(Task curr) {
        String s = "OK, I've marked this task as not done yet: ";
        s += String.format("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        return s;
    }

    /**
     * print number of tasks left inside Tasklist
     */
    public static String printTotalTasks() {
        return String.format("Now you have %d task on the list.\n", Task.totalTask);
    }


    /**
     * print when user adds Task
     * @param task task to add into TaskList
     */
    public static String printAddThisTask(Task task) {
        String s;
        s = "Got it. I've added this task: ";
        if (task.type.equals("D")) {
            s += String.format(" [D][%s] %s (by: %s) \n", task.getStatus(), task.name, task.time);
        } else if (task.type.equals("T")) {
            s += String.format(" [T][%s] %s\n", task.getStatus(), task.name);
        } else {
            s += String.format(" [E][%s] %s (on: %s) \n", task.getStatus(), task.name, task.time);
        }
        return s;
    }
}
