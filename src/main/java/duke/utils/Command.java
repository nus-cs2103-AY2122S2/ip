package duke.utils;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Class that contains methods to handle
 * various user commands
 */
public class Command {

    /**
     * Marks a task as complete based on the index
     * provide by the user input
     *
     * @param tl TaskList being operated on
     * @param st contains user command
     * @return String message to display to user
     * @throws DukeException when an Invalid Index is entered by the user
     */
    protected String markTaskComplete(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            int toMark = Integer.parseInt(st.nextToken());
            return tl.markTaskAsCompleted(toMark);
        } catch (DukeException | NumberFormatException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }

    /**
     * Marks a task as incomplete based on the index
     * provide by the user input
     *
     * @param tl TaskList being operated on
     * @param st contains user command
     * @return String message to display to user
     * @throws DukeException when an Invalid Index is entered by the user
     */
    protected String markTaskIncomplete(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            int toUnmark = Integer.parseInt(st.nextToken());
            return tl.markTaskAsUncomplete(toUnmark);
        } catch (DukeException | NumberFormatException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }

    /**
     * Creates and adds a new ToDo task
     * to the TaskList
     *
     * @param tl TaskList being operated on
     * @param st contains user command
     * @return String message to display to user
     * @throws DukeException when a ToDo description is not provided
     */
    protected String createToDo(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            return tl.addToDo(st.nextToken(""));
        } catch (NoSuchElementException e) {
            throw new DukeException.DukeNoTaskGivenException();
        }
    }

    /**
     * Creates and adds a new Deadline task
     * to the TaskList
     *
     * @param tl TaskList being operated on
     * @param userInput contains description of the Deadline
     * @param curr the date of the deadline
     * @return String message to display to user
     * @throws DukeException when a Deadline date is not provided
     */
    protected String createDeadline(String userInput, TaskList tl, String curr) throws DukeException {
        try {
            userInput = userInput.replace(curr, "");
            String[] spl = userInput.split("/by ");
            if (spl.length <= 1) {
                throw new DukeException.DukeNoTimeProvided();
            }
            return tl.addDeadline(spl[0], spl[1]);
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Creates and adds a new Event task
     * to the TaskList
     *
     * @param tl TaskList being operated on
     * @param userInput contains description of the event
     * @param curr the date of the event
     * @return String message to display to user
     * @throws DukeException when an event date is not provided
     */
    protected String createEvent(String userInput, TaskList tl, String curr) throws DukeException {
        try {
            userInput = userInput.replace(curr, "");
            String[] splo = userInput.split("/at ");
            if (splo.length <= 1) {
                throw new DukeException.DukeNoTimeProvided();
            }
            return tl.addEvent(splo[0], splo[1]);
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Deletes a task from the tasklist
     * based on it's index
     *
     * @param tl TaskList being operated on
     * @param st contains user command
     * @return String message to display to user
     * @throws DukeException when an Invalid Index is provided
     */
    protected String deleteTask(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            int toDelete = Integer.parseInt(st.nextToken());
            return tl.deleteTask(toDelete);
        } catch (NumberFormatException | DukeException e) {
            throw new DukeException.DukeInvalidNumberException();
        }
    }

    /**
     * Returns a list of events that contain
     * the user provided keyword
     *
     * @param tl Tasklist being operated on
     * @param st contains user command
     * @return String message to display to the user
     * @throws DukeException when no keyword is provided to search
     */
    protected String findTask(TaskList tl, StringTokenizer st) throws DukeException {
        try {
            return tl.findEvent(st.nextToken(""));
        } catch (NoSuchElementException e) {
            throw new DukeException.DukeNoTaskGivenException();
        }
    }

    /**
     * Returns a list of sorted events/deadlines
     * based on user input
     *
     * @param tl TaskList being operated on
     * @param st contains user command
     * @return String message to be displayed to the user
     * @throws DukeException when an invalid task type is given
     */
    protected String sortTasks(TaskList tl, StringTokenizer st) throws DukeException {

        String whichTask = st.nextToken();

        if (whichTask.equals("deadline")) {
            return tl.getDeadlinesSorted();
        } else if (whichTask.equals("event")) {
            return tl.getEventsSorted();
        } else {
            throw new DukeException.DukeInvalidCommandException();
        }
    }

    /**
     * Handles the case where the user enters
     * bye
     *
     * @param st contains user Command
     * @return String message saying goodbye
     * @throws DukeException if the bye command has extra words
     */
    protected String handleBye(StringTokenizer st) throws DukeException {
        if (st.hasMoreTokens()) {
            throw new DukeException.DukeInvalidCommandException();
        } else {
            return Ui.printBye();
        }
    }
}
