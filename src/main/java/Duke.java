import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList;
    public static String firstThreeChar;
    public static String firstFourChar;
    public static String firstFiveChar;
    public static String firstSixChar;
    public static String firstSevenChar;
    public static String firstEightChar;
    public static void main(String[] args) {
        // Declare Task ArrayList to keep tasks and starting Task number
        taskList = new ArrayList<>();
        // Print welcome message
        System.out.println("Hello, this is Belvedere, your virtual assistant!\nHow may i assist you?");
        printLine();
        // Scanner object to detect input from CLI
        Scanner scanner = new Scanner(System.in);
        // Loop to keep getting input until bye
        while (true) {
            String input = scanner.nextLine();
            int inputLength = input.length();
            // setFirstChars
            setFirstChars(input,inputLength);
            if (firstThreeChar.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            if (firstFourChar.equals("list")) {
                printTaskList();
                continue;
            }
            if (firstFourChar.equals("mark")) {
                int selectedTaskNum = Character.getNumericValue(input.charAt(inputLength-1));
                Task selectedTask = taskList.get(selectedTaskNum-1);
                selectedTask.markAsDone();
                System.out.println(String.format("Ok, I've marked this task as done:\n%s",selectedTask.toString()));
                printLine();
                continue;
            }
            if (firstFourChar.equals("todo")) {
                String description = input.substring(5,inputLength);
                Todo newTask = new Todo(description);
                taskList.add(newTask);
                printAck(newTask);
                printLine();
                continue;
            }
            if (firstFiveChar.equals("event")){
                String descriptionAndSetAt = input.substring(6,inputLength);
                String[] split = descriptionAndSetAt.split("/");
                Event newTask = new Event(split[0], split[1]);
                taskList.add(newTask);
                printAck(newTask);
                printLine();
                continue;
            }
            if (firstSixChar.equals("unmark")) {
                int selectedTaskNum = Character.getNumericValue(input.charAt(inputLength-1));
                Task selectedTask = taskList.get(selectedTaskNum-1);
                selectedTask.markAsUndone();
                System.out.println(String.format("Ok, I've marked this task as not done yet:\n%s",selectedTask.toString()));
                printLine();
                continue;
            }
            if (firstEightChar.equals("deadline")) {
                String descriptionAndDueBy = input.substring(9,inputLength);
                String[] split = descriptionAndDueBy.split("/");
                Deadline newTask = new Deadline(split[0], split[1]);
                taskList.add(newTask);
                printAck(newTask);
                printLine();
                continue;
            } else {
                System.out.println("added: " + input);
                Task newTask = new Task(input);
                taskList.add(newTask);
                printLine();
            }
        }
    }
    // Static method to print a line in the terminal to separate call and response
    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }
    private static void printAck(Task task) {
        System.out.println(String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list",task.toString(),taskList.size()));
    }

    private static void printTaskList() {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.print(String.format("%d. %s \n", i+1, taskList.get(i).toString()));
        }
        printLine();
    }
    private static void setFirstChars(String input, int inputLength) {
        //reset first
        firstThreeChar = "123";
        firstFourChar = "1234";
        firstFiveChar = "12345";
        firstSixChar = "123456";
        firstSevenChar = "1234567";
        firstEightChar = "12345678";
        if (inputLength >= 3) {
            firstThreeChar = input.substring(0,3);
        }
        if (inputLength >= 4) {
            firstFourChar = input.substring(0,4);
        }
        if (inputLength >= 5) {
            firstFiveChar = input.substring(0,5);
        }
        if (inputLength >= 6) {
            firstSixChar = input.substring(0,6);
        }
        if (inputLength >= 7) {
            firstSevenChar = input.substring(0,7);
        }
        if (inputLength >= 8) {
            firstEightChar = input.substring(0,8);
        }
    }
}
