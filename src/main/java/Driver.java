import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Class that executes the main functionality
 */
public class Driver {
    private TaskList tasks;
    private final String line = "\t____________________________________________________________";

    /*
     * Constructor that initializes the tasks arraylist
     */
    public Driver() {
        this.tasks = new TaskList();
        this.loadTask();
    }

    /**
     * Executes the instructions given by user
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;
        while (!exit) {
            try {
                input = sc.nextLine();
                String[] commands = input.split(" ", 2);
                Command command = parseString(commands);
                switch (command) {
                case BYE:
                    exit = true;
                    System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                    this.saveTask();
                    break;
                case LIST:
                    displayTasks();
                    break;
                case MARK:
                    markAsDone(commands[1], true);
                    break;
                case UNMARK:
                    unmarkDone(commands[1]);
                    break;
                case DELETE:
                    deleteTask(commands[1]);
                    break;
                case INVALID:
                    System.out.println(line + "\n\t" + commands[0] + " is not a valid command\n" + line);
                    break;
                case EMPTY:
                    break;
                default:
                    addTask(command, commands[1], true);
                    break;
                }
            } catch (DukeException e) {
                System.out.println(line + "\n\t" + e.getMessage() + "\n" + line);
            }
        }
    }

    public Command parseString(String[] command) throws DukeException {
        switch (command[0].toLowerCase()) {
        case "":
            return Command.EMPTY;
        case "bye":
            if (command.length > 1 && !command[1].equals(""))
                throw new DukeWrongInputFormatException("Command bye should not have any arguments");
            return Command.BYE;
        case "list":
            if (command.length > 1 && !command[1].equals(""))
                throw new DukeWrongInputFormatException("Command list should not have any arguments");
            return Command.LIST;
        case "mark":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to mark");
            return Command.MARK;
        case "unmark":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to unmark");
            return Command.UNMARK;
        case "delete":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("Missing task number to delete");
            return Command.DELETE;
        case "todo":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of a todo cannot be empty.");
            return Command.TODO;
        case "deadline":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of a deadline cannot be empty.");
            return Command.DEADLINE;
        case "event":
            if (command.length <= 1 || command[1].equals(""))
                throw new DukeWrongInputFormatException("The description of an event cannot be empty.");
            return Command.EVENT;
        default:
            return Command.INVALID;
        }
    }

    private void loadTask() {
        try {
            Path path = Paths.get(".","data","duke.txt");
            if(Files.notExists(path)) {
                Files.createDirectories(Paths.get(".","data"));
                Files.createFile(path);
            }
            List<String> list = Files.readAllLines(path);
            for (String s: list) {
                if(s.equals("")) {
                    continue;
                }
                if(s.charAt(0) == 'T') {
                    String[] temp = s.split(" ", 3);
                    addTask(Command.TODO, temp[2], false);
                    if(temp[1].charAt(1) == '1') markAsDone(String.valueOf(tasks.size()), false);
                } else if (s.charAt(0) == 'D') {
                    String[] temp = s.split(" ", 3);
                    addTask(Command.DEADLINE, temp[2], false);
                    if(temp[1].charAt(1) == '1') markAsDone(String.valueOf(tasks.size()), false);
                } else if (s.charAt(0) == 'E'){
                    String[] temp = s.split(" ", 3);
                    addTask(Command.EVENT, temp[2], false);
                    if(temp[1].charAt(1) == '1') markAsDone(String.valueOf(tasks.size()), false);
                } else {
                    System.out.println(line + "\n\t" + "Wrong task format in file" + "\n" + line);
                }
            }
        } catch (IOException e) {
            System.out.println(line + "\n\t" + "Unable to read from file" + "\n" + line);
        } catch (DukeException e) {
            System.out.println(line + "\n\t" + e.getMessage() + "\n" + line);
        }
    }

    private void saveTask() {
        Path path = Paths.get("data","duke.txt");
        try {
            Files.write(path, tasks.saveToFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addTask(Command c, String task, boolean showMessage) throws DukeException {
        Task t;
        switch (c) {
        case TODO:
            t = new ToDo(task);
            break;
        case DEADLINE:
            String[] descriptionAndDate = task.split(" /by ", 2);
            if (descriptionAndDate.length <= 1) throw new DukeWrongInputFormatException("Missing deadline by date.");
            t = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
            break;
        case EVENT:
            String[] descriptionAndTime = task.split(" /at ", 2);
            if (descriptionAndTime.length <= 1) throw new DukeWrongInputFormatException("Missing event at date.");
            t = new Event(descriptionAndTime[0], descriptionAndTime[1]);
            break;
        default:
            t = null;
            break;
        }
        this.tasks.add(t);
        if(showMessage) {
            System.out.println(line + "\n\tGot it. I've added this task:\n\t\t" + t + "\n\t" +
                    "Now you have " + tasks.size() + " in the list.\n" + line);
        }

    }

    private void displayTasks() {
        System.out.println(line + "\n\tHere are the tasks in your list:");
        this.tasks.printTasks();
        System.out.println(line);
    }

    private void markAsDone(String in, boolean showMessage) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                Task temp = this.tasks.mark(index-1);
                if (showMessage) {
                    System.out.println(line + "\n\tNice! I've marked this task as done:\n\t\t" + temp.toString() + "\n" + line);
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing mark: mark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: mark <taskNum>");
        }
    }

    private void unmarkDone(String in) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                Task temp = this.tasks.unmark(index-1);
                System.out.println(line + "\n\tOK, I've marked this task as not done yet:\n\t\t" + temp.toString() + "\n" + line);
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing unmark: unmark <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid mark command: unmark <taskNum>");
        }
    }

    private void deleteTask(String in) throws DukeException {
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                Task deleted = tasks.remove(index - 1);
                System.out.println(line + "\n\tNoted. I've removed this task:\n\t\t" + deleted.toString()
                        + "\n\tNow you have " + tasks.size() + " tasks in the list.\n" + line);
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing delete: delete <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid delete command: delete <taskNum>");
        }
    }
}
