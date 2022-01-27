package main.java.duke;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void exit() {
        horizontal();
        System.out.println("Bye nerd. Glad you'll be interacting with a real life human now! Haha...");
        horizontal();
    }

    public void horizontal() {
        System.out.println("--------------------------------------------------");
    }

    public void welcome(){
        horizontal();
        System.out.println("Greetings, NERD! I'm Duke");
        System.out.println("Fine, I'm programmed to be nice today. What can I do for you? :)");
        horizontal();
    }

    public String readCommand() {
        String userinput = sc.nextLine();
        return userinput;
    }


    public void showTask(int taskNum, Task t) {
        System.out.println(taskNum + ". " + t);
    }

    public void showEmptyTask() {
        System.out.println("No tasks added yet!");
    }

    public void showMarkTask(Task t) {
        System.out.println("Cool! You seemed to have been productive just like me! I've marked this task as done:");
        System.out.println(t);
    }

    public void showSucessfulAdd(Task t, int num) {
        System.out.println("Roger, I got you. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public void showRemoveTask(Task t, int num) {
        System.out.println("Noted, I have removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + num + " tasks in the list.");
    }

    public void showUnmarkTask(Task t) {
        System.out.println("Did you mess up something? Fine... I'll mark it as undone -- but I believe you can do it!:");
        System.out.println(t);
    }

    public void showError(String type) {
        switch (type) {
        case ("LoadingError"):
            System.out.println("Something went wrong with the loading of the file");
            System.out.println("Try deleting the data/tasks.txt file, but this will wipe all previous tasks stored (if any).");
            break;
        case ("UnknownCommand"):
            System.out.println("I'm sorry, but I can't execute this command!");
            break;
        case ("TodoFormatError"):
            System.out.println("Follow this format:");
            System.out.println("todo YOUR_TASK");
            break;
        case ("DeadlineFormatError"):
            System.out.println("Follow this format:");
            System.out.println("deadline YOUR_TASK /by yyyy-mm-dd TIME");
            System.out.println("eg: deadline return book /by 2019-10-15 18:00");
            break;
        case ("EventFormatError"):
            System.out.println("Follow this format:");
            System.out.println("event YOUR_TASK /at yyyy-mm-dd TIME");
            System.out.println("eg: event project meeting /at 2019-10-15 18:00");
            break;
        case ("IOException"):
            System.out.println("Something went wrong with the writing to the file");
            break;
        case ("DateTimeParseException"):
            System.out.println("Please enter the correct format for Datetime! yyyy-mm-dd HH:mm");
        }
    }

    public void showTasksLoaded(TaskList tasks) {
        System.out.println("Here are the tasks we loaded up from your previous usage!");
        ArrayList<Task> t = tasks.getTaskArr();
        if (t.size() == 0) {
            showEmptyTask();
        }

        for (int i = 0; i < t.size(); i++ ) {
            showTask(i + 1, t.get(i));
        }
    }

    public void sayGoodbye() {
        System.out.println("Bye! I'm sure you'll start talking to a real human now. Haha...");
    }

    public void showKeywords() {
        System.out.println("Here are the matching tasks in your list:");
    }
}
