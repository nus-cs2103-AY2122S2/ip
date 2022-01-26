package duke;

import java.util.Scanner;

/**
 * User Interface class, print beautiful output to user
 */
public class Ui {
    private String lines = "____________________________" +
            "________________________________";
    private String endline = "___________________________" +
            "_________________________________\n";

    /**
     * constructor
     */
    public Ui() {
        System.out.println("Hello!!! I am Duke, a new born chatbot\n");
        System.out.println("How may I serve you?");
    }

    /**
     * print bye message
     */
    public void bye() {
        System.out.println(lines);
        System.out.println("Bye! See you again");
        System.out.println(endline);
    }

    /**
     * print list of tasks
     * @param tasks current list of tasks
     */
    public void list(TaskList tasks) {
        System.out.println(lines);
        System.out.println("Here are all your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
        System.out.println(lines + "\n");
    }

    /**
     * Mark task as done
     * @param tasks current list of tasks
     * @param parts User's input (split into parts)
     * @throws DukeException
     * @throws IndexOutOfBoundsException
     */
    public void mark(TaskList tasks, String[] parts) throws DukeException, IndexOutOfBoundsException {
        try {
            Task markTask = tasks.get(Integer.parseInt(parts[1]) - 1);
            System.out.println(lines);
            if (markTask.isDone == true) {
                System.out.println("You have already done this task!");
                System.out.println(markTask.markAsDone());
                System.out.println(endline);
            }
            System.out.println("Good Job! You have marked this task as done!");
            System.out.println(markTask.markAsDone());
            System.out.println(endline);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You have entered invalid task or that task does not exist!");
        }
    }

    /**
     * Mark task as undone
     * @param tasks current list of tasks
     * @param parts User's input (split into parts)
     * @throws DukeException
     * @throws IndexOutOfBoundsException
     */
    public void unmark(TaskList tasks, String[] parts) throws DukeException, IndexOutOfBoundsException {
        try {
            Task markTask = tasks.get(Integer.parseInt(parts[1]) - 1);
            System.out.println(lines);
            if (markTask.isDone == false) {
                System.out.println("This task is already in undone status");
                System.out.println(markTask.markAsUndone());
                System.out.println(endline);
            }
            System.out.println("OK, I have marked this as not done yet:");
            System.out.println(markTask.markAsUndone());
            System.out.println(endline);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("You have entered invalid task or that task does not exist!");
        }
    }

    /**
     * Add a todo task
     * @param tasks Current list of tasks
     * @param parts User's input (split into parts)
     * @param input User's original input
     * @throws TodoException
     */
    public void todo(TaskList tasks, String[] parts, String input) throws TodoException {
        if (parts.length == 1) {
            throw new TodoException("☹ OOPS!!! The description of a todo cannot be empty.(please insert again)");
        }
        String todoDesription = input.substring(5);
        Task todo = new Todo(todoDesription);
        tasks.add(todo);
        System.out.println(lines);
        System.out.println("Got it, I have added a TODO task:");
        System.out.println(todo.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(endline);
    }

    /**
     * Add a deadline task
     * @param tasks Current list of tasks
     * @param parts User's input (split into parts)
     * @param input User's original input
     * @throws DeadlineException
     */
    public void deadline(TaskList tasks, String[] parts, String input) throws DeadlineException {
        if (parts.length == 1) {
            throw new DeadlineException("Emm, what is your task again? (please insert again)");
        }
        String[] split1 = input.split("/by ");
        if (split1.length == 1) {
            throw new DeadlineException("You need to tell me your deadline date\n e.g deadline <yourtask> /by <deadline date>");
        }
        String deadlineDesription = split1[0].substring(9);
        String deadlineDate = split1[1];
        Task deadline = new Deadline(deadlineDesription, deadlineDate);
        tasks.add(deadline);
        System.out.println(lines);
        System.out.println("Got it, I have added a DEADLINE task:");
        System.out.println(deadline.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(endline);
    }

    /**
     * Add an event task
     * @param tasks Current list of tasks
     * @param parts User's input (split into parts)
     * @param input User's original input
     * @throws DeadlineException
     */
    public void event(TaskList tasks, String[] parts, String input) throws EventException {
        if (parts.length == 1) {
            throw new EventException("The format should be: event <description> /at <date>");
        }
        String[] split1 = input.split("/at ");
        if (split1.length == 1) {
            throw new EventException("You need to tell me your event date\n e.g event <description> /at <date>");
        }
        String eventDesription = split1[0].substring(6);
        String eventDate = split1[1];
        Task event = new Event(eventDesription, eventDate);
        tasks.add(event);
        System.out.println(lines);
        System.out.println("Got it, I have added an EVENT task:");
        System.out.println(event.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(endline);
    }

    /**
     * Delete a task
     * @param tasks Current list of tasks
     * @param parts User's input (split into parts)
     * @throws DeleteException
     */
    public void delete(TaskList tasks, String[] parts) throws DeleteException{
        try {
            if (parts.length == 1) {
                System.out.println("Which task are you deleting? " +
                        "Insert a number like this: delete <task number>");
            }
            if (parts.length > 2) {
                throw new NumberFormatException();
            }
            int index = Integer.parseInt(parts[1]) - 1;
            Task taskToBeDelete = tasks.get(index);
            System.out.println(lines);
            System.out.println("Okay, I have removed this task:");
            System.out.println(taskToBeDelete);
            tasks.remove(index);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(endline);
        } catch (IndexOutOfBoundsException e) {
            throw new DeleteException("This task does not exist, there are " + tasks.size() + " tasks now");
        }
        catch (NumberFormatException e) {
            throw new DeleteException("format must be: delete <task number> , other format is not acceptable");
        }
    }
}
