import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mike {
    private final List<Task> listOfTasks;

    public static void main(String[] args) {
        Mike mike = new Mike();
        mike.start();

        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String trimmedInputString = inputString.trim();
        while(!trimmedInputString.equals("bye")) {
            String[] splitString = trimmedInputString.split(" ");

            if (!trimmedInputString.isEmpty()) {
                String command = splitString[0];
                //TODO: throw exceptions when command is not entered correctly
                switch(command) {
                    case "list":
                        mike.printList();
                        break;
                    case "mark":
                        int markNumber = Integer.parseInt(splitString[1]);
                        mike.mark(markNumber);
                        break;
                    case "unmark":
                        int unmarkNumber = Integer.parseInt(splitString[1]);
                        mike.unmark(unmarkNumber);
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
                        mike.printInvalidCommandMessage(trimmedInputString);
                }
            } else {
                mike.printNoCharactersMessage();
            }

            //this segment reads the next input
            sc = new Scanner(System.in);
            mike.printNextCommandInstruction();
            inputString = sc.nextLine();
            trimmedInputString = inputString.trim();
        }

        mike.printGoodbyeMessage();
    }

    public static String removeCommandFromString(String str) {
        int indexOfFirstSpace = str.indexOf(" ");
        int indexOfTaskParameters = indexOfFirstSpace + 1;
        return str.substring(indexOfTaskParameters);
    }

    public Mike() {
        this.listOfTasks = new ArrayList<>();
    }

    void start() {
        printGreeting();
        printStartingInstruction();
    }

    void printGreeting() {
        String textBanner = "" +
                "  _     _   _                   \n" +
                " | |   (_) (_)                  \n" +
                " | |__  _   _    __ _ _ __ ___  \n" +
                " | '_ \\| | | |  / _` | '_ ` _ \\ \n" +
                " | | | | | | | | (_| | | | | | |\n" +
                " |_| |_|_| |_|_ \\__,_|_| |_| |_|\n" +
                "           (_) |                \n" +
                "  _ __ ___  _| | _____          \n" +
                " | '_ ` _ \\| | |/ / _ \\         \n" +
                " | | | | | | |   <  __/         \n" +
                " |_| |_| |_|_|_|\\_\\___|         \n" +
                "                                \n" +
                "                                ";
        System.out.println("Welcome!\n" + textBanner);
    }

    void printStartingInstruction() {
        String tip = "**Tip: type bye to end conversation**";
        System.out.println(tip);
        System.out.println("Enter a command:\n");
    }

    void printReply(String str){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + str);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    void printInvalidCommandMessage(String str) {
        printReply(String.format("I don't understand the command \"%s\"", str));
    }

    void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye and see you again :)";
        printReply(goodbyeMessage);
    }

    void printNoCharactersMessage() {
        printReply("Hey! You didn't enter any characters!");
    }

    void addToList(Task task) {
        this.listOfTasks.add(task);
        String taskName = task.getTaskName();
        String addTaskOutput = "Added \"" + taskName + "\" to the list!";
        int noOfTasks = this.listOfTasks.size();
        String noOfTasksOutput = String.format("You now have *%d* task(s) in your list.", noOfTasks);
        printReply(String.format("%s\n%s", addTaskOutput, noOfTasksOutput));
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
            System.out.println(String.format("%d. %s", counter, task));
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
