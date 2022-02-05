import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    }

    public void showLoadingError() {
        System.out.println("A loading error has occurred.");
    }

    public void showLine() {
        System.out.println("_______");
    }

    public void showDeadline(Deadline deadline, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    public void showEvent(Event event, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    public void showTodo(Todo todo, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        if (tasks.getSize() == 1) {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    public void showDelete(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        if (tasks.getSize() == 2) {
            System.out.println("Now you have " + (tasks.getSize() - 1) + " task in the list.");
        } else {
            System.out.println("Now you have " + (tasks.getSize() - 1) + " tasks in the list.");
        }
    }

    public void showExit(Storage storage, TaskList tasks) throws DukeException {
        storage.save(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTasks(TaskList tasks) {
        if(tasks.getSize() == 0) {
            System.out.println("Now you have 0 tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.getSize(); i++) {
                System.out.println( Integer.toString(i + 1) + "." + tasks.get(i));
            }
        }
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
}
