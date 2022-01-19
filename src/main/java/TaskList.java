import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void addTask(Task e){
        tasks.add(e);
    }
    
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public void markDone(int taskNo) {
        tasks.get(taskNo).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNo).getStatusIcon() + tasks.get(taskNo).getName());

    }

    public void unmark(int taskNo) {
        tasks.get(taskNo).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNo).getStatusIcon() + tasks.get(taskNo).getName());
    }
    
}