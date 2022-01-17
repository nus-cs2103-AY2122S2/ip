public class Lister {
    Task[] tasks;
    boolean[] done;


    public Lister() {
        tasks = new Task[100];
    }

    public void add(Task task) {
        tasks[task.id - 1] = task;
        System.out.println("added: " + task + " =.=");
    }

    public void list() {
        for (int i = 0; i < Task.count; i++) {
            if (tasks[i].isDone) {
                System.out.println(Integer.toString(i + 1) + ".[X] " + tasks[i]);
            } else {
                System.out.println(Integer.toString(i + 1) + ".[ ] " + tasks[i]);
            }
        }
    }

    public void mark(int i) {
        if (i < 0 || i > Task.count) {
            System.out.println("Invalid entry number entered! =.=");
        } else {
            tasks[i - 1].markDone();
            System.out.println("You've finished this task. Good for you... =.=\n  [X] " + tasks[i - 1]);
        }
    }

    public void unmark(int i) {
        if (i < 0 || i > Task.count) {
            System.out.println("Invalid entry number entered! =.=");
        } else {
            tasks[i - 1].markUndone();
            System.out.println("Marked undone. Stop slacking off... =.=\n  [ ] " + tasks[i - 1]);
        }
    }
}
