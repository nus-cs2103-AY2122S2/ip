import java.util.Arrays;
import java.util.Scanner;

public class Duke {
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
            System.out.println(Arrays.toString(command));
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
                    addItem(userInput);
            }
//            if (userInput.equals("bye")){
//                break;
//            } else if (userInput.equals("list")) {
//                listItem();
//            } else {
//                addItem(userInput);
//            }
        }

        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    public static String[] parseInput(String input) {
        return input.split(" ");
    }


    public static void addItem(String item) {
        itemArray[numberOfItems] = new String[]{item, "0"};
        numberOfItems++;
        System.out.println("__________________________________");
        System.out.println("added: " + item);
        System.out.println("__________________________________");
    }

    public static void listItem() {
        System.out.println("__________________________________");
        for (int i = 0; i < 100; i++) {
            if (itemArray[i] == null) {
                break;
            } else if (itemArray[i][1].equals("0")) {
                System.out.printf("%d. [ ] " + itemArray[i][0] + "\n", i+1);
            } else {
                System.out.printf("%d. [X] " + itemArray[i][0] + "\n", i+1);
            }
        }
        System.out.println("__________________________________");
    }

    public static void markItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        itemArray[(index-1)][1] = "1";
        System.out.println("__________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[X] " + itemArray[index-1][0]);
        System.out.println("__________________________________");
    }

    public static void unmarkItem(String[] command) {
        int index = Integer.parseInt(command[1]);
        itemArray[(index-1)][1] = "0";
        System.out.println("__________________________________");
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[ ] " + itemArray[index-1][0]);
        System.out.println("__________________________________");
    }
}

