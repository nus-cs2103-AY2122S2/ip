import java.util.ArrayList;
import java.util.Scanner;

class ToDoList {
    ArrayList<ToDoItem> list;
    public ToDoList() {
        this.list = new ArrayList<ToDoItem>();
    }

    public void addTo(String item) {
        ToDoItem newItem = new ToDoItem(item);
        list.add(newItem);
    }

    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + ". " + list.get(i) + "\n";
        }
        return output;
    }
}

class ToDoItem {
    private String name;
    public ToDoItem(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
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
        ToDoList list = new ToDoList();
        while (true) {
            String command = receiveInput(scanner);
            performAction(command, list);
            if (checkExit(command)) {
                break;
            }
        }
    }

    public static String receiveInput(Scanner scanner) {
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();
        return input;
    }

    public static void performAction(String command, ToDoList list) {
        if (command.equals("list")) {
            System.out.println(list.listOut());

        }
        else if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else {
            list.addTo(command);
        }

    }


    public static boolean checkExit(String input) {
        return input.equals("bye");
    }

}
