package duke;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that is responsible for input and output of Duke program
 */

public class Ui {

    public Ui(){
        startup();
    }

    /**
     * Main program that Duke runs on, will loop till input is 'bye'
     */
    public void loop(TaskList tasklist){
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while(!isBye) {
            String input = sc.nextLine();
            isBye = Parser.parseIsBye(input, tasklist);
        }
        printBye();
    }

    /**
     * startup logo
     */
    public static void startup(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
    }

    /**
     * prints output when user enters bye
     */
    public static void printBye(){
        System.out.println("Bye. I hope to see you sometime soon! :)");
    }

    /**
     * print when user enters input not recognised by Duke
     */
    public static void printWhatDoesThatMean(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what does that mean :-(");
    }

    /**
     * print when user leaves description of task empty
     */
    public static void printEmptyDescriptionException(){
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }

    /**
     * print all task currently found in the Tasklist
     */
    public static void printAllTasks(TaskList tasklist){
        System.out.print("Here are the tasks in your list:\n");
        for(int i=0; i<Task.totalTask; i++){
            System.out.print(tasklist.tasklist.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }


    public static void printMatchTasks(ArrayList<Task> tasklist){
        System.out.printf("Here are the match tasks in your list:\n");
        for(int i=0; i<tasklist.size(); i++){
            System.out.print(tasklist.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }

    /**
     * print the task that got removed
     * @param num index of task to remove (zero indexed)
     */
    public static void printRemovedThisTask(int num, TaskList taskList){
        System.out.println(" Noted. I've removed this task: ");
        System.out.printf("  [%s][%s] %s\n",taskList.tasklist.get(num).type, taskList.tasklist.get(num).getStatus(), taskList.tasklist.get(num).name);
    }

    /**
     * print when task is marked as done
     * @param curr task to be marked as done
     */
    public static void printMarkTaskDone(Task curr){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
    }

    /**
     * print when task is marked as not done
     * @param curr task to be marked as not done
     */
    public static void printMarkTaskNotDone(Task curr){
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
    }

    /**
     * print number of tasks left inside Tasklist
     */
    public static void printTotalTasks(){
        System.out.printf("Now you have %d task on the list.\n", Task.totalTask);
    }

    /**
     * print when user adds Task
     * @param task task to add into TaskList
     */
    public static void printAddingTasks(Task task) {
        System.out.println("Got it. I've added this task: ");
        if (task.type.equals("D")) {
            System.out.printf(" [D][%s] %s (by: %s) \n", task.getStatus(), task.name, task.time);
        } else if (task.type.equals("T")) {
            System.out.printf(" [T][%s] %s\n", task.getStatus(), task.name);
        } else {
            System.out.printf(" [E][%s] %s (on: %s) \n", task.getStatus(), task.name, task.time);
        }
    }
}
