import java.util.Scanner;
import java.util.ArrayList;
import chatbot.*;
public class Duke {
    protected static String straightLine = "____________________________________________________________";
    
    // farewell message
    public static void displayFarewell() {
        String byeMessage = "Farewell Sir. May you have a wonderful day.";
        System.out.println(straightLine + "\n " + byeMessage + "\n" + straightLine);
    }

    // insert new Task
    public static void insertNewTask(String type, String inputText, ArrayList<Task> taskList) {
        String reply = "";
        Task newTask;
        try {
            if (type.equals("deadline")) {
                String[] inputStringArray = inputText.split("/by ");
                newTask = new Deadline(inputStringArray[0].substring(9), inputStringArray[1]);
            } else if (type.equals("event")) {
                String[] inputStringArray = inputText.split("/at ");
                newTask = new Event(inputStringArray[0].substring(6), inputStringArray[1]);
            } else { // this is a to-do task
                newTask = new Todo(inputText.substring(5));
            }
            taskList.add(newTask);
            String taskListLength = Integer.toString(taskList.size());
            reply += "Very well Sir. I have added this task:";
            reply += "\n   " + newTask.toString();
            reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
            System.out.println(straightLine + "\n " + reply + "\n" + straightLine);
            
        } catch (Exception ArrayIndexOutOfBoundsException) {
            replyUnrecognizedCommand();
        }
    }

    // display task list
    public static void displayTaskList(ArrayList<Task> taskList) {
        Integer counter = 1;
        String reply = "";
        for (Task task : taskList) {
            String taskInfo = task.toString();
            reply += "\n " + counter.toString() + "." + taskInfo; // note: new line is at the start
            counter++;
        }
        String listHeader = "This is your agenda of tasks Sir:";
        System.out.println(straightLine + "\n " + listHeader + reply + "\n" + straightLine);
    }

    // mark / unmark command
    public static void markCommand(String[] inputStringArray, ArrayList<Task> taskList) {
        // check command
        boolean markAsDone = false;
        if (inputStringArray[0].equals("mark")) {
            markAsDone = true;
        } else if (inputStringArray[0].equals("unmark")) {
            markAsDone = false;
        } else { // not a mark / unmark command
            replyUnrecognizedCommand();
            return;
        }
        
        // check if there is an integer after the text command input
        String inputNumberString = inputStringArray[1];
        try {
            Integer inputNumberInteger = Integer.parseInt(inputNumberString);
            String reply = "";
            int taskIndex = inputNumberInteger - 1;
            if (taskIndex >= taskList.size()) { // invalid integer input
                reply = "I'm very sorry Sir, there is no such task you mentioned.";
            } else {
                Task task = taskList.get(taskIndex);
                task.markTask(markAsDone);
                
                reply = "Very well Sir, I have marked this task as incomplete: \n";
                if (markAsDone) {
                    reply = "Very well Sir, I have marked this task as complete: \n";
                }
                reply += "   " + task.toString();
            }
            System.out.println(straightLine + "\n " + reply + "\n" + straightLine);

        } catch (NumberFormatException exception) { // not a number, hence not a mark / unmark command
            replyUnrecognizedCommand();
        }
    }

    // unrecognized command used
    public static void replyUnrecognizedCommand() {
        String reply = "Please use a valid command Sir.";
        System.out.println(straightLine + "\n " + reply + "\n" + straightLine);
    }
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        // print introduction message
        String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
        System.out.println(straightLine + "\n " + introductionMessage + "\n" + straightLine);
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (true) {
            String inputText = scanner.nextLine().trim();
            String[] inputStringArray = inputText.split(" ");
            if (inputText.equals("bye")) { // bye command
                displayFarewell();
                scanner.close();
                break;
            
            } else if (inputText.equals("list")) { // list command
                displayTaskList(taskList);

            } else if (inputStringArray[0].contains("mark")) { // mark / unmark command
                markCommand(inputStringArray, taskList);

            } else if (inputStringArray[0].equals("todo") 
            || inputStringArray[0].equals("deadline") 
            || inputStringArray[0].equals("event")){
                insertNewTask(inputStringArray[0], inputText, taskList);

            } else { // other text input
                replyUnrecognizedCommand();
            }
        }
    }
}
