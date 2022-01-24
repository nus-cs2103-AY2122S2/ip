import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Chi {
    /**
     * Stores the messages sent by the user.
     */
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;
    // delete these
    private List<Task> messages;
    private static final String DATA_FOLDER = "data/";
    private static final String DATA_FILE = "data/tasks.txt";
    private File dataFile;

    public Chi(String filepath) {
        this.storage = new Storage(filepath);
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (ChiException e) {
            // print error for file not found
            ui.printErrorMsg(e.getMessage());
            this.taskList = new TaskList();
        } catch (IOException e) {
            // print error for IO problems
            ui.printErrorMsg("There's something wrong with the IO nyan!");
        }
        // Delete this
        this.messages = new ArrayList<>();
        // Make sure data file exists in directory
    }

    public void run() {
        ui.printWelcome();
        ui.requestInput(this.taskList, this.storage, this.parser);
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        Chi myBot = new Chi("data/tasks.txt");
        myBot.run();
    }

    // Delete this
    public void load() throws IOException, ChiException {
        try {
            this.dataFile = new File(DATA_FILE);
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String task = s.nextLine();
                Task t = manageFileData(task);
                this.addTask(t);
            }
        } catch (FileNotFoundException e) {
            File dataFolder = new File(DATA_FOLDER);
            if (!dataFolder.isDirectory()) {
                dataFolder.mkdir();
            }
            File newDataFile = new File(DATA_FILE);
            newDataFile.createNewFile();
            throw new ChiException("File not found, restart program nyan!");
        }

    }

    // Delete this
    private Task manageFileData(String task) {
        // We assume the task stored inside is correct
        String[] splitTask = task.split("\\|");
        for (int i = 0; i < splitTask.length;i++) {
            splitTask[i] = splitTask[i].trim();
        }
        if (splitTask[0].equals("T")) {
            if (splitTask[1].equals("1")) {
                return new Todo(splitTask[2], true);
            }
            return new Todo(splitTask[2], false);
        } else if (splitTask[0].equals("D")) {
            return new Deadline(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
        } else {
            return new Event(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")),
                    LocalTime.parse(splitTask[5], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
            }
    }

    // Delete this
    public void store() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE);
        for (Task t: messages) {
            String des = t.writeToFile() + "\n";
            fw.write(des);
        }
        fw.close();
    }
    // Delete this
    private void addTask(Task t) {
        this.messages.add(t);
    }

    // Delete this
    private void deleteTask(Task t) {
        this.messages.remove(t);
    }
    /**
     * Displays a message based on the user input.
     *
     * @param msg The message inputted by the user.
     * @throws ChiException if messages are invalid.
     */
    public void respondToMsg(String msg) throws ChiException {
        // Obtain 1st word
        String[] command = msg.trim().split(" ");
        if (command.length == 1) {
            if (command[0].equals("list")) {
                System.out.println("Here are your list items nyan~:");
                int i = 1;
                for (Task t : messages) {
                    // Print task and its status
                    System.out.printf("%d. %s\n", i, t);
                    ++i;
                }
                return;
            }
            // Unknown message, or command lacks task
            throw new ChiException(msg.trim().toLowerCase());
        } else {
            // Check for keywords
            switch (command[0].toLowerCase()) {
                case "mark":
                    // Retrieve the task from the list
                    Task doneTask = messages.get(Integer.parseInt(command[1]) - 1);
                    // Mark as done
                    doneTask.markAsDone();
                    // Print completion message
                    System.out.printf("Great job nyan~!\n%s\n", doneTask);
                    break;
                case "unmark":
                    Task doneTask1 = messages.get(Integer.parseInt(command[1]) - 1);
                    doneTask1.markAsUndone();
                    // Print undo message
                    System.out.printf("Let's get it done next time nyan~!\n%s\n", doneTask1);
                    break;
                case "todo":
                    // Obtain the ToDo
                    Task newTask = new Todo(msg.substring(4).trim(), false);
                    // Add task to list
                    this.addTask(newTask);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask, messages.size());

                    break;
                case "deadline":
                    // Separate task and deadline
                    String[] content = msg.substring(8).split("/by");
                    // Create new Deadline object
                    if (content[0].trim().equals("")) {
                        throw new ChiException("deadline");
                    }
                    // Create new Deadline object
                    LocalDate d = LocalDate.parse(content[1].trim().split(" ")[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime t;
                    Task newTask1;
                    if (content[1].trim().split(" ").length == 2) {
                        t = LocalTime.parse(content[1].trim().split(" ")[1].trim(),
                                DateTimeFormatter.ofPattern("HH:mm"));
                        newTask1 = new Deadline(content[0].trim(), d, t, false);
                    } else {
                        newTask1 = new Deadline(content[0].trim(), d, false);
                    }
                    this.addTask(newTask1);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask1, messages.size());

                    break;
                case "event":
                    // Separate task and timing
                    String[] content1 = msg.substring(5).split("/at");
                    if (content1[0].trim().equals("")) {
                        throw new ChiException("event");
                    }
                    // Create new Event object
                    LocalDate ds = LocalDate.parse(content1[1].trim().split(" ")[0].trim(),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime t1;
                    LocalTime t2;
                    Task newTask2;
                    if (content1[1].trim().split(" ").length == 2) {
                        t1 = LocalTime.parse(content1[1].trim().split(" ")[1].trim().split("-")[0],
                                DateTimeFormatter.ofPattern("HH:mm"));
                        t2 = LocalTime.parse(content1[1].trim().split(" ")[1].trim().split("-")[1],
                                DateTimeFormatter.ofPattern("HH:mm"));
                        newTask2 = new Event(content1[0].trim(), ds, t1, t2, false);
                    } else {
                        newTask2 = new Event(content1[0].trim(), ds, false);
                    }
                    this.addTask(newTask2);
                    System.out.printf("Ok! Chi-san has added:\n%s\nYou have %d tasks nyan~!\n",
                            newTask2, messages.size());
                    break;
                case "delete":
                    Task toDelete = messages.get(Integer.parseInt(command[1]) - 1);
                    this.deleteTask(toDelete);
                    System.out.printf("Chi-san has removed task:\n %s\nYou now have %d tasks nyan~!\n",
                            toDelete, messages.size());
                    break;
                default:
                    // Some message which does not start with a keyword
                    throw new ChiException(msg);
            }
        }
    }
}
