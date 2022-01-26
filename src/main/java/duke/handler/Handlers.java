package duke.handler;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Tasklist;
import duke.task.Todo;

/**
 * Enum class used to handle the different commands used in the application.
 */
public enum Handlers {
    Bye("bye"),
    Deadline("deadline"),
    Delete("delete"),
    Event("event"),
    Find("find"),
    Mark("mark"),
    List("list"),
    Todo("todo"),
    Unmark("unmark");

    public final String label;

    /**
     * Enum constructor to reference the exact commands used.
     *
     * @param label Exact commands used in the application.
     */
    Handlers(String label) {
        this.label = label;
    }

    /**
     * Handles and distributes all commands to the respective handlers.
     *
     * @param list Tasklist that contains all tasks.
     * @param scn Scanner used to read in the arguments.
     */
    public static void commandHandler(Tasklist list, Scanner scn) {
        while (scn.hasNextLine()) {
            String input = scn.nextLine();
            String[] inputArr = input.split(" ");
            String cmd = inputArr[0];
            try {
                if (cmd.equals(Bye.label)) {
                    Handlers.byeHandler(list);
                    break;
                } else if (cmd.equals(List.label)) {
                    Handlers.listHandler(list);
                } else if (cmd.equals(Deadline.label)) {
                    Handlers.deadlineHandler(list, input, cmd);
                } else if (cmd.equals(Event.label)) {
                    Handlers.eventHandler(list, input, cmd);
                } else if (cmd.equals(Todo.label)) {
                    Handlers.todoHandler(list, input);
                } else if (cmd.equals(Mark.label)) {
                    Handlers.markHandler(list, input);
                } else if (cmd.equals(Unmark.label)) {
                    Handlers.unmarkHandler(list, input);
                } else if (cmd.equals(Find.label)) {
                    Handlers.findHandler(list, input);
                } else if (cmd.equals(Delete.label)) {
                    Handlers.deleteHandler(list, input);
                } else {
                    throw new IllegalArgumentException("Sorry, this is not a recognized command.\n");
                }
            } catch (IllegalArgumentException err) {
                System.out.println(err.getMessage());
            }
        }
    }

    /**
     * Handles the command 'bye'. Ends the program.
     *
     * @param list Tasklist that contains all tasks.
     */
    public static void byeHandler(Tasklist list) {
        System.out.println("Bye. Hope to see you again soon!");
        FileHandler.writeToFile(list);
    }

    /**
     * Handles the command 'deadline'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     * @param cmd String representing the command being used.
     */
    public static void deadlineHandler(Tasklist list, String input, String cmd) {
        try {
            int index = input.indexOf("/by");
            String[] time = DukeException.isTaskValid(index, input, cmd);
            Deadline task;
            LocalDate date = LocalDate.of(Integer.parseInt(time[2]), Integer.parseInt(time[1]),
                    Integer.parseInt(time[0]));
            if (time.length == 3) {
                task = new Deadline(false, input.substring(9, index - 1), date, "");
            } else {
                task = new Deadline(false, input.substring(9, index - 1), date, time[3]);
            }
            list.addTask(task);
            FileHandler.writeToFile(list);
            System.out.println("Deadline Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | DateTimeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'delete'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static void deleteHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            System.out.println("Noted. Deleting this task...");
            Task t = list.delete(index);
            FileHandler.writeToFile(list);
            System.out.println(t.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'event'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     * @param cmd String representing the command being used.
     */
    public static void eventHandler(Tasklist list, String input, String cmd) {
        try {
            int index = input.indexOf("/at");
            String[] time = DukeException.isTaskValid(index, input, cmd);
            Event task;
            LocalDate date = LocalDate.of(Integer.parseInt(time[2]), Integer.parseInt(time[1]),
                    Integer.parseInt(time[0]));
            if (time.length == 3) {
                task = new Event(false, input.substring(6, index - 1), date, "");
            } else {
                task = new Event(false, input.substring(6, index - 1), date, time[3]);
            }
            list.addTask(task);
            FileHandler.writeToFile(list);
            System.out.println("Event Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException | DateTimeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'find'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static void findHandler(Tasklist list, String input) {
        try {
            String searchPhrase = DukeException.isWordValid(input);
            LinkedList<Task> filtered = new LinkedList<>();
            for (int i = 0; i < list.getTotalTasks(); i++) {
                if (list.getTask(i).getTaskName().contains(searchPhrase)) {
                    filtered.add(list.getTask(i));
                }
            }
            if (filtered.size() == 0) {
                System.out.println("Sorry, but we could not find any tasks containing the search term.\n");
            } else {
                System.out.println("Here are the matching tasks!");
                System.out.println(Tasklist.taskFormatter(filtered));
            }
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'mark'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static void markHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            System.out.println("Nice! I've marked this task as done!");
            Task t = list.mark(index);
            FileHandler.writeToFile(list);
            System.out.println(t.toString() + "\n");
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'list'.
     *
     * @param list
     */
    public static void listHandler(Tasklist list) {
        System.out.println(list.toString());
    }

    /**
     * Handles the command 'todo'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static void todoHandler(Tasklist list, String input) {
        try {
            DukeException.isTaskValid(input);
            Todo task = new Todo(false, input.substring(5));
            list.addTask(task);
            FileHandler.writeToFile(list);
            System.out.println("Todo Added: " + task.toString());
            System.out.println("There are now " + list.getTotalTasks() + " tasks in the list.\n");
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Handles the command 'unmark'.
     *
     * @param list Tasklist that contains all tasks.
     * @param input The string that the user has entered following the command.
     */
    public static void unmarkHandler(Tasklist list, String input) {
        try {
            int index = DukeException.isIndexValid(input, list);
            System.out.println("Okay! I've marked this as undone!");
            Task t = list.unmark(index);
            FileHandler.writeToFile(list);
            System.out.println(t.toString() + "\n");
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }
}
