package DukeComponent;

import Tasks.*;

public class Command {
    String userInput;
    TaskList tasks;

    public Command(String userInput, TaskList t) {
        this.userInput = userInput;
        this.tasks = t;
    }

    public void execute() {
        String[] wordSplit = userInput.split(" ");
        String action = wordSplit[0];
        String[] split = userInput.split("/");
        int start = userInput.indexOf(" ") + 1;
        int end = userInput.lastIndexOf('/');
        String task = end == -1 ? "" : userInput.substring(start, end - 1);
        String details = split.length > 1 ? split[1].substring(3) : "";

        if (userInput.equals("bye")) {
            sayBye();
            return;
        }

        switch (action) {
        case "list":
            printList();
            break;
        case "mark":
            mark(Integer.parseInt(wordSplit[1]) - 1);
            break;
        case "unmark":
            unmark(Integer.parseInt(wordSplit[1]) - 1);
            break;
        case "todo":
            addTask(new ToDos(userInput.substring(start), false));
            break;
        case "deadline":
            addTask(new DeadLines(task, false, details));
            break;
        case "event":
            addTask(new Events(task, false, details));
            break;
        case "delete":
            deleteTask(Integer.parseInt(wordSplit[1]) - 1);
            break;
        case "find":
            find(userInput.substring(5));
            break;
        }
    }

    private void find(String description) {
        System.out.println("Here are the matching task in your list: ");
        tasks.find(description);
    }

    private void deleteTask(int indx) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.remove(indx));
        String s = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(s);
    }

    private void mark(int indx) {
        System.out.println("Nice! I've marked this task as done:");
        Task t = tasks.get(indx);
        t.setMarked(true);
        System.out.println(t);
    }

    private void unmark(int indx) {
        System.out.println("OK, I've marked this task as not done yet:");
        Task t = tasks.get(indx);
        t.setMarked(false);
        System.out.println(t);
    }

    private void addTask(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + t);
        String s = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(s);
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + tasks.get(i).toString());
        }
    }

    private void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
