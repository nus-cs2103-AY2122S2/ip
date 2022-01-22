import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
/**
 * Represents a Personal Assistant Chatbot based on Project Duke.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Duke {
    /**
     * Contains a list of tasks stored for the user
     */
    private static List<Task> tasks;
    /**
     * A divider for design purposes
     */
    public static final String DIVIDER = "\n____________________________________________________________\n";

    public static void main(String[] args) {
        File dukeFile = new File("data/duke.txt");
        File folderPath = new File("data");
        tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        if (!folderPath.exists()) {
            folderPath.mkdirs();
        } else if (!dukeFile.exists()) { //checks if duke.txt exists, if yes, then write the tasks to the "tasks" object
            try {
                dukeFile.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created. Check directory.");
                System.exit(0);
            }
        } else {
            readDukeFile();
        }

        String userInput = "";

        System.out.println(DIVIDER + "Why hello there! My name is Wensleydale.\nWhat do you need?" + DIVIDER);

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();

            try {
                String message = processMessage(userInput);

                if (message == null) {
                    break;
                } else {
                    System.out.println(message);
                }
            } catch (DukeException e) {
                System.out.println(DIVIDER + e.getMessage() + DIVIDER);
            }
        }

        System.out.println(DIVIDER + "Farewell then!" + DIVIDER);
    }

    private static void readDukeFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            Scanner s = new Scanner(dukeFile);

            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] currLineArr = currLine.split("\\|");

                switch (currLineArr[0]) {
                case "T":
                    Task tempT = new ToDo(currLineArr[2]);

                    if (currLineArr[1].equals("X")) {
                        tempT.markDone();
                    }

                    tasks.add(tempT);
                    break;
                case "D":
                    Task tempD = new Deadline(currLineArr[2], currLineArr[3]);

                    if (currLineArr[1].equals("X")) {
                        tempD.markDone();
                    }

                    tasks.add(tempD);
                    break;
                case "E":
                    Task tempE = new Event(currLineArr[2], currLineArr[3]);

                    if (currLineArr[1].equals("X")) {
                        tempE.markDone();
                    }

                    tasks.add(tempE);
                    break;
                default:
                    //empty
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found"); //code should not reach here since file is automatically created
            System.exit(0);
        }
    }

    private static String processMessage(String message) throws DukeException {
        String currMessage;
        String[] messageArr;
        Task currTask;
        int index;

        if (message.equalsIgnoreCase("bye")) {
            return null;
        }

        if (message.indexOf(" ") == -1) { //take the first word of the input
            currMessage = message.toLowerCase();
        } else {
            currMessage = message.substring(0, message.indexOf(" ")).toLowerCase();
        }

        switch (currMessage) {
        case "list":
            StringBuilder listOfTasks = new StringBuilder();
            listOfTasks.append("Provided are the tasks currently in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOfTasks.append(i + 1).append(".").append(tasks.get(i)).append("\n");
            }

            message = listOfTasks.toString();
            break;
        case "mark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.get(index);
            currTask.markDone();

            message = "Alright then! I've marked that task as done:" + "\n\t" + currTask;
            break;
        case "unmark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.get(index);
            currTask.markUndone();

            message = "Alright then! I've marked that task as not done:" + "\n\t" + currTask;
            break;
        case "delete":
            index = getIndexFromMessage(message);

            message = confirmAction(tasks.remove(index), ConfirmCodes.DELETION);
            break;
        case "todo":
            messageArr = getMessageContents(message, TaskTypes.TODO);
            currTask = new ToDo(messageArr[1]);

            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        case "deadline":
            messageArr = getMessageContents(message, TaskTypes.DEADLINE);
            currTask = new Deadline(messageArr[0], messageArr[1]);
            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        case "event":
            messageArr = getMessageContents(message, TaskTypes.EVENT);
            currTask = new Event(messageArr[0], messageArr[1]);
            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        default:
            throw new DukeException("Pardon me, but I did not understand what you said.");
        }
        return DIVIDER + message + DIVIDER;
    }

    /**
     * Transforms the message input into a String array separated by " ", then modifies the array to order the contents
     * of the array based on the TaskType object for the processMessage() method to process and returns it
     * @param message the message from the User
     * @param type The type of the task
     * @return String array containing ordered based on contents of the task in String format
     */
    private static String[] getMessageContents(String message, TaskTypes type) throws DukeException {
        String[] messageArr;
        switch (type) {
        case TODO:
            messageArr = message.split(" ", 2);
            if (messageArr.length <= 1) {
                throw new DukeException("Pardon me, but the description of a todo cannot be empty.");
            } else {
                return messageArr;
            }
        case DEADLINE: //both cases have similar outcomes
        case EVENT:
            int indexOfSpace = message.indexOf(" ");
            int indexOfSlash = message.indexOf("/");

            if (indexOfSpace == -1) {
                throw new DukeException("Pardon me, but the description of a deadline/event cannot be empty.");
            } else if (indexOfSlash == -1) {
                throw new DukeException("Pardon me, but the format is incorrect. The format should be:\n\t" +
                        "[Task] [Description] /[by\\at] [date\\time]");
            }

            String messageWithoutCommand = message.substring(message.indexOf(" ") + 1);

            messageArr = messageWithoutCommand.split("/");

            if (messageArr[1].length() < 4) {
                throw new DukeException("Pardon me, but the date/time cannot be empty.");
            }

            messageArr[0] = messageArr[0].substring(0, messageArr[0].length() - 1); //remove last " "
            messageArr[1] = messageArr[1].substring(3);

            return messageArr;
        default:
            throw new DukeException("INTERNAL ERROR: Invalid Type Declaration");
        }
    }

    private static int getIndexFromMessage(String message) throws DukeException {
        int index = message.indexOf(" ");

        if (index == -1) {
            throw new DukeException("Pardon me, but the index cannot be empty.");
        }

        int indexOfItem;
        try {
            indexOfItem = Integer.parseInt(message.substring(message.indexOf(" ") + 1)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Pardon me, but the provided index was not an integer.");
        }

        try {
            tasks.get(indexOfItem); //used to see if index is out of bounds
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Pardon me, but the provided index does not exist in your list.");
        }
        return indexOfItem;
    }

    private static String confirmAction(Task task, ConfirmCodes code) throws DukeException {
        try {
            FileWriter fw;
            switch (code) {
            case ADDITION:
                fw = new FileWriter("data/duke.txt", true);
                fw.write(task.toStringInFileFormat());
                fw.close();
                return "Alright then! I've added the task to your list:" + "\n\t" + task + viewTasksCount();
            case DELETION:
                fw = new FileWriter("data/duke.txt");
                fw.write(listInFileFormat());
                fw.close();
                return "As you wish. I've removed the task from your list:" + "\n\t" + task.toString()
                        + "\nI hope it was nothing important..." + viewTasksCount();
            default:
                throw new DukeException("INTERNAL ERROR: Invalid Type Declaration");
            }
        } catch (IOException e) {
            System.out.println("INTERNAL ERROR: File cannot be accessed. Check directory.");
            System.exit(0);
        }

        return null; //code should not reach here
    }

    private static String viewTasksCount() {
        return "\nYou currently have " + tasks.size() + " task(s) remaining in your list.";
    }

    private static String listInFileFormat() {
        StringBuilder list = new StringBuilder();

        for (Task t : tasks) {
            list.append(t.toStringInFileFormat()).append(System.lineSeparator());
        }

        return list.toString();
    }
}
