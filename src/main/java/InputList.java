import java.util.ArrayList;

public class InputList {

    private int count;
    private ArrayList<Task> tasks;

    public InputList() {
        count = 0;
        tasks = new ArrayList<Task>(100);
    }

    public void printList() {
        for(int i = 0; i < count; i++) {
            System.out.print(i + 1);
            System.out.println(". " + tasks.get(i).toString());
        }
    }

    public void add(Task newTask) {
        tasks.add(newTask);
        count++;
    }

    public void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index - 1).toString());
        tasks.remove(index - 1);
        count--;
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
        return this.count;
    }
}
