package Duke;

import java.util.ArrayList;
import java.util.Scanner;

class Ui {
    public final static String welcomeMsg = "Hello! I am Duke\nWhat can I do for you?";
    public final static String horizontalLine = "________________________________";
    public final static String byeMsg = "Bye! hope to see you again soon!";
    public final static String listMsg = "Here are a list of your tasks!";
    public final static String deleteMsg = "Alright! Let me delete this task:\n";
    public final static String invalidCommandMsg = "I do not understand your command!";
    public final static String addedTaskMsg = "Got it. I've added this task:\n";
    public final static String completedTaskMsg = "Task has been marked as completed.\n";
    public final static String notCompletedTaskMsg = "Task has been marked as not completed.";
    public final static String expectedDeadline = "Try typing it in the following format:\n " +
            "deadline <desciption> /by 31-01-2022 1800";
    public final static String expectedEvent = "Try typing it in the following format:\n " +
            "event <desciption> /at 31-01-2022 1800_to_31-01-2022 2100";
    public final static String getTaskMsg = "Here are the tasks containing the keyword";


    /**
     * Prints out the number of items in the list of tasks after adding in a new one.
     */
    public static String getTaskInListMsg(Task task, int numTask){
        return addedTaskMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
    }

    /**
     * Prints out the number of items in the list of tasks after deleting one.
     */
    public static String getDeleteTaskInListMsg(Task task, int numTask){
        return deleteMsg + "\t" + task + "\nNow you have " + numTask + " tasks in the list";
    }

    /**
     * Return completion status of task.
     */
    public static String getCompleteMessage(boolean isCompleted){
        return (isCompleted)?completedTaskMsg:notCompletedTaskMsg;
    }

    /**
     * Print the tasks present in the arraylist.
     */
    public String printTasks(ArrayList<Task> taskList){ //needs to be un-voided
        //System.out.println(horizontalLine);
        String listReturn = "";
        int  i = 1;
        for(Task t: taskList){
            //System.out.println((i++) +". " +t);
            String temp = i++ + ". " + t + "\n";
            listReturn += temp;
        }
        return listReturn;
    }

    /**
     * Format print messages.
     */
    public String showWelcomeMessage() {
        return "Hi! My name is Duke and I will be your task manager for today :)"
                + "checkout https://prgj.github.io/ip/ for a list of commands I can manage";
    }

    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Format print messages.
     */
    public void printMessage(String msg) {
        System.out.println(horizontalLine);
        System.out.println(msg);
        System.out.println(horizontalLine);
    }
}