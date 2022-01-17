public class Lister {
    Task[] tasks;
    boolean[] done;
    int count;


    public Lister() {
        tasks = new Task[100];
        count = 0;
    }

    public void add(Task task) {
        tasks[count] = task;
        count++;
        System.out.println("   ________________________________________");
        System.out.println("    Fine I'll add this task in: \n      " + task);
        if (count == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + count + " tasks in the list. =.=");
        }
        System.out.println("   ________________________________________");
    }

    public void list() {
        System.out.println("   ________________________________________");
        for (int i = 0; i < count; i++) {
            if (tasks[i].isDone) {
                System.out.println("    " + Integer.toString(i + 1) + "." + tasks[i]);
            } else {
                System.out.println("    " + Integer.toString(i + 1) + "." + tasks[i]);
            }
        }
        System.out.println("   ________________________________________");
    }

    public void mark(int i) {
        System.out.println("   ________________________________________");
        if (i < 0 || i > count) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            tasks[i - 1].markDone();
            System.out.println("    You've finished this task. Good for you... =.=\n      " + tasks[i - 1]);
        }
        System.out.println("   ________________________________________");
    }

    public void unmark(int i) {
        System.out.println("   ________________________________________");
        if (i < 0 || i > count) {
            System.out.println("    Invalid entry number entered! =.=");
        } else {
            tasks[i - 1].markUndone();
            System.out.println("    Marked undone. Stop slacking off... =.=\n      " + tasks[i - 1]);
        }
        System.out.println("   ________________________________________");
    }
}
