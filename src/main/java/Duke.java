import java.util.Scanner;

public class Duke {
    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();
        TaskManager taskManager = new TaskManager();

        while(true) {
            if (input.equalsIgnoreCase("bye")) { //bye, shut down Cortana
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) { //list all the tasks
                taskManager.list();
            } else if (input.matches("mark \\d+")) { //mark a task
                int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
                taskManager.mark(index);
            } else if (input.matches("unmark \\d+")) { //unmark a task
                int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
                taskManager.unmark(index);
            } else { //add task
                taskManager.addTask(new Task(input));
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
        System.out.println("Hello from\n" + logo + "My name is Cortana, what can I do for you, master?");
        simpleTodo();
    }
}