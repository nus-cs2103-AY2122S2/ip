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
                        DateManager dateChecker = new DateManager(date);
                        if (!dateChecker.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new TaskCreator('D', false, name, date).createTask();

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
                    date = sc.next();

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
                        DateManager dateChecker = new DateManager(date);
                        if (!dateChecker.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            System.out.println(space + line);
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new TaskCreator('E', false, name, date).createTask();

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

                    // create new deadline task
                    currentTask = new TaskCreator('T', false, name, "0000000").createTask();

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
