import java.util.ArrayList;
import java.util.Scanner;

class TaskList {
    ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTo(String item, boolean marked) {
        Task newItem = new Task(item, marked);
        list.add(newItem);
    }

    public String listOut() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i+1 + "." + list.get(i).getStatusIcon()
                    + " " + list.get(i) + "\n";
        }
        return output;
    }

    public Task getItem(int itemNo) {
        return list.get(itemNo);
    }
}

class Task {
    private String name;
    private boolean isMarked = false;
    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    public String getStatusIcon() {
        return (isMarked ? "[X]" : "[ ]");
    }

    public void markItem() {
        isMarked = true;
        System.out.println("Nice! I've marked this task as done:\n"
                + "  " + getStatusIcon() + " " + name);
    }

    public void unmarkItem() {
        isMarked = false;
        System.out.println("OK, I've marked this task as not done yet:\n"
                + "  " + getStatusIcon() + " " + name);
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
                list.addTo(command, false);
            }
        }
    }

    public static boolean checkExit(String input) {
        return input.equals("bye");
    }
}
