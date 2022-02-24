package duke.commands;

import duke.tasks.*;

import java.util.PriorityQueue;

/**
 * Command that sorts and displays all Task in TaskList.
 */
public class SortCommand extends Command {
    private PriorityQueue<Deadline> deadlineList = new PriorityQueue<>(new TaskDateComparator());
    private PriorityQueue<Event> eventList = new PriorityQueue<>(new TaskDateComparator());
    private PriorityQueue<Todo> todoList = new PriorityQueue<>(new TaskDateComparator());

    @Override
    public String execute() {
        initializeQueues();
        String sortedDeadlines = "These are your upcoming Deadlines: \n" + priorityQueueStringFormat(deadlineList);
        String sortedEvents = "These are your upcoming Events: \n" + priorityQueueStringFormat(eventList);
        String sortedTodos = "These are your upcoming Todos: \n" + priorityQueueStringFormat(todoList);
        return sortedDeadlines + "\n" + sortedEvents + "\n" + sortedTodos;
    }

    private static String priorityQueueStringFormat(PriorityQueue<? extends Task> pq) {
        if(pq.isEmpty()) {
            return "You have no such tasks\n";
        }

        int index = 1;
        StringBuilder numberedPriorityQueue = new StringBuilder();
        while (!pq.isEmpty()) {
            numberedPriorityQueue.append(String.format("%d: %s\n", index, pq.poll().toString()));
            index++;
        }
        return numberedPriorityQueue.toString();
    }

    private void initializeQueues() {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.getTask(i);
            if (currTask instanceof Deadline) {
                deadlineList.add((Deadline) currTask);
            } else if (currTask instanceof Event) {
                eventList.add((Event) currTask);
            } else {
                todoList.add((Todo) currTask);
            }
        }
    }
}
