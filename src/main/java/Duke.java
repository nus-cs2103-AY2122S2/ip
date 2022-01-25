import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void displayList(ArrayList<String> taskList) {
        int taskCounter = 1;
        for(String task:taskList) {
            System.out.println(String.format("%d. %s", taskCounter, task));
            taskCounter++;
        }
    }

    public static void listOperations() {
        ArrayList<String> taskList = new ArrayList<>();
        Scanner getUserInput = new Scanner(System.in);
        String userInput = getUserInput.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                displayList(taskList);
            } else {
                taskList.add(userInput);
                System.out.println("added: " + userInput);
            }
            userInput = getUserInput.nextLine();
        }
        System.out.println("Till we meet again");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I assist you?");
        listOperations();
    }
}
