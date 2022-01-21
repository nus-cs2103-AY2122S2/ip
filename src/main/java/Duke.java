import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Duke {
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Duke.log(Constants.GREETINGS);
        Duke.setup();
        Duke.run();
        Duke.log(Constants.BYE);
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String[] input = sc.nextLine().strip().split(" ", 2);

                if (!Constants.tasks.contains(input[0])) {
                    throw new CommandNotFoundException();
                }

                if (input[0].equals("bye")) {
                    return;
                } else if (input[0].equals("list")) {
                    Duke.log(taskList.getTasks());
                } else {
                    if (input.length != 2) {
                        throw new InvalidArgumentException();
                    }

                    if (input[0].equals("mark") || input[0].equals("unmark")) {
                        int taskId = Integer.parseInt(input[1]);
                        Duke.log(taskList.mark(taskId, input[0]));
                    } else if (input[0].equals("todo")) {
                        String toDo = input[1].strip();
                        Duke.log(taskList.addTask(new ToDo(toDo)));
                    } else if (input[0].equals("event")) {
                        String[] eventDetails = input[1].strip().split(" /at ", 2);
                        Duke.log(taskList.addTask(new Event(eventDetails[0], eventDetails[1])));
                    } else if (input[0].equals("deadline")) {
                        String[] deadlineDetails = input[1].strip().split(" /by ", 2);
                        Duke.log(taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1])));
                    } else if (input[0].equals("delete")) {
                        int taskId = Integer.parseInt(input[1]);
                        Duke.log(taskList.remove(taskId));
                    }
                }
            } catch (Exception e) {
                Duke.log(e.getMessage());
            }
            Duke.update();
        }
    }

    public static void log(String args) {
        System.out.println(Constants.HORIZONTAL_LINE);
        System.out.println(args);
        System.out.println(Constants.HORIZONTAL_LINE);
    }

    public static void setup() {
        try {
            File directory = new File(Constants.DATA_DIRECTORY);
            if (!directory.exists()) {
                if (!directory.mkdirs()) {
                    throw new IOException("Cannot create new directory");
                }
            }
            File dataFile = new File(directory, Constants.FILE_NAME);

            if (!dataFile.createNewFile()) {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNext()) {
                    String[] line = sc.nextLine().strip().split(" <> ");
                    Task currentTask;
                    switch (line[0]) {
                        case "T":
                            currentTask = new ToDo(line[2], line[1].equals("1"));
                            break;
                        case "D":
                            currentTask = new Deadline(line[2], line[1].equals("1"), line[3]);
                            break;
                        default:
                            currentTask = new Event(line[2], line[1].equals("1"), line[3]);
                            break;
                    }
                    taskList.addTask(currentTask);
                }
                sc.close();
            }
        } catch (IOException e) {
            Duke.log("Cannot read data, please restart the application");
            e.printStackTrace();
        }

    }

    public static void update() {
        try {
            File directory = new File(Constants.DATA_DIRECTORY);
            File tempFile = new File(directory, Constants.TEMP_FILE_NAME);
            File originalFile = new File(directory, Constants.FILE_NAME);

            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            for (Task task : taskList.listTasks()) {
                pw.write(task.encode());
            }

            pw.close();

            if (!originalFile.delete()) {
                throw new IOException("Cannot update file");
            }

            if (!tempFile.renameTo(originalFile)) {
                throw new IOException("Cannot update file");
            }
        } catch (Exception e) {
            Duke.log("Failed to update the data");
            e.printStackTrace();
        }
    }
}
