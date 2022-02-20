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
        if (isExitCommand(message)) {
            return null;
        }
        String confirmationMessage = null;
        String currentMessage = getFirstWord(message);
        Task currentTask;
        int index;
        switch (currentMessage) {
        case "help":
            confirmationMessage = Ui.COMMANDS_lIST;
            break;
        case "list":
            confirmationMessage = getListAsString(tasks);
            break;
        case "sort":
            sortList(message, tasks, storage);
            confirmationMessage = confirmSort(tasks);
            break;
        case "mark":
            index = getIndexFromMessage(message);
            currentTask = tasks.getTaskAtIndex(index);
            modifyTaskMark(currentTask, ConfirmCodes.MARK);
            confirmationMessage = confirmMarkChange(currentTask, ConfirmCodes.MARK);
            break;
        case "unmark":
            index = getIndexFromMessage(message);
            currentTask = tasks.getTaskAtIndex(index);
            modifyTaskMark(currentTask, ConfirmCodes.UNMARK);
            confirmationMessage = confirmMarkChange(currentTask, ConfirmCodes.UNMARK);
            break;
        case "delete":
            index = getIndexFromMessage(message);
            currentTask = tasks.getTaskAtIndex(index);
            deleteTask(index , tasks, storage);
            confirmationMessage = confirmTaskDeletion(currentTask, tasks);
            break;
        case "find":
            confirmationMessage = getResultsOfFind(message, tasks);
            break;
        case "todo":
            currentTask = parseMessageContents(message, TaskTypes.TODO);
            addTask(currentTask, storage, tasks);

            confirmationMessage = confirmTaskAddition(currentTask, tasks);
            break;
        case "deadline":
            currentTask = parseMessageContents(message, TaskTypes.DEADLINE);
            addTask(currentTask, storage, tasks);
            confirmationMessage = confirmTaskAddition(currentTask, tasks);
            break;
        case "event":
            currentTask = parseMessageContents(message, TaskTypes.EVENT);
            addTask(currentTask, storage, tasks);
            confirmationMessage = confirmTaskAddition(currentTask, tasks);
            break;
        default:
            throwInvalidInput();
            break;
        }
        return confirmationMessage;
    }

    /**
     * Parses the message contents and returns the suitable Task object.
     *
     * @param message the message from the User
     * @param type    The type of the task
     * @return Task object
     */
    @SuppressWarnings("checkstyle:Regexp")
    private Task parseMessageContents(String message, TaskTypes type) throws DukeException {
        String[] messageArr = getMessageArray(message, type);
        assert messageArr != null : "Message Array is not supposed to be null";
        throwIfWrongFormat(message, messageArr, type);

        String description = getDescription(messageArr, type);
        String dateString = getDateString(messageArr, type);
        String timeBeginString = getTimeBeginString(messageArr, type);

        LocalDate date = parseDateFromString(dateString, type);
        LocalTime timeBegin = parseTimeFromString(timeBeginString, type);
        Task currentTask = null;
        switch (type) {
        case TODO:
            currentTask = new ToDo(description);
            break;
        case DEADLINE:
            currentTask = new Deadline(description, date, timeBegin);
            break;
        case EVENT:
            String timeEndString = getTimeEndString(messageArr);
            LocalTime timeEnd = parseTimeFromString(timeEndString, TaskTypes.EVENT);
            assert timeEnd != null;
            throwIfEndTimeBeforeStartTime(timeBegin, timeEnd);
            currentTask = new Event(description, date, timeBegin, timeEnd);
            break;
        default:
            throwInvalidTypeDeclaration();
            break;
        }
        return currentTask;
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
        if (type == TaskTypes.TODO) {
            return null;
        }
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
            if (type == TaskTypes.TODO || (type == TaskTypes.DEADLINE && timeString == null)) {
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

    private String confirmTaskAddition(Task task, TaskList tasks) {
        return "Alright then! I've added the task to your list:" + "\n\t" + task + "\n"
            + getNumOfTasksMessage(tasks);
    }

    private String confirmTaskDeletion(Task task, TaskList tasks) {
        return "As you wish. I've removed the task from your list:" + "\n\t" + task
            + "\nI hope it was nothing important..." + "\n" + getNumOfTasksMessage(tasks);
    }

    private String confirmMarkChange(Task task, ConfirmCodes code) throws DukeException {
        if (code == ConfirmCodes.MARK) {
            return "Alright then! I've marked that task as done:" + "\n\t" + task;
        } else if (code == ConfirmCodes.UNMARK) {
            return "Alright then! I've marked that task as not done:" + "\n\t" + task;
        } else {
            throwInvalidInput();
            return null; //should not reach here
        }
    }

    private String confirmSort(TaskList tasks) {
        return "Alright then! I've sorted the tasks accordingly: \n" + tasks;
    }

    private String getResultsOfFind(String message, TaskList tasks) throws DukeException {
        String keyword = getKeyword(message);
        String foundTasks = tasks.find(keyword);
        if (foundTasks.equals("")) {
            return "My apologies, but no tasks were found for the given keyword.";
        } else {
            return "I've searched the archives and found these matching tasks in your list:\n"
                + tasks.find(keyword);
        }
    }

    private String getFirstWord(String message) {
        if (!message.contains(" ")) { //check if the message contains only one word
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

    private String getDescription(String[] messageArr, TaskTypes type) throws DukeException {
        switch (type) {
        case TODO:
            return messageArr[1];
        case DEADLINE:
        case EVENT:
            String description = messageArr[0];

            return description.endsWith(" ") ? messageArr[0].substring(0, messageArr[0].length() - 1)
                : description; //remove last whitespace if it exists
        default:
            throwInvalidTypeDeclaration();
            break;
        }
        assert false : "Runtime should not reach here";
        return null; //should not reach here
    }

    private String getDateString(String[] messageArr, TaskTypes type) {
        if (type == TaskTypes.TODO) {
            return null;
        } else {
            return messageArr[1].substring(3);
        }
    }

    private String getTimeBeginString(String[] messageArr, TaskTypes type) {
        if (type == TaskTypes.EVENT || (type == TaskTypes.DEADLINE && hasTimeParameter(messageArr))) {
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

    private String[] getMessageArray(String message, TaskTypes types) throws DukeException {
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

    private boolean hasTimeParameter(String[] messageArr) {
        return messageArr.length == 3;
    }

    private void addTask(Task task, Storage storage, TaskList tasks) throws DukeException {
        tasks.addTask(task);
        storage.modifyStorage(task, ConfirmCodes.ADDITION, tasks);
    }

    private void sortList(String message, TaskList tasks, Storage storage) throws DukeException {
        String typeString = getKeyword(message);
        SortType type = getSortType(typeString);
        assert type != null : "Sort type is not supposed to be null";
        tasks.sort(type);
        storage.modifyStorage(null, ConfirmCodes.SORT, tasks);
    }

    private void modifyTaskMark(
        Task currentTask, ConfirmCodes code) throws DukeException {
        switch (code) {
        case MARK:
            throwIfMarked(currentTask);
            currentTask.markDone();
            break;
        case UNMARK:
            throwIfNotMarked(currentTask);
            currentTask.markUndone();
            break;
        default:
            throwInvalidTypeDeclaration();
            break;
        }
    }

    private void deleteTask(int index, TaskList tasks, Storage storage) throws DukeException {
        Task currentTask = tasks.removeTask(index);
        storage.modifyStorage(currentTask, ConfirmCodes.DELETION, tasks);
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
            } else if (messageArr.length < 2 || messageArr[1].indexOf("by") != 0) {
                throwWrongDeadlineFormatException();
            } else if (messageArr[1].length() < 4) {
                throwEmptyDateException();
            }
            break;
        case EVENT:
            if (indexOfSpace == -1) {
                throwEmptyDescriptionException();
            } else if (messageArr.length < 4 || messageArr[1].indexOf("at") != 0) {
                throwWrongEventFormatException();
            } else if (messageArr[1].length() < 4) {
                throwEmptyDateException();
            }
            break;
        default:
            throwInvalidTypeDeclaration();
            break;
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
    private void throwIfMarked(Task currentTask) throws DukeException {
        if (currentTask.isDone) {
            throw new DukeException("Pardon me, but the task has already been marked.");
        }
    }

    private void throwIfNotMarked(Task currentTask) throws DukeException {
        if (!currentTask.isDone) {
            throw new DukeException("Pardon me, but the task is already not marked.");
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

    private String getNumOfTasksMessage(TaskList tasks) {
        return "You currently have " + tasks.getTasksCount() + " task(s) remaining in your list.";
    }
}
