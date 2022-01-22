import java.util.Scanner;

public class Duke {
    public static String[] itemArray = new String[100];
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
        String userInput = "";
        while (true) {
            userInput = dukeScan.nextLine();
            if (userInput.equals("bye")){
                break;
            } else if (userInput.equals("list")) {
                listItem();
            } else {
                addItem(userInput);
            }
        }
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");
    }

    public static void addItem(String item) {
        itemArray[numberOfItems] = item;
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
            }
            System.out.printf("%d. " + itemArray[i] + "\n", i+1);
        }
        System.out.println("__________________________________");
    }
}
