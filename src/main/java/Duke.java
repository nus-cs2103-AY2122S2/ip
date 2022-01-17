import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String bar = "-------------------------------------------------"; //Reusable horizontal bar display
        String indent = "      "; //Reusable indentation display
        System.out.println(bar);
        System.out.println("Hi I'm Zen!\n" +
                "How can I help ?");
        System.out.println(bar);
        System.out.println();

        String[] listOfTasks = new String[100];
        int count = 0;

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        while (!input.equals("bye")) {

            if (input.equals("list")) { //Lists out all tasks in the array
                System.out.println(bar);
                displayList(count, listOfTasks);
                System.out.println(bar);
            } else {
                listOfTasks[count] = input; //Store the task into the array
                count++;
                System.out.println(bar);
                System.out.println(indent + "added: " + input);
                System.out.println(bar);
                System.out.println();
            }
            input = sc.nextLine();
        }
        System.out.println(bar);
        System.out.println(indent + "Bye! See you soon !");
        System.out.println(bar);
    }

    //Function that prints out all string elements in an array, line by line
    public static void displayList(int lenOfArray, String[] arr) {
        if (lenOfArray == 0) {
            System.out.println("Nothing added yet!");
            return;
        }
        for (int i = 0; i < lenOfArray; i++) {
            System.out.println("      " + (i + 1) + ". " + arr[i]);
        }
        return;
    }
}
