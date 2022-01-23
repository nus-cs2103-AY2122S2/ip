import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) {
        greet();
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<String> content = new ArrayList<>();
        try {
            content = retrieveSavedTasks(FILE_PATH, tasks);
        } catch (IOException e) {
            System.out.println("There are no saved tasks.");
        }
        run(tasks, content);
    }

    private static void run(ArrayList<Task> tasks, ArrayList<String> content) {
        String command = "";
        Scanner sc = new Scanner(System.in);
        boolean isRun = true;
        while (isRun) {
            command = sc.nextLine();
            String[] commandSplitBySpace = command.split(" ");
            try {
                new DukeException().validateInputs(command, commandSplitBySpace, tasks);
                Task t;
                String desc;
                String oldDetails;
                switch (commandSplitBySpace[0]) {
                case "bye":
                    isRun = false;
                    break;
                case "list":
                    list(tasks);
                    break;
                case "mark":
                    t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                    oldDetails = t.getSaveFormat();
                    t.markAsDone();
                    updateSavedTasks(FILE_PATH, oldDetails, t.getSaveFormat(), content);
                    printMsg("Good job! I've marked this task as done:\n" + t);
                    break;
                case "unmark":
                    t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                    oldDetails = t.getSaveFormat();
                    t.markAsNotDone();
                    updateSavedTasks(FILE_PATH, oldDetails, t.getSaveFormat(), content);
                    printMsg("Okay, I've marked this task as not done yet:\n" + t);
                    break;
                case "todo":
                    Todo todo = new Todo(command.substring(5));
                    addTask(tasks, todo);
                    updateSavedTasks(FILE_PATH, "", todo.getSaveFormat(), content);
                    break;
                case "deadline":
                    int indexOfBy = command.indexOf("/by");
                    desc = command.substring(9, indexOfBy - 1);
                    String by = command.substring(indexOfBy + 4);
                    Deadline d = new Deadline(desc, by);
                    addTask(tasks, d);
                    updateSavedTasks(FILE_PATH, "", d.getSaveFormat(), content);
                    break;
                case "event":
                    int indexOfAt = command.indexOf("/at");
                    desc = command.substring(6, indexOfAt - 1);
                    String at = command.substring(indexOfAt + 4);
                    Event e = new Event(desc, at);
                    addTask(tasks, e);
                    updateSavedTasks(FILE_PATH, "", e.getSaveFormat(), content);
                    break;
                case "delete":
                    t = tasks.get(Integer.parseInt(commandSplitBySpace[1]) - 1);
                    removeTask(tasks, t);
                    updateSavedTasks(FILE_PATH, t.getSaveFormat(), "", content);
                    break;
                default:
                    break;
                }
            } catch (DukeException e) {
                printMsg(e.getMessage());
            } catch (IOException e) {
                printMsg("Something went wrong with saving the task");
            }
        }
        printMsg("Okay, bye! Hope to see you again :)");
    }

    private static void greet() {
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
    }

    private static void list(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(Integer.toString(i + 1) + "." + tasks.get(i));
        }
        printMsg(sb.toString());
    }

    /**
     * Prints the message that is parsed into this method with dividers.
     *
     * @param msg a String containing the message to be printed.
     */
    private static void printMsg(String msg) {
        String divider = "---------------------------------------------------------";
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }

    /**
     * Adds new Task into ArrayList of Task and prints the message for adding this Task
     * @param tasks ArrayList of Task to contain the list of tasks added so far
     * @param t New Task to be added
     */
    private static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
        printMsg("Got it. I've added this task:\n  " + t + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Removes Task from the ArrayList of Task and prints the success message
     * @param tasks ArrayList of Task that contains the list of tasks added so far
     * @param t Task to be removed
     */
    private static void removeTask(ArrayList<Task> tasks, Task t) {
        tasks.remove(t);
        printMsg("Noted. I've removed this task:\n  " + t + "\n" + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private static ArrayList<String> retrieveSavedTasks(String filePath, ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> content = new ArrayList<>();
        while (s.hasNextLine()) {
            content.add(s.nextLine());
            String[] command = content.get(content.size() - 1).split(",");
            Task t;
            switch (command[0]) {
            case "T":
                t = new Todo(command[2]);
                break;
            case "D":
                t = new Deadline(command[2], command[3]);
                break;
            case "E":
                t = new Event(command[2], command[3]);
                break;
            default:
                t = new Task("placeholder task");
                break;
            }

            if (command[1].equals("1")) {
                t.markAsDone();
            }
            tasks.add(t);
        }
        return content;
    }

    private static void updateSavedTasks(String filePath, String oldDetails, String updatedDetails, ArrayList<String> content) throws IOException {
        FileWriter fw;
        if (oldDetails.isEmpty()) {
            content.add(updatedDetails);
            fw = new FileWriter(filePath, true);
            fw.write(updatedDetails);
        } else {
            fw = new FileWriter(filePath);
            StringBuilder sb = new StringBuilder();
            if (updatedDetails.isEmpty()) {
                int indexToRemoveAt = 0;
                for (int i = 0; i < content.size(); i++) {
                    if (!content.get(i).equals(oldDetails)) {
                        sb.append(content.get(i) + System.lineSeparator());
                    } else {
                        indexToRemoveAt = i;
                    }
                }
                content.remove(indexToRemoveAt);
            } else {
                for (int i = 0; i < content.size(); i++) {
                    if (content.get(i).equals(oldDetails)) {
                        content.set(i, updatedDetails);
                    }
                    sb.append(content.get(i) + System.lineSeparator());
                }
            }
            fw.write(sb.toString());
        }
        fw.close();
    }
}
