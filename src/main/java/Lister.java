public class Lister {
    String[] list;
    boolean[] done;
    int count;

    public Lister() {
        count = 0;
        list = new String[100];
        done = new boolean[100];
    }

    public void add(String task) {
        list[count] = task;
        count++;
        System.out.println("added: " + task + " =.=");
    }

    public void list() {
        for (int i = 0; i < count; i++) {
            if (done[i]) {
                System.out.println(Integer.toString(i + 1) + ".[X] " + list[i]);
            } else {
                System.out.println(Integer.toString(i + 1) + ".[ ] " + list[i]);
            }
        }
    }

    public void mark(int i) {
        if (i < 0 || i > count) {
            System.out.println("Invalid entry number entered! =.=");
        } else {
            done[i - 1] = true;
            System.out.println("You've finished this task. Good for you... =.=\n  [X] " + list[i]);
        }
    }

    public void unmark(int i) {
        if (i < 0 || i > count) {
            System.out.println("Invalid entry number entered! =.=");
        } else {
            done[i - 1] = false;
            System.out.println("Marked undone. Stop slacking off... =.=\n  [ ] " + list[i]);
        }
    }
}
