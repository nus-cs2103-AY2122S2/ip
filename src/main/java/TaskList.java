import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> objectives;

    public TaskList() {
        this.objectives = new ArrayList<>();
    }

    public TaskList(List<Task> objectives) {
        this.objectives = objectives;
    }

    public void add(Task obj) {
        objectives.add(obj);
    }

    public void list(Ui ui) {
        Integer count = 1;
        for (Task t : objectives) {
            ui.print(count+1);
            ui.print(t);
        }
    }

    public List<Task> getObjectives() {
        return this.objectives;
    }

    public Task getTask(int i) {
        return this.objectives.get(i);
    }

    public Integer getSize() {
        return objectives.size();
    }

    public void mark(Integer index, boolean mark) {
        if (mark) {
            objectives.get(index).mark();
        } else {
            objectives.get(index).unmarked();
        }
    }

    public void delete(Integer index) {
        this.objectives.remove((int) index);
    }

    public List<String> serializedList () {
        List<String> encodedList = new ArrayList<>();
        for (Task task : objectives) {
            encodedList.add(task.serialize());
        }
        return encodedList;
    }
}
