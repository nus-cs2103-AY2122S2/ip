package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a taskList that supports multiple actions.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Class constructor.
     * Creates an empty list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Loads a task from storage file when Duke is started.
     * No messages printed.
     *
     * @param task task to read from file to the taskList.
     */
    public void readFromFile(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a Todo task by user command.
     *
     * @param taskInfo information of the task to add.
     * @return responding messages.
     */
    public String addTodo(String taskInfo) {
        Todo todo = new Todo(taskInfo);
        tasks.add(todo);
        return addMessage();
    }

    /**
     * Adds a Deadline task by user command.
     *
     * @param taskInfo information of the task to add.
     * @return responding messages.
     */
    public String addDdl(String taskInfo) {
        int i = taskInfo.indexOf(" /by ");
        if (i > 0 && i + 5 < taskInfo.length()) {
            try {
                LocalDate time = LocalDate.parse(taskInfo.substring(i + 5).trim());
                Deadline deadline = new Deadline(taskInfo.substring(0, i).trim(), time);
                tasks.add(deadline);
                return addMessage();
            } catch (DateTimeParseException e) {
                throw new DukeException("The description of a deadline should be \"<task> /by yyyy-mm-dd\".");
            }
        } else {
            throw new DukeException("The description of a deadline should be \"<task> /by yyyy-mm-dd\".");
        }
    }

    /**
     * Adds an Event task by user command.
     *
     * @param taskInfo information of the task to add.
     * @return responding messages.
     */
    public String addEvt(String taskInfo) {
        int i = taskInfo.indexOf(" /at ");
        if (i > 0 && i + 5 < taskInfo.length()) {
            Event event = new Event(
                    taskInfo.substring(0, i).trim(),
                    taskInfo.substring(i + 5).trim());
            tasks.add(event);
            return addMessage();
        } else {
            throw new DukeException("The description of an event should be \"<task> /at <time>\".");
        }
    }

    /**
     * Returns messages for an add task action.
     *
     * @return messages for an add task action.
     */
    private String addMessage() {
        StringBuilder message = new StringBuilder("Got it. I've added this task:\n  ");
        int n = tasks.size();
        message.append(String.format("%s\nNow you have %d task", tasks.get(n - 1), n));
        if (n > 1) {
            message.append("s");
        }
        message.append(" in the list.");
        return message.toString();
    }

    /**
     * Lists tasks in the taskList.
     *
     * @return responding messages.
     */
    public String list() {
        if (tasks.isEmpty()) {
            return "You don't have tasks listed.";
        } else {
            StringBuilder message = new StringBuilder();
            message.append(String.format("You have %d task", tasks.size()));
            if (tasks.size() > 1) {
                message.append("s");
            }
            message.append(" in the list:");

            for (int i = 0; i < tasks.size(); i++) {
                message.append(String.format("\n%d.%s", i + 1, tasks.get(i)));
            }
            return message.toString();
        }
    }

    /**
     * Changes the completion state of one task.
     *
     * @param command other information of the marking operation.
     * @param isDone state of the task to change to.
     * @return responding messages.
     */
    public String mark(String command, boolean isDone) {
        Scanner markInfo = new Scanner(command);
        if (!markInfo.hasNextInt()) {
            throw new DukeException("Please enter an index.");
        }
        int index = markInfo.nextInt() - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        tasks.set(index, tasks.get(index).mark(isDone));

        String message;
        if (isDone) {
            message = "Nice! I've marked this task as done:";
        } else {
            message = "OK, I've marked this task as not done yet:";
        }
        return message + "\n  " + tasks.get(index);
    }

    /**
     * Deletes one task from taskList.
     *
     * @param command other information of the deleting operation.
     * @return responding messages.
     */
    public String delete(String command) {
        Scanner deleteInfo = new Scanner(command);
        if (!deleteInfo.hasNextInt()) {
            throw new DukeException("Please enter an index");
        }
        int index = deleteInfo.nextInt() - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please enter a valid index.");
        }
        Task taskRemoved = tasks.remove(index);

        StringBuilder message = new StringBuilder("Noted. I've removed this task:\n  ");
        message.append(String.format("%s\nNow you have %d task", taskRemoved, tasks.size()));
        if (tasks.size() > 1) {
            message.append("s");
        }
        message.append(" in the list.");
        return message.toString();
    }

    /**
     * Finds tasks that match the keyword.
     *
     * @param keyword keyword to search for a match.
     * @return responding messages.
     */
    public String find(String keyword) {
        StringBuilder message = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            boolean isMatch = t.match(keyword);
            if (isMatch && !isFound) {
                message.append("Here are the matching tasks in your list:");
                isFound = true;
            }
            if (isMatch) {
                message.append(String.format("\n%d.%s", i + 1, t));
            }
        }
        if (isFound) {
            return message.toString();
        }
        return "There is no matching task in your list.";
    }

    /**
     * Detects duplicate tasks in taskList.
     *
     * @return responding messages.
     */
    public String duplicates() {
        StringBuilder message = new StringBuilder();
        List<Task> duplicates = new ArrayList<>();
        for (int i = 0; i < tasks.size() - 1; i++) {
            Task taskAtI = tasks.get(i);
            for (int j = i + 1; j < tasks.size(); j++) {
                Task taskAtJ = tasks.get(j);
                boolean isDuplicate = taskAtI.isDuplicate(taskAtJ);
                if (isDuplicate && duplicates.isEmpty()) {
                    message.append("Here are the duplicate tasks:");
                }
                if (isDuplicate && !duplicates.contains(taskAtI)) {
                    duplicates.add(taskAtI);
                    message.append(String.format("\n%d.%s", i + 1, taskAtI));
                }
                if (isDuplicate && !duplicates.contains(taskAtJ)) {
                    duplicates.add(taskAtJ);
                    message.append(String.format("\n%d.%s", j + 1, taskAtJ));
                }
            }
        }
        if (duplicates.isEmpty()) {
            return "There is no duplicate task in your list.";
        }
        return message.toString();
    }

    /**
     * Clears all tasks in taskList.
     *
     * @return responding messages.
     */
    public String clear() {
        tasks.clear();
        return "You now have no task listed.";
    }

    /**
     * Provides user guide.
     *
     * @return responding messages.
     */
    public String help() {
        return "# User Guide\n"
                + "`help`: See user guide.\n"
                + "`list`: Lists all the tasks.\n"
                + "`todo <description>`: Adds a todo task.\n"
                + "`deadline <description> /by yyyy-mm-dd`: Adds a task with a deadline.\n"
                + "`event <description> /at <time>`: Adds a task with a time period.\n"
                + "`mark <index>`: Marks a task as completed by index.\n"
                + "`unmark <index>`: Marks a task as uncompleted by index.\n"
                + "`delete <index>`: Deletes a task by index.\n"
                + "`find <keyword>`: Finds tasks that match with the keyword given.\n"
                + "`duplicate`: Finds duplicate tasks.\n"
                + "`save`: Saves all changes.\n"
                + "`clear`: Clears all tasks."

                + "* Duke does not save automatically. Please `save` frequently to avoid loss of information.\n"
                + "* Tasks are listed in the format of\n"
                + "  `[task type][completed] task description (time)`.\n"
                + "  - task type: `T` - `todo`; `D` - `deadline`; `E` - `event`.\n"
                + "  - completed: `X` - completed; ` ` - uncompleted.";
    }

    @Override
    public String toString() {
        StringBuilder fileContent = new StringBuilder();
        for (Task t : tasks) {
            fileContent.append(t.fileFormat());
        }
        return fileContent.toString();
    }
}
