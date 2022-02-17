package duke.sonautil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * contains the Duke.task list. Has operations to add/delete tasks in the list
 */
public class TaskList {

    private static int taskAddedIndex;
    private final ArrayList<Task> list;

    /**
     * Constructor for object TaskList
     *
     * @param list an arraylist with Task
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
        taskAddedIndex = list.size();
    }

    /**
     * Constructor for object TaskList
     */
    public TaskList() {
        list = new ArrayList<>(100);
        taskAddedIndex = 0;
    }

    /**
     * Executes the user command on the Duke.task list. Does nothing when keyword is "unknown"
     *
     * @param command details of command retrieved from Parser
     * @throws DukeException if command is invalid
     */
    public String executeCommand(String[] command) throws DukeException {
        String keyword = command[0];
        assert (keyword != null && !keyword.equals(""));
        switch (keyword) {
        case "list":
            if (taskAddedIndex == 0) {
                return Ui.emptyListMessage();
            } else {
                return Ui.showListMessage() +  "\n" + showList();
            }
        case "todo": {
            assert (command[1] != null && !command[1].equals("")); //description is not empty and not null
            Task task = new Todo(command[1]);
            addTask(task);
            return Ui.todoEnteredSuccessMessage(task, taskAddedIndex);
        }
        case "deadline": {
            assert (command[1] != null && !command[1].equals(""));
            assert (command[2] != null && !command[2].equals(""));
            assert (command[3] != null && !command[3].equals(""));

            Task task = new Deadline(command[1], LocalDateTime.parse(command[2]));
            addTask(task);
            return Ui.deadlineEnterSuccessMessage(task, command[3], taskAddedIndex);
        }
        case "event": {
            assert (command[1] != null && !command[1].equals(""));
            assert (command[2] != null && !command[2].equals(""));
            assert (command[3] != null && !command[3].equals(""));

            Task task = new Event(command[1], LocalDateTime.parse(command[2]));
            addTask(task);
            return Ui.eventEnterSuccessMessage(task, command[3], taskAddedIndex);
        }
        case "mark": {
            return executeMarkCommand(command);
        }
        case "unmark": {
            return executeUnmarkCommand(command);
        }
        case "delete": {
            return executeDeleteCommand(command);
        }
        case "schedule":
            return executeScheduleCommand(command);
        case "find":
            return executeFindCommand(command);
        case "update":
            return executeUpdateCommand(command);
        case "clear":
            clearTask();
            return Ui.clearSuccessMessage();
        case "unknown":
            //fallthrough
        default:
            return "";
        }
    }

    /**
     * Processes user's 'mark' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     * @throws DukeException if command is invalid / not understood
     */
    private String executeMarkCommand(String[] command) throws DukeException {
        assert (command[1] != null && !command[1].equals(""));
        int taskIndex = Integer.parseInt(command[1]);

        //user trying to mark a non-existing Duke.task
        if (taskIndex >= taskAddedIndex || taskIndex < 0) {
            throw new DukeException(Ui.taskDontExistMessage(taskIndex));
        }

        //user trying to mark a done Duke.task as done again
        if (list.get(taskIndex).isDone()) {
            throw new DukeException(Ui.markRepeatMessage());
        }
      
        list.get(taskIndex).markAsDone();
        return Ui.markSuccessMessage(list.get(taskIndex));
    }
      

    /**
     * Processes user's 'unmark' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     * @throws DukeException if command is invalid / not understood
     */
    private String executeUnmarkCommand(String[] command) throws DukeException {
        int taskIndex = Integer.parseInt(command[1]);
        //user trying to unmark a non-existing Duke.task
        if (taskIndex >= taskAddedIndex || taskIndex < 0) {
            throw new DukeException(Ui.taskDontExistMessage(taskIndex));
        }
        //user trying to mark a undone Duke.task as undone again
        if (!list.get(taskIndex).isDone()) {
            throw new DukeException(Ui.unmarkRepeatMessage());
        }
        list.get(taskIndex).unmark();
        return Ui.unmarkSuccessMessage(list.get(taskIndex));
    }

    /**
     * Processes user's 'delete' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     * @throws DukeException if command is invalid / not understood
     */
    private String executeDeleteCommand(String[] command) throws DukeException {
        //list is empty
        if (taskAddedIndex == 0) {
            throw new DukeException(Ui.listEmptyMessage());
        }
        int taskIndex = Integer.parseInt(command[1]);
        //user trying to delete a non-existing Duke.task
        if (taskIndex >= taskAddedIndex || taskIndex < 0) {
            throw new DukeException(Ui.taskDontExistMessage(taskIndex));
        }
        String str = Ui.taskRemovedMessage(getTask(taskIndex));
        removeTask(taskIndex);
        return str;
    }

