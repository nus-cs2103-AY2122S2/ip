package wonka.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import wonka.enums.Type;
import wonka.exceptions.InvalidCommandException;
import wonka.task.Deadline;
import wonka.task.Event;
import wonka.task.FixedDurationTask;
import wonka.task.Task;
import wonka.task.Todo;

/**
 * Represents a handler to load a save file, or save to a file.
 */
public class Save {
    private static final String WONKA_PATHNAME = "src/main/java/wonka/data/wonka.txt";
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
            System.out.println("\t Loading from save file caused an error. Please remove and add your tasks again.");
        }
    }

    /**
     * Saves the current content into the file with given path.
     */
    public void save() {
        try {
            FileWriter fw = new FileWriter(WONKA_PATHNAME);
            String textToWrite = simplify();
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            System.out.println("\t Hmm.. The file cannot be written to. Try changing permissions.");
        }
    }

    /**
     * Reads from the given file and adds into a list to be processed later.
     */
    public void readAndAdd() {
        try {
            File file = new File(WONKA_PATHNAME);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.split(" / ");
                String type = tokens[0];
                String status = tokens[1];
                String name = tokens[2];

                Type typeEnum = Type.valueOf(type.toUpperCase());

                if (type.equals("D") || type.equals("E") || type.equals("F")) {
                    String date = tokens[3];
                    addToList(typeEnum, status, name, date);
                } else {
                    addToList(typeEnum, status, name);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\t Save file not found, creating new save file.");
            File fileDir = new File("src/main/java/wonka/data");
            fileDir.mkdirs();
            File fileToCreate = new File(fileDir, "wonka.txt");
            try {
                fileToCreate.createNewFile();
            } catch (IOException e1) {
                System.out.println("\t File cannot be created");
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
     * Adds a task to the list that has either a date, time or duration.
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
            inputsToBeProcessed.add("fixed " + name + " /needs " + date);
            this.count++;
            if (status.equals("1")) {
                inputsToBeProcessed.add("mark " + this.count);
            }
        }
    }

    /**
     * Processes the saved file and adds to the list of tasks.
     *
     * @param taskList ArrayList of Strings to be processed
     */
    public void process(ArrayList<String> taskList) throws InvalidCommandException {
        taskList.forEach(x -> Parser.parse(x).execute(this.tasks, this));
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
                s = s + "E / " + (event.getStatus().equals("[X]") ? "1 / " : "0 / ") + event.getName() + " / "
                        + event.getTime();
            } else if (task instanceof FixedDurationTask) {
                FixedDurationTask fixed = (FixedDurationTask) task;
                s = s + "F / " + (fixed.getStatus().equals("[X]") ? "1 / " : "0 / ") + fixed.getName() + " / "
                        + fixed.getDuration();

            } else {
                Deadline deadline = (Deadline) task;
                s = s + "D / " + (deadline.getStatus().equals("[X]") ? "1 / " : "0 / ") + deadline.getName()
                        + " / " + deadline.getUnconvertedDate();
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
