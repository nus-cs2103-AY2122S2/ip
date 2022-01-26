import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;


public class Duke {

    static String path = "src/main/java/duke.txt";
    private static Ui ui;
    private Ui uiDuke;

    public Duke(String filePath) {
        uiDuke = new Ui();
    }
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }

    }
    public static void main(String[] args) throws DukeException {
        // Array of Task instead, each task has its state and behaviour
        ArrayList<Task> inputStore = new ArrayList<Task>();
        TaskList taskDataList;
        String input;
        ui = new Ui();
        int index = 0;

        // reading data file into current list
        /* try {
            FileInputStream databaseInputStream = new FileInputStream("src/main/java/TaskDatabase.ser");
            ObjectInputStream readDataBaseStream = new ObjectInputStream(databaseInputStream);
            ArrayList inputDatabase = (ArrayList<Task>) readDataBaseStream.readObject();
            taskDataList = new TaskList(inputDatabase);
            readDataBaseStream.close();
            System.out.println("Reading of database stopped");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Database is Empty!");
            inputStore = new ArrayList<>();
            taskDataList = new TaskList(inputStore);
        }*/

        ui.hello();
        Storage fileStore = new Storage(path);
        ArrayList inputDatabase = fileStore.readFile();
        taskDataList = new TaskList(inputDatabase);
        Scanner chatInput = new Scanner( System.in );

        input = chatInput.nextLine();

        while (!input.equals("bye")) {
            String[] inputStrings = input.trim().split(" ",2);
            String inputCommand = inputStrings[0];
            // has command and body
            String inputBody = inputStrings.length == 2 ? inputStrings[1] : "";
            Task task;
            int taskNumber;
            int taskIndex;
            // convert to parser each line,
            try {
                switch (inputCommand) {

                    case "list":
                        taskDataList.printOutTaskList();
                        break;

                    case "deadline":
                        // check if Task Description is empty
                        String[] deadlineInfo = inputBody.split("/by ");
                        if (deadlineInfo[0].trim().length() == 0 ) {
                            throw new DukeException("Please enter Task Description");
                        }
                        if (endIndexOfTask(input) == -1) {
                            throw new EmptyByException("Please remember to include deadline time with /by");
                        }
                        boolean inputIsValid = true;
                        // description , by
                        try {
                            task = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                            taskDataList.addTask(task);
                        } catch (DateTimeException d) {
                            throw new DukeException("invalid date input, use yyyy-MM-dd or dd-MM-yyyy or dd/MM/yyyy or dd:MM:yyyy");
                        } catch (Exception e) {
                            throw new DukeException("Please include correct deadline time");
                        }
                        // when date numbers exceed will have null pointer here, do a check for null pointer?
                        break;
                    case "event":
                        String[] eventInfo = inputBody.split("/at ");
                        // check if Task Description is empty
                        if (eventInfo[0].trim().length() == 0 ) {
                            throw new DukeException("Please enter Task Description");
                        }
                        if (endIndexOfTask(input) == -1) {
                            throw new EmptyEventAtException("Please remember to include event time and date with /at");
                        }
                        task = new Event(input.substring(indexOfTask("event"), endIndexOfTask(input)), input.substring(endIndexOfTask(input) + 4));
                        taskDataList.addTask(task);
                        break;

                    case "todo":
                        if (input.length() != "todo".length() && inputBody != "") {
                            task = new Todo(input.substring(indexOfTask("todo")));
                            taskDataList.addTask(task);
                        } else {
                            throw new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "mark":
                        if (input.length() == "mark".length() || inputBody == "") {
                            throw new EmptyDescriptionException("OOPS!!! The description cannot be empty.");
                        }
                        taskNumber = Integer.valueOf(inputBody);
                        taskIndex = taskNumber - 1;
                        taskDataList.mark(taskIndex);
                        break;
                    case "unmark":
                        if (input.length() == "mark".length() || inputBody == "") {
                            throw new EmptyDescriptionException("OOPS!!! The description cannot be empty.");
                        }
                        taskNumber = Integer.valueOf(inputBody);
                        taskIndex = taskNumber - 1;
                        taskDataList.unMark(taskIndex);
                        break;
                    case "delete":
                        taskNumber = Integer.valueOf(inputBody);
                        taskIndex = taskNumber - 1;
                        taskDataList.deleteTask(taskIndex);
                        break;
                    default:
                        throw new InvalidCommandException("OOPS!!! I'm sorry, but i don't know what that means :-(");
                }
            }
            catch (DukeException e) {
                ui.showError(e);
            }
            input = chatInput.nextLine();
        }

        // write into file
        fileStore.saveToFile(taskDataList.getTaskStore());
        ui.bye();
        chatInput.close();
    }

    public static void writeDataInputToDisk(ArrayList<Task> inputTaskList) {
        try {
            FileOutputStream writeDatabaseInput = new FileOutputStream("src/main/java/TaskDatabase.ser");
            ObjectOutputStream writeDatabaseStream = new ObjectOutputStream(writeDatabaseInput);
            writeDatabaseStream.writeObject(inputTaskList);
            writeDatabaseStream.flush();
            writeDatabaseStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int indexOfTask(String command) {
        int index = command.length() + 1;
        return index;
    }

    public static int endIndexOfTask(String input) {
        int index = input.indexOf("/");
        return index;
    }

}
