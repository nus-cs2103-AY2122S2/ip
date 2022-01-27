package duke.utils;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


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
    public TaskList(ArrayList<Task> saved){
        this.tasks = saved;
    }

    /**
     * Add new ToDo task to
     * the list of tasks
     *
     * @param desc Description of ToDo
     * @throws DukeException.DukeNoTaskGivenException when no description is provided
     */
    public void addToDo(String desc) throws DukeException.DukeNoTaskGivenException {

        if (desc.replace(" ", "").equals("")) {
            throw new DukeException.DukeNoTaskGivenException();
        }
        ToDo curr = new ToDo(desc, false);
        this.tasks.add(curr);
        Ui.printTaskAddition(curr, getSize());
    }

    /**
     * Add new Deadline task to
     * the list of tasks
     *
     * @param desc description of Deadline
     * @param date date by which task has to be completed
     * @throws DukeException.DukeNoTimeProvided when an invalid date is entered
     */
    public void addDeadline(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Deadline curr = new Deadline(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            Ui.printTaskAddition(curr, getSize());
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
     * @throws DukeException.DukeNoTimeProvided when an invalid date is entered
     */
    public void addEvent(String desc, String date) throws DukeException.DukeNoTimeProvided {

        try {
            Event curr = new Event(desc, false, LocalDate.parse(date));
            this.tasks.add(curr);
            Ui.printTaskAddition(curr, getSize());
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeNoTimeProvided();
        }
    }


    /**
     * Delete a task from the list
     * of tasks based on index.
     *
     * @param toDelete index of task to be deleted
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public void deleteTask(int toDelete) throws DukeException.DukeInvalidNumberException {
        if(toDelete < 0 || toDelete > tasks.size()){
            throw new DukeException.DukeInvalidNumberException();
        }
        Task toBeRemoved = tasks.get(toDelete - 1);
        this.tasks.remove(toDelete - 1);
        Ui.printTaskDeletion(toBeRemoved, getSize());
    }

    /**
     * Save the list of tasks to
     * the hard disk
     */
    public void saveListToStorage(){
        Storage.saveListToDisk(tasks);
    }

    /**
     * Mark a task as completed based on
     * it's index
     *
     * @param toMark index of task to be marked as completed
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public void markTaskAsCompleted(int toMark) throws DukeException.DukeInvalidNumberException {
        if(toMark < 0 || toMark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        tasks.get(toMark - 1).markCompleted();
    }

    /**
     * Mark a task as not complete based on
     * it's index
     *
     * @param toUnmark index of task to be marked as completed
     * @throws DukeException.DukeInvalidNumberException when an invalid index is entered
     */
    public void markTaskAsUncomplete(int toUnmark) throws DukeException.DukeInvalidNumberException {
        if(toUnmark < 0 || toUnmark > tasks.size()) {
            throw new DukeException.DukeInvalidNumberException();
        }
        tasks.get(toUnmark - 1).markNotCompleted();
    }

    /**
     * Print the list of tasks
     */
    public void printList(){
        Ui.printList(tasks);
    }

    /**
     * Returns the number of tasks
     * in the list
     *
     * @return the number of tasks
     */
    public int getSize(){
        return tasks.size();
    }




}
