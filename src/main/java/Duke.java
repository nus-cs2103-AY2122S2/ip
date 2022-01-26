import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public static String formatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static void displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(formatMsg(welcomeMsg));
    }

    /**
     * Returns a styled exit message on exit
     */
    public static void displayExitMsg() {
        System.out.println(formatMsg("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Runs Level 1 version of the app, Greet/Echo/Exit
     */
    public static void levelOne() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                displayExitMsg();
                return;
            } else {
                System.out.println(formatMsg(command));
            }
        }
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of string data
     * @return     data as a list in a single string
     */
    public static String renderList(ArrayList<String> data) {
        String renderStr = "";

        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }

        return renderStr;
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of Task data
     * @return     data as a list in a single string
     */
    public static String renderTaskList(ArrayList<Task> data) {
        String renderStr = "";

        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }

        return renderStr;
    }

    /**
     * Runs Level 2 version of the app, Mark as done
     */
    public static void levelTwo() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                displayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(formatMsg(renderList(data)));
            } else {
                data.add(input);
                String output = " added: " + input + "\n";
                System.out.println(formatMsg(output));
            }
        }
    }

    /**
     * Display message after marking task as complete
     *
     * @param task Task to be marked as complete
     */
    public static void displayMarkMsg(String task) {
        String markMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        System.out.println(formatMsg(markMsg));
    }

    /**
     * Display message after marking task as incomplete
     *
     * @param task Task to be marked as incomplete
     */
    public static void displayUnmarkMsg(String task) {
        String unmarkMsg = "Nice! I've marked this task as NOT done:\n"
                + task + "\n";
        System.out.println(formatMsg(unmarkMsg));
    }

    /**
     * Runs Level 3 version of the app, Mark as done
     */
    public static void levelThree() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                displayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(formatMsg(renderTaskList(data)));
                continue;
            }

            if (input.contains("mark")) {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);

                if (itemIndex <= data.size()) {
                    Task selectedTask = data.get(itemIndex - 1);
                    if (input.contains("unmark")) {
                        selectedTask.markAsIncomplete();
                        displayUnmarkMsg(selectedTask.toString());
                    } else {
                        selectedTask.markAsComplete();
                        displayMarkMsg(selectedTask.toString());
                    }
                }
                continue;
            }

            Task newTask = new Task(input);
            data.add(newTask);
            String output = " added: " + input + "\n";
            System.out.println(formatMsg(output));
        }
    }

    /**
     * Display listed message after listing task
     *
     * @param task Task that was listed
     * @param size Number of tasks remaining in list
     */
    public static void displayListedText(Task task, int size) {
        String output = " Got it. I've added this task:\n   "
                + task.toString()
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    /**
     * Get timing from event task
     *
     * @param text Text input from user
     * @return Timing for event task
     */
    public static String getEventTiming(String text) {
        return text.split("/at")[1].trim();
    }

    /**
     * Get timing from deadline task
     *
     * @param text Text input from user
     * @return Timing for deadline task
     */
    public static String getDeadlineTiming(String text) {
        return text.split("/by")[1].trim();
    }

    /**
     * Runs Level 4 version of the app, Mark as done
     */
    public static void levelFour() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                displayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(formatMsg(renderTaskList(data)));
                continue;
            }

            if (input.contains("mark")) {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);

                if (itemIndex <= data.size()) {
                    Task selectedTask = data.get(itemIndex - 1);
                    if (input.contains("unmark")) {
                        selectedTask.markAsIncomplete();
                        displayUnmarkMsg(selectedTask.toString());
                    } else {
                        selectedTask.markAsComplete();
                        displayMarkMsg(selectedTask.toString());
                    }
                }
                continue;
            }

            if (input.split(" ")[0].equals("deadline")) {
                String timing = getDeadlineTiming(input);
                String taskName = input.replaceAll("deadline", "").split("/by")[0];
                Task newTask = new Task(taskName, timing);
                newTask.setDeadline();
                data.add(newTask);
                displayListedText(newTask, data.size());
            }

            if (input.split(" ")[0].equals("event")) {
                String timing = getEventTiming(input);
                String taskName = input.replaceAll("event", "").split("/at")[0];
                Task newTask = new Task(taskName, timing);
                newTask.setEvent();
                data.add(newTask);
                displayListedText(newTask, data.size());
            }

            if (input.split(" ")[0].equals("todo")) {
                Task newTask = new Task(input.replaceAll("todo", ""));
                newTask.setTodo();
                data.add(newTask);
                displayListedText(newTask, data.size());
            }

            continue;
        }
    }

    /**
     * Display deletion message after deleting task
     *
     * @param deletedTask Task that was deleted
     * @param size Number of tasks remaining in list
     */
    public static void displayDeletedMessage(Task deletedTask, int size) {
        String output = " Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + "\n Now you have "
                + size
                + " tasks in the list.\n";
        System.out.println(formatMsg(output));
    }

    /**
     * Load list data from "data/list.txt" if exists, otherwise
     * create empty "data/list.txt" file and return empty arraylist
     *
     * @return Arraylist of Tasks
     * @throws FileNotFoundException
     */
    public static ArrayList<Task> loadListFromDisk(String defaultPath) throws FileNotFoundException {
        File defaultFile = new File(defaultPath);

        // if list.txt does not exist, create empty list.txt file
        // and return an empty ArrayList
        if (!defaultFile.exists()) {
            if (!defaultFile.getParentFile().exists()) {
                String output = "Data directory does not exist, creating list file now at: "
                        + defaultPath
                        + "\n";
                System.out.println(formatMsg(output));
                if (!defaultFile.getParentFile().mkdirs()) {
                    System.out.println("Error occurred when trying to create Data directory :(");
                }
            }

            try {
                defaultFile.createNewFile();
            } catch (IOException err) {
                System.out.println("An IO error occurred while trying to create list.txt file :(");
                err.printStackTrace();
            }

            return new ArrayList<>();

        } else {
            // if list.txt file exists, load from disk and return
            // ArrayList of Tasks from list.txt
            Scanner f = new Scanner(defaultFile);
            ArrayList<Task> data = new ArrayList<>();

            while (f.hasNext()) {
                String[] taskLineSplit = f.nextLine().split(",");
                Boolean completed = Integer.parseInt(taskLineSplit[1]) == 1 ? true : false;
                String taskName = taskLineSplit[2];
                Task storedTask;

                // get event type + timing
                if (taskLineSplit[0].equals("T")) {
                    storedTask = new Task(taskName);
                    storedTask.setTodo();
                } else {
                    storedTask = new Task(taskName, taskLineSplit[3]);
                    if (taskLineSplit[0].equals("E")) {
                        storedTask.setEvent();
                    } else if (taskLineSplit[0].equals("D")) {
                        storedTask.setDeadline();
                    }
                }

                // get completion status
                if (completed)
                    storedTask.markAsComplete();
                else
                    storedTask.markAsIncomplete();

                data.add(storedTask);
            }

            return data;
        }
    }

    /**
     * Overwrite data in "data/list.txt" with current list data
     *
     * @param data Arraylist of Tasks data
     * @param defaultPath Default path "data/list.txt"
     * @throws IOException Throws FileNotFoundException error if writing to non-existent file
     */
    public static void saveListOnDisk(ArrayList<Task> data, String defaultPath) throws IOException {
        String dataText = "";

        for (Task task: data) {
            String taskText = "";
            // set event type
            if (task.eventType.equals(Task.Type.EVENT))
                taskText += "E,";
            else if (task.eventType.equals(Task.Type.TODO))
                taskText += "T,";
            else if (task.eventType.equals(Task.Type.DEADLINE))
                taskText += "D,";

            // set task completion status
            if (task.getIsDone())
                taskText += "1,";
            else
                taskText += "0,";

            // set task name & timing
            if (task.eventType.equals(Task.Type.TODO)) {
                taskText += task.getDescription().trim();
            } else {
                taskText += task.getDescription().trim() + ",";
                taskText += task.getTime();
            }

            taskText += "\n";
            dataText += taskText;
        }

        FileWriter fw = new FileWriter(defaultPath, false);
        fw.write(dataText);
        fw.close();
    }

    /**
     * Runs Level 5, 6 & 7 version of the app, Exception handling,
     * Hard disk storage
     *
     * @throws DukeException for checked errors handled by Duke app
     */
    public static void levelFinal() throws DukeException {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();
        String defaultPath = "data/list.txt";

        try {
            data = loadListFromDisk(defaultPath);
        } catch (FileNotFoundException err) {
            System.out.println("File not found");
        }

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine().trim();
            String command = input.split(" ")[0];

            if (command.equals("bye")) {
                try {
                    saveListOnDisk(data, defaultPath);
                } catch (IOException err) {
                    System.out.println("Error occurred while trying to save list data to disk");
                    err.printStackTrace();
                }

                displayExitMsg();
                return;

            } else if (command.equals("list")) {
                System.out.println(formatMsg(renderTaskList(data)));
                continue;

            } else if (command.contains("mark")) {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);

                if (itemIndex <= data.size()) {
                    Task selectedTask = data.get(itemIndex - 1);
                    if (command.contains("unmark")) {
                        selectedTask.markAsIncomplete();
                        displayUnmarkMsg(selectedTask.toString());
                    } else {
                        selectedTask.markAsComplete();
                        displayMarkMsg(selectedTask.toString());
                    }
                }
                continue;

            } else if (command.equals("deadline")) {
                String timing = getDeadlineTiming(input);
                String taskName = input.replaceAll("deadline", "").split("/by")[0];
                Task newTask = new Task(taskName, timing);
                newTask.setDeadline();
                data.add(newTask);
                displayListedText(newTask, data.size());

            } else if (command.equals("event")) {
                String timing = getEventTiming(input);
                String taskName = input.replaceAll("event", "").split("/at")[0];
                Task newTask = new Task(taskName, timing);
                newTask.setEvent();
                data.add(newTask);
                displayListedText(newTask, data.size());

            } else if (command.equals("todo")) {
                String taskName = input.replaceAll("todo", "");

                if (input.split(" ").length <= 1) {
                    throw new TodoEmptyException();
                }

                Task newTask = new Task(taskName);
                newTask.setTodo();
                data.add(newTask);
                displayListedText(newTask, data.size());

            } else if (command.equals("delete")) {
                if (input.split("").length <= 1) {
                    throw new DeleteEmptyException();
                }

                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                try {
                    Task deletedTask = data.remove(taskIndex - 1);
                    displayDeletedMessage(deletedTask, data.size());
                } catch (IndexOutOfBoundsException err) {
                    throw new DukeException("task index provided is invalid :(");
                }

            } else {
                throw new UnknownCommandException();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        levelFinal();
    }
}
