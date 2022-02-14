package heylo.tasks;

import java.util.ArrayList;

import heylo.storage.FilesReader;
import heylo.storage.FilesWriter;
import heylo.util.PriorityLevel;

/**
 * Represents a task added by the user.
 */
public abstract class Task {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int taskCount = 0;
    protected String description;
    protected boolean isDone;
    protected PriorityLevel priority;

    /**
     * Sets description of child task classes.
     *
     * @param description String task-description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static String markAsDone(int index) {
        StringBuilder str = new StringBuilder();
        try {
            taskList.get(index).isDone = true;
            str.append(" Well done!\n");
            str.append("  ")
                    .append(taskList.get(index).toString())
                    .append("\n");
            assert taskList.get(index).isDone : "Mark as done failed";
        } catch (IndexOutOfBoundsException e) {
            str.append(" This task does not exist.\n");
        }
        return str.toString();
    }

    /**
     * Marks the task as not done if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static String markAsNotDone(int index) {
        StringBuilder str = new StringBuilder();
        try {
            taskList.get(index).isDone = false;
            str.append(" Oops! Fixed that for you.\n");
            str.append("  ")
                    .append(taskList.get(index).toString())
                    .append("\n");
            assert !taskList.get(index).isDone : "Mark as not done failed";
        } catch (IndexOutOfBoundsException e) {
            str.append(" This task does not exist.\n");
        }
        return str.toString();
    }

    public static String markPriority(int index, PriorityLevel priority) {
        StringBuilder str = new StringBuilder();
        try {
            taskList.get(index).priority = priority;
            str.append(" Getting your priorities straight!\n");
            str.append("  ")
                    .append(taskList.get(index).toString())
                    .append("\n");
            assert taskList.get(index).priority != null : "Setting priority failed";
        } catch (IndexOutOfBoundsException e) {
            str.append(" This task does not exist.\n");
        }
        return str.toString();
    }

    /**
     * Adds the given task to the task list if valid and increments task count.
     *
     * @param task Task added by user.
     */
    public static String addToList(Task task) {
        StringBuilder str = new StringBuilder();
        if (task.description == null) {
            str.append(" Please enter the description as well!\n");
            str.append(" Command format: task-type task-description /by task-duration OR /at task-deadline\n");
            return str.toString();
        }

        if (task instanceof Event) {
            Event event = (Event) task;
            if (event.duration == null) {
                str.append(" Please enter the duration as well!\n");
                str.append(" Command format: task-type task-description /by YYYY-MM-DD\n");
                return str.toString();
            }
        }

        if (task instanceof Deadline) {
            Deadline taskWithDeadline = (Deadline) task;
            if (taskWithDeadline.deadline == null) {
                str.append(" Please enter the deadline as well!\n");
                str.append(" Command format: task-type task-description /at YYYY-MM-DD\n");
                return str.toString();
            }
        }

        assert !task.description.isBlank() : "Task description should not be blank";
        taskList.add(task);

        if (taskCount == 0) {
            str.append(" Added! Now you have 1 item in your tasks list.\n");
        } else {
            str.append(" Added! Now you have ")
                    .append(taskCount + 1)
                    .append(" items in your tasks list.\n");
        }
        str.append("  ")
                .append(taskList.get(taskCount).toString())
                .append("\n");
        taskCount++;

        assert taskCount > 0 : "Task count should be greater than 0 after adding to list";

        return str.toString();
    }

    /**
     * Removes the task from the task list if it exists.
     *
     * @param index int Index of the task in the displayed list of tasks.
     */
    public static String removeFromList(int index) {
        StringBuilder str = new StringBuilder();
        try {
            String outputTask = "  " + taskList.get(index).toString();
            taskList.remove(index);
            str.append(" Okay, I've deleted this task.\n");
            str.append(outputTask).append("\n");
            taskCount--;
        } catch (IndexOutOfBoundsException e) {
            str.append(" This task does not exist.\n");
        }
        return str.toString();
    }

