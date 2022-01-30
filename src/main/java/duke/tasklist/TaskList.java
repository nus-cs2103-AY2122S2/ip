package duke.tasklist;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.validation.TaskValidator;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return existing tasks from task list
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Set a task as complete
     *
     * @param index the task number to set as complete
     */
    public void completedTask(int index) {
        tasks.get(index).setComplete();
    }

    /**
     * Validate user input and create ToDo task
     *
     * @param userInput the input from user
     */
    public boolean addToDoTask(String userInput) {
        String description = TaskValidator.validateToDo(userInput);
        boolean noDescription = description.equals("");

        if (noDescription) {
            return false;
        }
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        return true;
    }

    /**
     * Validate user input and create Deadline task
     *
     * @param userInput the input from user
     */
    public boolean addDeadlineTask(String userInput) {
        try {
            String[] information = TaskValidator.validateDeadline(userInput);
            boolean isDescriptionEmpty = information[0].equals("");
            boolean isDatelineEmpty = information[1].equals("");

            if (isDescriptionEmpty || isDatelineEmpty) {
                return false;
            }
            Deadline deadline = new Deadline(information[0], TaskValidator.convertDate(information[1]));
            tasks.add(deadline);
            return true;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Validate user input and create Event task
     *
     * @param userInput the input from user
     */
    public boolean addEventTask(String userInput) {
        try {
            String[] information = TaskValidator.validateEvent(userInput);
            boolean isDescriptionEmpty = information[0].equals("");
            boolean isDatelineEmpty = information[1].equals("");

            if (isDescriptionEmpty || isDatelineEmpty) {
                return false;
            }

            Event event = new Event(information[0], TaskValidator.convertDate(information[1]));
            tasks.add(event);
            return true;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
