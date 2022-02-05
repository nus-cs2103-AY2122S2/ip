package backend;

import exception.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks;

    /**
     * Returns a list of all tasks beginning with index 1
     */
    public static String list() {
        // print the ui user message
        Ui.list();
        String output = "";

        // print out all objects in the list
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            int index = i + 1;
            System.out.println(index + "." + currentTask.toString());
            output = output + index + "." + currentTask.toString() + "\n";
        }
        return output;
    }

    /**
     * Marks the task at the supplied integer as completed, based on their ordering in this.list().
     * @param indexMarked index of the completed task
     */
    public static String mark(int indexMarked) {
        String output = "";
        try {
            Task currentTask = tasks.get(indexMarked);
            currentTask.setDone(true);
            output = Ui.mark() + "\n";
            output = output + currentTask.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
        return output;
    }

    /**
     * Unmarks the task at the supplied integer as completed, based on their ordering in this.list().
     * @param indexUnmarked index of the task to be unmarked
     */
    public static String unmark(int indexUnmarked) {
        String output = "";
        try {
            Task currentTask = tasks.get(indexUnmarked);
            currentTask.setDone(false);
            output = Ui.unmark() + "\n";
            output = output + currentTask.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
        return output;
    }

    /**
     * removes the task at the supplied integer from tasklist, based on their ordering in this.list().
     * @param indexDelete index of the task to be deleted.
     */
    public static String delete(int indexDelete) {
        String output = "";
        try {
            Task deletedTask = tasks.remove(indexDelete);
            output =  Ui.delete() + "\n";
            output = output + deletedTask.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(new DukeException("task does not exist!"));
        } catch (NumberFormatException e) {
            System.out.println(new DukeException("provide an index instead :)"));
        }
        return output;
    }

    /**
     * creates a Todo task and adds it to the end of the tasklist
     * @param description description of the todo task.
     */
    public static String addTodo(String description) {
        String output = "";
        try {
            if (description.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Todo currentTodo = new Todo(description);
            tasks.add(currentTodo);
            output =  Ui.todo() + "\n";
            output = output + currentTodo.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a todo cannot be empty"));
        }
        return output;
    }


    /**
     * creates a deadline task and adds it to the end of the tasklist.
     * @param description description of the deadline task.
     * @param time time of the deadline task.
     */
    public static String addDeadline(String description, String time) {
        String output = "";
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Deadline currentDeadline = new Deadline(description, time);
            tasks.add(currentDeadline);
            output =  Ui.deadline() + "\n";
            output = output + currentDeadline.toString();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a deadline cannot be empty"));
        }
        return output;
    }

    /**
     * creates a event task and adds it to the end of the tasklist.
     * @param description description of the event task.
     * @param time time of the event task.
     */
    public static String addEvent(String description, String time) {
        String output = "";
        try {
            if (description.isBlank() || time.isBlank()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            Event currentEvent = new Event(description, time);
            tasks.add(currentEvent);
            output =  Ui.event() + "\n";
            output = output + currentEvent.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(new DukeException("The description of a event cannot be empty"));
        }
        return output;
    }

    public static String find(String keyword) {
        int counter = 1;
        String output = Ui.find() + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                output = output + counter + ". " + tasks.get(i).toString();
                counter += 1;
            }
        }
        return output;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * initialises the tasklist with an arraylist of tasks
     * @param tasks pre saved tasks from data/duke.txt
     */
    public static void initialise(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
    }
}
