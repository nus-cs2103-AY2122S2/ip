package TaskList;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import validation.TaskValidator;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void completedTask(int index) {
        tasks.get(index).setComplete();
    }

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
