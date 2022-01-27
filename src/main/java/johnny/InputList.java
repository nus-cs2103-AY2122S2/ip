package johnny;

import java.util.ArrayList;

public class InputList {

    private ArrayList<Task> tasks;

    public InputList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Writes the current list of tasks to
     * the text file
     *
     * @param store  Storage object.
     */
    public void writeToFile(Storage store) {
        store.writeTasks(this.tasks);
    }

    /**
     * Prints the current list of tasks
     *
     */
    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.get(i).toString());
        }
    }

    /**
     * Adds new task to the list
     *
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Deletes task at a particular index from
     * the list
     *
     * @param index  Index of task to delete
     */
    public void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
    }

    /**
     * Marks task at a particular index from
     * the list as done
     *
     * @param index  Index of task to mark as done
     */
    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString());
    }

    /**
     * Unmarks task at a particular index from
     * the list as not done yet
     *
     * @param index  Index of task to unmark
     */
    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public void searchEventAndPrint(String searchString) {
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getDescription().contains(searchString)) {
                System.out.print(i + 1);
                System.out.println(". " + tasks.get(i).toString());
            }
        }
    }

    /**
     * Returns number of tasks in list
     *
     * @return Number of tasks in list
     * @throws IllegalArgumentException  If zone is <= 0.
     */
    public int getCount() {
        return tasks.size();
    }
}
