import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // task list to be initialized first
        ArrayList<Task> taskList = new ArrayList<>();

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
                + space + "Input anything to begin!\n"
                + space + "(All inputs are NOT case-sensitive!)\n"
                + space + line;

        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            System.out.println(space + "What can I do for you?");
            sc.nextLine();

            boolean validCommand;
            do {
                String inputCommand  = sc.next();

                validCommand = true;
                Task currentTask;
                String name;
                String date;
                int taskNumber;

                switch (inputCommand.toLowerCase()) {
                case "deadline":
                    // get name of task
                    System.out.println(space + "Please input the name of the task");
                    sc.nextLine();
                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        break;
                    }

                    // get date of task
                    System.out.println(space + "Please input the deadline of the task in the format: DDMMYYYY");
                    date = sc.nextLine();

                    if (date.isBlank()) {
                        System.out.println(space + "Deadline of the task cannot be blank!");
                        break;
                    }

                    // checks for date length and date validity
                    if (date.length() != 8) {
                        System.out.println(space + "Invalid date format!");
                        break;
                    } else {
                        DateChecker dateChecker = new DateChecker(date);
                        if (!dateChecker.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new TaskCreator('D', false, name, date).createTask();

                    // add task to tasklist
                    taskList.add(currentTask);
                    System.out.println(space + "Task has been added to the list!");

                    // display tasklist
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }
                    break;

                case "event":
                    // get name of task
                    System.out.println(space + "Please input the name of the task");
                    sc.nextLine();
                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        break;
                    }

                    // get date of task
                    System.out.println(space + "Please input the date of the event");
                    date = sc.next();

                    if (date.isBlank()) {
                        System.out.println(space + "Date of the event cannot be blank!");
                        break;
                    }

                    // checks for date length and date validity
                    if (date.length() != 8) {
                        System.out.println(space + "Invalid date format!");
                        break;
                    } else {
                        DateChecker dateChecker = new DateChecker(date);
                        if (!dateChecker.isDateValid()) {
                            System.out.println(space + "Invalid date!");
                            break;
                        }
                    }

                    // create new deadline task
                    currentTask = new TaskCreator('E', false, name, date).createTask();

                    // add task to tasklist
                    taskList.add(currentTask);
                    System.out.println(space + "Task has been added to the list!");

                    // display tasklist
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }
                    break;

                case "todo":
                    // get name of task
                    System.out.println(space + "Please input the name of the task\n");
                    sc.nextLine();
                    name = sc.nextLine();

                    if (name.isBlank()) {
                        System.out.println(space + "Task name cannot be blank!");
                        break;
                    }

                    // create new deadline task
                    currentTask = new TaskCreator('T', false, name, "0000000").createTask();

                    // add task to tasklist
                    taskList.add(currentTask);
                    System.out.println(space + "Task has been added to the list!");

                    // display tasklist
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }
                    break;

                case "list":
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }
                    break;

                case "exit":
                    System.out.println(space + line);
                    System.out.println(space + "Bye! Hope to see you again soon!");
                    System.out.println(space + line);
                    break;

                case "mark":
                    if (taskList.size() == 0) {
                        System.out.println(space + "No available tasks to mark!");
                        break;
                    }

                    System.out.println(space + "Here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to mark as complete\n");
                    taskNumber = sc.nextInt();

                    if (taskNumber > taskList.size()) {
                        System.out.println(space + "Error! Task does not exist!");
                        break;
                    }

                    if(taskList.get(taskNumber - 1).isMarked()) {
                        System.out.println(space + "Error! Task has already been marked as complete!");
                        break;
                    }

                    taskList.get(taskNumber - 1).mark();
                    break;

                case "unmark":
                    if (taskList.size() == 0) {
                        System.out.println(space + "No available tasks to mark!");
                        break;
                    }
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to mark as incomplete");
                    taskNumber = sc.nextInt();

                    if (taskNumber > taskList.size()) {
                        System.out.println(space + "Error! Task does not exist!");
                        break;
                    }

                    if(taskList.get(taskNumber - 1).isMarked()) {
                        System.out.println(space + "Error! Task has already been marked as incomplete!");
                        break;
                    }

                    taskList.get(taskNumber - 1).unmark();
                    break;

                case "delete":
                    if (taskList.size() == 0) {
                        System.out.println(space + "No available tasks to delete!");
                        break;
                    }
                    System.out.println(space + "Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }

                    System.out.println(space + "Please input the number of the task " +
                            "that you wish to delete");
                    taskNumber = sc.nextInt();

                    if (taskNumber > taskList.size()) {
                        System.out.println(space + "Error! Task does not exist!");
                        break;
                    }

                    System.out.println(space + "Are you sure you wish to delete\n"
                            + space + taskList.get(taskNumber - 1) + " ?\n"
                            + space + "Input Y/N to continue.");

                    char deleteConfirmation = sc.next().toLowerCase().charAt(0);

                    if (deleteConfirmation ==  'y') {
                        taskList.remove(taskNumber - 1);
                        System.out.println(space + "Okie Dokie! Task has been deleted!");
                    } else if (deleteConfirmation ==  'n') {
                        System.out.println(space + "Delete action has been aborted! Your task is safe!");
                    } else {
                        System.out.println(space + "Invalid input!\n"
                                + space + "(You only had to input one of 2 letters in the alphabet...)");
                    }

                    System.out.println("Here are the tasks in your list:\n");
                    for (int i = 0; i < taskList.size(); i++) {
                        String bullet = space + (i + 1) + ".";
                        System.out.println(bullet + taskList.get(i).toString());
                    }
                    break;

                default:
                    validCommand = false;
                    System.out.println(space + "Invalid input!!!\n"
                            + space + "Here is the list of valid commands:\n"
                            + space + "Deadline\n"
                            + space + "Event\n"
                            + space + "Todo\n"
                            + space + "Mark\n"
                            + space + "Unmark\n"
                            + space + "Delete\n"
                            + space + "Exit");
                }

            } while (!validCommand);
        }
        sc.close();
    }
}
