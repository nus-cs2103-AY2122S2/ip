import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> list= new ArrayList<Task>();

    public TaskManager(){}

    public boolean addTask(String s){
        try {
            Task t = Task.newTask(s);
            list.add(t);
            UI.showAddedTask(t,list.size());

            return true;
        } catch (InvalidTaskDescriptionException i){
            UI.showInvalidTaskNameError();
            return false;
        } catch (InvalidTaskDataTimeException u){
            UI.showInvalidDateTimeError();
            return false;
        } catch (InvalidTaskTypeException t){
            System.out.println(t.toString());
            return false;
        }
    }
    public void addTask(Task t){
        this.list.add(t);
    }

    public boolean deleteTask(int index){
        if (list.size() == 0){
            UI.showDeleteEmptyList();
            return false;
        } else {
            if (index <= 0 || index >list.size()){
                UI.showDeleteOutOfBounds(list.size());
                return false;
            } else {
                Task t = list.get(index-1);
                list.remove(index-1);
                UI.showDeletedTask(t,list.size());
                return true;
            }
        }
    }

    public boolean markTaskDone(int index){
        if (list.size() <= 0){
            UI.showMarkEmptyList();
            return false;
        } else {
            if (index <= 0 || index > list.size()){
                UI.showMarkOutOfBounds();
                return false;
            } else {
                Task t = list.get(index - 1);
                if (t.done == ' ') {
                    t.markDone();
                    UI.showMarked(t);
                    return true;
                } else {
                    UI.showMarkNotNeeded(t);
                    return false;
                }
            }
        }
    }

    public boolean markTaskUndone(int index){
        if (list.size() <= 0){
            UI.showUnmarkEmptyList();
            return false;
        } else {
            if (index <= 0 || index > list.size()) {
                UI.showUnmarkOutOfBounds();
                return false;
            } else {
                Task t = list.get(index - 1);
                if (t.done == 'X') {
                    t.markunDone();
                    UI.showUnmarked(t);
                    return true;
                } else {
                    UI.showUnmarkNotNeeded(t);
                    return false;
                }
            }
        }
    }

    public ArrayList<Task> getTaskList(){
        return this.list;
    }

    public int size(){
        return list.size();
    }
}
