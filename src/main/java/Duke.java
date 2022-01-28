import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    /**
     * Main class
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Storage storage = new Storage();
        TaskList pastTasks = storage.taskList;

        Ui ui = new Ui();
        Parser parser = new Parser();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ui.println("Hello from\n" + logo);

        ui.printMessage("""
                Hello! I'm Duke
                What can I do for you?""");

        storage.load();

        try {
            while (true) {
                String command = sc.nextLine().trim();

                if (parser.getType(command).equals("bye")) {
                    ui.printMessage("Bye. Hope to see you again soon!");
                    break;
                } else if (parser.getType(command).equals("list")) {
                    ui.printMessage(pastTasks.toString());

                } else if (parser.getType(command).equals("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    pastTasks.get(index).isDone = false;

                    ui.printMessage("OK, I've marked this task as not done yet:\n  "
                            + pastTasks.get(index)
                    );
                } else if (parser.getType(command).equals("todo")) {
                    if (command.length() == 4) {
                        throw new InputMismatchException("The description of a todo cannot be empty.");
                    }


                    pastTasks.add(parser.parseTodos(command));
                    storage.save();

                    pastTasks.add(parser.parseTodos(command));
                    storage.save();

                    ui.printMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("deadline")) {
                    pastTasks.add(parser.parseDeadlines(command));
                    storage.save();

                    ui.printMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("event")) {
                    pastTasks.add(parser.parseEvent(command));
                    storage.save();

                    ui.printMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    ));
                } else if (parser.getType(command).equals("delete")) {
                    Task removedTask = pastTasks.remove(parser.getDeleteIndex(command));

                    storage.save();

                    ui.printMessage(String.format("""
                                    Noted. I've removed this task:
                                     %s
                                    Now you have %d %s in the list.""",
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
