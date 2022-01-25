import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private static void init() throws DukeException, IOException {
        // TODO: file handling using website
        String currDir = System.getProperty("user.dir");
        String dataName = "todo.dat";
        Path dataDir = Paths.get(currDir, "data", dataName);
        File dataFile = new File(String.valueOf(dataDir));

        // Check if directory and file exists
        dataFile.getParentFile().mkdirs();
        if (!dataFile.exists()) { dataFile.createNewFile(); }

        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));

            taskList = new ArrayList<>();
            List<String> testList = br.lines().collect(Collectors.toList());
            testList.forEach(line -> {
                String[] input = line.split(" \\| ");
                Task newEntry = null;

                // TODO: error handling
                switch (input[0]) {
                case "T":
                    newEntry = new ToDo(input[2]);
                    break;
                case "D":
                    try {
                        newEntry = new Deadline(input[2], input[3]);
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                    break;
                case "E":
                    try {
                        newEntry = new Event(input[2], input[3]);
                    } catch (DukeException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
                }
                assert newEntry != null;
                newEntry.isMarked = input[1].equals("1");
                taskList.add(newEntry);
            });
            br.close();
        } catch (Exception e) {
            throw new DukeException(e.toString());
        }
    }

    public static void save() throws IOException {
        String currDir = System.getProperty("user.dir");
        String dataName = "todo.dat";
        Path dataDir = Paths.get(currDir, "data", dataName);
        File dataFile = new File(String.valueOf(dataDir));
        BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile, false));

        taskList.forEach(entry -> {
            try {
                bw.write(entry.write());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public static void main(String[] args) throws IOException, DukeException {
        init();
        greet();
        run();
        save();
    }

}
