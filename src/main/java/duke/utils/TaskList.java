package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



/**
 * Store list of tasks and has
 * relevant methods such as
 * adding/deleting a task
 */
public class TaskList {

    /**
     * Store the list of tasks
     */
    private ArrayList<Task> tasks;


    /**
     * Constructor method for
     * TaskList
     *
     * @param saved List of tasks from hard disk
     */
    public TaskList(ArrayList<Task> saved) {
        this.tasks = saved;
    }

    /**
     * Add new ToDo task to
     * the list of tasks
     *
     * @param desc Description of ToDo
     * @return String message confirming the ToDo has been added
     * @throws DukeException.DukeNoTaskGivenException when no description is provided
     */
    public String addToDo(String desc) throws DukeException.DukeNoTaskGivenException {

        if (desc.replace(" ", "").equals("")) {
            throw new DukeException.DukeNoTaskGivenException();
        }
        ToDo curr = new ToDo(desc, false);
        this.tasks.add(curr);
        return Ui.printTaskAddition(curr, getSize());
    }

    /**
     * Add new Deadline task to
     * the list of tasks
     *
     * @param desc description of Deadline
     * @param date date by which task has to be completed
     * @return String message confirming the deadline has been added
     * @throws DukeException.DukeNoTimeProvided when an invalid date is entered
     */
    public String addDeadline(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Deadline curr = new Deadline(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            return Ui.printTaskAddition(curr, getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeNoTimeProvided();
        }
    }

    /**
     * Add an Event task to
     * the list of tasks
     *
     * @param desc Description of event
     * @param date Date of event
     * @return String message confirming the event has been added
     * @throws DukeException.DukeNoTimeProvided when an invalid date is entered
     */
    public String addEvent(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Event curr = new Event(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            return Ui.printTaskAddition(curr, getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeNoTimeProvided();
        }
    }


    /**
     * Delete a task from the list
     * of tasks based on index.
     *
     * @param toDelete index of task to be deleted
     * @return String message confirming the task has been deleted
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public String deleteTask(int toDelete) throws DukeException.DukeInvalidNumberException {
        if (toDelete < 0 || toDelete > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        Task toBeRemoved = tasks.get(toDelete - 1);
        this.tasks.remove(toDelete - 1);
        return Ui.printTaskDeletion(toBeRemoved, getSize());
    }


    /**
     * Save the list of tasks to
     * the hard disk
     */
    public void saveListToStorage() {
        Storage.saveListToDisk(tasks);
    }

    /**
     * Mark a task as completed based on
     * it's index
     *
     * @param toMark index of task to be marked as completed
     * @return String message confirming the task has been marked not complete
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public String markTaskAsCompleted(int toMark) throws DukeException.DukeInvalidNumberException {
        if (toMark < 0 || toMark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        return tasks.get(toMark - 1).markCompleted();
    }

    /**
     * Mark a task as not complete based on
     * it's index
     *
     * @param toUnmark index of task to be marked as completed
     * @return String message confirming the task has been marked completed
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public String markTaskAsUncomplete(int toUnmark) throws DukeException.DukeInvalidNumberException {
        if (toUnmark < 0 || toUnmark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        return tasks.get(toUnmark - 1).markNotCompleted();
    }


    /**
     * Print the list of tasks
     *
     * @return Aforementioned list of tasks as string
     */
    public String printList() {
        return Ui.printList(tasks);
    }

    /**
     * Returns the number of tasks
     * in the list
     *
     * @return the number of tasks
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Print list of tasks that
     * contains user's keyword
     *
     * @param desc keyword to search for
     * @return Aforementioned list of tasks as string
     * @throws DukeException.DukeNoTaskGivenException when no keyword is provided to search
     */
    public String findEvent(String desc) throws DukeException.DukeNoTaskGivenException {
        ArrayList<Task> matches = new ArrayList<>();

        if (desc.replace(" ", "").equals("")) {
            throw new DukeException.DukeNoTaskGivenException();
        }

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(desc)) {
                matches.add(tasks.get(i));
            }
        }

        return Ui.printSearchList(matches);
    }

    /**
     * Shows the various deadlines in the task list
     * sorted by earliest to latest (Date)
     *
     * @return a String containing the list of sorted deadlines
     */
    protected String getDeadlinesSorted() {

        ArrayList<Deadline> deadlines = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                deadlines.add((Deadline) tasks.get(i));
            }
        }

        Collections.sort(deadlines);
        return Ui.printSortedList(deadlines, "deadline");
    }

    /**
     * Shows the various events in the task list
     * sorted by earliest to latest (Date)
     *
     * @return a String containing the list of sorted events
     */
    protected String getEventsSorted() {

        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Event) {
                events.add((Event) tasks.get(i));
            }
        }

        Collections.sort(events);
        return Ui.printSortedList(events, "event");
    }



}
