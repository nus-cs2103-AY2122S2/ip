import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duck\nWhat can I do for you? *quack*");

        Scanner sc = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = sc.nextLine();
            System.out.println(String.format("You quacked: %s", command));
        }
        System.out.println("Aww. Hope to see you again soon! *quack*");
    }
}
