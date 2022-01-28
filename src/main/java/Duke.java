import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String BOT_NAME = "Feline";
    private static ArrayList<Task> tasks = new ArrayList<>();
    //private static final String[] commands = {"bye", "mark", "unmark", "list", "todo", "deadline", "event"};

    private static void greet() {
        System.out.println(String.format("Yoooo! My name is %s!\nHow can i help you bro?\n", BOT_NAME));
    }
    private static void farewell() {
        System.out.println("See you next time!");
    }

    private static void printTaskCount() {
        System.out.println(String.format("Now you have %d task(s) in your list.", tasks.size()));
    }
    private static void printTaskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        printTaskCount();
    }

    private static void addTask(String action, String input) {    //adds task to list
        switch (action) {
        case "todo":
                try {
                    String[] todoArr = input.split("\\s", 2);
                    if (todoArr.length <= 1) {
                        throw new InvalidArgumentException("todo.. todo what?");
                    }
                    tasks.add(new Todo(todoArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
        case "deadline":
                //deadline do hw /by no idea :-p
                try {
                    String[] deadlineArr = input.split("/by", 2);
                    String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
                    if (deadlineSplit.length <= 1) {    // no description
                        throw new InvalidArgumentException("Sorry but.. deadline of what??");
                    }
                    if (deadlineArr.length <= 1) { // don't have /by keyword
                        throw new InvalidArgumentException("By when?? ..");
                    }
                    String description = deadlineSplit[1].trim();
                    tasks.add(new Deadline(description, deadlineArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
        case "event":
                //event project meeting /at Mon 2-4pm
                try {
                    String[] eventArr = input.split("/at", 2);
                    String[] eventSplit = eventArr[0].split("\\s", 2);
                    if (eventSplit.length <= 1) {
                        throw new InvalidArgumentException("What event? No event stated.");
                    }
                    if (eventArr.length <= 1) {
                        throw new InvalidArgumentException("At where? Please specify again");
                    }
                    String description = eventSplit[1].trim();
                    tasks.add(new Event(description, eventArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
        }

    }

    private static String getTaskStatement(int i) {
        //[X] read book
        return tasks.get(i).toString();
    }
    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + getTaskStatement(i));
        }
    }

    private static void mark(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) { //e.g mark 2, cannot be mark 2 2 or just mark
                throw new InvalidArgumentException("Please specify what to mark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(Duke.getTaskStatement(taskNumber - 1));
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void unmark(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to unmark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            tasks.get(taskNumber - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(getTaskStatement(taskNumber - 1));
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void delete(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to delete clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("Cannot delete" +
                        " as task %d does not exist!", taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(getTaskStatement(taskNumber - 1));
            tasks.remove(taskNumber - 1);
            printTaskCount();
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getCommands() {
        return "list, todo, deadline (using /by), event (using /at), mark, unmark, delete";
    }

    public static void main(String[] args) {
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputWords = input.split("\\s", 2);
                switch (input) {
                    case "bye":
                        Duke.farewell();
                        return;
                    case "list":
                        printList();
                        break;
                    default: // not the one word commands that we have. check further commands, else throw exception
                        String firstWord = inputWords[0];
                        switch (firstWord) {
                            case "mark":
                                Duke.mark(inputWords);
                                break;
                            case "unmark":
                                Duke.unmark(inputWords);
                                break;
                            case "delete":
                                Duke.delete(inputWords);
                                break;
                            case "todo":
                            case "deadline":
                            case "event":
                                Duke.addTask(firstWord, input);
                                break;
                            default:
                                throw new UnknownException("Unknown command! the follow are the commands: "
                                        + Duke.getCommands()); //can show help commands
                        }
                }
            } catch (UnknownException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        Duke.farewell();
    }
}
