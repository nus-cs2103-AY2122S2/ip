package DukeComponent;

import Tasks.Tasks;
import Tasks.ToDos;
import Tasks.Events;
import Tasks.DeadLines;

/**
 * A class that belongs to the DukeComponent Package.
 * This class encapsulates the possible user commands from Duke.
 */
public class Command {
    private final String userInput;
    /**
     * {@link DukeComponent.TaskList}
     */
    private final TaskList tasks;

    /**
     * Constructor for Command class.
     * @param userInput User input as a type String.
     * @param t Provides access to TaskList for some manipulation in the Duke program.
     */
    public Command(String userInput, TaskList t) {
        this.userInput = userInput;
        this.tasks = t;
    }

    /**
     * Execution of command logic in Duke.
     */
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
        case "list" -> printList();
        case "mark" -> mark(Integer.parseInt(wordSplit[1]) - 1);
        case "unmark" -> unmark(Integer.parseInt(wordSplit[1]) - 1);
        case "todo" -> addTask(new ToDos(userInput.substring(start), false));
        case "deadline" -> addTask(new DeadLines(task, false, details));
        case "event" -> addTask(new Events(task, false, details));
        case "delete" -> deleteTask(Integer.parseInt(wordSplit[1]) - 1);
        case "find" -> find(userInput.substring(5));
        }
    }

    /**
     * Finds Task that matches that description from the TaskList.
     * @param description Description to be used for matching.
     */
    private void find(String description) {
        System.out.println("Here are the matching task in your list: ");
        tasks.find(description);
    }


    /**
     * Deletes the task in the TaskList.
     * @param i 0-based index for deletion of Task in the TaskList.
     */
    private void deleteTask(int i) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.remove(i));
        String s = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(s);
    }

    /**
     * Marks the task in the TaskList.
     * @param i 0-based index for users to mark the Task in the TaskList.
     */
    private void mark(int i) {
        System.out.println("Nice! I've marked this task as done:");
        Tasks t = tasks.get(i);
        t.setMarked(true);
        System.out.println(t);
    }

    /**
     * Un-marks the task in the TaskList.
     * @param i  0-based index for users to unmark the task in the TaskList.
     */
    private void unmark(int i) {
        System.out.println("OK, I've marked this task as not done yet:");
        Tasks t = tasks.get(i);
        t.setMarked(false);
        System.out.println(t);
    }

    /**
     * Adds task to the TaskList.
     * @param t Task to be added.
     */
    private void addTask(Tasks t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + t);
        String s = String.format("Now you have %d tasks in the list.", tasks.size());
        System.out.println(s);
    }

    /**
     * Prints the TaskList in the Ui.
     */
    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String num = String.format("%d. ", i + 1);
            System.out.println(num + tasks.get(i).toString());
        }
    }

    /**
     * Prints the bye Ui for the user.
     */
    private void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
