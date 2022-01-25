import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static Task[] taskArray = new Task[100];
    public static String[][] itemArray = new String[100][];
    public static int numberOfItems = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("__________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you");
        System.out.println("__________________________________");

        listen();
    }

    public static void listen() {
        Scanner dukeScan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            String userInput = dukeScan.nextLine();
            String[] command = parseInput(userInput);
            switch (command[0]) {
                case "bye":
                    running = false;
                    break;
                case "list":
                    listItem();
                    break;
                case "mark":
                    markItem(command);
                    break;
                case "unmark":
                    unmarkItem(command);
                    break;
                default:
                    addItem(command);
            }
        }

        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    public static String[] parseInput(String input) {
        String[] inputArray = input.split(" ");
        String[] tempArray = Arrays.copyOfRange(inputArray, 1, inputArray.length);
        return new String[] {inputArray[0], String.join(" ", tempArray)};
    }


    public static void addItem(String[] command) {
        String input = command[0];
        Task task = null;
        String title = "";
        String time = "";
        switch (input) {
            case "todo":
                task = new Todo(command[1]);
                break;
            case "deadline":
                title = command[1].split("/by ")[0];
                time = command[1].split("/by ")[1];
                task = new Deadline(title, time);
                break;
            case "event":
                title = command[1].split("/at ")[0];
                time = command[1].split("/at ")[1];
                task = new Event(title, time);
                break;
        }

        taskArray[numberOfItems] = task;
        numberOfItems++;
        System.out.println("__________________________________");
        System.out.println("added: " + task);
        System.out.printf("You have %d tasks in your list\n", numberOfItems);
        System.out.println("__________________________________");
    }

    public static void listItem() {
        System.out.println("__________________________________");
        for (int i = 0; i < 100; i++) {
            if (taskArray[i] == null) {
                break;
            } else {
                System.out.printf("%d. " + taskArray[i] + "\n", i+1);
            }
        }
        System.out.println("__________________________________");
    }

    public static void markItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        taskArray[(index-1)].setChecked();
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskArray[index-1]);
        System.out.println("__________________________________");
    }

    public static void unmarkItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        taskArray[(index-1)].setUnChecked();
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(taskArray[index-1]);
        System.out.println("__________________________________");
    }
}

