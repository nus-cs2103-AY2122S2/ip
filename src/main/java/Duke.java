import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int totalTasks = 0;

    private static final String topLine = "    ――――――――――――――――――――――――――――――――――\n    ";
    private static final String bottomLine = "    ――――――――――――――――――――――――――――――――――\n";
    private static final String indent = "    ";

    private static final String filePath = "data/duke.txt";

    private static void loadTaskList(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskComponents = task.split(" [|] "); // [type, status, description, time(if any)]
            Task t;

            switch (taskComponents[0]) {
            case "T":
                t = new Todo(taskComponents[2]);
                break;
            case "D":
                t = new Deadline(taskComponents[2], taskComponents[3]);
                break;
            case "E":
                t = new Event(taskComponents[2], taskComponents[3]);
                break;
            default:
                t = new Task(taskComponents[2]);
            }
            tasks.add(t);
            totalTasks++;

            if (taskComponents[1].equals("1")) {
                t.markAsDone();
            }
        }
    }

    private static String listStatus(int totalTasks) {
        if (totalTasks > 1) {
            return "There are " + totalTasks + " tasks in the list now.\n";
        } else {
            return "There is " + totalTasks + " task in the list now.\n";
        }
    }

    private static void printList() {
        if (totalTasks == 0) {
            System.out.print(topLine + "There are no tasks in your list.\n");
        } else {
            System.out.print(topLine + "Here are the tasks in your list:\n");
            int index = 1;
            for (int n = 0; n < totalTasks; n++) {
                Task t = tasks.get(n);
                System.out.println(indent + index + "." + t.toString());
                index++;
            }
            System.out.print(bottomLine);
        }
    }

    private static void deleteTask(String[] inputArr) {
        int taskNum = Integer.parseInt(inputArr[1]);
        Task t = tasks.remove(taskNum - 1);
        totalTasks--;
        System.out.print(topLine + "Okay, I've deleted this task:\n  "
                + indent + t + "\n" + indent + listStatus(totalTasks) + bottomLine);
    }

    private static void markTask(Command command, String[] inputArr) {
        int taskNum = Integer.parseInt(inputArr[1]);
        Task t = tasks.get(taskNum - 1);

        if (command.equals(Command.MARK)) { // mark task as done
            System.out.print(topLine + "Nice! You've completed this task:\n  ");
            t.markAsDone();
        } else { // unmark task
            System.out.print(topLine + "Okay, I've marked this task as undone:\n  ");
            t.markAsUndone();
        }
        System.out.print(indent + t + "\n" + bottomLine);
    }

    private static void addTask(Command command, String input) throws DukeException {
        String descr;

        if (command.equals(Command.TODO)) {
            descr = input.substring(5);
            tasks.add(new Todo(descr));

        } else if (command.equals(Command.DEADLINE)) {
            descr = input.substring(9);
            String[] descrArr = descr.split(" /by ");
            if (descrArr.length == 1) {
                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                        + bottomLine);
            } else {
                tasks.add(new Deadline(descrArr[0], descrArr[1]));
            }

        } else {
            descr = input.substring(6);
            String[] descrArr = descr.split(" /at ");
            if (descrArr.length == 1) {
                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                        + bottomLine);
            } else {
                tasks.add(new Event(descrArr[0], descrArr[1]));
            }
        }
        totalTasks++;
        System.out.print(topLine + "Got it! I've added this task:\n  "
                + indent + tasks.get(totalTasks - 1).toString() + "\n"
                + indent + listStatus(totalTasks) + bottomLine);
    }

    private static String getListToSave() {
        String list = "";
        for (Task t: tasks) {
            list = list + t.getType() + " | " + (t.getIsDone() ? "1" : "0") + " | " + t.getDescription() + "\n";
        }
        return list;
    }

    private static void saveToHardDisk(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.print(topLine + "Unable to save changes to hard disk :(" + bottomLine);
        }
    }

    public static void main(String[] args) throws IOException {
        String logo = "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String intro = "    Hello! I'm Duke!\n"
                + "    What can I do for you?\n";

        // Print introduction
        System.out.print(logo + "\n" + intro + bottomLine);

        // Load task list saved in hard disk, else create file to save task list
        File file = new File(filePath);
        if (file.exists()) {
            loadTaskList(file);
        } else { // create folder and file
            File folder = new File("data");
            if (folder.mkdir()) {
                boolean isCreated = file.createNewFile();
            }
        }

        // Get user input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while (!input.equals("bye")) { // Program only ends when user enters 'bye' command

            try {
                String[] inputArr = input.split(" ");
                String c = inputArr[0].toUpperCase();
                Command command = Command.valueOf(c);

                switch (command) {
                case LIST:
                    printList();
                    break;
                case MARK:
                    // Fallthrough
                case UNMARK:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine
                                + "Sorry, I don't know which task you would like to mark :(\n"
                                + bottomLine);
                    } else {
                        markTask(command, inputArr);
                    }
                    break;
                case DELETE:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine
                                + "Sorry, I don't know which task you would like to delete :(\n"
                                + bottomLine);
                    } else {
                        deleteTask(inputArr);
                    }
                    break;
                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine + "Oops, the task needs a description!\n" + bottomLine);
                    } else {
                        addTask(command, input);
                    }
                    break;
                }
            } catch (DukeException e) {
                System.out.print(e.message);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(topLine + "Invalid task number. " + listStatus(totalTasks) + bottomLine);
            } catch (IllegalArgumentException e) {
                System.out.print(topLine + "Oh no! I don't understand what that means...\n" + bottomLine);
            } finally {
                // Save changes to hard disk
                saveToHardDisk(filePath, getListToSave());

                // Get next input from user
                input = br.readLine();
            }
        }

        // Print closing statement
        System.out.print(topLine + "Bye! Hope to see you again soon!" + "\n" + bottomLine);
    }
}


