import java.util.Scanner;

public class Duke {

    public static final String greeting = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
    public static final String goodbye = "\tBye. Hope to see you again soon!";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        System.out.println(greeting);

        while (!input.equals("bye")) {
            System.out.println("\t"+input+"\n");
            input = sc.nextLine();
        }

        System.out.println(goodbye);
    }
}
