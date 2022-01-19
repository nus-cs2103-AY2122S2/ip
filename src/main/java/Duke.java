import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greetings
        System.out.println("Greetings! I'm Duke" + "\n" + "What can I do for you today?\n" + logo);

        //to read user input
        Scanner scanned = new Scanner(System.in);
        System.out.println("");
        String input = scanned.nextLine();

        //continue the scanning if user does not say bye, else continue repeating what the user says
        while (!input.equals("bye")) {

            //prints the list of items when user inputs list
            if (input.equals("list")) {
                printList(list);
            }

            //marks a task as done if the user inputs a string in format "mark xx"
            //NOTE: currently, inputting "mark" alone results in a task named "mark" to be added to the list of tasks
            else if (input.length() > 4 && "mark".equals(input.substring(0, 4))) {
                if (Integer.parseInt(input.substring(5)) <= list.size() && Integer.parseInt(input.substring(5)) > 0) {
                    Task toBeMarked = list.get(Integer.parseInt(input.substring(5)) - 1);
                    toBeMarked.markAsDone();
                }
            }

            //unmarks a task as undone if the user inputs a string in format "unmark xx"
            //NOTE: currently, inputting "unmark" alone results in a task named "unmark" to be added to the list of tasks
            else if (input.length() > 6 && "unmark".equals(input.substring(0, 6))) {
                if (Integer.parseInt(input.substring(7)) <= list.size() && Integer.parseInt(input.substring(7)) > 0) {
                    Task toBeMarked = list.get(Integer.parseInt(input.substring(7)) - 1);
                    toBeMarked.markAsUndone();
                }
            }

            //adds user input to the list and notifies user
            else {
                addToList(input);
                System.out.println("added: " + input);
            }

            //to continue reading user input
            scanned = new Scanner(System.in);
            System.out.println("");
            input = scanned.nextLine();
        }

        //goodbye msg
        System.out.println("Sad to see you leave, come back soon!");
    }

    //to add the user's input to list
    public static void addToList(String description) {
        Task item = new Task(description);
        list.add(item);
    }

    //to print the current list of items
    public static void printList(ArrayList<Task> arraylist) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arraylist.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + arraylist.get(i).getStatusIcon() + " " + arraylist.get(i).returnDescription());
        }
    }
}
