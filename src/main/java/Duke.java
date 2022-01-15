import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Welcome to Duke! \nWhat can i do for you?\n");

        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();

        String command = sc.next();
        String input = sc.nextLine();
        input = input.equals("")? input : input.substring(1);

        while(!command.equals("bye")) {
            if (command.equals("list")) {
                list.print();
            } else if(command.equals("mark")) {
                int indexOfList = Integer.parseInt(input);
                list.markComplete(indexOfList);
                System.out.println("Marked as complete");
                list.print(indexOfList);
            } else if(command.equals("unmark")) {
                int indexOfList = Integer.parseInt(input);
                list.markIncomplete(indexOfList);
                System.out.println("Marked as incomplete");
                list.print(indexOfList);
            } else if (command.equals("event")){
                boolean addSuccess = list.add(TaskType.EVENTS,input);
                if (addSuccess) {
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Failed to add " + input);
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
            } else if (command.equals("deadline")){
                boolean addSuccess = list.add(TaskType.DEADLINE,input);
                if (addSuccess) {
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Failed to add " + input);
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
            } else if (command.equals("todo")){
                boolean addSuccess = list.add(TaskType.TODO,input);
                if (addSuccess) {
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Failed to add " + input);
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
            } else {
                System.out.println("Unrecognised Command");
            }
            command = sc.next();
            input = sc.nextLine();
            input = input.equals("")? input : input.substring(1);
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
