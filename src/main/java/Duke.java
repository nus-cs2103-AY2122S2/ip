import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String logo = "   _____       _ _                        \n" +
                "  / ____|     (_) |                       \n" +
                " | (___   __ _ _| |_ __ _ _ __ ___   __ _ \n" +
                "  \\___ \\ / _` | | __/ _` | '_ ` _ \\ / _` |\n" +
                "  ____) | (_| | | || (_| | | | | | | (_| |\n" +
                " |_____/ \\__,_|_|\\__\\__,_|_| |_| |_|\\__,_|\n";

        System.out.println(logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Saitama, a hero for fun.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String[] command = sc.nextLine().trim().toLowerCase().split(" ", 2);
            System.out.println("____________________________________________________________");

            if (command[0].equals("bye")) {
                break;
            }

            try {
                switch (command[0]) {
                case "list":
                    taskList.list();
                    break;
                case "mark":
                    taskList.markTask(command);
                    break;
                case "unmark":
                    taskList.unmarkTask(command);
                    break;
                case "delete":
                    taskList.delete(command);
                    break;
                case "todo":
                case "deadline":
                case "event":
                    taskList.add(command);
                    break;
                default:
                    throw new InvalidCommandException("huh?!\nPlease enter a valid command.");
                }
            } catch (InvalidCommandException | InvalidTaskNumberException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("OK...");
    }
}