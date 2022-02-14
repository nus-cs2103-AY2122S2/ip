import java.util.ArrayList;

class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {

        this.tasks = new ArrayList<>();
    }


    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){

        return this.tasks;
    }

    /**
     *
     * @param e
     */
    public void addTask(Task e){
        tasks.add(e);
    }

    /**
     *
     * @param taskNum
     */
    public void deleteTask(int taskNum) {
        System.out.println(" Noted. I've removed this task:");
        tasks.remove(taskNum);
        System.out.println("Now you have" + tasks.size() + "tasks in the list.");
    }

    /**
     *
     * @param taskNo
     */
    /** Changes task status to done*/
    public void markDone(int taskNo) {
        tasks.get(taskNo).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNo).getStatusIcon() + tasks.get(taskNo).getName());

    }

    /**
     *
     * @param taskNo
     */
    /** Changes task status to not done yet*/
    public void unmark(int taskNo) {
        tasks.get(taskNo).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNo).getStatusIcon() + tasks.get(taskNo).getName());
    }

    /**
     * Finds the tasks that contains the keyword provided.
     * @param keyword Keyword for searching a task.
     * @return An ArrayList of the Tasks that contains the specific keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> relatedTasks = new ArrayList<>();
        for (Task current : this.tasks) {
            String taskName = current.getName();
            if (taskName.contains(keyword)) {
                relatedTasks.add(current);
            }
        }
        return relatedTasks;
    }
}