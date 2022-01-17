import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";
        System.out.println(intro);
        ArrayList<String> tasks = new ArrayList<>();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i - 1));
                }
            } else {
                tasks.add(cmd);
                System.out.println("added: " + cmd);
            }
            cmd = sc.nextLine();

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
