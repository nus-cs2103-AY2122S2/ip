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

        Command actionType = Ui.getCommand();
        String[] parsedInput = Ui.getInputs();
//        while (actionType == null) {
//            try {
//                command = sc.next();
//                input = sc.nextLine();
//                actionType = Parser.parseCommand(command);
//            } catch (CommandNotFoundException e) {
//                System.out.println("Sorry, i don't understand what you are saying");
//            }
//        }
        while(actionType != Command.BYE) {
            int indexOfList = -1;
            Boolean addSuccess = null;


            switch (actionType) {
                case LIST:
                    list.print();
                    break;
                case MARK:
                    indexOfList = Integer.parseInt(parsedInput[0]);
                    list.markComplete(indexOfList);
                    System.out.println("Marked as complete");
                    list.print(indexOfList);
                    break;
                case UNMARK:
                    indexOfList = Integer.parseInt(parsedInput[0]);
                    list.markIncomplete(indexOfList);
                    System.out.println("Marked as incomplete");
                    list.print(indexOfList);
                    break;
                case EVENT:
                    try {
                        addSuccess = list.add(TaskType.EVENT, parsedInput);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + parsedInput[0].strip());
                    } else {
                        System.out.println("Failed to add " + parsedInput[0].strip());
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
                case DEADLINE:
                    try {
                        addSuccess = list.add(TaskType.DEADLINE, parsedInput);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + parsedInput[0].strip());
                    } else {
                        System.out.println("Failed to add " + parsedInput[0].strip());
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
                case TODO:
                    try {
                        addSuccess = list.add(TaskType.TODO, parsedInput);
                    } catch (EmptyDescriptionException e) {
                        System.out.println("Description of task can't be empty");
                        addSuccess = false;
                    }
                    if (addSuccess) {
                        System.out.println("added: " + parsedInput[0].strip());
                    } else {
                        System.out.println("Failed to add " + parsedInput[0].strip());
                    }
                    System.out.print("Number of tasks: ");
                    System.out.println(list.getLength());
                    break;
                case DELETE:
                    indexOfList = Integer.parseInt(parsedInput[0]);
                    System.out.println("Noted. I've removed this task:");
                    list.print(indexOfList);
                    list.delete(indexOfList);
                    System.out.println("Now you have " + Integer.toString(list.getLength()) + " tasks in the list.");
            }

            DataStore.saveData(list);

            actionType = Ui.getCommand();
            parsedInput = Ui.getInputs();
//            while (actionType == null) {
//                try {
//                    command = sc.next();
//                    input = sc.nextLine();
//                    actionType = Parser.parseCommand(command);
//                } catch (CommandNotFoundException e) {
//                    System.out.println("Sorry, i don't understand what you are saying");
//                    actionType = null;
//                }
//            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

}
