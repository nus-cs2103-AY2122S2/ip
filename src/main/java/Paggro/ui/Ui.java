import java.util.Scanner;

import java.util.ArrayList;

public class Ui {
    Scanner sc;

    public Ui () {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    Hi I'm PaggroBot =.=\n    What do you want? =.=");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("    Oh finally. Please don't come back anytime soon. =.=");
    }

    public void showLine() {
        System.out.println("   ________________________________________");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isDone) {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            } else {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            }
        }
    }

    public void showEmptyDate() {
        System.out.println("    Nothing happening on this date... =.=");
    }

    public void showMarked(Task task) {
        System.out.println("    You've finished this task. Good for you... =.=\n      " + task);
    }

    public void showUnmarked(Task task) {
        System.out.println("    Marked undone. Stop slacking off... =.=\n      " + task);
    }

    public void showDeleted(Task task) {
        System.out.println("    Fine. I've removed this task:\n      " + task.toString());
    }

    public void showAdded(Task task) {
        System.out.println("    Fine I'll add this task in:\n      " + task);
    }

    public void showNumber(int size) {
        if (size == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + size + " tasks in the list. =.=");
        }
    }

}
