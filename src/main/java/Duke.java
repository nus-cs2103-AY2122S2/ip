import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HORIZONTAL_LINE = "\t" + "____________________________________________________________";
        System.out.println(HORIZONTAL_LINE);
        System.out.println("\t" + "Hello! I'm Duke");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + "Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("\t" + userInput);
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}
