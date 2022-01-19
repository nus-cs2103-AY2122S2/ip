import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input = null;
        String bye = "bye";
        while(true) {
            input = scanner.nextLine();
            if(input.equals(bye)) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(input);
        }
    }
}
