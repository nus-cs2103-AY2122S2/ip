import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Spike {
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

        public static void processCommand(Command c, String command, String[] commandWords) {
            switch (c) {
            case LIST:
                try {
                    if (commandWords.length >= 2) {
                        printListByDate(command);
                    } else {
                        printList();
                    }
                } catch (SpikeException d) {
                    printMsg(d.toString());
                }
                break;
            case MARK:
                try {
                    mark(commandWords);
                } catch (SpikeException d) {
                    printMsg(d.toString());
                }
                break;
            case UNMARK:
                try {
                    unmark(commandWords);
                } catch (SpikeException d) {
                    printMsg(d.toString());
                }
                break;
            case DELETE:
                try {
                    delete(commandWords);
                } catch (SpikeException d) {
                    printMsg(d.toString());
                }
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
            case EVENT:
                try {
                    addTask(c, command, commandWords);
                } catch (SpikeException d) {
                    printMsg(d.toString());
                }
                break;
            default:
                break;
            }
        }
    }

    public static void main(String[] args) {
        // Check first whether the data folder exists, if not, create it
        File dataDir = new File("data/");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Check whether the data file exists, if not, create it
        File dataFile = new File("data/Spike.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                printMsg("Sorry, I couldn't create the task list file for you.");
            }
        }

        // Load data from the file if it is not empty
        if (!(dataFile.length() == 0)) {
            try {
                Scanner fileRead = new Scanner(dataFile);
                while (fileRead.hasNextLine()) {
                    String currLine = fileRead.nextLine();
                    String[] info = currLine.split(" \\| ");
                    Task task = null;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    switch (info[0]) {
                    case "T":
                        task = new ToDo(info[2]);
                        break;
                    case "E":
                        task = new Event(info[2], LocalDateTime.parse(info[3], dtf));
                        break;
                    case "D":
                        task = new Deadline(info[2], LocalDateTime.parse(info[3], dtf));
                        break;
                    default:
                        break;
                    }
                    taskList.add(task);
                    if (info[1].equals("1")) {
                        task.markAsDone();
                    }
                }
            } catch (FileNotFoundException e) {
                printMsg("I couldn't find your task list file :(");
            }
        }

        if (dataFile.length() == 0) {
            printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃ Nice to meet you!\nWhat can I do for you?");
        } else {
            printMsg("Welcome back! Enter 'list' to see your saved task(s).");
        }


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
                    saveChanges();
                    printMsg("See you soon! ﾍ(=￣∇￣)ﾉ");
                    break;
                } else {
                    // else we check if it is a valid command to decide how to process it
                    Command c = isValidCommand(commandWords[0]);
                    if (c != null) {
                        Command.processCommand(c, command, commandWords);
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
     * Checks whether it is a valid command.
     * If valid, return that command enum number, else return null.
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
     * Saves the latest task list into hard disk when user exits.
     */
    public static void saveChanges() {
        try {
            FileWriter fw = new FileWriter("data/Spike.txt");
            String latestList = "";
            for (Task task : taskList) {
                latestList = latestList + task.toFileFormat() + "\n";
            }
            fw.write(latestList);
            fw.close();
        } catch (IOException e) {
            printMsg("Oops, something went wrong with saving your file :(");
        }
    }

    /**
     * Adds task into the list and prints.
     */
    public static void addTask(Command c, String command, String[] commandWords) throws SpikeException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        switch (c) {
        case TODO:
            if (command.length() <= 5) {
                throw new SpikeException("Hmmmm what to do? Think again?");
            }
            ToDo newTD = new ToDo(command.substring(command.indexOf("todo") + 5));
            taskList.add(newTD);
            printAddedTask(newTD);
            break;
        case DEADLINE:
            // Extract description and deadline and pass to constructor
            if (commandWords.length <= 2 || command.indexOf("/by") == -1
                    || commandWords[1].equals("/by") || command.indexOf("/by") + 3 == command.length()) {
                throw new SpikeException("Deadline or task description missing.");
            }
            LocalDateTime deadlineT = parseDateTime(command.substring(command.indexOf("/by") + 4), dtf);
            if (!(deadlineT == null)) {
                Deadline newD = new Deadline(command.substring(command.indexOf("deadline") + 9,
                        command.indexOf("/by") - 1), deadlineT);
                taskList.add(newD);
                printAddedTask(newD);
            }
            break;
        case EVENT:
            if (commandWords.length <= 2 || command.indexOf("/at") == -1
                    || commandWords[1].equals("/at") || command.indexOf("/at") + 3 == command.length()) {
                throw new SpikeException("Event time or event description missing.");
            }
            LocalDateTime eventT = parseDateTime(command.substring(command.indexOf("/at") + 4), dtf);
            if (!(eventT == null)) {
                Event newE = new Event(command.substring(command.indexOf("event") + 6,
                        command.indexOf("/at") - 1), eventT);
                taskList.add(newE);
                printAddedTask(newE);
            }
            break;
        default:
            break;
        }
    }

    /**
     * Parses date and time input by user and returns valid LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String s, DateTimeFormatter dtf) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(s, dtf);
            return dateTime;
        } catch (DateTimeParseException e) {
            printMsg("Please enter a valid date in the format yyyy-MM-dd HHmm");
            return null;
        }
    }

    /**
     * Marks the task as done.
     */
    public static void mark(String[] commandWords) throws SpikeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1
                || isInt(commandWords[1]) > taskList.size()) {
            throw new SpikeException("Invalid arguments for marking. Please check again!");
        }
        Task toMark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        toMark.markAsDone();
        printMsg("Great! One more task done:\n" + toMark);
    }

    /**
     * Marks the task as undone.
     */
    public static void unmark(String[] commandWords) throws SpikeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1
                || isInt(commandWords[1]) > taskList.size()) {
            throw new SpikeException("Invalid arguments for unmarking. Please check again!");
        }
        Task toUnmark = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        toUnmark.markAsNotDone();
        printMsg("Oops, I've marked this task as not done yet:\n" + toUnmark);
    }

    /**
     * Deletes task from the list.
     */
    public static void delete(String[] commandWords) throws SpikeException {
        if (commandWords.length != 2 || isInt(commandWords[1]) == -1
                || isInt(commandWords[1]) > taskList.size()) {
            throw new SpikeException("Invalid arguments for deletion. Please check again!");
        }
        Task toDelete = taskList.get(Integer.parseInt(commandWords[1]) - 1);
        taskList.remove(toDelete);
        printMsg(" Noted. I've removed this task: \n"
                + String.format("    %s\n", toDelete)
                + String.format("Now you have %s task(s) in the list.", taskList.size()));
    }

    /**
     * Checks whether the input string is integer.
     * If yes, return it, else return -1.
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
     * Formats and prints general response.
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }

    /**
     * Formats response to request to add task.
     */
    public static void printAddedTask(Task task) {
        System.out.println("-------------------------------------------------\n"
                + "Got it. I've added this task:\n"
                + String.format("    %s\n", task.toString())
                + String.format("Now you have %s task(s) in the list.\n", taskList.size())
                + "-------------------------------------------------");
    }

    /**
     * Prints all items in the list.
     */
    public static void printList() {
        int i = 1;
        String result = "Here are the task(s) in your list:\n";
        for (Task task : taskList) {
            result += i + "." + task;
            if (i != taskList.size()) {
                result += "\n";
            }
            i++;
        }
        printMsg(result);
    }

    /**
     * Prints deadlines/events occurring on the given date.
     */
    public static void printListByDate(String command) throws SpikeException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime ldt = parseDateTime(command.substring(5), dtf);
        if (ldt == null) {
            throw new SpikeException("Kindly enter the date in the format yyyy-MM-dd 0000 to filter by date");
        }
        int i = 1;
        String result = "Here are the task(s) in your list filtered by date:\n";
        for (Task task : taskList) {
            if (task.getDateTime().toLocalDate().equals(ldt.toLocalDate())) {
                result = result + i + "." + task;
                if (i != taskList.size()) {
                    result += "\n";
                }
            }
            i++;
        }
        printMsg(result);
    }
}
