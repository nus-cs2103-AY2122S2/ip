import java.util.Scanner;

public class Yale {
    public static void main(String[] args) {
        String logo = "\n" +
                " ____  ____     _       _____     ________  \n" +
                "|_  _||_  _|   / \\     |_   _|   |_   __  | \n" +
                "  \\ \\  / /    / _ \\      | |       | |_ \\_| \n" +
                "   \\ \\/ /    / ___ \\     | |   _   |  _| _  \n" +
                "   _|  |_  _/ /   \\ \\_  _| |__/ | _| |__/ | \n" +
                "  |______||____| |____||________||________| \n" +
                "                                            \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi, I'm Yale!\n" );
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();
        while (true) {
            String command = receiveInput(scanner);
            performAction(command, list);
            if (checkExit(command)) {
                break;
            }
        }
    }

    public static String receiveInput(Scanner scanner) {
        System.out.println("Enter command below:");
        String input = scanner.nextLine();
        return input;
    }

    public static void performAction(String command, TaskList list) {
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list\n"
                    + list.listOut());

        } else if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            if (command.contains("mark") || command.contains("unmark")) {
                String[] commandArray = command.split(" ");
                String markStatus = commandArray[0];
                int itemNo = Integer.parseInt(commandArray[1]);
                if (markStatus.equals("mark")) {
                    list.getItem(itemNo-1).markItem();
                } else {
                    list.getItem(itemNo-1).unmarkItem();
                }
            } else {
                if (command.contains("todo")) {
                    list.addTo(command, false); // WAS LAST HERE!!
                }

            }
        }
    }

    public static boolean checkExit(String input) {
        return input.equals("bye");
    }
}
