import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //Greetings
        System.out.println("Greetings! I'm Duke" + "\n" + "What can I do for you today?\n" + logo);

        //create a scanner object
        Scanner scanned = new Scanner(System.in);
        System.out.println("");

        //read the user input
        String input = scanned.nextLine();

        //continue the scanning if user does not say bye, else continue repeating what the user says
        while (!input.equals("bye")) {
            //prints the list of items when user inputs list
            if (input.equals("list")) {
                printList(list);
            }

            //adds user input to the list and notifies user
            else {
                addToList(input);
                System.out.println("added: " + input);
            }

            //create a scanner object which contains user input
            scanned = new Scanner(System.in);
            System.out.println("");

            //read the new user input
            input = scanned.nextLine();
        }

        //goodbye msg
        System.out.println("Sad to see you leave, come back soon!");
    }

    //to add the user's input to list
    public static void addToList(String item) {
        list.add(item);
    }

    //to print the current list of items
    public static void printList(ArrayList<String> arraylist) {
        for (int i = 0; i < arraylist.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + arraylist.get(i));
        }
    }
}
