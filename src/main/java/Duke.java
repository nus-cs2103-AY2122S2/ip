import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println("  ===================================");

            if (userInput.equals("bye")) {
                break;
            }

            if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }

            } else if (parseUserInput(userInput).equals("mark")) {
                String[] userInputArr = userInput.split(" ", 2);
                Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task.toString());

            } else if (parseUserInput(userInput).equals("unmark")) {
                String[] userInputArr = userInput.split(" ", 2);
                Task task = tasks.get(Integer.parseInt(userInputArr[1]) - 1);

                task.markAsNotDone();

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + task.toString());
            } else {
                tasks.add(new Task(userInput));
                System.out.println("  Added:  " + userInput);
            }

            System.out.println("  ===================================");
        }

        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("  ===================================");

    }

    public static String parseUserInput(String userInput) {
        String[] userInputArr = userInput.split(" ", 2);
        if(userInputArr.length > 2) {
            return "";
        }

        return userInputArr[0];
    }
}
