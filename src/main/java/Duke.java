import java.util.*;

public class Duke {

    public static String indent = "    ";
    public static String separator = "--------------------------------------------";
    public static String[] openingMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};
    public static String closingMessage = "Bye. Hope to see you again soon!";

    public static ArrayList<Task> allTasks = new ArrayList<>();

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public static void printIndent(String s) {
        System.out.println(indent + s);
    }

    public static void prettyPrint(String s) {
        printIndent(separator);
        printIndent(s);
        printIndent(separator + "\n");
    }

    public static void prettyPrint(String[] messages) {
        printIndent(separator);
        for (String message : messages) printIndent(message);
        printIndent(separator + "\n");
    }

    public static void displayTasks() {
        printIndent(separator);
        if (allTasks.size() == 0) {
            printIndent("You have no tasks");
        } else {
            printIndent("Here are the tasks in your list:");
            for (int i = 0; i < allTasks.size(); i++) {
                printIndent(String.format("%d. %s", i+1, allTasks.get(i)));
            }
        }
        printIndent(separator + "\n");
    }

    public static void addTask(String taskString) throws DukeException {
        String taskName;
        Task t;
        if (taskString.startsWith("todo ")) {
            if (taskString.length() <= 5) {
                throw new DukeException("Description cannot be empty!");
            } else {
                taskName = taskString.substring(5);  // "todo " has 5 characters
                t = new ToDo(taskName);
            }
        } else if (taskString.startsWith("deadline ")) {
            if (taskString.length() <= 9) {
                throw new DukeException("Description cannot be empty!");
            } else {
                int byIdx = taskString.indexOf("/by");
                if (byIdx <= 9) {  // either -1, or 0 to 9
                    throw new DukeException("Format for deadlines: 'deadline [some task] /by [time]'");
                } else {
                    taskName = taskString.substring(9, byIdx-1);  // "deadline " has 9 characters
                    try {
                        String taskDeadline = taskString.substring(byIdx + 4);  // "/by " has 4 characters
                        t = new Deadline(taskName, taskDeadline);
                    } catch (StringIndexOutOfBoundsException err) {
                        throw new DukeException("Format for deadlines: 'deadline [some task] /by [time]'");
                    }
                }
            }
        } else if (taskString.startsWith("event ")) {
            if (taskString.length() <= 6) {
                throw new DukeException("Description cannot be empty!");
            } else {
                int atIdx = taskString.indexOf("/at");
                if (atIdx <= 6) {  // either -1, or 0 to 6
                    throw new DukeException("Format for events: 'event [some event] /at [time]'");
                } else {
                    taskName = taskString.substring(6, atIdx-1);  // "event " has 9 characters
                    try {
                        String taskTime = taskString.substring(atIdx + 4);  // "/at " has 4 characters
                        t = new Event(taskName, taskTime);
                    } catch (StringIndexOutOfBoundsException err) {
                        throw new DukeException("Format for events: 'event [some event] /at [time]'");
                    }
                }
            }
        } else {
            throw new DukeException("I don't think I know what this is!");
        }

        allTasks.add(t);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                t.toString(),
                "Now you have " + allTasks.size() + " tasks in the list."
        };
        prettyPrint(messages);
    }

    public static void handleMarkTask(String command) throws DukeException {
        String taskString = command.substring(5);  // "mark " is 5 letters
        int taskToMark;
        try {
            taskToMark = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToMark && taskToMark <= allTasks.size()) {
            markTask(taskToMark - 1);
        } else {
            throw new DukeException(String.format("Task %d does not exist!", taskToMark));
        }
    }

    public static void markTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        if (thisTask.isDone()) {
            prettyPrint("This task is already done!");
        } else {
            thisTask.setDone(true);
            prettyPrint(new String[] {"Nice! I've marked this task as done:", thisTask.toString()});
        }
    }

    public static void handleUnmarkTask(String command) throws DukeException {
        String taskString = command.substring(7);  // "unmark " is 7 letters
        int taskToUnmark;
        try {
            taskToUnmark = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToUnmark && taskToUnmark <= allTasks.size()) {
            unmarkTask(taskToUnmark - 1);
        } else {
            throw new DukeException(String.format("Task %d does not exist!", taskToUnmark));
        }
    }

    public static void unmarkTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        if (!thisTask.isDone()) {
            prettyPrint("This task has not been done yet!");
        } else {
            thisTask.setDone(false);
            prettyPrint(new String[] {"Ok, I've marked this task as not done yet:", thisTask.toString()});
        }
    }

    public static void handleDeleteTask(String command) throws DukeException {
        String taskString = command.substring(7);  // "delete " is 7 letters
        int taskToDelete;
        try {
            taskToDelete = Integer.parseInt(taskString);
        }
        catch (NumberFormatException err) {
            throw new DukeException("Not a valid task number!");
        }

        if (1 <= taskToDelete && taskToDelete <= allTasks.size()) {
            deleteTask(taskToDelete - 1);
        } else {
            throw new DukeException("Task %d does not exist!");
        }
    }

    public static void deleteTask(int taskNum) {
        Task thisTask = allTasks.get(taskNum);
        allTasks.remove(taskNum);
        prettyPrint(new String[] {
                "Noted. I've removed this task:",
                thisTask.toString(),
                String.format("Now you have %d tasks in the list.", allTasks.size())});
    }

    public static void main(String[] args) {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        // introduction messages
        System.out.println(logo);
        prettyPrint(openingMessage);

        // read input
        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean finished = false;
        while (!finished) {
            userInput = sc.nextLine();

            try {
                if (userInput.equals("bye")) {  // end
                    prettyPrint(closingMessage);
                    finished = true;
                } else if (userInput.equals("list")) {  // display tasks
                    displayTasks();
                } else if (userInput.startsWith("mark ")) {  // mark task as done
                    handleMarkTask(userInput);
                } else if (userInput.startsWith("unmark ")) {  // mark task as undone
                    handleUnmarkTask(userInput);
                } else if (userInput.startsWith("delete ")) {  // delete task
                    handleDeleteTask(userInput);
                } else {  // add task
                    addTask(userInput);
                }
            } catch (DukeException err) {
                prettyPrint(err.getMessage());
            }

        }
    }
}
