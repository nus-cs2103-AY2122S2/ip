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

        TaskList list = DataStore.loadData();
        Scanner sc = new Scanner(System.in);

        String command = "";
        String input = "";

        Command actionType = null;
        while (actionType == null) {
            try {
                command = sc.next();
                input = sc.nextLine();
                input = input.equals("")? input : input.substring(1);
                actionType = parseCommand(command);
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
                        addSuccess = list.add(TaskType.EVENT, parseInput(input));
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
                        addSuccess = list.add(TaskType.DEADLINE, parseInput(input));
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
                        addSuccess = list.add(TaskType.TODO, parseInput(input));
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
                case DELETE:
                    indexOfList = Integer.parseInt(input);
                    System.out.println("Noted. I've removed this task:");
                    list.print(indexOfList);
                    list.delete(indexOfList);
                    System.out.println("Now you have " + Integer.toString(list.getLength()) + " tasks in the list.");
            }

            DataStore.saveData(list);

            actionType = null;
            while (actionType == null) {
                try {
                    command = sc.next();
                    input = sc.nextLine();
                    input = input.equals("")? input : input.substring(1);
                    actionType = parseCommand(command);
                } catch (CommandNotFoundException e) {
                    System.out.println("Sorry, i don't understand what you are saying");
                    actionType = null;
                }
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static Command parseCommand(String command) throws CommandNotFoundException {
        command = command.toLowerCase();
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
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else {
            throw new CommandNotFoundException("Unrecognised Command");
        }
    }

    public static String[] parseInput(String input) {
        String[] inputs = new String[2];
        if (input.indexOf("/by") != -1) {
            int indexOfTime = input.indexOf("/by");
            inputs[0] = input.substring(0,indexOfTime);
            inputs[1] = input.substring(indexOfTime+4);
        } else if (input.indexOf("/at") != -1) {
            int indexOfTime = input.indexOf("/at");
            inputs[0] = input.substring(0,indexOfTime);
            inputs[1] = input.substring(indexOfTime+4);
        } else {
            inputs[0] = input;
            inputs[1] = "";
        }
        return inputs;
    }
}
