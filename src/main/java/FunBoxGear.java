import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
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
            ToDo tempToDo = new ToDo(description, "todo");
            tempToDo = (ToDo) this.isExistingTaskDone(tempToDo, isDone);
            this.tasksList.add(tempToDo);
            break;
        case "E":
            dateTime = taskArr[3];
            Event tempEvent = new Event(description, stringToLocalDate(dateTime),
                    getTime(dateTime), "event");
            tempEvent = (Event) this.isExistingTaskDone(tempEvent, isDone);
            this.tasksList.add(tempEvent);
            break;
        case "D":
            dateTime = taskArr[3];
            Deadline tempDead = new Deadline(description, stringToLocalDate(dateTime),
                    getTime(dateTime), "deadline");
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
     * Prints out the default GREETING on console
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Checks whether if user's input is a commands
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
        case "find":
            this.getTasksOnThisDate(formattedMsg);
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
     * Adds user's tasks to the list based on the type of task
     *
     * @param formattedMsg The original message from the users split by " " into an array
     * @param type         The type of task: event, deadline, todo
     */
    private void addToList(String[] formattedMsg, String type) {
        String description = this.getMessage(formattedMsg);
        String[] resultArr;
        String resultToBeWritten;
        int noOfItems;
        try {
            switch (type) {
            case "event":
                resultToBeWritten = "E,0";
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(resultArr[0])
                        .concat(DELIMITOR).concat(resultArr[1]);
                this.tasksList.add(new Event(resultArr[0], stringToLocalDate(resultArr[1]),
                        getTime(resultArr[1]), type));
                break;
            case "deadline":
                resultToBeWritten = "D,0";
                this.isDescriptionError(description, type);
                resultArr = this.getDescriptionAndDate(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(resultArr[0])
                        .concat(DELIMITOR).concat(resultArr[1]);
                this.tasksList.add(new Deadline(resultArr[0], stringToLocalDate(resultArr[1]),
                        getTime(resultArr[1]), type));
                break;
            case "todo":
                resultToBeWritten = "T,0";
                this.isDescriptionError(description, type);
                resultToBeWritten = resultToBeWritten.concat(DELIMITOR).concat(description);
                this.tasksList.add(new ToDo(description, type));
                break;
            default:
                throw new FunBoxExceptions("ERROR! I do not know what the commands means :<");
            }
            writeToFile(resultToBeWritten);
        } catch (FunBoxExceptions e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Please ensure date is formatted as followed: yyyy-mm-dd!");
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
     * Checks whether user input has a description
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
     * Loops through a list of items and print out each item
     */
    private void showList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasksList.size(); i++) {
            System.out.println((i + 1) + "." + this.tasksList.get(i));
        }
    }

    /**
     * Formats the user's message to be able to differentiate between special commands
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
     * Gets the message from the formatted message without the first item of the array which typically contains the
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
     * Splits the message, date and time from a message which is needed for certain commands
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
     * Gets the tasks found on the date requested by user and prints it out.
     *
     * @param taskArr The commands provided by the users
     */
    private void getTasksOnThisDate(String[] taskArr) {
        LocalDate date;
        try {
            date = LocalDate.parse(taskArr[1]);
            ArrayList<Task> eventList = new ArrayList<>(this.tasksList);
            ArrayList<Task> deadlineList = new ArrayList<>(this.tasksList);
            eventList.removeIf(task -> (task.type.contains("todo") || task.type.contains("deadline")));
            deadlineList.removeIf(task -> (task.type.contains("todo") || task.type.contains("event")));

            int counter = 0;
            int eventSize = eventList.size();
            int deadlineSize = deadlineList.size();


            for (int i = 0; i < eventSize; i++) {
                Event temp = (Event) eventList.get(i);
                if (temp.date.equals(date)) {
                    counter++;
                    System.out.println(counter + "." + temp);
                }
            }

            for (int i = 0; i < deadlineSize; i++) {
                Deadline temp = (Deadline) deadlineList.get(i);
                if (temp.date.equals(date)) {
                    counter++;
                    System.out.println(counter + "." + temp);
                }
            }

            if (counter == 0) {
                System.out.println("No tasks found on this date! You are free!");
            }

        } catch (DateTimeParseException e) {
            System.out.println("ERROR! Please ensure date is in the correct format: yyyy-mm-dd");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ERROR! Date not found in command `find`!");
        }
    }

    private void filterList() {
        for (Task task : this.tasksList) {

        }
    }

    /**
     * Converts string consisting of a date and time to LocalDate object
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a LocalDate object
     * @throws FunBoxExceptions If dateTimeArr.length != 2
     */
    private LocalDate stringToLocalDate(String dateTime) throws FunBoxExceptions {
        String[] dateTimeArr = this.formatCommands(dateTime);
        LocalDate result;
        if (dateTimeArr.length == 2) {
            result = LocalDate.parse(dateTimeArr[0]);
        } else {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " is in the correct format: yyyy-mm-dd time");
        }

        return result;
    }

    /**
     * Gets the time from a string consisting of a date and time
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a time
     * @throws FunBoxExceptions If dateTimeArr.length != 2
     */
    private String getTime(String dateTime) throws FunBoxExceptions {
        String[] dateTimeArr = this.formatCommands(dateTime);
        String time;
        if (dateTimeArr.length == 2) {
            time = dateTimeArr[1];
        } else {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " is in this format: yyyy-mm-dd time");
        }
        return time;
    }

    /**
     * Checks if commands such as mark, unmark, delete has the correct input
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
     * Marks the item on the list as done
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
     * Marks the item on the list as not done
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
     * Deletes the task requested by the user
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
     * Prints out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon [0 n 0]");
    }
}
