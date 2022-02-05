package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Parses the input given from the user and performs the required actions.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Parser {
    /**
     * Processes the message given by the user. Returns the response to the user.
     *
     * @param message the message inputted by user
     * @param tasks   the object representing the list of tasks
     * @param storage the object representing the stored tasks
     * @return Confirmation message for the user
     * @throws DukeException throws if the format of the message was incorrect, or if the message was not understood
     */
    public String processMessage(String message, TaskList tasks, Storage storage) throws DukeException {
        String returnMessage = null;
        String currMessage;
        Task currTask;
        int index;

        if (isExitCommand(message)) {
            return null;
        }

        currMessage = getFirstWord(message);

        switch (currMessage) {
        case "help":
            returnMessage = Ui.HELP;
            break;
        case "list":
            returnMessage = getListAsString(tasks);
            break;
        case "sort":
            String typeString = getKeyword(message);
            SortType type = getSortType(typeString);
            tasks.sort(type);
            storage.modifyStorage(null, ConfirmCodes.SORT, tasks);
            returnMessage = getSortMessage(tasks);
            break;
        case "mark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.getTaskAtIndex(index);
            currTask.markDone();

            returnMessage = markTaskMessage(currTask);
            break;
        case "unmark":
            index = getIndexFromMessage(message); //get the index
            currTask = tasks.getTaskAtIndex(index);
            currTask.markUndone();

            returnMessage = unmarkTaskMessage(currTask);
            break;
        case "delete":
            index = getIndexFromMessage(message);
            currTask = tasks.removeTask(index);
            storage.modifyStorage(currTask, ConfirmCodes.DELETION, tasks);

            returnMessage = deleteTaskMessage(currTask, tasks);
            break;
        case "find":
            String keyword = getKeyword(message);
            returnMessage = getResultsOfFind(keyword, tasks);
            break;
        case "todo":
            currTask = parseMessageContents(message, TaskTypes.TODO);
            addTask(currTask, storage, tasks);
            returnMessage = addTaskMessage(currTask, tasks);
            break;
        case "deadline":
            currTask = parseMessageContents(message, TaskTypes.DEADLINE);
            addTask(currTask, storage, tasks);
            returnMessage = addTaskMessage(currTask, tasks);
            break;
        case "event":
            currTask = parseMessageContents(message, TaskTypes.EVENT);
            addTask(currTask, storage, tasks);
            returnMessage = addTaskMessage(currTask, tasks);
            break;
        default:
            throwInvalidInput();
        }
        return returnMessage;
    }

    /**
     * Parses the message contents and returns the suitable Task object.
     *
     * @param message the message from the User
     * @param type    The type of the task
     * @return Task object
     */
    private Task parseMessageContents(String message, TaskTypes type) throws DukeException {
        String[] messageArr;

        assert (message != null) : "message should not be null";

        switch (type) {
        case TODO:
            messageArr = getMessageArr(message, TaskTypes.TODO);
            throwIfWrongFormat(message, messageArr, TaskTypes.TODO);
            assert messageArr != null;
            return new ToDo(messageArr[1]);
        case DEADLINE:
            messageArr = getMessageArr(message, TaskTypes.DEADLINE);
            throwIfWrongFormat(message, messageArr, TaskTypes.DEADLINE);

            assert messageArr != null;
            return parseTask(messageArr, TaskTypes.DEADLINE);
        case EVENT:
            messageArr = getMessageArr(message, TaskTypes.EVENT);
            throwIfWrongFormat(message, messageArr, TaskTypes.EVENT);

            assert messageArr != null;
            return parseTask(messageArr, TaskTypes.EVENT);
        default:
            throwInvalidTypeDeclaration();
        }
        assert false : "Runtime should not reach here";
        return null; //should not reach here
    }

    @SuppressWarnings("checkstyle:Regexp")
    private Task parseTask(String[] messageArr, TaskTypes type) throws DukeException {
        String description = getDescription(messageArr);
        String dateString = getDateString(messageArr);
        String timeBeginString = getTimeBeginString(messageArr, type);
        String timeEndString = (type == TaskTypes.DEADLINE) ? null : getTimeEndString(messageArr);

        LocalDate date = parseDateFromString(dateString, type);
        LocalTime timeBegin = parseTimeFromString(timeBeginString, type);
        LocalTime timeEnd = null;

        if (type == TaskTypes.EVENT) {
            timeEnd = parseTimeFromString(timeEndString, TaskTypes.EVENT);
            assert timeEnd != null;
            throwIfEndTimeBeforeStartTime(timeBegin, timeEnd);
        }

        if (type == TaskTypes.DEADLINE) {
            return new Deadline(description, date, timeBegin);
        } else if (type == TaskTypes.EVENT) {
            return new Event(description, date, timeBegin, timeEnd);
        } else {
            throwInvalidTypeDeclaration();
        }
        assert false : "Runtime should not reach here";
        return null; //should not reach here
    }

    private int getIndexFromMessage(String message) throws DukeException {
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

    private SortType getSortType(String message) throws DukeException {
        if (message.equalsIgnoreCase("chronologically")) {
            return SortType.CHRONOLOGICALLY;
        } else if (message.equalsIgnoreCase("alphabetically")) {
            return SortType.ALPHABETICALLY;
        } else if (message.equalsIgnoreCase("done")) {
            return SortType.DONE;
        } else {
            throwInvalidInput();
        }
        assert false : "Runtime should not reach here";
        return null;
    }
    private LocalDate parseDateFromString(String dateString, TaskTypes type) throws DukeException {
        try {
            return LocalDate.parse(dateString, Task.YEAR_FORMAT);
        } catch (DateTimeException e) {
            if (type == TaskTypes.DEADLINE) {
                throwWrongDeadlineFormatException();
            } else if (type == TaskTypes.EVENT) {
                throwWrongEventFormatException();
            } else {
                throwInvalidTypeDeclaration();
            }
        }
        assert false : "Runtime should not reach here";
        return null; //should not reach here
    }

    private LocalTime parseTimeFromString(String timeString, TaskTypes type) throws DukeException {
        try {
            if (type == TaskTypes.DEADLINE && timeString == null) {
                return null;
            } else {
                return LocalTime.parse(timeString, Task.TIME_FORMAT);
            }
        } catch (DateTimeException e) {
            if (type == TaskTypes.DEADLINE) {
                throwWrongDeadlineFormatException();
            } else {
                throwWrongEventFormatException();
            }
        }
        assert false : "Runtime should not reach here";
        return null; //should not reach here
    }

    private String addTaskMessage(Task task, TaskList tasks) {
        return "Alright then! I've added the task to your list:" + "\n\t" + task + "\n"
            + getTaskCountMessage(tasks);
    }

    private String deleteTaskMessage(Task task, TaskList tasks) {
        return "As you wish. I've removed the task from your list:" + "\n\t" + task
            + "\nI hope it was nothing important..." + "\n" + getTaskCountMessage(tasks);
    }

    private String markTaskMessage(Task task) {
        return "Alright then! I've marked that task as done:" + "\n\t" + task;
    }

    private String unmarkTaskMessage(Task task) {
        return "Alright then! I've marked that task as not done:" + "\n\t" + task;
    }
    private String getSortMessage(TaskList tasks) {
        return "Alright then! I've sorted the tasks accordingly: \n" + tasks;
    }

    private String getResultsOfFind(String keyword, TaskList tasks) {
        String foundTasks = tasks.find(keyword);
        if (foundTasks.equals("")) {
            return "My apologies, but no tasks were found for the given keyword.";
        } else {
            return "I've searched the archives and found these matching tasks in your list:\n"
                + tasks.find(keyword);
        }
    }

    private String getFirstWord(String message) {
        if (!message.contains(" ")) { //if the message is only one word
            return message.toLowerCase();
        } else {
            return message.substring(0, message.indexOf(" ")).toLowerCase();
        }
    }

    private String getKeyword(String message) throws DukeException {
        String keyword = message.substring(message.indexOf(" ") + 1).toLowerCase();
        throwIfNoKeyword(message, keyword);
        return keyword;
    }

    private String getDescription(String[] messageArr) {
        return messageArr[0].substring(0, messageArr[0].length() - 1); //remove last whitespace
    }

    private String getDateString(String[] messageArr) {
        return messageArr[1].substring(3);
    }

    private String getTimeBeginString(String[] messageArr, TaskTypes type) {
        if (type == TaskTypes.EVENT || (type == TaskTypes.DEADLINE && containsTimeString(messageArr))) {
            return messageArr[2];
        } else {
            return null;
        }
    }

    private String getTimeEndString(String[] messageArr) {
        return messageArr[3];
    }

    private String getListAsString(TaskList tasks) {
        String listOfTasks = tasks.toString();
        if (listOfTasks.isEmpty()) {
            return "Excuse me, but the list is empty. Perhaps the archive is incomplete...";
        } else {
            return "Provided are the tasks currently in your list:\n" + listOfTasks;
        }
    }

    private String[] getMessageArr(String message, TaskTypes types) throws DukeException {
        if (types == TaskTypes.TODO) {
            return message.split(" ", 2);
        } else if (types == TaskTypes.DEADLINE || types == TaskTypes.EVENT) {
            String messageWithoutCommand = message.substring(message.indexOf(" ") + 1);
            return messageWithoutCommand.split("/");
        } else {
            throwInvalidTypeDeclaration();
        }
        return null; //should not reach here
    }

    private boolean isExitCommand(String message) {
        return message.equalsIgnoreCase("bye");
    }

    private boolean containsTimeString(String[] messageArr) {
        return messageArr.length == 3;
    }

    private void addTask(Task task, Storage storage, TaskList tasks) throws DukeException {
        tasks.addTask(task);
        storage.modifyStorage(task, ConfirmCodes.ADDITION, tasks);
    }

    private void throwIfWrongFormat(String message, String[] messageArr, TaskTypes type) throws DukeException {
        int indexOfSpace = message.indexOf(" ");
        switch (type) {
        case TODO:
            if (messageArr.length <= 1) {
                throwWrongToDoFormatException();
            }
            break;
        case DEADLINE:
            if (indexOfSpace == -1) {
                throwEmptyDescriptionException();
            } else if (messageArr.length < 2 || !message.contains("by")) {
                throwWrongDeadlineFormatException();
            } else if (messageArr[1].length() < 4) {
                throwEmptyDateException();
            }
            break;
        case EVENT:
            if (indexOfSpace == -1) {
                throwEmptyDescriptionException();
            } else if (messageArr.length < 4 || !message.contains("at")) {
                throwWrongEventFormatException();
            } else if (messageArr[1].length() < 4) {
                throwEmptyDateException();
            }
            break;
        default:
            throwInvalidTypeDeclaration();
        }
    }

    private void throwIfEndTimeBeforeStartTime(LocalTime beginTime, LocalTime endTime) throws DukeException {
        if (endTime.isBefore(beginTime)) {
            throw new DukeException("Pardon me, but the end time you have provided me"
                + " takes place before the begin time. I must say that time travel is...\nnot in my repertoire...");
        }
    }

    private void throwIfNoKeyword(String message, String keyword) throws DukeException {
        int indexOfSpace = message.indexOf(" ");
        if (keyword.length() < 1 || indexOfSpace == -1) {
            throw new DukeException("Pardon me, but the body of this command should not be empty");
        }
    }

    private void throwWrongToDoFormatException() throws DukeException {
        throw new DukeException("Pardon me, but the description of a todo cannot be empty.");
    }

    private void throwWrongDeadlineFormatException() throws DukeException {
        throw new DukeException("Pardon me, but the Deadline format is incorrect."
            + " The format should be:\n\t" + Deadline.FORMAT);
    }

    private void throwWrongEventFormatException() throws DukeException {
        throw new DukeException("Pardon me, but the Event format is incorrect."
            + " The format should be:\n\t" + Event.FORMAT);
    }

    private void throwEmptyDescriptionException() throws DukeException {
        throw new DukeException("Pardon me, but the description cannot be empty.");
    }

    private void throwEmptyDateException() throws DukeException {
        throw new DukeException("Pardon me, but the date/time cannot be empty.");
    }

    private void throwInvalidTypeDeclaration() throws DukeException {
        throw new DukeException(DukeException.INVALID_TYPE);
    }

    private void throwInvalidInput() throws DukeException {
        throw new DukeException(DukeException.DID_NOT_UNDERSTAND);
    }

    private String getTaskCountMessage(TaskList tasks) {
        return "You currently have " + tasks.getTasksCount() + " task(s) remaining in your list.";
    }
}
