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
            if ( trimmedInputString.isEmpty()) {
                mike.printNoCharactersMessage();
            } else {
                String command = splitString[0];
                if (command.equals("list")) {
                    mike.printList();
                } else if (command.equals("mark")) {
                    int value = Integer.parseInt(splitString[1]);
                    mike.mark(value);
                } else if (command.equals("unmark")) {
                    int value = Integer.parseInt(splitString[1]);
                    mike.unmark(value);
                }
                else {
                    mike.addToList(trimmedInputString);
                }
            }

            sc = new Scanner(System.in);
            mike.printNextCommandInstruction();
            inputString = sc.nextLine();
            trimmedInputString = inputString.trim();
        }
        mike.printGoodbyeMessage();
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

    void printGoodbyeMessage() {
        String goodbyeMessage = "Goodbye and see you again :)";
        printReply(goodbyeMessage);
    }

    void printNoCharactersMessage() {
        printReply("Hey! You didn't enter any characters!");
    }

    void addToList(String str) {
        Task task = new Task(str);
        this.listOfTasks.add(task);
        printReply("Added \"" + str + "\" to the list!");
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

    void mark(int value) {
        int index = value - 1;
        Task oldTask = this.listOfTasks.get(index);
        Task newTask = oldTask.markAsDone();
        this.listOfTasks.set(index, newTask);

        String outputMessage = String.format("Great job, %s marked as done!", newTask);
        printReply(outputMessage);
    }

    void unmark(int value) {
        int index = value - 1;
        Task oldTask = this.listOfTasks.get(index);
        Task newTask = oldTask.markAsUndone();
        this.listOfTasks.set(index, newTask);

        String outputMessage = String.format("Sure thing, %s marked as undone!", newTask);
        printReply(outputMessage);
    }
}
