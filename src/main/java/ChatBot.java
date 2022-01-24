/**
 * Represents a chatbot used to keep track of various tasks.
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ChatBot {
    private String name;
    private String line = "-------------------------------------------------";
    private ArrayList<Task> tasks;
    private Storage taskStorage;

    /**
     * Chatbot to be instantiated with a name.
     *
     * @param name The name of the chatbot.
     */
    public ChatBot(String name, Storage taskStorage) {
        this.name = name;
        this.taskStorage = taskStorage;
        try {
            this.tasks = taskStorage.retrieveTaskList();
        } catch (FileNotFoundException e) {
            this.tasks = new ArrayList<Task>();
        }
    }

    /**
     * Prints welcome message to the user.
     */
    public void greet() {
        System.out.printf("%s%n %s%s%n %s%n%s%n", line, "Hello! I'm ", name, "What can I do for you", line);
    }

    /**
     * Takes in a string of command from the user,
     * and performs the required actions or signal error for invalid command.
     *
     * @param command A string input from the user.
     * @return The boolean value to signal the exit from chatbot.
     */
    public boolean runCommand(String command) {
        String [] input = command.split(" ", 2);
        switch (input[0].toLowerCase()) {
            case "bye":
                quit();
                return true;

            case "list":
                printTasks();
                break;

            case "mark":
                try {
                    markTask(Integer.parseInt(input[1].trim()) - 1);
                } catch(NumberFormatException e) {
                    throw new DukeException("Please enter an integer!", e);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task!", e);
                }
                break;

            case "unmark":
                try {
                    unmarkTask(Integer.parseInt(input[1].trim()) - 1);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter an integer!", e);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task!", e);
                }
                break;

            case "todo":
                try {
                    addTask(new ToDo(input[1]));
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException(
                            "Missing details! Please use the format: todo <description>", e);
                }
                break;

            case "deadline":
                try {
                    String[] dDetail = input[1].split(" /by ");
                    addTask(new Deadline(dDetail[0], dDetail[1]));
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException(
                            "Missing details! Please use the format: deadline <description> /by <date/time>", e);
                }
                break;

            case "event":
                try {
                    String[] eDetail = input[1].split(" /at ");
                    addTask(new Event(eDetail[0], eDetail[1]));
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException(
                            "Missing details! Please use the format: event <description> /at <date/time>", e);
                }
                break;

            case "delete":
                try {
                    deleteTask(Integer.parseInt(input[1].trim()) - 1);
                } catch(NumberFormatException e) {
                    throw new DukeException("Please enter an integer!", e);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Invalid task!", e);
                }
                break;

            default:
                System.out.printf("%s%n %s%n%s%n", line, "I'm sorry, but I don't know what that means :-(",
                        line);
                break;
        }

        try {
            taskStorage.saveTaskList(this.tasks);
        } catch (IOException e) {
            System.out.println("Oh no something went wrong when saving your data!\n" +
                    "They will be all lost.");
        }

        return false;
    }

    /**
     * Add a task to the end of the list of current task(s).
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
        System.out.printf("%s%n %s%n   %s%n %s%n%s%n",
                line, "Got it. I've added this task:",
                t.toString(), "Now you have " + tasks.size() + " task(s) in the list.",
                line);
    }

    /**
     * Delete a task for the list of task(s).
     *
     * @param index The index of the task in the list.
     */
    public void deleteTask(int index) {
        Task t = tasks.remove(index);
        System.out.printf("%s%n %s%n   %s%n %s%n%s%n",
                line, "Got it. I've removed this task:",
                t.toString(), "Now you have " + tasks.size() + " task(s) in the list.",
                line);
    }

    /**
     * Mark a task as done.
     *
     * @param index The index of the task in the list.
     */
    public void markTask(int index) {
        tasks.get(index).setDone(true);
        System.out.printf("%s%n %s%n    %s%n%s%n", line, "Nice! I've marked this task as done:",
                tasks.get(index).toString(), line);
    }

    /**
     * Mark a task as not done yet.
     *
     * @param index The index of the task in the list.
     */
    public void unmarkTask(int index) {
        tasks.get(index).setDone(false);
        System.out.printf("%s%n %s%n    %s%n%s%n", line, "Nice! I've marked this task as not done yet:",
                tasks.get(index).toString(), line);
    }

    /**
     * Print out all the tasks in the list in the order as they were added in.
     */
    public void printTasks() {
        String title = tasks.isEmpty() ? "You got no task now! Start by adding new tasks."
                : "Here are the task(s) in your list:";
        System.out.println(line);
        System.out.println(title);
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf(" %d.%s%n", i + 1, tasks.get(i).toString());
        }
        System.out.println(line);
    }

    /**
     * Echo a message.
     *
     * @param input Message to be echoed.
     */
    public void echo(String input) {
        System.out.printf("%s%n %s%n%s%n", line, input, line);
    }

    /**
     * Prints the exit message.
     */
    public void quit() {
        System.out.printf("%s%n %s%n%s%n", line, "Bye. Hope to see you again soon!", line);
    }
}
