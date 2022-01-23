import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    ArrayList<Task> tasks;

    Duke(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }

    private void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    private void addToDo(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n"
                + task + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addDeadline(Deadline deadline) {
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void addEvent(Event event) {
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n"
                + event + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    private void deleteTask(int number) {
        Task taskToDelete = tasks.get(number - 1);
        tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    public void run() throws DukeException, IOException {

        Parser parser = new Parser(tasks);
        parser.run();

    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke(Storage.readFile());
        duke.run();
    }
}
