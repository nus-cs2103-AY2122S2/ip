package bob.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;

public class PriorityListCommand extends ListCommand {
    public PriorityListCommand() {
        super(false);
    }

    /**
     * {@inheritDoc}
     * Lists out the tasks present in the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        if (tasks.isEmpty()) {
            return ui.free();
        }
        StringBuilder reply = new StringBuilder();
        reply.append(ui.preListReply() + "\n");
        List<List<Task>> priorityLists = splitTasksByPriority(tasks);
        String priorityString = getPrioritizedReply(priorityLists);
        reply.append(priorityString);
        reply.append(ui.postListFace());
        return reply.toString();
    }

    private String getPrioritizedReply(List<List<Task>> priorityLists) {
        StringBuilder reply = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (priorityLists.get(i).isEmpty()) {
                continue;
            }
            if (i != 3) {
                reply.append("\n" + Task.Priority.values()[i].name() + "\n");
            } else {
                reply.append("\n" + "NO PRIORITY\n");
            }
            List<Task> pList = priorityLists.get(i);
            pList.forEach(x -> {
                reply.append(String.format("%o. %s\n", pList.indexOf(x) + 1,
                        x.printStatus()));
            });
        }
        return reply.toString();
    }

    private static List<List<Task>> splitTasksByPriority(TaskList tasks) {
        List<Task> highTasks = new ArrayList<>();
        List<Task> mediumTasks = new ArrayList<>();
        List<Task> lowTasks = new ArrayList<>();
        List<Task> noneTasks = new ArrayList<>();
        List<List<Task>> result = new ArrayList<>();
        for (int i = 1; i <= tasks.size(); i++) {
            Task currentTask = tasks.getTask(i - 1);
            if (currentTask.isHigh()) {
                highTasks.add(currentTask);
            } else if (currentTask.isMedium()) {
                mediumTasks.add(currentTask);
            } else if (currentTask.isLow()) {
                lowTasks.add(currentTask);
            } else if (currentTask.isNone()) {
                noneTasks.add(currentTask);
            }
        }
        Stream<List<Task>> streamOfTaskLists = Stream.of(highTasks, mediumTasks, lowTasks, noneTasks);
        streamOfTaskLists.forEach(x -> {
            Collections.sort(x);
            result.add(x);
        });
        return result;
    }
}
