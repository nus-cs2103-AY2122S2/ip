import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye"))
                break;
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
