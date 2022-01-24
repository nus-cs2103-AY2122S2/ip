package duke;

import java.io.IOException;
import java.util.Scanner;

public class Ui {

    TaskList tasks;

    Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    enum Commands {
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find");

        final String command;

        Commands(String command) {
            this.command = command;
        }
    }

    void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm duke.Duke\n" +
                "What can I do for you?";
        System.out.println(intro);

        String cmd = sc.next();
        Parser parser = new Parser(tasks);

        while (!cmd.equals("bye")) {
            if (cmd.equals(Ui.Commands.LIST.command)) {
                parser.listTasks();

            } else if (cmd.equals(Ui.Commands.MARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                parser.mark(taskNumber);

            } else if (cmd.equals(Ui.Commands.UNMARK.command)) {
                int taskNumber = 0;
                taskNumber = sc.nextInt();
                parser.unmark(taskNumber);

            } else if (cmd.equals(Ui.Commands.TODO.command)) {
                String description = sc.nextLine();
                if (description.length() == 0) {
                    throw new DukeException("OOPS!!! The description of " +
                            "a todo cannot be empty.");
                }
                ToDo todo = new ToDo(description);
                parser.addToDo(todo);

            } else if (cmd.equals(Ui.Commands.DEADLINE.command)) {
                String info = sc.nextLine();
                String[] split = info.split(" /by ");
                String description = split[0];
                String time = split[1];
                Deadline deadline = new Deadline(description, time);
                parser.addDeadline(deadline);

            } else if (cmd.equals(Ui.Commands.EVENT.command)) {
                String info = sc.nextLine();
                String[] split = info.split(" /at ");
                String description = split[0];
                String time = split[1];
                Event event = new Event(description, time);
                parser.addEvent(event);

            } else if (cmd.equals(Ui.Commands.DELETE.command)) {
                int taskNumber = sc.nextInt();
                parser.deleteTask(taskNumber);

            } else if(cmd.equals(Ui.Commands.FIND.command)) {
                String keyword = sc.next();
                parser.findTask(keyword);
                sc.nextLine();

            } else {
                throw new DukeException("OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
            }
            Storage.saveToFile(tasks);
            cmd = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
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
}
