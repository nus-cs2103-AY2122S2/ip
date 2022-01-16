import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(generateIntro());
        // Starting input loop
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> toDoList = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String command = inputArray[0];
            if (command.equals("quit") || command.equals("exit")) {
                System.out.println(generateGoodbye());
                break;
            } else if (command.equals("list")) { // List
                String listOutput = "";
                for (int i = 0; i < toDoList.size(); i++) {
                    if (i != 0) {
                        listOutput += "\n" + (i + 1) + ". " + toDoList.get(i).getCurrentStatus();
                    } else {
                        listOutput += (i + 1) + ". " + toDoList.get(i).getCurrentStatus();
                    }
                }
                System.out.println(generateResponse(listOutput));
            } else if (command.equals("mark")) {
                int taskNo = Integer.parseInt(inputArray[1]) - 1;
                Task currentTask = toDoList.get(taskNo);
                currentTask.markAsDone();
                String output = "Marked as done:\n" + currentTask.getCurrentStatus();
                System.out.println(output);
            } else if (command.equals("unmark")) {
                int taskNo = Integer.parseInt(inputArray[1]) - 1;
                Task currentTask = toDoList.get(taskNo);
                currentTask.markAsUndone();
                String output = "Marked as undone:\n" + currentTask.getCurrentStatus();
                System.out.println(output);
            } else { // Add to list
                toDoList.add(new Task(input));
                String message = "added " + input;
                System.out.println(generateResponse(message));
            }
        }
    }

    public static String generateResponse(String input) {
        String temp = "<---------------------------------------------------------->\n";
        return temp + input + "\n" + temp;
    }

    public static String generateIntro() {
        String welcomeMessage = "Hello! I'm Duke, your personal assistant\nWhat can I do for you?";
        return generateResponse(welcomeMessage);
    }

    public static String generateGoodbye() {
        String goodbyeMessage = "Goodbye!";
        return generateResponse(goodbyeMessage);
    }
}
