import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(separation + greeting + separation);


        while(true) {
            Scanner input = new Scanner(System.in);
            String inp = input.nextLine();
            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(separation+ inp + separation);
            }
        }

    }

    public static String greeting = "Hello! I'm Duke\nWhat can I do for you?";

    public static String separation = "\n******************************\n";
}
