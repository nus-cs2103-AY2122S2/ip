package bobby;

import java.util.ArrayList;

public class Ui {

    public static void showWelcome() {
        System.out.println("Bobby greets you. Bobby is here to help.");
    }

    public static void fileExists() {
        System.out.println("Bobby remembers previous tasks.");
    }

    public static void newFileCreated() {
        System.out.println("Bobby has created a new list.");
    }

    public static void printList(ArrayList<Task> taskArray) {
        System.out.println("Here is what you told Bobby:");
        for (int i = 0; i < taskArray.size(); i++) {
            Task t = taskArray.get(i);
            int count = i + 1;
            System.out.println(count + "." + t);
        }
    }

    public static void printExit() {
        System.out.println("Bobby bids you farewell.");
    }

    public static void taskDone(Task t) {
        System.out.println("Bobby applauds you. This task is done:\n" + t);
    }

    public static void taskNotDone(Task t) {
        System.out.println("Bobby will remember that this task is not yet done:\n" + t);
    }

    public static void printTask(Task t) {
        System.out.println(t);
    }
}
