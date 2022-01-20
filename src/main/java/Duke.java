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
        System.out.println("I'm Saitama, a hero for fun.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String[] str = sc.nextLine().trim().toUpperCase().split(" ", 2);
            System.out.println("____________________________________________________________");

            try {
                Command command = Command.getCommand(str);

                switch (command) {
                case BYE:
                    break;
                case LIST:
                    taskList.list();
                    continue;
                case MARK:
                    taskList.markTask(str);
                    continue;
                case UNMARK:
                    taskList.unmarkTask(str);
                    continue;
                case DELETE:
                    taskList.delete(str);
                    continue;
                case TODO:
                case DEADLINE:
                case EVENT:
                    taskList.add(str);
                    continue;
                }
                break;
            } catch (InvalidCommandException | InvalidTaskNumberException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("OK...");
    }
}