    /**
     * Processes user's 'schedule' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     * @throws DukeException if command is invalid / not understood
     */
    private String executeScheduleCommand(String[] command) throws DukeException {
        //list is empty
        if (taskAddedIndex == 0) {
            throw new DukeException(Ui.scheduleEmptyMessage());
        }
        ArrayList<Task> scheduleList = new ArrayList<>(100);
        LocalDate date = LocalDate.parse(command[1]);

        for (int i = 0; i < taskAddedIndex; i++) {
            if (list.get(i) instanceof Event) {
                Event task = (Event) list.get(i);
                if (date.isEqual(task.getDate().toLocalDate())) {
                    scheduleList.add(task);
                }
            }
            if (list.get(i) instanceof Deadline) {
                Deadline task = (Deadline) list.get(i);
                if (date.isEqual((task.getDate().toLocalDate()))) {
                    scheduleList.add(task);
                }
            }
        }
      
        if (scheduleList.size() == 0) {
            return Ui.scheduleEmptyMessage();
        } else {
            String str = Ui.showSchedule(date.toString());
            for (int i = 0; i < scheduleList.size(); i++) {
                str += (i + 1) + "." + scheduleList.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Processes user's 'find' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     */
    private String executeFindCommand(String[] command) {
        String findWord = command[1];
        ArrayList<Task> searchList = new ArrayList<>(100);

        for (int i = 0; i < taskAddedIndex; i++) {
            String taskDescription = list.get(i).getDescription();
            if (taskDescription.contains(findWord)) {
                searchList.add(list.get(i));
            }
        }

        if (searchList.size() == 0) {
            return Ui.findNoMatchError();

        } else {
            String str = Ui.findShowResult();
            for (int i = 0; i < searchList.size(); i++) {
                str += (i + 1) + "." + searchList.get(i).toString() + "\n";
            }
            return str;
        }
    }

    /**
     * Processes user's 'update' command on tasklist
     *
     * @param command user's command info
     * @return message to user
     * @throws DukeException if command is invalid / not understood
     */
    private String executeUpdateCommand(String[] command) throws DukeException {
        //command: ["update", index, "type", "change", "timeEntered"]
        String changeType = command[2];
        String change = command[3];
        String timeEntered = command[4];
        int taskIndex = Integer.parseInt(command[1]);

        //list is empty
        if (taskAddedIndex == 0) {
            throw new DukeException(Ui.listEmptyMessage());
        }

        //user trying to update a non-existing task
        if (taskIndex >= taskAddedIndex || taskIndex < 0) {
            throw new DukeException(Ui.taskDontExistMessage(taskIndex));
        }

        Task oldTask = list.get(taskIndex);
        Task newTask;

        //trying to add date to todo
        if (oldTask instanceof Todo && changeType.equals("date")) {
            throw new DukeException(Ui.updateTodoDateError());
        }

        boolean isRedundantChange = (change.equals("todo") && oldTask instanceof Todo) ||
                (change.equals("deadline") && oldTask instanceof Deadline) ||
                (change.equals("event") && oldTask instanceof Event);
        if (isRedundantChange) {
            throw new DukeException(Ui.updateSameTypeError(change));
        }

        boolean hasSameDescription = oldTask.getDescription().equals(change) && changeType.equals("name");
        if (hasSameDescription) {
            throw new DukeException(Ui.updateSameDescriptionError());
        }

        if (changeType.equals("type")) {
            if (change.equals("todo")) {
                newTask = new Todo(oldTask.getDescription(), oldTask.isDone());
                list.set(taskIndex, newTask);
                return Ui.updateTypeSuccessMessage(newTask);
            } else if (change.equals("deadline") && oldTask instanceof Event) {
                newTask = new Deadline(oldTask.getDescription(), oldTask.isDone(), ((Event) oldTask).getDate());
                list.set(taskIndex, newTask);
                return Ui.updateTypeSuccessMessage(newTask);
            } else if (change.equals("event") && oldTask instanceof Deadline) {
                newTask = new Event(oldTask.getDescription(), oldTask.isDone(), ((Deadline) oldTask).getDate());
                list.set(taskIndex, newTask);
                return Ui.updateTypeSuccessMessage(newTask);
            } else {
                return Ui.updateTypeErrorMessage();
            }

        } else if (changeType.equals("name")) {
            list.get(taskIndex).updateDescription(change);
            return Ui.updateDescriptionSuccessMessage(list.get(taskIndex));
        } else { //changeType.equals("date")
            assert list.get(taskIndex) instanceof Event || list.get(taskIndex) instanceof Deadline;
            if (list.get(taskIndex) instanceof Event) {
                ((Event) list.get(taskIndex)).changeDate(change);
            } else {
                ((Deadline) list.get(taskIndex)).changeDate(change);
            }
            return Ui.updateDateSuccessMessage(list.get(taskIndex), timeEntered);
        }
    }


    /**
     * Returns the Duke.task of the given index
     *
     * @param index index of Duke.task in the list
     * @return Task of the given index
     */
    public Task getTask(int index) {
        assert (index <= taskAddedIndex && index >= 0);
        return list.get(index);
    }

    /**
     * returns all tasks in the list in string type
     *
     * @return list of tasks in string type
     */
    private String showList() {
        String listStr = "";
        for (int i = 0; i < taskAddedIndex; i++) {
            listStr = listStr + (i+1) + "." + list.get(i).toString() + "\n";
        }
        return listStr;
    }

    /**
     * Adds Duke.task into the list
     *
     * @param task Duke.task to be added into the list
     */
    private void addTask(Task task) {
        assert(task instanceof Todo || task instanceof Deadline || task instanceof Event);
        list.add(task);
        taskAddedIndex++;
    }

    /**
     * Removes Duke.task from the list
     *
     * @param taskIndex index of the Duke.task to be removed
     */
    private void removeTask(int taskIndex) {
        assert (taskIndex <= taskAddedIndex && taskIndex >= 0);
        list.remove(taskIndex);
        taskAddedIndex--;
    }

    /**
     * Clears the task list
     */
    private void clearTask() {
        list.clear();
        taskAddedIndex = 0;
    }

}
