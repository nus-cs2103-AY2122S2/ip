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

        TaskList list = new TaskList();
        Scanner sc = new Scanner(System.in);

        String command = "";
        String input = "";

        Command actionType = null;
        while (actionType == null) {
            try {
                command = sc.next();
                input = sc.nextLine();
                input = input.equals("")? input : input.substring(1);
                actionType = ParseCommand(command);
            } catch (CommandNotFoundException e) {
                System.out.println("Sorry, i don't understand what you are saying");
            }
        }
        while(actionType != Command.BYE) {
            int indexOfList = -1;
            Boolean addSuccess = null;
            switch (actionType) {
                case LIST:
                    list.print();
                    break;
                case MARK:
                    indexOfList = Integer.parseInt(input);
                    list.markComplete(indexOfList);
                    System.out.println("Marked as complete");
                    list.print(indexOfList);
                    break;
                case UNMARK:
                    indexOfList = Integer.parseInt(input);
                    list.markIncomplete(indexOfList);
                    System.out.println("Marked as incomplete");
                    list.print(indexOfList);
                    break;
                case EVENT:
                    try {
                        addSuccess = list.add(TaskType.EVENTS, input);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + input);
                    } else {
                        System.out.println("Failed to add " + input);
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
                case DEADLINE:
                    try {
                        addSuccess = list.add(TaskType.DEADLINE, input);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + input);
                    } else {
                        System.out.println("Failed to add " + input);
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
                case TODO:
                    try {
                        addSuccess = list.add(TaskType.TODO, input);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + input);
                    } else {
                        System.out.println("Failed to add " + input);
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
            }

            actionType = null;
            while (actionType == null) {
                try {
                    command = sc.next();
                    input = sc.nextLine();
                    input = input.equals("")? input : input.substring(1);
                    actionType = ParseCommand(command);
                } catch (CommandNotFoundException e) {
                    System.out.println("Sorry, i don't understand what you are saying");
                    actionType = null;
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static Command ParseCommand(String command) throws CommandNotFoundException {
        if (command.equals("list")) {
            return Command.LIST;
        } else if(command.equals("mark")) {
            return Command.MARK;
        } else if(command.equals("unmark")) {
            return Command.UNMARK;
        } else if (command.equals("event")){
            return Command.EVENT;
        } else if (command.equals("deadline")){
            return Command.DEADLINE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("bye")) {
            return Command.BYE;
        } else {
            throw new CommandNotFoundException("Unrecognised Command");
        }
    }
}
