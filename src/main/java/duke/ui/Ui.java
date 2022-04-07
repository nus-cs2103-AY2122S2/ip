package duke.ui;

public class Ui {
    public Ui() { }

    public String hello() {
        return ("Hello! Welcome, I'm Duke" + "\n" + "What can I do for you?");
    }

    public String bye() {
        return ("Bye. Hope to see you again soon!");
    }

    public void showError(Exception e) {
        System.out.println(e.getMessage());
    }
}
