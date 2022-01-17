import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that executes the main functionality
 */
public class Driver {
    private ArrayList<Task> tasks;
    private final String line = "\t____________________________________________________________";

    /*
     * Constructor that initializes the tasks arraylist
     */
    public Driver() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Executes the instructions given by user
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;
        while(!exit) {
            try {
                input = sc.nextLine();
                String[] commands = input.split(" ", 2);
                Command command = parseString(commands);
                switch (command) {
                    case BYE:
                        exit = true;
                        System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                        break;
                    case LIST:
                        displayTasks();
                        break;
                    case MARK:
                        markAsDone(commands[1]);
                        break;
                    case UNMARK:
                        unmarkDone(commands[1]);
                        break;
                    case DELETE:
                        deleteTask(commands[1]);
                        break;
                    case INVALID:
                        System.out.println(line + "\n\t" + commands[0] +" is not a valid command\n" + line);
                        break;
                    case EMPTY:
                        break;
                    default:
                        addTask(command, commands[1]);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(line + "\n\t" + e.getMessage() + "\n" + line);
            }
        }
    }

    public Command parseString(String[] command) throws DukeException{
            if (command[0].equals("")) {
                return Command.EMPTY;
            } else if (command[0].equals("bye")) {
                if(command.length > 1 && !command[1].equals("")) throw new DukeWrongInputFormatException("Command bye should not have any arguments");
                return Command.BYE;
            } else if (command[0].equals("list")) {
                if(command.length > 1 && !command[1].equals("")) throw new DukeWrongInputFormatException("Command list should not have any arguments");
                return Command.LIST;
            } else if (command[0].equals("mark")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("Missing task number to mark");
                return Command.MARK;
            } else if (command[0].equals("unmark")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("Missing task number to unmark");
                return Command.UNMARK;
            } else if(command[0].equals("delete")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("Missing task number to delete");
                return Command.DELETE;
            } else if(command[0].equals("todo")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("The description of a todo cannot be empty.");
                return Command.TODO;
            } else if(command[0].equals("deadline")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("The description of a deadline cannot be empty.");
                return Command.DEADLINE;
            } else if(command[0].equals("event")) {
                if(command.length <= 1 || command[1].equals("")) throw new DukeWrongInputFormatException("The description of an event cannot be empty.");
                return Command.EVENT;
            } else {
                return Command.INVALID;
            }
    }
    private void addTask(Command c, String task) throws DukeException {
        Task t;
        switch(c) {
            case TODO:
                t = new ToDo(task);
                break;
            case DEADLINE:
                String[] descriptionAndDate = task.split(" /by ", 2);
                if(descriptionAndDate.length <= 1) throw new DukeWrongInputFormatException("Missing deadline by date.");
                t = new Deadline(descriptionAndDate[0], descriptionAndDate[1]);
                break;
            case EVENT:
                String[] descriptionAndTime = task.split(" /at ", 2);
                if(descriptionAndTime.length <= 1) throw new DukeWrongInputFormatException("Missing event at date.");
                t = new Event(descriptionAndTime[0], descriptionAndTime[1]);
                break;
            default:
                t = null;
                break;
        }
        this.tasks.add(t);
        System.out.println(line + "\n\tGot it. I've added this task:\n\t\t" + t + "\n\t" +
                    "Now you have " + tasks.size() + " in the list.\n" + line);

    }

    private void displayTasks() {
        System.out.println(line + "\n\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + tasks.get(i).toString());
        }
        System.out.println(line);
    }

    private void markAsDone(String in) throws DukeException{
        try {
            int index = Integer.parseInt(in);
            if (index > this.tasks.size() || index < 1) {
                throw new DukeInvalidTaskNumberException("Task number: " + index + " does not exist");
            } else {
                tasks.get(index - 1).setDone(true);
                System.out.println(line + "\n\tNice! I've marked this task as done:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
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
                tasks.get(index - 1).setDone(false);
                System.out.println(line + "\n\tOK, I've marked this task as not done yet:\n\t\t" + tasks.get(index - 1).toString() + "\n" + line);
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
                        + "\n\tNow you have "+ tasks.size() +" tasks in the list.\n" + line);
            }
        } catch (NumberFormatException e) {
            throw new DukeWrongInputFormatException("Please input a valid Task number after typing delete: delete <taskNum>");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeWrongInputFormatException("Please input a valid delete command: delete <taskNum>");
        }
    }
}
