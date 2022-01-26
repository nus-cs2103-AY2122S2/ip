import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {

    public static List<Task> taskList;

    private static void run() {
        Scanner sc = new Scanner(System.in);
        boolean exitFlag = false;

        // TODO: error handling
        while(!exitFlag) {
            try {
                String[] input = sc.nextLine().split(" ", 2);
                Command inputCmd = Command.getByName(input[0]);     //input[1] = params
                switch (inputCmd) {
                case EXIT:
                    exitFlag = inputCmd.exitResponse();
                    break;
                case LIST:
                    inputCmd.listResponse(taskList);
                    break;
                case MARK:
                case UNMARK:
                    if (input.length == 2) {
                        inputCmd.toggleMarkResponse(taskList, input[0], input[1]);
                        break;
                    } else {
                        throw new DukeException("Invalid number of inputs!");
                    }
                case TODO:
                case EVENT:
                case DEADLINE:
                    if (input.length == 2) {
                        inputCmd.subtaskResponse(taskList, input[1]);
                        break;
                    } else {
                        throw new DukeException("Task description cannot be empty!");
                    }
                case REMOVE:
                case DELETE:
                    if (input.length == 2) {
                        inputCmd.deleteResponse(taskList, input[1]);
                        break;
                    } else {
                        throw new DukeException("Invalid number of inputs!");
                    }
                case CLEAR:
                    inputCmd.clearResponse(taskList);
                    break;
                default:
                    throw new DukeException("Invalid command!");
                }
            } catch (DukeException e) {
                e.exceptionResponse();
            }


        }
        sc.close();
    }

    private static void greet() {
        String strPadding = "      ";
        String botName = "Baymax";
        String greeting = "Greetings, I am " + botName + ".\n" +
                strPadding + "What can I do you for?";

        Command.GREET.genericResponse(greeting);
    }

    private static void init() throws IOException {
        File dataFile = new File("data/todo.dat");
        dataFile.getParentFile().mkdirs();
        if (!dataFile.exists()) { dataFile.createNewFile(); }

        taskList = new ArrayList<>();

        // TODO: error handling

        Scanner sc = new Scanner(dataFile).useDelimiter("\\n");
        while (sc.hasNext()) {
            String[] input = sc.nextLine().split(" \\| ");
            Task newTask = null;

            if (input[0].equals("T")) { newTask = new ToDo(input[2]); }
            else if (input[0].equals("D")) {
                try {
                    newTask = new Deadline(input[2], input[3]);
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    newTask = new Event(input[2], input[3]);
                } catch (DukeException e) {
                    e.printStackTrace();
                }
            }

            if (newTask != null) {
                newTask.isMarked = input[1].equals("1");
                taskList.add(newTask);
            }
        }
    }

    public static void save() throws IOException {
        FileWriter fw = new FileWriter("data/todo.dat", false);
        taskList.forEach(entry -> {
            try {
                fw.write(entry.write());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fw.close();
    }

    public static void main(String[] args) throws IOException, DukeException {
        init();
        greet();
        run();
        save();
    }

}
