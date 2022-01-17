import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke\n\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
        // Initialise String array of tasks
        String[] tasks = new String[100];
        int count = 0;
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye"))
                break;
            if (input.equals("list")) {
                System.out.println("\t____________________________________________________________");
                for (int taskCount = 0; taskCount < count; taskCount++) {
                    System.out.println("\t " + (taskCount + 1) + ". " + tasks[taskCount]);
                }
                System.out.println("\t____________________________________________________________");
                continue;
            }
            tasks[count] = input;
            count++;
            System.out.println("\t____________________________________________________________");
            System.out.println("\t added: " + input);
            System.out.println("\t____________________________________________________________");
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
