package duke;

import java.util.ArrayList;

/**
 * Class that contains UI related functions.
 */
public class Ui {

    /**
     * Method to print divider of type *.
     */
    public static void lineOne() {
        System.out.println("*************************************************************************");
    }

    /**
     * Method to print divider of type |-|.
     */
    public static void lineTwo() {
        System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
    }

    /**
     * Method to print all the tasks currently in the list.
     * @param list contains all the current tasks.
     * @param size list size.
     */
    public static void printList(ArrayList<Task> list, int size) {
        lineOne();
        System.out.println("List:");
        if(list.isEmpty()) {
            System.out.println("No tasks to complete! ^_^");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        lineOne();
    }

    /**
     * Method to print the start of program.
     */
    public static void enterHalloumi() {
        lineOne();
        System.out.println("Hi! I'm Halloumi ^_^");
        System.out.println("What do you need help with today?");
        lineOne();
    }

    /**
     * Method to exit the chatbot.
     * @return an int to signify the end of the program.
     */
    public static int exitHalloumi() {
        lineOne();
        System.out.println("See you soon! Have a good day ^_^");
        lineOne();
        return 1;
    }
}
