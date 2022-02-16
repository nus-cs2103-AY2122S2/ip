package duke.task;

import java.util.ArrayList;

import duke.HelpPage;
import duke.exception.DukeCommandDoesNotExistException;
import duke.exception.DukeException;
import duke.exception.DukeNoDescriptionException;
import duke.exception.DukeNoTimeSpecifiedException;
import duke.exception.DukeOutOfBoundException;
import duke.io.UserInput;

/**
 * This is a TaskList class that used to store the tasks in an ArrayList.
 */
public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>(); // arraylist to keep track of task

    /**
     * List out all the tasks currently in the task list.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @throws DukeException If user's input is invalid.
     */
    public String listTask(UserInput userInput) throws DukeException {
        StringBuilder returnMessage = new StringBuilder();
        if (!userInput.getDescription().equals("") || !userInput.getTime().equals("")) {
            // throw wrong command exception
            throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");
        }
        if (list.size() == 0) {
            return "Nothing on your list!";
        } else {
            returnMessage.append("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                returnMessage.append(String.format("%d.%s%n", i + 1, list.get(i).toString()));
            }
        }
        returnMessage.append(String.format("Now you have %d tasks in the list%n", list.size()));
        return returnMessage.toString();
    }

    private String appendTaskMessage(Task task) {
        StringBuilder returnMessage = new StringBuilder();

        returnMessage.append("Got it. I've added this task:\n");
        returnMessage.append(task);
        returnMessage.append(String.format("\nNow you have %d tasks in the list%n", list.size()));
        return returnMessage.toString();
    }

    private String addTodoTask(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task newTask = new ToDoTask(userInput.getDescription());
        list.add(newTask);
        return appendTaskMessage(newTask);
    }

    private String addDeadlineTask(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (userInput.getTime().equals("")) {
            // throw no time specified exception
            throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
        }

        Task newTask = new DeadlineTask(userInput.getDescription(), userInput.getTime());
        list.add(newTask);
        return appendTaskMessage(newTask);
    }

    private String addEventTask(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! The description of a event cannot be empty.");
        } else if (userInput.getTime().equals("")) {
            // throw no time specified exception
            throw new DukeNoTimeSpecifiedException("OOPS!!! Remember to set a time.");
        }

        Task newTask = new EventTask(userInput.getDescription(), userInput.getTime());
        list.add(newTask);
        return appendTaskMessage(newTask);
    }

    /**
     * Add task to the task list based on the user's input.
     * Print a message if the task is successfully added to the task list.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @return A String of message.
     * @throws DukeException If user's input is invalid.
     */
    public String addTask(UserInput userInput) throws DukeException {
        String returnMessage = "";

        // add ToDoTask
        if (userInput.getCommand().equals("todo")) {
            returnMessage = addTodoTask(userInput);
        }

        // add DeadlineTask
        if (userInput.getCommand().equals("deadline")) {
            returnMessage = addDeadlineTask(userInput);
        }

        // add EventTask
        if (userInput.getCommand().equals("event")) {
            returnMessage = addEventTask(userInput);
        }

        return returnMessage;
    }

    /**
     * load task to the task list based on the user's local data.
     * Does not print any message. This is used for loading up saved data when initialising Duke.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @throws DukeException If user's input is invalid.
     */
    public void loadTask(UserInput userInput) throws DukeException {
        // add ToDoTask
        if (userInput.getCommand().equals("todo")) {
            Task newTask = new ToDoTask(userInput.getDescription(), userInput.getIsDone());
            list.add(newTask);
            System.out.println(newTask);
        }

        // add DeadlineTask
        if (userInput.getCommand().equals("deadline")) {
            Task newTask = new DeadlineTask(userInput.getDescription(), userInput.getTime(), userInput.getIsDone());
            list.add(newTask);
            System.out.println(newTask);
        }

        // add EventTask
        if (userInput.getCommand().equals("event")) {
            Task newTask = new EventTask(userInput.getDescription(), userInput.getTime(), userInput.getIsDone());
            list.add(newTask);
            System.out.println(newTask);
        }
    }

    /**
     * Mark a task as done.
     * Print a message after the operation is completed.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @return A String of message.
     * @throws DukeException If user's input is invalid.
     */
    public String markDone(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
        try {
            int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));
            StringBuilder returnMessage = new StringBuilder();

            // check if the user's input value is within the range of the list before mark done the task
            checkOutOfBound(n - 1);

            list.get(n - 1).markDone();
            returnMessage.append("Nice! I've marked this task as done:\n");
            returnMessage.append(list.get(n - 1).toString());
            return returnMessage.toString();
        } catch (NumberFormatException e) {
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
    }

    /**
     * Mark a task as undone.
     * Print a message after the operation is completed.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @return A String of message.
     * @throws DukeException If user's input is invalid.
     */
    public String markUndone(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }

        try {
            int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));
            StringBuilder returnMessage = new StringBuilder();

            // check if the user's input value is within the range of the list before mark un done the task
            checkOutOfBound(n - 1);

            list.get(n - 1).markUndone();
            returnMessage.append("OK, I've marked this task as not done yet:\n");
            returnMessage.append(list.get(n - 1).toString());
            return returnMessage.toString();
        } catch (NumberFormatException e) {
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
    }

    /**
     * Delete a task from task list.
     * Print a message after the operation is completed.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @return A String of message.
     * @throws DukeException If user's input is invalid.
     */
    public String deleteTask(UserInput userInput) throws DukeException {
        if (userInput.getDescription().equals("")) {
            // throw no description exception
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
        try {
            int n = Integer.parseInt(userInput.getDescription().replaceAll("[^\\d-]", ""));
            StringBuilder returnMessage = new StringBuilder();

            // check if the user's input value is within the range of the list before deleting the task
            checkOutOfBound(n - 1);

            returnMessage.append("Noted. I've removed this task:\n");
            returnMessage.append(list.get(n - 1).toString());
            list.remove(n - 1);
            returnMessage.append(String.format("\nNow you have %d tasks in the list%n", list.size()));
            return returnMessage.toString();
        } catch (NumberFormatException e) {
            throw new DukeNoDescriptionException("OOPS!!! Please specify a number.");
        }
    }

    /**
     * Getter method to retrieve the task list.
     *
     * @return task list.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * A method to check if there is any index out of bound.
     *
     * @param i A int value.
     * @throws DukeOutOfBoundException If index is out of bound.
     */
    private void checkOutOfBound(int i) throws DukeOutOfBoundException {
        // check if the user's input value is within the range of the list
        if (i < 0) {
            throw new DukeOutOfBoundException("OOPS!!! Please key in a positive number");
        } else if (i >= list.size()) {
            throw new DukeOutOfBoundException("OOPS!!! Please double check the task number");
        }
    }

    /**
     * A method to find task with a keyword.
     *
     * @param userInput A UserInput object, capturing the user's input.
     * @return A String of message of the outcome.
     * @throws DukeException If user's input is invalid.
     */
    public String findTask(UserInput userInput) throws DukeException {
        if (!userInput.getTime().equals("")) {
            // throw wrong command exception
            throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");
        }

        StringBuilder returnMessage = new StringBuilder();
        ArrayList<Task> arrayList = new ArrayList<>();

        if (list.size() == 0) {
            returnMessage.append("Nothing on your list!");
        }

        for (Task task : list) {
            if (task.getTaskName().contains(userInput.getDescription())) {
                arrayList.add(task);
            }
        }

        if (arrayList.size() > 0) {
            returnMessage.append("Here are the matching tasks in your list:\n");
            for (int j = 0; j < arrayList.size(); j++) {
                returnMessage.append(j + 1).append(".").append(arrayList.get(j).toString());
            }

        } else {
            returnMessage.append("OOPS!!! There are no matching tasks in your list!");
        }

        return returnMessage.toString();
    }

    /**
     * A method to list out all the commands for this application.
     *
     * @return A string of preset message of all the commands available.
     */
    public String listOfCommands() {
        HelpPage helpPage = new HelpPage();
        return helpPage.toString();
    }
}
