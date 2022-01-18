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

        ArrayList<Task> listOfTasks = new ArrayList<Task>();
        int count = 0;

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        String command = getCommand(input); //Command is the first word of any input

        while (!command.equals("bye")) {

            if (command.equals("list")) { //Lists out all tasks in the array
                System.out.println(bar);
                System.out.println("Here are the tasks in your list: ");
                displayList(count, listOfTasks);
                System.out.println(bar);

            } else if (command.equals("mark")) {
                System.out.println(bar);
                int number = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                Task t = listOfTasks.get(number - 1);
                t.markTask();
                System.out.println("Nice! I've marked this task as done!");
                System.out.println(t.getSymbol() + " " + t.getName());
                System.out.println(bar);

            } else if (command.equals("unmark")) {
                System.out.println(bar);
                int number = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                Task t = listOfTasks.get(number - 1);
                t.unmarkTask();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println(t.getSymbol() + " " + t.getName());
                System.out.println(bar);

            } else {
                listOfTasks.add(new Task(input)); //Store the task into the array
                count++;
                System.out.println(bar);
                System.out.println(indent + "added: " + input);
                System.out.println(bar);
                System.out.println();
            }
            input = sc.nextLine();
            command = getCommand(input);
        }

        System.out.println(bar);
        System.out.println(indent + "Bye! See you soon !");
        System.out.println(bar);
    }

    //Function that prints out all string elements in an array, line by line
    public static void displayList(int lenOfArray, ArrayList<Task> arr) {
        if (lenOfArray == 0) {
            System.out.println("Nothing added yet!");
        }
        for (int i = 0; i < lenOfArray; i++) {
            Task t = arr.get(i);
            System.out.println("      " + (i + 1) + ". " + t.getSymbol() + " " + t.getName());
        }
    }

    public static String getCommand(String input) {
        int index = input.indexOf(' ');
        return (index >= 0) ? input.substring(0, index) : input;
    }

}
