package duke.stack;

import duke.task.TaskList;

import java.util.LinkedList;

public class CallStack {
    private int maxCapacity;
    private final LinkedList<TaskList> stateStack;

    public CallStack(int maxSize) {
        maxCapacity = maxSize;
        stateStack = new LinkedList<>();
    }

    public boolean isEmpty() {
        return stateStack.isEmpty();
    }

    public void pushState(TaskList newState) {
        if (stateStack.size() == maxCapacity) {
            stateStack.poll();
        }
        stateStack.push(newState.cloneSelf());
    }

    public TaskList popState() {
        if (isEmpty()) {
            return null;
        }
        return stateStack.pop();
    }
}
