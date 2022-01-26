//package DukeTaskBot;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> taskList;
    private static Scanner getUserInput;

    private static void initialize(){
        taskList = new ArrayList<>();
        getUserInput = new Scanner(System.in);
    }

    public static void displayList(ArrayList<Task> taskList) {
        int taskCounter = 1;
        for(Task task:taskList) {
            System.out.printf("%d. %s \n", taskCounter, task.toString());
            taskCounter++;
        }
    }

    public static void listOperations() {
        String userInput = getUserInput.nextLine();
        String[] parsedUserInput = userInput.split(" ");
        switch (parsedUserInput[0].toLowerCase()) {
            case "" :
                listOperations();
                break;
            case "bye":
                System.out.println("Till we meet again");
                break;
            case "list":
                displayList(taskList);
                listOperations();
                break;
            case "mark":
                int taskToMarkNumber = Integer.parseInt(parsedUserInput[1]);
                if (taskToMarkNumber > taskList.size()) {
                    System.out.println("I am afraid that's an invalid task! Please check your task number");
                    listOperations();
                    break;
                }
                Task taskToMark = taskList.get(taskToMarkNumber - 1);
                taskToMark.markAsDone();
                System.out.println("Duly noted. The following task is marked as done");
                System.out.println(taskToMark.toString());
                listOperations();
                break;
            case "unmark":
                int taskToUnmarkNumber = Integer.parseInt(parsedUserInput[1]);
                if (taskToUnmarkNumber > taskList.size()) {
                    System.out.println("I am afraid that's an invalid task! Please check your task number");
                    listOperations();
                    break;
                }
                Task taskToUnmark = taskList.get(taskToUnmarkNumber - 1);
                taskToUnmark.markAsNotDone();
                System.out.println("Very well. The following task has been marked as undone");
                System.out.println(taskToUnmark.toString());
                listOperations();
                break;
            default:
                Task taskToAdd = new Task(userInput);
                taskList.add(taskToAdd);
                System.out.println("added: " + userInput);
                listOperations();
        }
//        while (!userInput.equals("bye")) {
//            if (userInput.equals("list")) {
//                displayList(taskList);
//            } else {
//                Task taskToAdd = new Task(userInput);
//                taskList.add(taskToAdd);
//                System.out.println("added: " + userInput);
//            }
//            userInput = getUserInput.nextLine();
//        }
//        System.out.println("Till we meet again");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I assist you?");
        initialize();
        listOperations();
    }
}
