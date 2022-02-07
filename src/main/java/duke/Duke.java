package duke;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class.
 *
 * @author sibinhho99-nus
 */
public class Duke {
    /**
     * Main method.
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage();
        TaskList pastTasks = storage.getTaskList();

        Ui ui = new Ui();
        Parser parser = new Parser();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ui.println("Hello from\n" + logo);

        ui.printMessage("Hello! I'm Duke\n"
                + "What can I do for you?");

        storage.load();

        try {
            while (true) {
                String command = sc.nextLine().trim();

                if (parser.getType(command).equals("bye")) {
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (parser.getType(command).equals("list")) {
                    ui.printMessage(pastTasks.toString());
                } else if (parser.getType(command).equals("find")) {
                    TaskList results = new TaskList();
                    String nameOfTaskToFind = Parser.getTaskToFindName(command);

                    for (int i = 0; i < pastTasks.size(); i++) {
                        Task currentTask = pastTasks.get(i);
                        if (currentTask.getName().contains(nameOfTaskToFind)) {
                            results.add(currentTask);
                        }
                    }

                    ui.printMessage(results.toString());
                } else if (parser.getType(command).equals("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    pastTasks.get(index).mark(true);

                    ui.printMessage("OK, I've marked this task as done:\n  "
                            + pastTasks.get(index)
                    );
                } else if (parser.getType(command).equals("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    pastTasks.get(index).mark(false);

                    ui.printMessage("OK, I've marked this task as not done yet:\n  "
                            + pastTasks.get(index)
                    );
                } else if (parser.getType(command).equals("todo")) {
                    if (command.length() == 4) {
                        throw new InputMismatchException("The description of a todo cannot be empty.");
                    }


                    pastTasks.add(parser.parseTodos(command));
                    storage.save();

                    ui.printMessage(String.format("Got it. I've added this task:\n"
                                    + "%s\n"
                                    + "Now you have %d %s in the list.",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("deadline")) {
                    pastTasks.add(parser.parseDeadlines(command));
                    storage.save();

                    ui.printMessage(String.format("Got it. I've added this task:\n"
                                    + "%s\n"
                                    + "Now you have %d %s in the list.",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("event")) {
                    pastTasks.add(parser.parseEvent(command));
                    storage.save();

                    ui.printMessage(String.format("Got it. I've added this task:\n"
                                    + "%s\n"
                                    + "Now you have %d %s in the list.",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("delete")) {
                    Task removedTask = pastTasks.remove(Parser.getDeleteIndex(command));

                    storage.save();

                    ui.printMessage(String.format("Noted. I've removed this task:\n"
                                    + "%s\n"
                                    + "Now you have %d %s in the list.",
                            removedTask,
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else {
                    throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception e) {
            ui.printMessage(":( OOPS!!! " + e.getMessage());
        }

        sc.close();
    }


}
