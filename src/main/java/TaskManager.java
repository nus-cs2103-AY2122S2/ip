import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskManager(){}
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addTask(String s){
        try {
            Task t = Task.newTask(s);
            tasks.add(t);
            Ui.showAddedTask(t, tasks.size());

            return true;
        } catch (InvalidTaskDescriptionException i){
            Ui.showInvalidTaskNameError();
            return false;
        } catch (InvalidTaskDataTimeException u){
            Ui.showInvalidDateTimeError();
            return false;
        } catch (InvalidTaskTypeException t){
            System.out.println(t.toString());
            return false;
        }
    }
    public void addTask(Task t){
        this.tasks.add(t);
    }

    public boolean deleteTask(int index){
        if (tasks.size() == 0){
            Ui.showDeleteEmptyList();
            return false;
        } else {
            if (index <= 0 || index > tasks.size()){
                Ui.showDeleteOutOfBounds(tasks.size());
                return false;
            } else {
                Task t = tasks.get(index-1);
                tasks.remove(index-1);
                Ui.showDeletedTask(t, tasks.size());
                return true;
            }
        }
    }

    public boolean markTaskDone(int index){
        if (tasks.size() <= 0){
            Ui.showMarkEmptyList();
            return false;
        } else {
            if (index <= 0 || index > tasks.size()){
                Ui.showMarkOutOfBounds();
                return false;
            } else {
                Task t = tasks.get(index - 1);
                if (t.done == ' ') {
                    t.markDone();
                    Ui.showMarked(t);
                    return true;
                } else {
                    Ui.showMarkNotNeeded(t);
                    return false;
                }
            }
        }
    }

    public boolean markTaskUndone(int index){
        if (tasks.size() <= 0){
            Ui.showUnmarkEmptyList();
            return false;
        } else {
            if (index <= 0 || index > tasks.size()) {
                Ui.showUnmarkOutOfBounds();
                return false;
            } else {
                Task t = tasks.get(index - 1);
                if (t.done == 'X') {
                    t.markunDone();
                    Ui.showUnmarked(t);
                    return true;
                } else {
                    Ui.showUnmarkNotNeeded(t);
                    return false;
                }
            }
        }
    }

    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }

    public int size(){
        return tasks.size();
    }
}
