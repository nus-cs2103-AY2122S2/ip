import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {
    /**
     * Main class
     */
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(buildMessage("""
                Hello! I'm Duke
                What can I do for you?""")
        );

        Scanner sc = new Scanner(System.in);

        TaskList pastTasks = new TaskList();

        pastTasks.load();

        try {
            while (true) {
                String command = sc.nextLine().trim();

                if (command.equals("bye")) {
                    System.out.println(buildMessage("Bye. Hope to see you again soon!"));
                    break;
                } else if (command.equals("list")) {
                    System.out.println(buildMessage(pastTasks.toString()));

                } else if (command.split(" ")[0].equals("mark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    pastTasks.get(index).isDone = true;
                    System.out.println(buildMessage("Nice! I've marked this task as done: \n  "
                            + pastTasks.get(index))
                    );

                } else if (command.split(" ")[0].equals("unmark")) {
                    int index = Integer.parseInt(command.split(" ")[1]) - 1;
                    pastTasks.get(index).isDone = false;

                    System.out.println(buildMessage("OK, I've marked this task as not done yet:\n  "
                            + pastTasks.get(index)
                    ));
                } else if (command.split(" ")[0].equals("todo")) {
                    if (command.length() == 4) {
                        throw new InputMismatchException("The description of a todo cannot be empty.");
                    }

                    // Extract name from todo command, eg. todo borrow book
                    String name = command.substring(5);
                    pastTasks.add(new TaskToDos(false, name));
                    pastTasks.save();

                    System.out.println(buildMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    )));
                } else if (command.split(" ")[0].equals("deadline")) {
                    // Extract name and time to be done by from deadline command, eg. deadline return book /by Sunday.
                    String temp = command.substring(9);
                    String name = temp.split("/")[0].trim();
                    String doneBy = temp.split("/")[1].substring(3).trim();

                    pastTasks.add(new TaskDeadlines(false, name, doneBy));
                    pastTasks.save();

                    System.out.println(buildMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    )));
                } else if (command.split(" ")[0].equals("event")) {
                    // Extract name and time to be done by from event command, eg. event project meeting /at Mon 2-4pm
                    String temp = command.substring(6);
                    String name = temp.split("/")[0].trim();
                    String startAt = temp.split("/")[1].substring(3).trim();

                    pastTasks.add(new TaskEvents(false, name, startAt));
                    pastTasks.save();

                    System.out.println(buildMessage(String.format("""
                                    Got it. I've added this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            pastTasks.get(pastTasks.size() - 1),
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    )));
                } else if (command.split(" ")[0].equals("delete")) {
                    Task removedTask = pastTasks.remove(Integer.parseInt(command.split(" ")[1]) - 1);
                    pastTasks.save();

                    System.out.println(buildMessage(String.format("""
                                    Noted. I've removed this task:
                                     %s
                                    Now you have %d %s in the list.""",
                            removedTask,
                            pastTasks.size(),
                            pastTasks.size() == 1 ? "task" : "tasks"
                    )));
                } else {
                    throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (Exception e) {
            System.out.println(buildMessage(":( OOPS!!! " + e.getMessage()));
        }

        sc.close();
    }

    /**
     * Given a message, returns the formatted message for Duke to display
     *
     * @param message The message to display
     */
    public static String buildMessage(String message) {
        return String.format("""
                        ____________________________________________________________
                        %s
                        ____________________________________________________________""",
                message
        );
    }
}
