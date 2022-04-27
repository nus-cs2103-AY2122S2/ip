package duke2;// package duke1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {


    /**
     * Prints welcome msg of chatbot
     */
    public static String printWelcomeMsg() {
        return "Hello! I'm Duke \nWhat can I do for you?";
    }

    /**
     * Prints ending msg of chatbot
     */
    public static String printEndMsg() {
        return "Bye. Hope to see you again soon!";
    }

    public static String printAddedTask(ArrayList<Task> list) {
        StringBuilder str = new StringBuilder();
        str.append("Got it. I have added this task: " + list.get(list.size() - 1) + "\n");
        Duke.totalCount += 1;
        str.append("Now you have " + list.size() + " item in the list\n");
        return str.toString();
    }

    public static String printUpdate(String original, String word) {
        return "I have updated " + original + " to " + word + "\n";
    }



    public static String printDeletedTask(ArrayList<Task> list, Task t) {
        Duke.totalCount--;
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task: \n" + t + "\n");
        str.append("Now you have " + list.size() + " item in the list \n");
        return str.toString();
    }

    public static String printIdk() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-( \n";
    }

    public static String printEmptyError(String[] input) {
        return "OOPS!!! The description of a " + input[0] + " cannot be empty. \n";
    }

    public static String printList(ArrayList<Task> list) {
        System.out.println(list.toString());
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list: \n" );
        for (int i = 1; i <= list.size() && list.get(i - 1) != null; i++) {
            str.append(i + ". " + list.get(i - 1) + "\n");
        }
        return str.toString();
    }

    public static String printUndo() {
        return "I have undone the previous command \n";
    }
}
