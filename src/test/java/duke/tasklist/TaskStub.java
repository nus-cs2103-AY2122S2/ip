package duke.tasklist;

import duke.task.tasks.ITask;

public class TaskStub implements ITask {
    @Override
    public String switchMark(String instr) {
        return "";
    }

    @Override
    public String encode() {
        return "";
    }

    @Override
    public boolean hasWord(String word) {
        return true;
    }

    @Override
    public TaskStub cloneSelf() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object otherTask) {
        if (otherTask instanceof ITask) {
            return otherTask.toString().equals(this.toString());
        }
        return false;
    }
}
