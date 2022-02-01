import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // String helpers
        String space = "     ";
        String line = "____________________________________________________________";

        // create file object
        File dataFile = new File("data");

        // checks if data directory exists
        if (!dataFile.isDirectory()) {
            boolean isDataFileCreated = dataFile.mkdir();
            if (isDataFileCreated) {
                System.out.println(space + "New user detected. Setting up Duke...");
            } else {
                System.out.println(space + "Error creating save file. Please check ");
            }
        } else {
            System.out.println(space + "Previous data found. Now loading...");
        }

        // Create duke.txt if it doest not exist
        try {
            File dukeFile = new File("data/duke.txt");
            if (dukeFile.createNewFile()) {
                System.out.println(space + "A new Duke has been born...");
            } else {
                // read file
                System.out.println(space + "Duke is waking up...");
                BufferedReader br = new BufferedReader(new FileReader(dukeFile));

                /*
                 Populate task list with tasks from memory.
                 taskAsText will store tasks in the form of their prefix,
                 completed state, name, and postfix,
                 separated by "|"
                 For a todo task: todo clear the bin, taskAsText would be
                 T| |clear the bin|*
                 for the unmarked version, and
                 T|X|clear the bin|*
                 for the marked version.
                 A * is appended to the end in place of the date
                */

                String firstLine;
                int numOfExistingTasks = 0;

                if (!(firstLine = br.readLine()).isEmpty()) {
                    numOfExistingTasks = Integer.parseInt(firstLine);
                }

                while (numOfExistingTasks != 0) {
                    // convert taskAsText into its string array form,
                    // with 4 elements, being the prefix, completedState, name and date
                    numOfExistingTasks--;
                    String taskAsText = br.readLine();

                    TaskManager.loadTask(taskAsText);
                }

            }
        } catch (IOException e) {
            System.out.println("ERROR!!!\n"
                    + "Path to Duke's memory bank has been obstructed!\n"
                    + "This session will not be save!");
        }

        String greeting = space + line + "\n"
                + space + "Hello! I'm Duke\n"
                + space + "Nice to meet you!\n"
                + space + "To add a Deadline task, please input Deadline\n"
                + space + "To add an Event task, please input Event\n"
                + space + "To add a To Do task, please input ToDo\n"
                + space + "To mark a task as complete, please input Mark\n"
                + space + "To mark a task as incomplete, please input Unmark\n"
                + space + "To List current tasks in Duke, please input List\n"
                + space + "To Delete a task, please input Delete\n"
                + space + "To Exit Duke, please input Exit\n"
                + space + "(All inputs are NOT case-sensitive!)\n"
                + space + "Input anything to begin!\n"
                + space + line;

        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

            boolean isExitCommand;
            do {
                System.out.println(space + "What can I do for you?");

                String inputCommand  = sc.next();

                isExitCommand = false;
                Task currentTask;
                String name;
                String date;
                String time;
                int taskNumber;

                System.out.println(space + line);

                switch (inputCommand.toLowerCase()) {
                case "deadline":
                    // get name of task
                    System.out.println(space + "Please input the name of the task");
                    System.out.println(space + line);
                    sc.nextLine();

                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // get date of task
                    System.out.println(space + "Please input the deadline of the task in the format: DDMMYYYY");
                    System.out.println(space + line);
                    date = sc.nextLine();

                    if (date.isBlank()) {
                        System.out.println(space + "Deadline of the task cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // checks for date length and date validity
                    if (date.length() != 8) {
                        System.out.println(space + "Invalid date format!");
                        System.out.println(space + line);
                        break;
                    } else {
                        DateManager dateManager = new DateManager(date);
                        if (!dateManager.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // get time of task
                    System.out.println(space + "Please input the time of the task in the format: HHMM");
                    System.out.println(space + line);
                    time = sc.nextLine();

                    if (time.isBlank()) {
                        System.out.println(space + "Time of the task cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // checks for time length and date validity
                    if (time.length() != 4) {
                        System.out.println(space + "Invalid time format!");
                        System.out.println(space + line);
                        break;
                    } else {
                        TimeManager timeManager = new TimeManager(time);
                        if (!timeManager.isTimeValid()) {
                            System.out.println(space + "Invalid time!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new Deadline(name, date, time);

                    // add task to tasklist
                    TaskManager.add(currentTask);
                    System.out.println(space + "Task has been added to the list!");

                    System.out.println(space + line);
                    break;

                case "event":
                    // get name of task
                    System.out.println(space + "Please input the name of the task");
                    System.out.println(space + line);
                    sc.nextLine();

                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // get date of task
                    System.out.println(space + "Please input the date of the event");
                    System.out.println(space + line);
                    date = sc.nextLine();

                    if (date.isBlank()) {
                        System.out.println(space + "Date of the event cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // checks for date length and date validity
                    if (date.length() != 8) {
                        System.out.println(space + "Invalid date format!");
                        System.out.println(space + line);
                        break;
                    } else {
                        DateManager dateManager = new DateManager(date);
                        if (!dateManager.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // get time of task
                    System.out.println(space + "Please input the time of the task in the format: HHMM");
                    System.out.println(space + line);
                    time = sc.nextLine();

                    if (time.isBlank()) {
                        System.out.println(space + "Time of the task cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // checks for time length and date validity
                    if (time.length() != 4) {
                        System.out.println(space + "Invalid time format!");
                        System.out.println(space + line);
                        break;
                    } else {
                        TimeManager timeManager = new TimeManager(time);
                        if (!timeManager.isTimeValid()) {
                            System.out.println(space + "Invalid time!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new Event(name, date, time);

                    // add task to tasklist
                    TaskManager.add(currentTask);
                    System.out.println(space + "Task has been added to the list!");
                    System.out.println(space + line);
                    break;

                case "todo":
                    // get name of task
                    System.out.println(space + "Please input the name of the task");
                    sc.nextLine();
                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        System.out.println(space + line);
                        break;
                    }

                    // create new todo task
                    currentTask = new ToDo(name);

                    // add task to tasklist
                    TaskManager.add(currentTask);
                    System.out.println(currentTask.getPrefix());
                    System.out.println(space + "Task has been added to the list!");
                    System.out.println(space + line);
                    break;

                case "list":
                    // display tasklist
                    System.out.println(space + "Here is a list of your task:");
                    TaskManager.print();
                    System.out.println(space + line);
                    break;

                case "exit":
                    isExitCommand = true;
                    System.out.println(space + "Bye! Hope to see you again soon!");
                    System.out.println(space + line);
                    break;

                case "mark":
                    if (TaskManager.isEmpty()) {
                        System.out.println(space + "No available tasks to mark!");
                        System.out.println(space + line);
                        break;
                    }

                    TaskManager.print();

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to mark as complete\n");
                    System.out.println(space + line);
                    taskNumber = sc.nextInt();

                    TaskManager.mark(taskNumber);
                    System.out.println(space + line);
                    break;

                case "unmark":
                    if (TaskManager.isEmpty()) {
                        System.out.println(space + "No available tasks to mark!");
                        System.out.println(space + line);
                        break;
                    }

                    TaskManager.print();

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to mark as incomplete");
                    System.out.println(space + line);

                    taskNumber = sc.nextInt();

                    TaskManager.unmark(taskNumber);
                    System.out.println(space + line);
                    break;

                case "delete":
                    if (TaskManager.isEmpty()) {
                        System.out.println(space + "No available tasks to delete!");
                        System.out.println(space + line);
                        break;
                    }

                    TaskManager.print();

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to delete");
                    System.out.println(space + line);
                    taskNumber = sc.nextInt();

                    if (!TaskManager.isValidTask(taskNumber)) {
                        System.out.println(space + "Error! Task does not exist!");
                        System.out.println(space + line);
                        break;
                    }

                    System.out.println(space + "Are you sure you wish to delete\n"
                            + space + TaskManager.get(taskNumber) + " ?\n"
                            + space + "Input Y/N to continue.");
                    System.out.println(space + line);

                    char deleteConfirmation = sc.next().toLowerCase().charAt(0);

                    if (deleteConfirmation ==  'y') {
                        TaskManager.delete(taskNumber);
                        System.out.println(space + "Okie Dokie! Task has been deleted!");
                        System.out.println(space + line);
                        break;
                    }

                    if (deleteConfirmation ==  'n') {
                        System.out.println(space + "Delete action has been aborted! Your task is safe!");
                        System.out.println(space + line);
                        break;
                    }

                    System.out.println(space + "Invalid input!");
                    System.out.println(space + line);
                    break;

                default:
                    System.out.println(space + "Invalid input!!!\n"
                            + space + "Here is the list of valid commands:\n"
                            + space + "Deadline\n"
                            + space + "Event\n"
                            + space + "Todo\n"
                            + space + "Mark\n"
                            + space + "Unmark\n"
                            + space + "Delete\n"
                            + space + "Exit");
                    System.out.println(space + line);
                }

            } while (!isExitCommand);
        sc.close();
    }
}
