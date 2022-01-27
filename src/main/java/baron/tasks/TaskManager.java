package baron.tasks;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a manager that keeps track of all the tasks in an {@code ArrayList},
 * and allow clients to add, mark and delete tasks. It also allows support operations
 * such as getting the number of tasks and checking whether there is no tasks.
 */
public class TaskManager {
    private ArrayList<Task> taskList;
    private ArrayList<Task> previousTaskList = null;

    /**
     * Constructs a {@code TaskManager} object containing no tasks represented by an empty {@code ArrayList}.
     */
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskManager} object containing the defined tasks as given in {@code taskList}
     *
     * @param taskList {@code ArrayList} containing tasks to be managed by {@code TaskManager}.
     * @see ArrayList
     * @see Task
     */
    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Returns {@code true} if this {@code TaskManager} contains no tasks.
     *
     * @return {@code true} if this {@code TaskManager} contains no tasks.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Returns the number of tasks in this {@code TaskManager}.
     *
     * @return the number of tasks in this {@code TaskManager}.
     */
    public int getTaskCount() {
        return this.taskList.size();
    }

    /**
     * Appends the specified {@code Task} to the end of the {@code taskList} of {@code TaskManager}.
     *
     * @param newTask {@code Task} to be appended to the {@code taskList} of {@code TaskManager}.
     * @return the newly appended {@code Task}.
     */
    public Task addTask(Task newTask) {
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Appends the {@code Task} to the end of the {@code taskList} of {@code TaskManager}.
     * The {@code Task} is parsed from commandArg according to the requirements of each
     * {@code TaskType}, namely {@code ToDo}, {@code Deadline} and {@code Event}.
     *
     * @param taskType   type of the task {@code ToDo}, {@code Deadline} or {@code Event}.
     * @param commandArg command argument to be parsed.
     * @return the newly appended {@code Task}.
     * @throws BaronException If commandArg is in the wrong format as required by the taskType.
     * @see ToDo
     * @see Deadline
     * @see Event
     */
    public Task addTask(TaskType taskType, String commandArg) throws BaronException {
        Task newTask;
        if (taskType == TaskType.TODO) {
            newTask = new ToDo(commandArg);
        } else if (taskType == TaskType.DEADLINE) {
            String[] splitString = commandArg.split(" /by ", 2);
            if (splitString.length == 2) {
                LocalDateTime localDateTime = DateTimeUtil.getDateTime(splitString[1]);
                newTask = new Deadline(splitString[0], localDateTime);
            } else if (splitString.length == 1) {
                if (splitString[0].startsWith("/by")) {
                    throw new BaronException(Message.generateEmptyDescMessage(taskType));
                } else {
                    throw new BaronException("Please specify a deadline by the /by keyword.");
                }
            } else {
                throw new BaronException("Please specify only one deadline.");
            }
        } else {
            String[] splitString = commandArg.split(" /at ", 2);
            if (splitString.length == 2) {
                LocalDateTime localDateTime = DateTimeUtil.getDateTime(splitString[1]);
                newTask = new Event(splitString[0], localDateTime);
            } else if (splitString.length == 1) {
                if (splitString[0].startsWith("/at")) {
                    throw new BaronException(Message.generateEmptyDescMessage(taskType));
                }
                throw new BaronException("Please specify a date by the /at keyword.");
            } else {
                throw new BaronException("Please specify only one date.");
            }
        }

        this.taskList.add(newTask);
        this.previousTaskList = this.getAllTasks();
        return newTask;
    }

    /**
     * Retrieves the {@code Task} at the specified position (1-indexed).
     *
     * @param index the index of the task (1-indexed).
     * @return the {@code Task} at the specified position (1-indexed).
     * @throws BaronException If there is no tasks or index is out of bound.
     */
    public Task getTask(int index) throws BaronException {
        if (this.getTaskCount() == 0) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
        } else if (index < 1 && index > this.getTaskCount()) {
            throw new BaronException(Message.generateTaskIndexOutOfBoundMessage(this.getTaskCount()));
        }
        return this.taskList.get(index - 1);
    }

    /**
     * Retrieves all tasks in this {@code TaskManager} in the form of {@code ArrayList<Task>}.
     *
     * @return a new {@code ArrayList<Task>} containing all the tasks in this {@code TaskManager}.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.taskList);
    }

    /**
     * Marks the task that is at the specified position (1-indexed).
     *
     * @param index the index of the task (1-indexed).
     * @return true if the task is marked successfully.
     * @throws BaronException If there is no task, the specified task was already marked,
     *                        or the index is out of bound.
     */
    public boolean markTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
        } else if (index > 0 && index <= this.getTaskCount()) {
            if (this.taskList.get(index - 1).mark()) {
                return true;
            } else {
                throw new BaronException("The task is already marked.");
            }
        } else {
            throw new BaronException(Message.generateTaskIndexOutOfBoundMessage(this.getTaskCount()));
        }
    }

    /**
     * Un-marks the task that is at the specified position (1-indexed).
     *
     * @param index the index of the task (1-indexed).
     * @return true if the task is un-marked successfully.
     * @throws BaronException If there is no task, the specified task was not marked,
     *                        or the index is out of bound.
     */
    public boolean unmarkTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
        } else if (index > 0 && index <= this.getTaskCount()) {
            if (this.taskList.get(index - 1).unmark()) {
                return true;
            } else {
                throw new BaronException("The task is not marked.");
            }
        } else {
            throw new BaronException(Message.generateTaskIndexOutOfBoundMessage(this.getTaskCount()));
        }
    }

    /**
     * Removes the task that is at the specified position (1-indexed).
     * Shifts any subsequent tasks to the left.
     *
     * @param index the index of the task (1-indexed) to be removed.
     * @return removed {@code Task} if it was removed successfully.
     * @throws BaronException If there is no task or the index is out of bound.
     */
    public Task deleteTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
        } else if (index > 0 && index <= this.getTaskCount()) {
            this.previousTaskList = this.getAllTasks();
            Task deletedTask = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            return deletedTask;
        } else {
            throw new BaronException(Message.generateTaskIndexOutOfBoundMessage(this.getTaskCount()));
        }
    }

    /**
     * Reverts the changes after an edit operation ({@code addTask}, {@code deleteTask}, {@code markTask},
     * {@code unmarkTask}).
     * <p>
     * To use this method, the instance variable {@code previousTaskList} must be set
     * to the state of {@code taskList} using {@code this.previousTaskList = this.getAllTasks();}.
     * {@code previousTaskList} can be cleared using {@code commitChanges} below.
     *
     * @see #taskList
     * @see #previousTaskList
     * @see #commitChanges()
     */
    public void revertChanges() {
        if (this.previousTaskList != null) {
            this.taskList = new ArrayList<>(this.previousTaskList);
            this.previousTaskList = null;
        }
    }

    /**
     * Clears instance variable {@code previousTaskList} to null if the revert of the changes is not needed
     * anymore, and it is to be used hand-in-hand with {@code revertChanges}.
     *
     * @see #revertChanges()
     */
    public void commitChanges() {
        this.previousTaskList = null;
    }

    /**
     * Returns the string representation of this {@code TaskManager}.
     *
     * @return the string representation of this {@code TaskManager}.
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.getTaskCount(); i++) {
            output.append("  ").append(i + 1).append(".").append(this.taskList.get(i));

            if (i != this.getTaskCount() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
    }
}