    /**
     * Populates the task list with the previously saved tasks.
     */
    public static void getSavedTasks() {
        taskList = FilesReader.getTaskListFromFile();
        taskCount = taskList.size();
    }

    /**
     * Saves the tasks in the task list for later retrieval.
     */
    public static void saveTasks() {
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todo = (Todo) task;
                FilesWriter.writeToFile("T : "
                        + (todo.isDone ? "1 : " : "0 : ")
                        + todo.description + " : "
                        + (todo.priority != null ? todo.priority : "NONE") + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                FilesWriter.writeToFile("E : "
                        + (event.isDone ? "1 : " : "0 : ")
                        + event.description + " : "
                        + event.duration + " : "
                        + (event.priority != null ? event.priority : "NONE") + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                FilesWriter.writeToFile("D : "
                        + (deadline.isDone ? "1 : " : "0 : ")
                        + deadline.description + " : "
                        + deadline.deadline + " : "
                        + (deadline.priority != null ? deadline.priority : "NONE") + "\n");
            }
        }
    }

    /**
     * Converts the string format of the task to a Task object.
     *
     * @param task String task.
     * @return Task object.
     */
    public static Task convertStringToTask(String task) {
        String[] input = task.split(" : ");
        switch (input[0]) {
        case "T":
            Todo todo = new Todo(input[2]);
            todo.isDone = Integer.parseInt(input[1]) == 1;
            todo.priority = PriorityLevel.valueOf(input[3]);
            return todo;
        case "E":
            Event event = new Event(input[2], input[3]);
            event.isDone = Integer.parseInt(input[1]) == 1;
            event.priority = PriorityLevel.valueOf(input[4]);
            return event;
        case "D":
            Deadline deadline = new Deadline(input[2], input[3]);
            deadline.isDone = Integer.parseInt(input[1]) == 1;
            deadline.priority = PriorityLevel.valueOf(input[4]);
            return deadline;
        default:
            return null;
        }
    }

    /**
     * Prints the list of tasks in the task list.
     */
    public static String findInList(String subString) {
        StringBuilder str = new StringBuilder();
        if (taskCount == 0) {
            str.append(" You have not added any tasks yet.\n");
            return str.toString();
        }

        int[] foundIndexes = new int[taskCount];
        int foundCount = 0;

        for (int i = 0; i < taskCount; i++) {
            if (taskList.get(i).description.contains(subString)) {
                foundIndexes[foundCount] = i;
                foundCount++;
            }
        }

        if (foundCount == 0) {
            str.append(" Sorry, I couldn't find any matching tasks :(\n");
        } else {
            str.append(" Here are the tasks you may be looking for!\n");
        }

        for (int i = 0; i < foundCount; i++) {
            str.append("   ")
                    .append(foundIndexes[i] + 1)
                    .append(".")
                    .append(taskList.get(foundIndexes[i]).toString())
                    .append("\n");
        }
        return str.toString();
    }

    /**
     * Returns all the existing tasks as a numbered list.
     */
    public static String getAllTasks() {
        StringBuilder str = new StringBuilder();
        if (taskCount == 0) {
            str.append(" You have not added any tasks yet.\n");
            return str.toString();
        }
        str.append(" Here are your tasks. Make things happen!\n");
        for (int i = 0; i < taskCount; i++) {
            str.append("   ")
                    .append(i + 1)
                    .append(".")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return str.toString();
    }

    /**
     * Returns the done status of the task.
     *
     * @return String done status.
     */
    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[   ]";
    }

    public String getPriorityIcon() {
        assert this.priority != null;
        switch (this.priority) {
        case LOW:
            return "* ";
        case MEDIUM:
            return "** ";
        case HIGH:
            return "*** ";
        default:
            return "";
        }
    }

    /**
     * Converts the task to string format.
     *
     * @return String task.
     */
    @Override
    public String toString() {
        if (priority != null) {
            return (this.getStatusIcon() + " " + getPriorityIcon() + this.description);
        }
        return (this.getStatusIcon() + " " + this.description);
    }
}
