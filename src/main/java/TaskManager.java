import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskManager(){}
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addTask(String s) throws DukeException{
        try {
            Task t = Parser.parseToTask(s);
            tasks.add(t);
            //Ui.showAddedTask(t, tasks.size());

            return true;
        } catch (DukeException e){
            String message = e.getMessage();
            throw new DukeException(message + "/nUnable to add Task!");
        }
    }
    public void addTask(Task t){
        this.tasks.add(t);
    }

    public boolean deleteTask(int index){
        if (tasks.size() == 0){
            //Ui.showDeleteEmptyList();
            return false;
        } else {
            if (index < 0 || index >= tasks.size()){
                return false;
            } else {
                Task t = tasks.get(index);
                tasks.remove(index);
                return true;
            }
        }
    }

    public boolean markTaskDone(int index){
        if (tasks.size() <= 0){
            return false;
        } else {
            if (index < 0 || index >= tasks.size()){
                return false;
            } else {
                Task t = tasks.get(index);
                if (t.done == ' ') {
                    t.markDone();
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean markTaskUndone(int index){
        if (tasks.size() <= 0){
            return false;
        } else {
            if (index < 0 || index >= tasks.size()) {
                return false;
            } else {
                Task t = tasks.get(index);
                if (t.done == 'X') {
                    t.markUndone();
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public ArrayList<Task> getTaskList(){
        return this.tasks;
    }
    public Task getTask(int index) {
        if (size() == 0){
            return null;
        }

        if (index < 0 || index >= size()){
            return null;
        }

        return tasks.get(index);
    }

    public int size(){
        return tasks.size();
    }
}
