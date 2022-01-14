import java.util.Scanner;

public class Spark {
    public static void main(String[] args) {
        // assuming that there will be no more than 100 tasks,
        TaskList tasks = new TaskList();

        Scanner sc = new Scanner(System.in);

        // print welcome message
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello! I'm Spark");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------------------------------------------");

        String input;
        while (true) {
            // get input from user
            input = sc.nextLine();
            String[] split = input.split(" "); // split command into individual keywords
            String command = split[0]; // assume that the first keyword is always the command word

            System.out.println("----------------------------------------------------------------------");

            if (command.equals("bye")) {
                // print goodbye message
                System.out.println("Cool, see you around!");
                System.out.println("----------------------------------------------------------------------");

                break;
            } else if (command.equals("list")) {
                tasks.print();
            } else {
                tasks.add(input);
            }

            System.out.println("----------------------------------------------------------------------");
        }
    }
}
