package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
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
     * @param inputs Keyword for searching tasks.
     * @return An ArrayList of the Tasks that contains the specific keyword.
     */
    public ArrayList<Task> findTasks(String[] inputs) {
        ArrayList<Task> resultTasks = new ArrayList<>();
        ArrayList<Integer> noTasks = new ArrayList<>();
        for (String current : inputs) {
            for (int i = 0; i < this.tasks.size(); i++) {
                Task check = this.tasks.get(i);
                String name = check.getName();
                if (name.contains(current)) {
                    noTasks.add(i);
                }
            }

        }
        for (int found : noTasks) {
            resultTasks.add(this.tasks.get(found));
        }
        return resultTasks;
    }
}