package duke.stack;

import duke.task.TaskList;

import java.util.LinkedList;

public class CallStack {
    private static int MAX_CAPACITY;
    private final LinkedList<TaskList> stateStack;

    public CallStack(int maxSize) {
        MAX_CAPACITY = maxSize;
        stateStack = new LinkedList<>();
    }

    public boolean isEmpty() {
        return stateStack.isEmpty();
    }

    public void pushState(TaskList newState) {
        if (stateStack.size() == MAX_CAPACITY) {
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
