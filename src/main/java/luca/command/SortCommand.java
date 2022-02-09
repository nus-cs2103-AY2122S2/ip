package luca.command;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import luca.storage.Storage;
import luca.task.Deadline;
import luca.task.Event;
import luca.task.Task;
import luca.task.TaskList;
import luca.task.TaskType;

/**
 * Sorts the given type of tasks either by ascending or the descending order.
 */
public class SortCommand extends Command {

    /** Type of the task to be sorted. */
    private TaskType taskType;

    /** Records the order of the sort command: ascending or descending. */
    private Boolean isAscending;

    /**
     * Creates a sort command.
     *
     * @param taskType type of task.
     * @param isAscending order of the sort.
     */
    public SortCommand(TaskType taskType, Boolean isAscending) {
        super(CommandType.SORT);
        this.taskType = taskType;
        this.isAscending = isAscending;
    }

    /**
     * Filters the tasks by deadline and outputs the deadline by ascending order.
     *
     * @param taskList list of tasks.
     * @return sorted list of Deadlines.
     */
    private List<Deadline> getSortedDeadline(TaskList taskList) {
        List<Deadline> deadlines = taskList.stream().filter(task -> task.getType() == taskType)
                .map(task -> (Deadline) task)
                .sorted(Comparator.comparing(Deadline::getBy))
                .collect(Collectors.toList());
        return deadlines;
    }

    /**
     * Filters the tasks by Events and outputs the deadline by ascending order.
     *
     * @param taskList list of tasks.
     * @return sorted list of Events.
     */
    private List<Event> getSortedEvents(TaskList taskList) {
        List<Event> events = taskList.stream().filter(task -> task.getType() == taskType)
                .map(task -> (Event) task)
                .sorted(Comparator.comparing(Event::getStart))
                .collect(Collectors.toList());
        return events;
    }

    /**
     * Returns the string containing list of tasks.
     *
     * @param sortedTasks task
     * @return list of tasks as a string.
     */
    private String formatTasks(List<? extends Task> sortedTasks) {

        String taskTypeString = ((taskType == TaskType.DEADLINE) ? "deadlines" : "events");
        String order = isAscending ? "ascending" : "descending";

        if (sortedTasks.size() == 0) {
            return "There are no " + taskTypeString + " in your list:\n";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Here are the " + taskTypeString + " in the " + order + " order:\n");
        IntStream.range(0, sortedTasks.size())
                .forEach(index -> stringBuilder.append((index + 1) + "."
                        + sortedTasks.get(index).toString() + "\n"));

        return stringBuilder.toString().trim();
    }

    /**
     * Sorts the specified type of task by the given order and outputs
     * the formatter result.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return sorted list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<? extends Task> sortedTasks;

        assert (taskType == TaskType.DEADLINE || taskType == TaskType.EVENT) : "Sort Task type is invalid.";

        if (taskType == TaskType.DEADLINE) {
            sortedTasks = getSortedDeadline(taskList);
        } else {
            sortedTasks = getSortedEvents(taskList);
        }

        // reverses the order of tasks if specified to be descending
        if (!isAscending) {
            Collections.reverse(sortedTasks);
        }

        return formatTasks(sortedTasks);
    }
}
