package duke;

import java.util.ArrayList;

/**
 * Class that contains UI related functions.
 */
public class Ui {

    /**
     * Method to print divider of type *.
     */
    public static String lineOne() {
        return("***************************\n");
    }

    /**
     * Method to print divider of type |-|.
     */
    public static String lineTwo() {
        return("|-|-|-|-|-|-|-|-|-|-|-|-|-|\n");
    }

    /**
     * Method to print all the tasks currently in the list.
     * @param list contains all the current tasks.
     * @param size list size.
     */
    public static void printList(ArrayList<Task> list, int size, StringBuilder str) {
        str.append(lineOne());
        str.append("List: \n");
        if(list.isEmpty()) {
            str.append("No tasks to complete! ^_^\n");
            return;
        }
        for (int i = 0; i < size; i++) {
            str.append(i+1).append(". ").append(list.get(i)).append("\n");
        }
        str.append(lineOne());
    }

    /**
     * Method to print the start of program.
     */
    public static void enterHalloumi(StringBuilder str) {
        str.append(lineOne());
        str.append("Hi! I'm Halloumi ^_^\n");
        str.append("What do you need help with today?\n");
        str.append(lineOne());
    }

    public static String byeMessage() {
        String string = "See you soon! Have a good day ^_^\n";
        return string;
    }

    /**
     * Method to exit the chatbot.
     * @return an int to signify the end of the program.
     */
    public static int exitHalloumi(StringBuilder str) {
        str.append(lineOne());
        str.append(byeMessage());
        str.append(lineOne());
        return 1;
    }
}
