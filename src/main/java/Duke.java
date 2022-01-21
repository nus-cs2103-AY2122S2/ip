import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
// import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import chatbot.DukeException;
import chatbot.Todo;
import chatbot.Deadline;
import chatbot.Event;
import chatbot.Task;
public class Duke {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    protected static String FILE_PATH = "./data/duke.txt";
    protected static String FILE_DIR = "./data";

    // strings to use
    public static String straightLine = "____________________________________________________________";
    public static String introductionMessage = "Good day Sir. My name is Dook. \n How may I be of assistance?";
    public static String byeMessage = "Farewell Sir. May you have a wonderful day.";
    public static String noSuchTask = "I'm very sorry Sir, there is no such task you mentioned.";
    public static String notValidNumber = "Please enter a valid number Sir.";
    public static String commandRequiresNumber = "Sorry Sir, this command requires a number.";
    public static String unrecognizedCommand = "Sorry Sir, I do not understand that command.";
    public static String loadSuccess = "I have successfully loaded your saved agenda, Sir.";
    public static String unreadableFile = "Sorry Sir, I was unable to access the data file.";

    ////////////////////////////////////////////////////////////////
    // Main Methods

    // introduction message
    public static void displayGreeting() {
        System.out.println(createReply(introductionMessage));
    }

    // farewell message
    public static void displayFarewell() {
        System.out.println(createReply(byeMessage));
    }

