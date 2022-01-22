import java.util.Scanner;

public class Duke {
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
        while (!userInput.equals("bye")) {
            userInput = dukeScan.nextLine();
            System.out.println("__________________________________");
            System.out.println(userInput);
            System.out.println("__________________________________");
        }
        System.out.println("__________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("__________________________________");


    }
}
