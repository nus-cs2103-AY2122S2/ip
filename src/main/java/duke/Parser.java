package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the message contents and returns the suitable duke.Task object.
     *
     * @param message the message from the User
     * @param type    The type of the task
     * @return duke.Task object
     */
    private static Task parseMessageContents(String message, TaskTypes type) throws DukeException {
        DukeException wrongDeadlineFormat = new DukeException("Pardon me, but the duke.Deadline format is incorrect." +
                " The format should be:\n\t[duke.Task] [Description] /by yyyy-mm-dd/HH:mm (leave \"/HH:mm\"" +
                " empty if no time in current task)");
        DukeException wrongEventFormat = new DukeException("Pardon me, but the duke.Event format is incorrect." +
                " The format should be:\n\t[duke.Task] [Description] /at yyyy-mm-dd/HH:mm/HH:mm");

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
            } catch (DateTimeException e) {
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
            } catch (DateTimeException e) {
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

        return indexOfItem;
    }

    public String processMessage(String message, TaskList tasks, Storage storage) throws DukeException {
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
            String listOfTasks = tasks.toString();

            message = "Provided are the tasks currently in your list:\n" + listOfTasks;
            break;
        case "mark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.getTaskAtIndex(index);
            currTask.markDone();

            message = "Alright then! I've marked that task as done:" + "\n\t" + currTask;
            break;
        case "unmark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.getTaskAtIndex(index);
            currTask.markUndone();

            message = "Alright then! I've marked that task as not done:" + "\n\t" + currTask;
            break;
        case "delete":
            index = getIndexFromMessage(message);
            currTask = tasks.removeTask(index);
            storage.modifyStorage(currTask, ConfirmCodes.DELETION, tasks);
            message = "As you wish. I've removed the task from your list:" + "\n\t" + currTask
                    + "\nI hope it was nothing important..." + "\n" + getTaskCount(tasks);
            break;
        case "todo":
            currTask = parseMessageContents(message, TaskTypes.TODO);

            tasks.addTask(currTask);
            storage.modifyStorage(currTask, ConfirmCodes.ADDITION, tasks);
            message = "Alright then! I've added the task to your list:" + "\n\t" + currTask +
                    "\n" + getTaskCount(tasks);
            break;
        case "deadline":
            currTask = parseMessageContents(message, TaskTypes.DEADLINE);
            tasks.addTask(currTask);
            storage.modifyStorage(currTask, ConfirmCodes.ADDITION, tasks);
            message = "Alright then! I've added the task to your list:" + "\n\t" + currTask + "\n" +
                    getTaskCount(tasks);
            break;
        case "event":
            currTask = parseMessageContents(message, TaskTypes.EVENT);
            tasks.addTask(currTask);
            storage.modifyStorage(currTask, ConfirmCodes.ADDITION, tasks);
            message = "Alright then! I've added the task to your list:" + "\n\t" + currTask + "\n" +
                    getTaskCount(tasks);
            break;
        default:
            throw new DukeException("Pardon me, but I did not understand what you said.");
        }
        return message;
    }

    private String getTaskCount(TaskList tasks) {
        return "You currently have " + tasks.getTasksCount() + " task(s) remaining in your list.";
    }
}