    // insert new Task
    public static void insertNewTask(String type, String inputText) throws DukeException {
        String reply = "";
        Task newTask;
        String taskName = "";
        String taskDateTime = "";
        String missingDateTime = "Sorry Sir, the description of <" + type + "> is missing a date/time.";
        String missingTaskInfo = "Sorry Sir, the <" + type + "> command cannot be empty.";

        if (type.equals("deadline")) {
            String[] inputStringArray = inputText.split(" /by ");
            try { 
                taskName = inputStringArray[0].substring(9);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            try {
                taskDateTime = inputStringArray[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                throw new DukeException(missingDateTime);
            }
            newTask = new Deadline(taskName, taskDateTime);

        } else if (type.equals("event")) {
            String[] inputStringArray = inputText.split(" /at ");
            try { 
                taskName = inputStringArray[0].substring(6);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            try {
                taskDateTime = inputStringArray[1];
            } catch (Exception ArrayIndexOutOfBoundsException) {
                throw new DukeException(missingDateTime);
            }
            newTask = new Event(taskName, taskDateTime);

        } else { // this is a to-do task
            try { 
                taskName = inputText.substring(5);
            } catch (StringIndexOutOfBoundsException exception) {
                throw new DukeException(missingTaskInfo);
            }
            newTask = new Todo(taskName);
        }

        taskList.add(newTask);
        saveData();
        String taskListLength = Integer.toString(taskList.size());
        reply += "Very well Sir. I have added this task:";
        reply += "\n   " + newTask.toString();
        reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
        System.out.println(createReply(reply));
    }

    // display task list
    public static void displayTaskList() {
        Integer counter = 1;
        String reply = "";
        for (Task task : taskList) {
            String taskInfo = task.toString();
            reply += "\n " + counter.toString() + "." + taskInfo; // note: new line is at the start
            counter++;
        }
        String listHeader = "This is your agenda of tasks Sir:";
        System.out.println(createReply(listHeader + reply));
    }

    // mark / unmark command
    public static void markCommand(String[] inputStringArray, String type) throws DukeException {
        // check command
        boolean markAsDone = false;
        if (type.equals("mark")) {
            markAsDone = true;
        } else if (type.equals("unmark")) {
            markAsDone = false;
        } else { 
            
        }
        
        // check if there is an integer after the text command input
        try {
            String inputNumberString = inputStringArray[1];
            Integer inputNumberInteger = Integer.parseInt(inputNumberString);
            String reply = "";
            
            int taskIndex = inputNumberInteger - 1;
            if (taskIndex >= taskList.size()) { // invalid integer input
                throw new DukeException(noSuchTask);
            } 

            Task task = taskList.get(taskIndex);
            task.markTask(markAsDone);
            saveData();

            if (markAsDone) {
                reply += "Very well Sir, I have marked this task as complete: ";
            } else {
                reply += "Very well Sir, I have marked this task as incomplete: ";
            }
            reply += "\n   " + task.toString();

            System.out.println(createReply(reply));

        } catch (NumberFormatException exception) { // not a number
            throw new DukeException(notValidNumber);
        } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
            throw new DukeException(commandRequiresNumber);
        }
    }

    // delete command
    public static void deleteTask(String[] inputStringArray) throws DukeException {
        
        // check if there is an integer after the text command input
        try {
            String inputNumberString = inputStringArray[1];
            Integer inputNumberInteger = Integer.parseInt(inputNumberString);
            String reply = "";
            
            int taskIndex = inputNumberInteger - 1;
            if (taskIndex >= taskList.size()) { // invalid integer input
                throw new DukeException(noSuchTask);
            } 
            
            Task taskToRemove = taskList.get(taskIndex);
            taskList.remove(taskToRemove);
            saveData();
            String taskListLength = Integer.toString(taskList.size());

            reply += "Very well Sir. I have removed this task:";
            reply += "\n   " + taskToRemove.toString();
            reply += "\n You now have " + taskListLength + " tasks in the agenda Sir.";
            
            System.out.println(createReply(reply));

        } catch (NumberFormatException exception) { // not a number
            throw new DukeException(notValidNumber);
        } catch (ArrayIndexOutOfBoundsException exception) { // no input together with command
            throw new DukeException(commandRequiresNumber);
        } 
    }

    ////////////////////////////////////////////////////////////////
    // Helper Methods

    // inserts and formats the straight lines into the reply
    public static String createReply(String reply) {
        return straightLine + "\n " + reply + "\n" + straightLine;
    }

    // saves task list into duke.txt
    public static void saveData() throws DukeException {
        // create a directory if it doesn't exist
        File dataFile = new File(FILE_DIR);
        if (!dataFile.exists()){
            dataFile.mkdirs();
        }

        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            
            for (Task task : taskList) {
                String dataEntry = "";
                if (task instanceof Todo) {
                    dataEntry += "Todo";
                    dataEntry += task.getIsDone() ? "/;DONE" : "/;NOT_DONE";
                    dataEntry += "/;" + task.getTaskName();
                    dataEntry += "\n";
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    dataEntry += "Deadline";
                    dataEntry += deadline.getIsDone() ? "/;DONE" : "/;NOT_DONE";
                    dataEntry += "/;" + deadline.getTaskName();
                    dataEntry += "/;" + deadline.getDateTime();
                    dataEntry += "\n";
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    dataEntry += "Event";
                    dataEntry += event.getIsDone() ? "/;DONE" : "/;NOT_DONE";
                    dataEntry += "/;" + event.getTaskName();
                    dataEntry += "/;" + event.getDateTime();
                    dataEntry += "\n";
                }
                printWriter.printf(dataEntry);
            }
            printWriter.close();

        } catch (IOException exception) {
            throw new DukeException(unreadableFile);
        }
    }

    // load the duke.txt into the array
    public static void loadData() throws DukeException {
        try {
            // File directory = new File("./");
            // System.out.println(directory.getAbsolutePath()); // used to check directory java starts at

            // check if there is a file at all
            File dataFile = new File(FILE_PATH);
            if (!dataFile.exists()){
                return;
            }

            Scanner scanner = new Scanner(dataFile);
            String dataEntry;
            while (scanner.hasNext()) {
                dataEntry = scanner.nextLine();
                String[] dataEntryArray = dataEntry.split("/;"); // assumes data file is formatted correctly
                
                // check whether task is done
                boolean isDone = false;
                    if (dataEntryArray[1].equals("DONE")) {
                        isDone = true;
                    }
                
                Task newTask;
                switch (dataEntryArray[0]) {
                    case "Todo": // to-do task
                        newTask = new Todo(dataEntryArray[2], isDone);
                        break;
                    case "Deadline": // deadline task
                        newTask = new Deadline(dataEntryArray[2], dataEntryArray[3], isDone);
                        break;
                    case "Event": // event task
                        newTask = new Event(dataEntryArray[2], dataEntryArray[3], isDone);
                        break;
                    default: // unknown task - should not reach here
                        newTask = new Todo(dataEntryArray[2], isDone);
                        break;
                }
                taskList.add(newTask);
            }
            scanner.close();
            System.out.println(createReply(loadSuccess));
        } catch (FileNotFoundException e) {
            throw new DukeException(unreadableFile);
        }
    }

    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        try { // attempt to load saved data
            loadData();
        } catch (DukeException dukeException) {
            System.out.println(createReply(dukeException.toString()));
        }
        
        // print introduction message
        displayGreeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String inputText = scanner.nextLine().trim();
            String[] inputStringArray = inputText.split(" ");
            try {
                if (inputText.equals("bye")) { // bye command
                    displayFarewell();
                    scanner.close();
                    break;
                
                } else if (inputText.equals("list")) { // list command
                    displayTaskList();

                } else if (inputStringArray[0].equals("mark") || inputStringArray[0].equals("unmark")) { // mark / unmark command
                    markCommand(inputStringArray, inputStringArray[0]);

                } else if (inputStringArray[0].equals("todo") 
                || inputStringArray[0].equals("deadline") 
                || inputStringArray[0].equals("event")){
                    insertNewTask(inputStringArray[0], inputText);

                } else if (inputStringArray[0].equals("delete")) {
                    deleteTask(inputStringArray);

                } else { // other text input
                    throw new DukeException(unrecognizedCommand);
                }
            } catch (DukeException dukeException) {
                System.out.println(createReply(dukeException.toString()));
            }
        }
    }
}
