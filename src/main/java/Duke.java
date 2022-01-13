import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();

    public static void addTask(String task) {
        System.out.println("Added as per your request: " + task);
        tasks.add(task);
    }

    public static void listTasks() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.append(i+1).append(". ").append(tasks.get(i)).append("\n");
        }
        System.out.println(listOfTasks);
    }

    public static void main(String[] args) {

        String divider = "\n______________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Konnichiwassup from\n" + logo);
        Scanner scanner = new Scanner(System.in);


        System.out.println(divider);
        System.out.println("What do you need help with?\n");
        String userInput = scanner.nextLine();

        while (!userInput.equalsIgnoreCase("bye")) {
            if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
            System.out.println(divider);
            userInput = scanner.nextLine();
        }

        System.out.println(divider);
        System.out.println("Sayonara! Hope to see you again soon!");
    }
}
