import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();

        // Repeat till bye is entered
        while (!userInput.equals("bye")) {

            // Echo current input
            String toPrint = "    ____________________________________________________________\n"
                + "    " + userInput + "\n"
                + "    ____________________________________________________________";
            System.out.println(toPrint);

            // Get next input
            userInput = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");

    }
}
