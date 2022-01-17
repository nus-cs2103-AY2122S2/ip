import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Printer p = new Printer();
        p.print("Hello! I'm Duke," , "What can I do for you?");

        Scanner in = new Scanner(System.in);
        String userInput = in.nextLine();

        while(!userInput.equals("bye")) {
            p.print(userInput);
            userInput = in.nextLine();
        }

        p.print(" Bye. Hope to see you again soon!");
    }



}



