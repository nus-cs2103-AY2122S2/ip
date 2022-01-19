import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String botName = "Duke";
        Printer.printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        Printer.printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        while (!input.equals("bye")) {
            input = inputScanner.nextLine();
            int firstSpaceIndex = input.indexOf(" ");
            String firstArg = "";
            if (firstSpaceIndex != -1) { // input is more than one word
                firstArg = input.substring(0, firstSpaceIndex);
            } else { // input is a single word
                firstArg = input;
            }

            String[] inputArray = input.split(" ");
            if (inputArray.length < 1) {
                System.out.println("Empty command");
            } else if (inputArray.length == 1) {
                if (inputArray[0].equals("list")) {
                    Printer.printTodo(taskList);
                } else if (inputArray[0].equals("bye")) {
                    Printer.printEndMessage();
                } else {
                    System.out.println("Unknown command: " + inputArray[0]);
                }
            } else {
                if (firstArg.equals("mark")) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).mark();
                } else if (firstArg.equals("unmark")) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).unmark();
                } else if (firstArg.equals("deadline")) {
                    int indexOfBy = input.indexOf("\\by ");
                    String content = input.substring(firstArg.length() + 1, indexOfBy - 1);
                    String by = input.substring(indexOfBy + 4);
                    Task taskObj = new Deadline(content, by);
                    Printer.echoForAdd(taskObj, taskList.size());
                    taskList.add(taskObj);
                } else if (firstArg.equals("event")) {
                    int indexOfAt = input.indexOf("\\at ");
                    String content = input.substring(firstArg.length() + 1, indexOfAt - 1);
                    String at = input.substring(indexOfAt + 4);
                    Task taskObj = new Event(content, at);
                    Printer.echoForAdd(taskObj, taskList.size());
                    taskList.add(taskObj);
                } else if (firstArg.equals("todo")) {
                    String content = input.substring(firstArg.length() + 1);
                    Task taskObj = new ToDo(content);
                    Printer.echoForAdd(taskObj, taskList.size());
                    taskList.add(taskObj);
                } else {
                    System.out.println("Unknown Command: " + input);
                }
            }
        }
    }
}
