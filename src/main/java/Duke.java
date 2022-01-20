import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\n" +
                "    ___   ____________\n" +
                "   /   | / ____/ ____/\n" +
                "  / /| |/ /   / __/   \n" +
                " / ___ / /___/ /___   \n" +
                "/_/  |_\\____/_____/   \n" +
                "                      \n";
        Scanner reader = new Scanner(System.in);
        TaskList taskList = new TaskList();

        printAce(logo);
        printAce("Hey, I'm Ace. What can I help you with?");

        String userInput = reader.nextLine();

        while(!userInput.equals("bye")) {
            String[] splitInput = userInput.split(" ", 2);
            String command = splitInput[0];

            switch (command) {
                case "list":
                    String list = taskList.getTaskList();
                    printAce(list.length() > 0 ? "Here are the tasks in your list:\n" +
                            list : "There are no tasks in your list currently.");
                    break;

                case "todo":
                    try {
                        Todo newTodo = taskList.addTodo(splitInput[1]);
                        printAce("Added this item to your list:\n" + newTodo + "\nYou now have " +
                                taskList.getNumberOfTasks() + " task(s).");
                    } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                        printAce("Please provide a name for the todo item you'd like to create in the form 'todo {name}'.");
                    }
                    break;

                case "event":
                    try {
                        String[] eventInfo = splitInput[1].split(" /at ", 2);
                        Event newEvent = taskList.addEvent(eventInfo[0], eventInfo[1]);
                        printAce("Added this item to your list:\n" + newEvent + "\nYou now have " +
                                taskList.getNumberOfTasks() + " task(s).");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printAce("Please provide a name and time for the event you'd like" +
                                " to create in the form 'event {name} /at {time}'.");
                    } catch (DukeException e) {
                        printAce(e.getMessage() + "\nPlease provide a name and time for the event you'd like to" +
                                " create in the form 'event {name} /at {time}'.");
                    }
                    break;

                case "deadline":
                    try {
                        String[] deadlineInfo = splitInput[1].split(" /by ", 2);
                        Deadline newDeadline = taskList.addDeadline(deadlineInfo[0], deadlineInfo[1]);
                        printAce("Added this item to your list.\n" + newDeadline + "\nYou now have " +
                                taskList.getNumberOfTasks() + " task(s).");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printAce("Please provide a name and time for the deadline item you'd like" +
                                " to create in the form 'event {name} /at {time}'.");
                    } catch (DukeException e) {
                        printAce(e.getMessage() + "\nPlease provide a name and time for the deadline item you'd like to" +
                                " create in the form 'event {name} /at {time}'.");
                    }
                    break;

                case "mark":
                    try {
                        int taskNum = Integer.parseInt(splitInput[1]);
                        Task selectedTask = taskList.getTaskByNum(taskNum);
                        selectedTask.markAsComplete();
                        printAce("I've marked the following task as complete:\n" + selectedTask);
                    } catch (NumberFormatException e) {
                        printAce("Please provide the number of the task you'd like to mark.");
                    } catch (DukeException e) {
                        printAce(e.getMessage());
                    }
                    break;

                case "unmark":
                    try {
                        int taskNum = Integer.parseInt(splitInput[1]);
                        Task selectedTask = taskList.getTaskByNum(taskNum);
                        selectedTask.markAsIncomplete();
                        printAce("I've marked the following task as incomplete:\n" + selectedTask);
                    } catch (NumberFormatException e) {
                        printAce("Please provide the number of the task you'd like to mark.");
                    } catch (DukeException e) {
                        printAce(e.getMessage());
                    }
                    break;



                default:
                    printAce("Sorry I couldn't understand that. Here are a list of valid commands.\n" +
                            "list: Displays all tasks in your list.\n" +
                            "todo {name}: Adds a todo item to your list.\n" +
                            "event {name} /at {time}: Adds an event to your list.\n" +
                            "deadline {name} /by {time}: Adds a task with a deadline to your list.\n" +
                            "mark {task_number}: Marks a task as complete.\n" +
                            "unmark {task_number}: Marks a task as incomplete.\n" +
                            "bye: Ends our current session.");

            }
            userInput = reader.nextLine();
        }
        printAce("See you later!");
        reader.close();
    }

    private static void printAce(String str) {
        System.out.println("________\n" + str.indent(4));
    }
}

/*
case list:
    print tasks from taskList. If no tasks in list, string is empty. Print accordingly.

case mark:
    Split string at ' ' and get task from second part.
    Two potential errors:
        1. No number provided. Throw IncorrectInputFormatException.
        2. Number is out of range. Throw IndexOutOfRangeException.
    If task available, mark and print.

case unmark:
    Split string at ' ' and get task from second part.
    Two potential errors:
        1. No number provided. Throw IncorrectInputFormatException.
        2. Number is out of range. Throw IndexOutOfRangeException.
    If task available, unmark and print.

case todo:
    Split string at ' ' only once.
    Pass both sections into taskList to addTask.
    One potential error:
        1. No second half provided. Throw IncorrectInputFormatException.
    If task created, get the task and print. Print number of tasks.

case deadline:
    Split string once at ' ' and once at ' /by '.
    Pass three sections into taskList to addTask.
    One potential error:
        1. One section is missing. Throw IncorrectInputFormatException.
    If task created, get the task and print. Print number of tasks.

case event:
    Split string once at ' ' and once at ' /at '.
    Pass three sections into taskList to addTask.
    One potential error:
        1. One section is missing. Throw IncorrectInputFormatException.
    If task created, get the task and print. Print number of tasks.

case default:
    Print sorry I did not understand that command. Here are a list of commands.
 */