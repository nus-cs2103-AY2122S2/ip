import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        tasks = new ArrayList<>();
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

    private static String processMessage(String message) throws DukeException {
        String currMessage;
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
            currTask = parseMessageContents(message, TaskTypes.TODO);

            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        case "deadline":
            currTask = parseMessageContents(message, TaskTypes.DEADLINE);
            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        case "event":
            currTask = parseMessageContents(message, TaskTypes.EVENT);
            tasks.add(currTask);
            message = confirmAction(currTask, ConfirmCodes.ADDITION);
            break;
        default:
            throw new DukeException("Pardon me, but I did not understand what you said.");
        }
        return DIVIDER + message + DIVIDER;
    }

    /**
     * Parses the message contents and returns the suitable Task object.
     * @param message the message from the User
     * @param type The type of the task
     * @return Task object
     */
    private static Task parseMessageContents(String message, TaskTypes type) throws DukeException {
        DukeException wrongDeadlineFormat = new DukeException("Pardon me, but the Deadline format is incorrect." +
                " The format should be:\n\t[Task] [Description] /by yyyy-mm-dd/HH:mm (leave \"/HH:mm\"" +
                " empty if no time in current task)");
        DukeException wrongEventFormat = new DukeException("Pardon me, but the Event format is incorrect." +
                " The format should be:\n\t[Task] [Description] /at yyyy-mm-dd/HH:mm/HH:mm");

        LocalDate date;
        LocalTime timeBegin, timeEnd;
        String[] messageArr;
        String description;
        String dateString, timeBeginString, timeEndString;

        switch (type) {
        case TODO:
            messageArr = message.split(" ", 2);
            if (messageArr.length <= 1) {
                throw new DukeException("Pardon me, but the description of a todo cannot be empty.");
            } else {
                return new ToDo(messageArr[1]);
            }
        case DEADLINE:
            int indexOfSpace = message.indexOf(" ");

            if (indexOfSpace == -1) {
                throw new DukeException("Pardon me, but the description of a deadline/event cannot be empty.");
            }

            String messageWithoutCommand = message.substring(message.indexOf(" ") + 1);

            messageArr = messageWithoutCommand.split("/");

            if (messageArr.length < 2) {
                throw wrongDeadlineFormat;
            } else if (messageArr[1].length() < 4) {
                throw new DukeException("Pardon me, but the date/time cannot be empty.");
            }

            description = messageArr[0].substring(0, messageArr[0].length() - 1); //remove last " "
            dateString = messageArr[1].substring(3);
            timeBeginString = messageArr.length == 3 ? messageArr[2] : null;

            //check to see if date/time format is correct
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                timeBegin = timeBeginString == null ? null : LocalTime.parse(timeBeginString,
                        DateTimeFormatter.ofPattern("HH:mm"));
            }  catch (DateTimeException e) {
                throw wrongDeadlineFormat;
            }

            return timeBeginString == null ? new Deadline(description, date) :
                    new Deadline(description, date, timeBegin);
        case EVENT:
            int indexOfSpaceEvent = message.indexOf(" ");

            if (indexOfSpaceEvent == -1) {
                throw new DukeException("Pardon me, but the description of a deadline/event cannot be empty.");
            }

            String messageWithoutCommandEvent = message.substring(message.indexOf(" ") + 1);

            messageArr = messageWithoutCommandEvent.split("/");

            if (messageArr.length < 4) {
                throw wrongEventFormat;
            } else if (messageArr[1].length() < 4) {
                throw new DukeException("Pardon me, but the date/time cannot be empty.");
            }

            description = messageArr[0].substring(0, messageArr[0].length() - 1); //remove last " "
            dateString = messageArr[1].substring(3);
            timeBeginString = messageArr[2];
            timeEndString = messageArr[3];

            //check to see if date/time format is correct
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                timeBegin = LocalTime.parse(timeBeginString, DateTimeFormatter.ofPattern("HH:mm"));
                timeEnd = LocalTime.parse(timeEndString, DateTimeFormatter.ofPattern("HH:mm"));
            }  catch (DateTimeException e) {
                throw wrongEventFormat;
            }

            return new Event(description, date, timeBegin, timeEnd);
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

    private static String confirmAction(Task task, ConfirmCodes code) {
        switch (code) {
        case ADDITION:
            return "Alright then! I've added the task to your list:" + "\n\t" + task.toString() + viewTasksCount();
        case DELETION:
            return "As you wish. I've removed the task from your list:" + "\n\t" + task.toString()
                    + "\nI hope it was nothing important..." + viewTasksCount();
        default:
            return "FILLER MESSAGE HERE";
        }
    }

    private static String viewTasksCount() {
        return "\nYou currently have " + tasks.size() + " task(s) remaining in your list.";
    }
}
