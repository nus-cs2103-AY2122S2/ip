import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Attributes
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> LIST = new ArrayList<>();

    /**
     * Prints out the greeting message
     */
    public static void greet() {
        String output = LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE;
        System.out.println(output);
    }

    /**
     * Adds a Todo task to the tasklist
     *
     * @param task  the task to be added to the list
     */
    public static void addTodo(String task) {
        LIST.add(new Todo(task));
        String output = LINE + "\n Got it. I've added this task:\n" + "   " + LIST.get(LIST.size() - 1).toString()
                + String.format("\n Now you have %d tasks in the list.\n", LIST.size()) + LINE;
        System.out.println(output);
    }

    /**
     * Adds a Deadline task to the tasklist
     *
     * @param task  the task to be added to the list
     * @param deadline  the deadline of the task
     */
    public static void addDeadline(String task, String deadline) {
        LIST.add(new Deadline(task, deadline));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Adds an Event task to the tasklist
     *
     * @param task  the task to be added to the list
     * @param deadline  the deadline of the task
     */
    public static void addEvent(String task, String deadline) {
        LIST.add(new Event(task, deadline));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Prints out a list of all tasks in the tasklist
     */
    public static void list() {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= LIST.size(); i++) {
            System.out.printf(" %d.%s\n", i, LIST.get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Marks the indicated task as done
     *
     * @param taskNum   the task number which is to be marked as done
     */
    public static void mark(int taskNum) {
        System.out.println(LINE + "\n Nice! I've marked this task as done:");
        LIST.get(taskNum - 1).setDone();
        System.out.println("   " + LIST.get(taskNum - 1).toString());
        System.out.println(LINE);
    }

    /**
     * Marks the indicated task as not done
     *
     * @param taskNum   the task number which is to be marked as not done
     */
    public static void unmark(int taskNum) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        LIST.get(taskNum - 1).setNotDone();
        System.out.println("   " + LIST.get(taskNum - 1).toString());
        System.out.println(LINE);
    }

    /**
     * Delete the indicated task from the tasklist
     *
     * @param taskNum   the task number which is to be deleted
     */
    public static void delete(int taskNum) {
        System.out.println(LINE + "\n Noted. I've removed this task:");
        Task removed = LIST.get(taskNum - 1);
        LIST.remove(taskNum - 1);
        System.out.println("   " + removed.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Throw error depending on what kind of error it is
     *
     * @param type  The type of error to be thrown
     */
    public static void throwError(String type) {
        System.out.println(LINE);
        DukeException error;
        if (type.equals("")) {
            error = new DukeException();
        } else {
            error = new DukeException(type);
        }
        System.out.println(error);
        System.out.println(LINE);
    }

    /**
     * Print out a goodbye line
     */
    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void createDirectory() {
        String home = System.getProperty("user.home");
        java.nio.file.Path folderPath = java.nio.file.Paths.get(home, "data");
        File folder = new File(String.valueOf(folderPath));
        boolean folderCreated = folder.mkdir();
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "duke.txt");
        try {
            File file = new File(String.valueOf(filePath));
            boolean fileCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occured while creating the file data.txt");
        }
    }

    public static void updateFile(String filePath) throws IOException {
        FileWriter fWriter = new FileWriter(String.valueOf(filePath));
        BufferedWriter writer = new BufferedWriter(fWriter);
        StringBuilder output = new StringBuilder();
        for (Task task : LIST) {
            output.append(task.getType()).append(" | ").append(task.getStatusIcon())
                    .append(" | ").append(task.getDescription());
            if (!task.getType().equals("T")) {
                output.append(" | ").append(task.getBy());
            }
            output.append("\n");
        }
        writer.write(output.toString());
        writer.close();
    }

    public static void readFile(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] strArr;
            Task task = new Task("");
            strArr = line.split(" \\| ");
            switch (strArr[0]) {
                case "T":
                    task = new Todo(strArr[2]);
                    if (strArr[1].equals("1")) {
                        task.setDone();
                    }
                    break;
                case "D":
                    task = new Deadline(strArr[2], strArr[3]);
                    if (strArr[1].equals("X")) {
                        task.setDone();
                    }
                    break;
                case "E":
                    task = new Event(strArr[2], strArr[3]);
                    if (strArr[1].equals("X")) {
                        task.setDone();
                    }
                    break;
            }
            LIST.add(task);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        createDirectory();
        String home = System.getProperty("user.home");
        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "duke.txt");
        FileReader fReader = new FileReader(String.valueOf(filePath));
        BufferedReader reader = new BufferedReader(fReader);
        readFile(reader);
        reader.close();
        greet();
        whileLoop:
        while (sc.hasNextLine()) {
            String usrInput = sc.next();
            String task;
            switch (usrInput) {
                case "bye":
                    exit();
                    sc.close();
                    break whileLoop;
                case "list":
                    list();
                    break;
                case "mark": {
                    int taskNum = Integer.parseInt(sc.next());
                    mark(taskNum);
                    updateFile(String.valueOf(filePath));
                    break;
                }
                case "unmark": {
                    int taskNum = Integer.parseInt(sc.next());
                    unmark(taskNum);
                    updateFile(String.valueOf(filePath));
                    break;
                }
                case "delete": {
                    int taskNum = Integer.parseInt(sc.next());
                    delete(taskNum);
                    updateFile(String.valueOf(filePath));
                    break;
                }
                case "todo": {
                    usrInput = sc.nextLine();
                    if (usrInput.equals("")) {
                        throwError("todo");
                        break;
                    }
                    addTodo(usrInput.substring(1));
                    updateFile(String.valueOf(filePath));
                    break;
                }
                case "deadline": {
                    task = sc.next();
                    while (sc.hasNext()) {
                        String currStr = sc.next();
                        if (currStr.equals("/by")) {
                            String time = sc.nextLine();
                            addDeadline(task, time);
                            updateFile(String.valueOf(filePath));
                            break;
                        } else {
                            task += " " + currStr;
                        }
                    }
                    break;
                }
                case "event": {
                    task = sc.next();
                    while (sc.hasNext()) {
                        String currStr = sc.next();
                        if (currStr.equals("/at")) {
                            String time = sc.nextLine();
                            addEvent(task, time);
                            updateFile(String.valueOf(filePath));
                            break;
                        } else {
                            task += " " + currStr;
                        }
                    }
                    break;
                }
                default:
                    throwError("");
                    break;
            }
        }
        updateFile(String.valueOf(filePath));
    }
}
