package duke;

import java.util.Scanner;

public class Ui {

    Ui() {}

    public static String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________");
    }

    public void showList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in your list~");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showLoadingError(String message) {}

}
