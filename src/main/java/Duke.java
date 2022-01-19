import java.util.*;

public class Duke {
    private static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        // Greet
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        // Scanner Object
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // Add, List
        while (!input.equals("bye")) {
            int num = 1;
            if (input.equals("list")) {
                for (String item: list) {
                    System.out.println(num + ". " + item);
                    num++;
                }
            } else {
                String temp = "added: " + input;
                System.out.println(temp);
                list.add(temp);
            }
            input = scanner.nextLine();
        }
        //Exit
        System.out.println("Bye. Hope to see you again soon!");
    }
}
