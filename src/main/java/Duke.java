import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        String nextInput = userInput.nextLine();

        while (!nextInput.equalsIgnoreCase("bye")) {
            System.out.println(nextInput);
            nextInput = userInput.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
