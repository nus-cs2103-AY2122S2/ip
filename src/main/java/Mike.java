import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mike {
    private final List<Task> listOfTasks;

    public static void main(String[] args) {
        Mike mike = new Mike();
        mike.start();
        Scanner sc = new Scanner(System.in);

        while(true) {
            mike.printNextCommandInstruction();
            String inputString = sc.nextLine();
            String trimmedInputString = inputString.trim();
            if (trimmedInputString.equals("bye")) {
                break;
            }
            String[] splitString = trimmedInputString.split(" ");

            if (!trimmedInputString.isEmpty()) {
                String command = splitString[0];
                try {
                    //Todo: ask in forum how to use enum for switch statement when strings are used
                    switch (command) {
                    case "list":
                        mike.printList();
                        break;
                    case "mark":
                        int markIndex = Integer.parseInt(splitString[1]);
                        mike.mark(markIndex);
                        break;
                    case "unmark":
                        int unmarkIndex = Integer.parseInt(splitString[1]);
                        mike.unmark(unmarkIndex);
                        break;
                    case "remove":
                        int removeIndex = Integer.parseInt(splitString[1]);
                        mike.removeFromList(removeIndex);
                        break;
                    case "todo":
                        String todoParameters =
                                removeCommandFromString(trimmedInputString);
                        mike.addTodo(todoParameters);
                        break;
                    case "deadline":
                        String deadlineParameters =
                                removeCommandFromString(trimmedInputString);
                        mike.addDeadline(deadlineParameters);
                        break;
                    case "event":
                        String eventParameters =
                                removeCommandFromString(trimmedInputString);
                        mike.addEvent(eventParameters);
                        break;
                    default:
                        String invalidCommandMessage =
                                String.format("\n**Mike: I don't understand the command \"%s\"**",
                                        trimmedInputString);
                        throw new UnsupportedOperationException(invalidCommandMessage);
                    }
                } catch(UnsupportedOperationException e) {
                    e.printStackTrace();
                } catch(StringIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("**Mike: Hmm, you may have entered the command arguments incorrectly.**");
                } catch(IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.out.println("**Mike: That item number doesn't exist on your list :/**");
                }
            } else {
                mike.printNoCharactersMessage();
            }
        }
        sc.close();
        mike.printGoodbyeMessage();
    }

    public static String removeCommandFromString(String str) {
        int indexOfFirstSpace = str.indexOf(" ");
        int indexOfTaskParameters = indexOfFirstSpace + 1;
        return str.substring(indexOfTaskParameters);
    }

    /**
     * Constructor for Mike
     */
    public Mike() {
        this.listOfTasks = new ArrayList<>();
    }

    void start() {
        printGreeting();
        printStartingInstruction();
    }

    void printGreeting() {
        String textBanner = "" +
                "  _     _   _\n" +
                " | |   (_) (_)\n" +
                " | |__  _   _    __ _ _ __ ___\n" +
                " | '_ \\| | | |  / _` | '_ ` _ \\\n" +
                " | | | | | | | | (_| | | | | | |\n" +
                " |_| |_|_| |_|_ \\__,_|_| |_| |_|\n" +
                "           (_) |\n" +
                "  _ __ ___  _| | _____\n" +
                " | '_ ` _ \\| | |/ / _ \\\n" +
                " | | | | | | |   <  __/\n" +
                " |_| |_| |_|_|_|\\_\\___|\n";
        System.out.println("Welcome!\n" + textBanner);
    }

    void printStartingInstruction() {
        String tip = "**Tip: type bye to end conversation**";
        System.out.println(tip);
    }

    /**
     * Prints a string with line separators to make it
     * visually appealing
     *
     * @param str The string that will be printed
     */
    void printReply(String str){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + str);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye and see you again :)";
        printReply(goodbyeMessage);
    }

    void printNoCharactersMessage() {
        printReply("Hey! You didn't enter any characters!");
    }

    //TODO: consider making Mike immutable (return Mike with new list each time)
    void addToList(Task task) {
        this.listOfTasks.add(task);
        String taskName = task.getTaskName();
        String addTaskOutput = "Added \"" + taskName + "\" to the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        printReply(String.format("%s\n%s", addTaskOutput, noOfTasksOutput));
    }

    void removeFromList(int removeIndex) {
        Task removedTask = this.listOfTasks.remove(removeIndex - 1);
        String taskName = removedTask.getTaskName();
        String removeTaskOutput = "Removed \"" + taskName + "\" from the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        printReply(String.format("%s\n%s", removeTaskOutput, noOfTasksOutput));
    }

    void addTodo(String str) {
        Todo todo = new Todo(str);
        addToList(todo);
    }

    void addDeadline(String str) {
        int indexOfBy = str.indexOf("/by");
        int indexOfEndDate = indexOfBy + 4;
        String name = str.substring(0, indexOfBy - 1);
        String endDate = str.substring(indexOfEndDate);
        Deadline deadline = new Deadline(name, endDate);
        addToList(deadline);
    }

    void addEvent(String str) {
        int indexOfAt = str.indexOf("/at");
        int indexOfEventTime = indexOfAt + 4;
        String name = str.substring(0, indexOfAt - 1);
        String scheduledDate = str.substring(indexOfEventTime);
        Event event = new Event(name, scheduledDate);
        addToList(event);
    }

    void printList() {
        int counter = 1;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: Behold, your list!");
        for (Task task : listOfTasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void mark(int value) { //mark should throw an exception if value is out of bounds
        int index = value - 1;
        Task oldTask = this.listOfTasks.get(index);
        Task newTask = oldTask.markAsDone();
        this.listOfTasks.set(index, newTask);

        String outputMessage =
                String.format("Great job, \"%s\" marked as done!", newTask.getTaskName());
        printReply(outputMessage);
    }

    void unmark(int value) { //same as mark, should throw exception
        int index = value - 1;
        Task oldTask = this.listOfTasks.get(index);
        Task newTask = oldTask.markAsUndone();
        this.listOfTasks.set(index, newTask);

        String outputMessage =
                String.format("Sure thing, \"%s\" marked as undone!", newTask.getTaskName());
        printReply(outputMessage);
    }
}
