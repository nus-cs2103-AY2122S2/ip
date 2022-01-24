import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * The FunBoxGear class contains all the functionalities of FunBox.
 */
public class FunBoxGear {
    public final String GREETING = "Yo! I am FunBox [0 _ 0] \nWhat can I do for you?";
    // private Task[] tasksList;
    private ArrayList<Task> tasksList;

    private final String DIRURL = "data/";
    private final String FILEURL = "data/tasks.txt";
    private final String DELIMITOR = ",";

    /**
     * Constructor for FunBoxGear
     */
    public FunBoxGear() {
        this.tasksList = new ArrayList<Task>();

        if (!checkDirExist()) {
            boolean isDirCreated = this.createDir();

            if (isDirCreated) {
                System.out.println("Directory 'data' has been created for you!");
                this.createFile();
            } else {
                System.out.println("Directory already exists!");
            }

        } else if (!checkFileExist()) {
            this.createFile();
        } else {
            System.out.println("Loading saved task ... beep ... boop");
            try {
                this.readFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints out the default GREETING on console
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Check if directory 'data' exists in 'ip' folder
     *
     * @return Return true if directory exist, otherwise, false
     */
    private boolean checkDirExist() {
        File tempFile = new File(this.DIRURL);
        return tempFile.isDirectory();
    }

    /**
     * Check if file tasks.txt exist in 'data'
     *
     * @return Return true if file exist, otherwise, false
     */
    private boolean checkFileExist() {
        Path tempFile = Paths.get(FILEURL);
        return Files.exists(tempFile);
    }

    /**
     * Create directory 'data' under 'ip'
     *
     * @return Return true if directory exists, otherwise, false
     */
    private boolean createDir() {
        File tempFile = new File(DIRURL);
        return tempFile.mkdir();
    }

    /**
     * Create file tasks.txt under 'data' directory
     */
    private void createFile() {
        File tempFile = new File(FILEURL);
        try {
            if (tempFile.createNewFile()) {
                System.out.println("I have created tasks.txt for you!");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Read the existing file, if there are existing tasks in the file, add to the current list
     *
     * @throws FileNotFoundException If file is not found in the given URL
     * @throws IOException If an I/O error occur
     */
    private void readFile() throws FileNotFoundException, IOException {
        File file = new File(this.FILEURL);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String task;

        while(true) {
            task = br.readLine();
            if (task == null) {
                break;
            } else {
                try {
                    String[] taskArr = task.split(DELIMITOR);
                    this.insertExistingTask(taskArr);
                } catch (FunBoxExceptions e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Write existing task to tasks.txt
     *
     * @param taskArr The task in tasks.txt file broken into format readable by FunBox
     * @throws FunBoxExceptions If type of tasks not equals to T, E, D
     */
    private void insertExistingTask(String[] taskArr) throws FunBoxExceptions {
        String type = taskArr[0];
        String isDone = taskArr[1];
        String description = taskArr[2];
        String dateTime;
        switch (type) {
        case "T":
            ToDo tempToDo = new ToDo(description);
            tempToDo = (ToDo) this.isExistingTaskDone(tempToDo, isDone);
            this.tasksList.add(tempToDo);
            break;
        case "E":
            dateTime = taskArr[3];
            Event tempEvent = new Event(description, dateTime);
            tempEvent = (Event) this.isExistingTaskDone(tempEvent, isDone);
            this.tasksList.add(tempEvent);
            break;
        case "D":
            dateTime = taskArr[3];
            Deadline tempDead = new Deadline(description, dateTime);
            tempDead = (Deadline) this.isExistingTaskDone(tempDead, dateTime);
            this.tasksList.add(tempDead);
            break;
        default:
            throw new FunBoxExceptions("ERROR! Unable to read file!");
        }
    }

    /**
     * Check if existing task is done
     * @param task
     * @param isDone
     * @return
     */
    private Task isExistingTaskDone(Task task, String isDone) {
        if (isDone.equals("1")) {
            task.presetDone();
        }
        return task;
    }

    /**
     * Check whether if user's input is a commands
     *
     * @param message The user's input to the command prompt
     * @return Return false if message is "bye", otherwise return true
     */
    public boolean getCommands(String message) {
        String[] formattedMsg = this.formatCommands(message);
        switch (formattedMsg[0]) {
        case "bye":
            this.sayBye();
            return false;
        case "list":
            this.showList();
            return true;
        case "mark":
            this.markDone(formattedMsg);
            return true;
        case "unmark":
            this.markUndone(formattedMsg);
            return true;
        case "delete":
            this.deleteTask(formattedMsg);
            return true;
        default:
            this.addToList(formattedMsg, formattedMsg[0]);
            return true;
        }
    }

    /**
     * Add user's tasks to the list based on the type of task
     *
     * @param formattedMsg The original message from the users split by " " into an array
     * @param type         The type of task: event, deadline, todo
     */
    private void addToList(String[] formattedMsg, String type) {
        String description = this.getMessage(formattedMsg);
        String[] resultArr;
        String resultToBeWritten;
        try {
            switch (type) {
            case "event":
                resultToBeWritten = "E,0";
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(resultArr[0])
                                    .concat(DELIMITOR).concat(resultArr[1]);
                this.tasksList.add(new Event(resultArr[0], resultArr[1]));
                break;
            case "deadline":
                resultToBeWritten = "D,0";
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(resultArr[0])
                                    .concat(DELIMITOR).concat(resultArr[1]);
                this.tasksList.add(new Deadline(resultArr[0], resultArr[1]));
                break;
            case "todo":
                resultToBeWritten = "T,0";
                this.isDescriptionError(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(description);
                this.tasksList.add(new ToDo(description));
                break;
            default:
                throw new FunBoxExceptions("ERROR! I do not know what the commands means :<");
            }
            writeToFile(resultToBeWritten);

        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Write task of users to a file
     *
     * @param task The task user inputted
     */
    private void writeToFile(String task) {
        try {
            FileWriter fw = new FileWriter(FILEURL, true);
            fw.write(task + "\n");
            fw.close();
            int noOfItems = this.tasksList.size();
            System.out.println("Gotcha! I've added this task!");
            System.out.println(this.tasksList.get(noOfItems - 1));
            System.out.println("Now you have " + noOfItems + " tasks in the list!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Check whether user input has a description
     *
     * @param description The description of the user input which usually comes after the command type
     * @param type        The type of tasks the user used
     * @throws FunBoxExceptions If description == ""
     */
    private void isDescriptionError(String description, String type) throws FunBoxExceptions {
        if (description.equals("")) {
            throw new FunBoxExceptions("ERROR! The description of a " + type + " cannot be empty!");
        }
    }

    /**
     * Loop through a list of items and print out each item
     */
    private void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + "." + this.tasksList.get(i));
        }
    }

    /**
     * Format the user's message to be able to differentiate between special commands
     *
     * @param message The user's message to be formatted
     * @return Return a String array which contains the split message. The first element is used to differentiate
     * whether it's a message, command, or command which require special formatting
     */
    private String[] formatCommands(String message) {
        // Split message by blank space
        return message.split(" ");
    }

    /**
     * Get the message from the formatted message without the first item of the array which typically contains the
     * command
     *
     * @param formattedMsg The message sent by the user which has been formatted
     * @return Return a string of the original message sent by the users without the command
     */
    private String getMessage(String[] formattedMsg) {
        StringBuilder sb = new StringBuilder();

        int size = formattedMsg.length;

        for (int i = 1; i < size; i++) {
            if (i == size - 1) {
                sb.append(formattedMsg[i]);
            } else {
                sb.append(formattedMsg[i]).append(" ");
            }
        }
        return sb.toString();
    }

    /**
     * Used to split the message, date and time from a message which is needed for certain commands
     *
     * @param message The message to retrieve the date and time from
     * @return Return a String array of size 2 where the first item on the list contains the message and
     * the second item contains the date and time.
     */
    private String[] getDescriptionAndDate(String message, String type) {
        if (type.equals("event")) {
            return message.split(" /at ");
        }
        return message.split(" /by ");
    }

    /**
     * Check if commands such as mark, unmark, delete has the correct input
     *
     * @param messageArr The commands to be checked against
     * @throws FunBoxExceptions If messageArr.length != 2
     */
    private void checkTaskError(String[] messageArr) throws FunBoxExceptions {
        if (messageArr.length != 2) {
            if (messageArr.length == 1) {
                throw new FunBoxExceptions("Missing Task No. !!!");
            } else {
                throw new FunBoxExceptions("Invalid input !!!");
            }
        }
    }

    /**
     * Mark the item on the list as done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as done
     */
    private void markDone(String[] messageArr) {
        try {
            this.checkTaskError(messageArr);
            int taskNo = Integer.parseInt(messageArr[1]);
            this.tasksList.get(taskNo - 1).setDone();
        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Mark the item on the list as not done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as not done
     */
    private void markUndone(String[] messageArr) {
        try {
            this.checkTaskError(messageArr);
            int taskNo = Integer.parseInt(messageArr[1]);
            this.tasksList.get(taskNo - 1).setUndone();
        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete the task requested by the user
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be deleted
     */
    private void deleteTask(String[] messageArr) {
        try {
            this.checkTaskError(messageArr);
            int taskNo = Integer.parseInt(messageArr[1]) - 1;
            Task temp = this.tasksList.get(taskNo);
            this.tasksList.remove(taskNo);
            System.out.println("Noted! I've removed this task:");
            System.out.println(temp);
            System.out.println("Now you have " + this.tasksList.size() + " tasks in the list");
        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Print out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon [0 n 0]");
    }
}
