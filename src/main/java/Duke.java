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

            switch (command) {
                case "list":
                    System.out.println(bar);
                    System.out.println(indent + "Here are the tasks in your list: ");
                    displayList(count, listOfTasks);
                    System.out.println(bar);
                    break;
                case "mark":
                    System.out.println(bar);
                    int number = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task markT = listOfTasks.get(number - 1);
                    markT.markTask();
                    System.out.println(indent + "Nice! I've marked this task as done!");
                    System.out.println(indent + markT.toString());
                    System.out.println(bar);
                    break;
                case "unmark":
                    System.out.println(bar);
                    int num = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                    Task unmarkT = listOfTasks.get(num - 1);
                    unmarkT.unmarkTask();
                    System.out.println(indent + "OK, I've marked this task as not done yet");
                    System.out.println(indent + unmarkT.toString());
                    System.out.println(bar);
                    break;
                case "todo":
                    System.out.println(bar);
                    Todo td = Todo.formatInput(input);
                    System.out.println(indent + "Got it! I've added this task:");
                    System.out.println(indent + td.toString());
                    listOfTasks.add(td);
                    count++;
                    System.out.println(indent + "Now you have " + listOfTasks.size() + " tasks in the list");
                    System.out.println(bar);
                    break;
                case "deadline":
                    System.out.println(bar);
                    Deadline dl = Deadline.formatInput(input);
                    System.out.println(indent + "Got it! I've added this task:");
                    System.out.println(indent + dl.toString());
                    listOfTasks.add(dl);
                    count++;
                    System.out.println(indent + "Now you have " + listOfTasks.size() + " tasks in the list");
                    System.out.println(bar);
                    break;
                case "event":
                    System.out.println(bar);
                    Event ev = Event.formatInput(input);
                    System.out.println(indent + "Got it! I've added this task:");
                    System.out.println(indent + ev.toString());
                    listOfTasks.add(ev);
                    count++;
                    System.out.println(indent + "Now you have " + listOfTasks.size() + " tasks in the list");
                    System.out.println(bar);
                    break;
                default:
                    System.out.println("Nothing here yet");
            }
            System.out.println();
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
            System.out.println("      " + (i + 1) + ". " + t.toString());
        }
    }

    //Gets the first word of every line of input, labelled as the "command" word
    public static String getCommand(String input) {
        int index = input.indexOf(' ');
        return (index >= 0) ? input.substring(0, index) : input;
    }

}
