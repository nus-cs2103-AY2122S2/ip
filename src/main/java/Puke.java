import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Puke {
    public static final String LINE = "____________________________________________________________\n";
    public static final String DATA_DIR_PATH = "./../../../data";
    public static final String DATA_FILE_PATH = "./../../../data/puke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | |_| | | | | |/ / _ \\\n"
                + "  | |__/| |_| |   <  __/\n"
                + "  |_|    \\__,_|_|\\_\\___|\n";

        System.out.print(LINE + logo + "Hello! I'm Puke, your friendly neighbourhood chatbot!\n"
                + "What do you want to do?\n" + LINE);

        ArrayList<Task> tasks = new ArrayList<>();
        File dir = new File(DATA_DIR_PATH);
        File f = new File(DATA_FILE_PATH);

        try {
            dir.mkdir();
            f.createNewFile();
            Scanner fileSc = new Scanner(f);
            populateTasks(tasks, fileSc);
        } catch (IOException e) {
            System.out.println("Data file cannot be created!");
            return;
        }

        while (true) {
            System.out.print("> ");

            String[] input = sc.nextLine().split(" ", 2);
            String command = input[0]; // get the first word of the input

            if (command.equals("bye")) {
                break;
            }

            String argument = null;
            if (input.length > 1) {
                argument = input[1]; // remaining input (if any)
            }

            try {
                switch (command) {
                case "list":
                    if (argument == null) {
                        listTasks(tasks);
                    } else {
                        System.out.println("I don't need any argument for list..");
                    }
                    break;
                case "mark":
                    markTask(tasks, Integer.parseInt(argument));
                    break;
                case "unmark":
                    unmarkTask(tasks, Integer.parseInt(argument));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    addTask(tasks, command, argument);
                    break;
                case "delete":
                    deleteTask(tasks, Integer.parseInt(argument));
                    break;
                default:
                    System.out.println("Are you sure you're making sense?");
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("I'll need a valid task number for it..");
            }

            System.out.print(LINE);
        }

        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            saveTasks(tasks, fw);
        } catch (IOException e) {
            System.out.println("Tasks cannot be saved to file!");
            return;
        }

        System.out.print("Alright bye. Come back to Puke anytime!\n" + LINE);
    }

    public static void listTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no task right now!");
            return;
        }

        System.out.println("Here are the tasks you have:");
        int i = 1;
        for (Task t : tasks) {
            System.out.println(i + "." + t.toString());
            i++;
        }
    }

    public static void markTask(ArrayList<Task> tasks, int taskNo) throws DukeException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeException(String.format("%d is not a valid task number!", taskNo));
        }
        Task t = tasks.get(taskNo - 1);
        if (t.isDone()) {
            throw new DukeException(String.format("Task #%d is already marked as done!", taskNo));
        }
        t.mark();
        System.out.println("Kudos! I've marked this task as done:\n  " + t);
    }

    public static void unmarkTask(ArrayList<Task> tasks, int taskNo) throws DukeException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeException(String.format("%d is not a valid task number!", taskNo));
        }
        Task t = tasks.get(taskNo - 1);
        if (!t.isDone()) {
            throw new DukeException(String.format("Task #%d is not yet marked as done!", taskNo));
        }
        t.unmark();
        System.out.println("Alright, I've marked this task as not done yet:\n  " + t);
    }

    public static void addTask(ArrayList<Task> tasks, String type, String args) throws DukeException {
        if (args == null) {
            throw new DukeException("I'll need a description for the task..");
        }

        Task t;
        if (type.equals("todo")) {
            t = new Todo(args.trim());
        } else {
            String[] taskDetail = args.split("/");

            if (taskDetail.length < 2) {
                throw new DukeException("I'll need a date/time for this task..");
            }

            String dateTimeStr = taskDetail[1].split(" ", 2)[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            try {
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                if (dateTime.isBefore(LocalDateTime.now())) {
                    throw new DukeException("Why are you trying to create a task in the past?");
                }

                if (type.equals("deadline")) {
                    t = new Deadline(taskDetail[0], dateTime);
                } else {
                    t = new Event(taskDetail[0], dateTime);
                }
            } catch (DateTimeParseException e) {
                throw new DukeException("I'll need a valid date/time in the format yyyy-mm-dd hh:mm");
            }
        }

        tasks.add(t);
        System.out.println("Got it. I've added this task:\n  " + t
                + "\nNow you have " + Task.getNoOfTasks()
                + (Task.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.");
    }

    public static void deleteTask(ArrayList<Task> tasks, int taskNo) throws DukeException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new DukeException(String.format("%d is not a valid task number!", taskNo));
        }

        String taskInfo = tasks.get(taskNo - 1).toString();
        tasks.remove(taskNo - 1);
        Task.removeTask();
        System.out.println("There's no going back now. I've removed this task:\n  " + taskInfo
                + "\nNow you have " + Task.getNoOfTasks()
                + (Task.getNoOfTasks() <= 1 ? " task" : " tasks") + " in the list.");
    }

    public static void populateTasks(ArrayList<Task> tasks, Scanner s) {
        while (s.hasNext()) {
            String[] taskInfo = s.nextLine().split("@@");
            Task t = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            switch (taskInfo[0]) {
            case "T":
                t = new Todo(taskInfo[2]);
                break;
            case "D":
                t = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            case "E":
                t = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3], formatter));
                break;
            }

            if (taskInfo[1].equals("1")) {
                t.mark();
            }

            tasks.add(t);
        }
    }

    public static void saveTasks(ArrayList<Task> tasks, FileWriter fw) throws IOException {
        String toWrite = "";
        for (int i = 0; i < tasks.size(); i++) {
            toWrite += tasks.get(i).toSaveString();
            if (i != tasks.size() - 1) {
                toWrite += "\n";
            }
        }
        fw.write(toWrite);
        fw.close();
    }
}
