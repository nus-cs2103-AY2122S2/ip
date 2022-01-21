import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    enum Command {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

        String command;

        private Command (String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }

        public static void process(Command c, String command, String[] commandWords) {
            switch (c) {
                case LIST:
                    printList();
                    break;
                case MARK:
                    try {
                        mark(commandWords);
                    } catch (DukeException d) {
                        printMsg(d.toString());
                    }
                    break;
                case UNMARK:
                    try {
                        unmark(commandWords);
                    } catch (DukeException d) {
                        printMsg(d.toString());
                    }
                    break;
                case DELETE:
                    try{
                        delete(commandWords);
                    } catch (DukeException d) {
                        printMsg(d.toString());
                    }
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    try {
                        addTask(command, commandWords);
                    } catch (DukeException d) {
                        printMsg(d.toString());
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            // Get the command as one line
            String command = sc.nextLine();
            // Also split the command into words for easy processing
            String[] commandWords =  command.split(" ");
            if (commandWords.length > 0) {
                // If user want to exit the program
                if (commandWords[0].equals("bye")) {
                    // If user want to exit the program
                    printMsg("See you soon! ﾍ(=￣∇￣)ﾉ");
                    break;
                } else {
                    // else we check if it is a valid command to decide how to process it
                    Command c = isValidCommand(commandWords[0]);
                    if (c != null) {
                        Command.process(c, command, commandWords);
                    } else {
                        printMsg("Sorry, I am not programmed to do this yet :(");
                    }
                }
            }
        }
        sc.close();
        return;
    }

    /**
     * Check whether it is a valid command, if valid, return that command enum number
     */
    public static Command isValidCommand(String command) {
        for (Command c : Command.values()) {
            if (c.getCommand().equals(command)) {
                return c;
            }
        }
        return null;
    }
    
    /**
     * Add task into the list and print
     */
    public static void addTask(String command, String[] commandWords) throws DukeException {
        switch (commandWords[0]) {
            case "todo":
                if (command.length() <= 5) {
                    throw new DukeException("Hmmmm what to do? Think again?");
                }
                ToDo newTD = new ToDo(command.substring(command.indexOf("todo") + 5));
                taskList.add(newTD);
                printAddedTask(newTD);
                break;
            case "deadline":
                // Extract description and deadline and pass to constructor
                if (commandWords.length <= 2 || command.indexOf("/by") == -1
                        || commandWords[1].equals("/by") || command.indexOf("/by") + 3 == command.length()) {
                    throw new DukeException("Deadline or task description missing.");
                }
                Deadline newD = new Deadline(command.substring(command.indexOf("deadline") + 9, command.indexOf("/by") - 1)
                        , command.substring(command.indexOf("/by") + 4));
                taskList.add(newD);
                printAddedTask(newD);
                break;
            case "event":
                if (commandWords.length <= 2 || command.indexOf("/at") == -1
                        || commandWords[1].equals("/at") || command.indexOf("/at") + 3 == command.length()) {
                    throw new DukeException("Event time or event description missing.");
                }
                Event newE = new Event(command.substring(command.indexOf("event") + 6, command.indexOf("/at") - 1)
                        , command.substring(command.indexOf("/at") + 4));
                taskList.add(newE);
                printAddedTask(newE);
                break;
        }
    }

    /**
     * Mark the task as done
     */
    public static void mark(String[] commandWords) throws DukeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1 || isInt(commandWords[1]) > taskList.size()) {
            throw new DukeException("Invalid arguments for marking. Please check again!");
        }
        Task toMark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        toMark.markAsDone();
        printMsg("Great! One more task done:\n" + toMark);
    }

    /**
     * Mark the task as undone
     */
    public static void unmark(String[] commandWords) throws DukeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1 || isInt(commandWords[1]) > taskList.size()) {
            throw new DukeException("Invalid arguments for unmarking. Please check again!");
        }
        Task toUnmark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        toUnmark.markAsNotDone();
        printMsg("Oops, I've marked this task as not done yet:\n" + toUnmark);
    }

    /**
     * Delete task from the list
     */
    public static void delete(String[] commandWords) throws DukeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1 || isInt(commandWords[1]) > taskList.size()) {
            throw new DukeException("Invalid arguments for deletion. Please check again!");
        }
        Task toDelete = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        taskList.remove(toDelete);
        printMsg(" Noted. I've removed this task: \n"
                + String.format("    %s\n", toDelete)
                + String.format("Now you have %s task(s) in the list.", taskList.size()));
    }

    /**
     * Checking whether the input string is integer, if yes, return it, else return -1
     */
    public static int isInt(String str) {
        try {
            int x = Integer.parseInt(str);
            return x; // it is an integer
        } catch (NumberFormatException e) {
            return -1; // not an integer
        }

    }


    /**
     * Format and print general response
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }

    /**
     * Format response to request to add task
     */
    public static void printAddedTask(Task task) {
        System.out.println("-------------------------------------------------\n"
                + "Got it. I've added this task:\n"
                + String.format("    %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", taskList.size())
                + "-------------------------------------------------");
    }

    /**
     * Print all items in the list
     */
    public static void printList() {
        int i = 1;
        String result = "Here are the task(s) in your list:\n";
        for (Task task : taskList) {
            if (i == taskList.size()) {
                result = result + i + "." + task;
            } else {
                result = result + i + "." + task + "\n";
            }
            i++;
        }
        printMsg(result);
    }
}
