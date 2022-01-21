import java.util.Scanner;

public class Duke {
    public static String[] taskList;
    public static int taskListSize;
    public static int taskNumber;
    public static void main(String[] args) {
        // Declare array to keep tasks and starting name number
        taskList = new String[100];
        taskListSize = 0;
        taskNumber = 1;
        // Print welcome message
        System.out.println("Hello, this is Belvedere, your virtual assistant!\nHow may i assist you?");
        printLine();
        // Scanner object to detect input from CLI
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // Loop to keep getting input until bye
        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            if (input.equals("list")) {
                printTaskList();
                input = scanner.nextLine();
                continue;
            }
            System.out.println("added: " + input);
            String newLine = taskNumber + ". " + input;
            taskList[taskNumber] = newLine;
            taskNumber++;
            taskListSize++;
            printLine();
            input = scanner.nextLine();
        }
    }
    // Static method to print a line in the terminal to separate calls
    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }
    public static void printTaskList() {
        for (int i = 1; i < taskNumber; i++) {
            System.out.print(taskList[i] + "\n");
        }
        printLine();
    }
}
