package johnny;

import java.util.ArrayList;

public class InputList {

    private ArrayList<Task> tasks;

    public InputList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void writeToFile(Storage store) {
        store.writeTasks(this.tasks);
    }

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.get(i).toString());
        }
    }

    public void add(Task newTask) {
        tasks.add(newTask);
    }

    public void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
    }

    public void mark(int index) {
        tasks.get(index - 1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public void unmark(int index) {
        tasks.get(index - 1).markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public int getCount() {
        return tasks.size();
    }
}
