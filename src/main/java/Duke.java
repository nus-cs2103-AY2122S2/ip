import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String greeting = "     ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "     ____________________________________________________________";

        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {

            String input = sc.next();
            String output = "";
            String line = "     ____________________________________________________________\n";

            if (input.equals("bye"))
                output = "Bye. Hope to see you again soon!";
            else
                output = input;

            System.out.println(line
                    + "     "
                    + output
                    + "\n"
                    + line);

            if (!output.equals(input))
                break;
        }

        sc.close();
    }
