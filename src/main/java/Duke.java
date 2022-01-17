import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(intro);
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            System.out.println(cmd);
            cmd = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
