package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.CommandEnums;
import duke.enums.Type;
import duke.exceptions.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a handler to load a save file, or save to a file.
 */
public class Save {
    private static final String DUKE_PATHNAME = "src/main/java/Duke/data/duke.txt";
    private final ArrayList<String> inputsToBeProcessed = new ArrayList<>(100);
    private TaskList tasks = new TaskList(100);
    private int count;

    /**
     * Creates a new instance of Save that loads the history from a specific path.
     */
    public Save() {
        load();
    }

    /**
     * Loads the current content of the file in appropriate format for the TaskList tasks.
     */
    public void load() {
        readAndAdd();
        try {
            process(this.inputsToBeProcessed);
        } catch (InvalidCommandException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Loading from save file caused an error. Please remove and add your tasks again.");
            System.out.println("\t____________________________________________________________");
        }
    }

    /**
     * Saves the current content into the file with given path.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter(DUKE_PATHNAME);
            String textToWrite = simplify();
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t Hmm.. The file cannot be written to. Try changing permissions.");
            System.out.println("\t____________________________________________________________");
        }
    }

    /**
     * Reads from the given file and adds into a list to be processed later.
     */
    public void readAndAdd() {
        try {
            File file = new File(DUKE_PATHNAME);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(" / ");
                String type = tokens[0];
                String status = tokens[1];
                String name = tokens[2];

                Type typeEnum = Type.valueOf(type.toUpperCase());

                if (type.equals("D") || type.equals("E")) {
                    String date = tokens[3];
                    addToList(typeEnum, status, name, date);
                } else {
                    addToList(typeEnum, status, name);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t____________________________________________________________");
            System.out.println("\t☹ Oops! The path you provided does not exist!");
            System.out.println("\t☹ I have created the file for you!");
            System.out.println("\t____________________________________________________________");
            File fileDir = new File("src/main/java/Duke/data");
            fileDir.mkdirs();
            File fileToCreate = new File(fileDir, "duke.txt");
            try {
                FileWriter createdFile = new FileWriter(fileToCreate);
            } catch (IOException e1) {
                System.out.println("File cannot be created");
            }
        }
    }

    /**
     * Adds a task to the list that is of type Todo.
     *
     * @param type   Type of command.
     * @param status Status of Task.
     * @param name   Name of Task.
     */
    public void addToList(Type type, String status, String name) {
        if (type == Type.T) {
            inputsToBeProcessed.add("todo " + name);
            this.count++;
            if (status.equals("1")) {
                inputsToBeProcessed.add("mark " + this.count);
            }
        }
    }

    /**
     * Adds a task to the list that has a date or time.
     *
     * @param type   Type of command.
     * @param status Status of Task.
     * @param name   Name of Task.
     * @param date   Date of Task.
     */
    public void addToList(Type type, String status, String name, String date) {
        switch (type) {
        case D:
            inputsToBeProcessed.add("deadline " + name + " /by " + date);
            this.count++;
            if (status.equals("1")) {
                inputsToBeProcessed.add("mark " + this.count);
            }
            break;
        case E:
            inputsToBeProcessed.add("event " + name + " /at " + date);
            this.count++;
            if (status.equals("1")) {
                inputsToBeProcessed.add("mark " + this.count);
            }
            break;
        default:
        }
    }

    /**
     * Processes the saved file and adds to the list of tasks.
     *
     * @param taskList ArrayList of Strings to be processed
     */
    public void process(ArrayList<String> taskList) throws InvalidCommandException {

        for (String s : taskList) {
            String[] tokens = s.split(" ");
            String command = tokens[0];

            CommandEnums inputCommandEnums = CommandEnums.valueOf(command.toUpperCase());

            int sizeOfInputArr = tokens.length;

            String name = "";
            for (int i = 1; i < sizeOfInputArr - 1; i++) {
                name = name.concat(tokens[i]);
                name = name.concat(" ");
            }
            name = name.concat(tokens[sizeOfInputArr - 1]); // to eliminate white space at the end

            switch (inputCommandEnums) {
            case MARK:
                String markStr = tokens[1];
                int taskNumMark = Integer.parseInt(markStr) - 1;
                this.tasks.getTask(taskNumMark).mark();
                break;
            case UNMARK:
                String unmarkStr = tokens[1];
                int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
                tasks.getTask(taskNumUnmark).unmark();
                break;
            case TODO:
                Todo todo = new Todo(name);
                tasks.add(todo);
                break;
            case EVENT:
                String[] tokensEvent = s.split("/at ");
                String time = tokensEvent[1];

                String[] tokensNameEvent = name.split("/");
                String eventName = tokensNameEvent[0];
                Event event = new Event(eventName, time);
                tasks.add(event);
                break;
            case DEADLINE:
                String[] tokensDeadline = s.split("/by ");
                String date = tokensDeadline[1];

                String[] tokensNameDeadline = name.split("/");
                String deadlineName = tokensNameDeadline[0];
                Deadline deadline = new Deadline(deadlineName, date);
                tasks.add(deadline);
                break;
            default:
                throw new InvalidCommandException("Incorrect format");
            }

        }
    }

    /**
     * Converts the format of the tasks in the list to appropriate save format.
     *
     * @return String representation of tasks in appropriate format
     */
    public String simplify() {
        String s = "";
        for (int i = 0; i < this.tasks.getCount(); i++) {
            Task task = this.tasks.getTask(i);
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                s = s + "T / " + (todo.getStatus().equals("[X]") ? "1 / " : "0 / ") + todo.getName();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                s = s + "E / " + (event.getStatus().equals("[X]") ? "1 / " : "0 / ") + event.getName() + "/ "
                        + event.getTime();
            } else {
                Deadline deadline = (Deadline) task;
                s = s + "D / " + (deadline.getStatus().equals("[X]") ? "1 / " : "0 / ") + deadline.getName()
                        + "/ " + deadline.getUnconvertedDate();
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Returns the list of saved tasks.
     *
     * @return List of saved tasks.
     */
    public TaskList taskList() {
        return this.tasks;
    }
}
