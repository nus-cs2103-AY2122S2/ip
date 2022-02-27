package gui;

import walle.Task;
import walle.TaskList;

import java.util.ArrayList;

public class Ui {

    static public String WRONG_DATE_FORMAT = "Note that dates should be in <<YYYY-MM-DD HHMM>> format\n";

    public static String printStartup() {
        String s = "Beep Boop. This is Walle \n";
        s += "Welcome Captain B. McCrea \n";
        s += "\nWhat can i do for you?";
        return s;
    }

    /**
     * prints output when user enters bye
     *
     * @return string output during exit
     */
    public static String printBye() {
        return "Bye. I hope to see you sometime soon! :)";
    }

    /**
     * prints when user enters input not recognised by Walle
     *
     * @return string output when unrecognised input is entered
     */
    public static String printWhatDoesThatMean() {
        return "BEEP BOOP ERROR!!! I'm sorry, but I don't know what does that mean :-(";
    }

    /**
     * prints when user leaves description of task empty
     *
     * @return string output of empty descriptor
     */
    public static String printEmptyDescriptionException() {
        return "BEEP BOOP ERROR!!! The description of a task cannot be empty.";
    }

    /**
     * prints all task currently found in the Tasklist
     *
     * @param tasklist tasklist to print from
     * @return string representation of all tasks
     */
    public static String printAllTasks(TaskList tasklist) {
        String s = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasklist.get().size(); i++){
            s += tasklist.get().get(i).toString();
        }
        return s;
    }

    /**
     * prints task that matches keyword
     *
     * @param tasklist list of tasks that contains keyword
     * @return string representation of task that matches keyword
     */
    public static String printMatchTasks(ArrayList<Task> tasklist) {
        String s = "Here are the match tasks in your list:\n";
        for (int i = 0; i < tasklist.size(); i++){
           s += tasklist.get(i).toString();
        }
        return s;
    }


    /**
     * prints the task that got removed
     *
     * @param num index of task to remove (zero indexed)
     * @param taskList tasklist to remove task from
     * @return string representation of task that have been removed
     */
    public static String printRemovedThisTask(int num, TaskList taskList) {
        String s = "BEEP. I've removed this task: ";
        s += String.format("  [%s][%s] %s\n",taskList.tasklist.get(num).type,
                taskList.tasklist.get(num).getStatus(), taskList.tasklist.get(num).name);
        return s;
    }

    /**
     * prints when task is marked as done
     * @param curr task to be marked as done
     *
     * @return string representation of task that have been marked
     */
    public static String printMarkTaskDone(Task curr) {
       String s = "BEEP! I've marked this task as done: ";
       s += String.format("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
       return s;
    }

    /**
     * print when task is marked as not done
     * @param curr task to be marked as not done
     *
     * @return string representation of task that have been unmarked
     */
    public static String printMarkTaskNotDone(Task curr) {
        String s = "OK, I've marked this task as not done yet: ";
        s += String.format("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
        return s;
    }

    /**
     * print number of tasks left inside Tasklist
     *
     * @return string representation of total tasks
     */
    public static String printTotalTasks(int size) {
        return String.format("Now you have %d task on the list.\n", size + 1);
    }


    /**
     * print when user adds Task
     *
     * @param task task to add into TaskList
     * @return string representation of printed task
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

    public static String printNoSuchTask() {
        return "BEEP! There exists no such task.\n";
    }

    public static String printDuplicateTask(int i){
        return String.format("BEEP! Error! There is a duplicate task (taskNumber %d)\n", i);
    }
}
