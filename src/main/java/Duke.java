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

    public static void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Your current task list is empty");
            return;
        }
        int taskCounter = 1;
        System.out.println("These are the current tasks in your list:");
        for(Task task:taskList) {
            System.out.printf("%d. %s \n", taskCounter, task.toString());
            taskCounter++;
        }
    }

    public static void displayTaskAdd(Task addedTask){
        System.out.println("This task has been added as requested:");
        System.out.println(addedTask.toString());
        System.out.printf("You now have %d item(s) in your list\n", taskList.size());
    }

    public static void commandProcessor() {
        String userInput = getUserInput.nextLine();
        String[] parsedUserInput = userInput.split(" ", 2);
        switch (parsedUserInput[0].toLowerCase()) {
        case "" :
            commandProcessor();
            break;
        case "bye":
            System.out.println("Till we meet again");
            break;
        case "list":
            displayTaskList();
            commandProcessor();
            break;
        case "mark":
            int taskToMarkNumber = Integer.parseInt(parsedUserInput[1]);
            if (taskToMarkNumber > taskList.size()) {
                System.out.println("I am afraid that's an invalid task! Please check your task number");
                commandProcessor();
                break;
            }
            Task taskToMark = taskList.get(taskToMarkNumber - 1);
            taskToMark.markAsDone();
            System.out.println("Duly noted. The following task has been marked as done");
            System.out.println(taskToMark.toString());
            commandProcessor();
            break;
        case "unmark":
            int taskToUnmarkNumber = Integer.parseInt(parsedUserInput[1]);
            if (taskToUnmarkNumber > taskList.size()) {
                System.out.println("I am afraid that's an invalid task! Please check your task number");
                commandProcessor();
                break;
            }
            Task taskToUnmark = taskList.get(taskToUnmarkNumber - 1);
            taskToUnmark.markAsNotDone();
            System.out.println("Very well. The following task has been marked as undone");
            System.out.println(taskToUnmark.toString());
            commandProcessor();
            break;
        case "todo":
            Todo newTodo = new Todo(parsedUserInput[1]);
            taskList.add(newTodo);
            displayTaskAdd(newTodo);
            commandProcessor();
            break;
        case "deadline":
            String[] deadlineParse = parsedUserInput[1].split(" /[Bb][Yy] ", 2);
            Deadline newDeadline = new Deadline(deadlineParse[0], deadlineParse[1]);
            taskList.add(newDeadline);
            displayTaskAdd(newDeadline);
            commandProcessor();
            break;
        case "event":
            String[] eventParse = parsedUserInput[1].split(" /[Aa][Tt] ", 2);
            Event newEvent = new Event(eventParse[0], eventParse[1]);
            taskList.add(newEvent);
            displayTaskAdd(newEvent);
            commandProcessor();
            break;
        default:
            System.out.println("I am unable to process your request. Please try again");
            commandProcessor();
        }
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
        commandProcessor();
    }
}
