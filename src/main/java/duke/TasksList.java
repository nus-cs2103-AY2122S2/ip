package duke;

import duke.exception.InvalidArgumentException;
import duke.exception.InvalidIndexException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksList {
    private List<Task> tasks;

    /**
     * TasksList constructor method.
     * Uses ArrayList<Task> as its core.
     */
    public TasksList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds user input task to the taskslist.
     * @param instructions List of user input words.
     * @return Response text to be printed.
     * @throws InvalidArgumentException If user input argument format is unknown/invalid.
     */
    public String addTask(List<String> instructions) throws InvalidArgumentException {
        Task newTask = Task.createTask(instructions);
        tasks.add(newTask);
        String response;
        response = String.format("Got it. I've added this task\n" + newTask + "\n" +
                "You have %d tasks in the list", this.tasks.size());
        return response;
    }

    /**
     * Deletes task of certain index from the taskslist.
     * @param index Index of the task (1 based).
     * @return Response text to be printed.
     * @throws InvalidIndexException If index is out of bounds.
     */
    public String deleteTask(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }

        String response = String.format("Ok, I will remove this task: \n %s", tasks.get(index - 1));
        tasks.remove(index - 1);
        return response;
    }

    /**
     * Returns the list of tasks in the form of String / text.
     * @return List of tasks with numberings in text format.
     */
    public String list() {
        StringBuilder response = new StringBuilder("");

        if (tasks.size() == 0) {
            return "You have 0 task";
        }

        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format((i + 1) + ". " + tasks.get(i) + "\n"));
        }
        return response.toString();
    }

    /**
     * Changes task status to marked.
     * @param index Index of the task (1 based).
     * @return Response text to be printed.
     * @throws InvalidIndexException If index is out of bounds.
     */
    public String mark(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }
        String response = tasks.get(index - 1).markAsDone();
        return response;
    }

    /**
     * Changes task status to unmarked.
     * @param index Index of the task (1 based).
     * @return Response text to be printed.
     * @throws InvalidIndexException If index is out of bounds.
     */
    public String unmark(int index) throws InvalidIndexException {
        if (index > this.tasks.size()) {
            throw new InvalidIndexException();
        }
        String response = tasks.get(index - 1).markAsNotDone();
        return response;
    }

    /**
     * Returns the tasks list in text format for storage.
     * The text format follows the initial user input.
     * @return List of texts. Each text represents 1 task.
     */
    public List<String> toStorageStrings() {
        List<String> responses = new ArrayList<>();
        for (Task task : tasks) {
            responses.add(task.toStorageString() + "\n");
        }
        return responses;
    }

    /**
     * Gets the number of tasks in the taskslists.
     * @return Number of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Imports tasks from a list of strings (each representing 1 task).
     * The tasksStrings is from history storage.
     * This method is the counterpart of toStorageStrings().
     * @param tasksStrings List of tasks in text format.
     * @return Response text to be printed.
     * @throws InvalidArgumentException If any of the task's format is unknown/invalid.
     */
    public String importStorageStrings(List<String> tasksStrings) throws InvalidArgumentException{
        for (String taskString : tasksStrings) {
            List<String> description = Arrays.asList(taskString.split(" "));
            Boolean isDone = description.get(0).equals("X");
            Task task = Task.createTask(description.subList(1, description.size()));
            if (isDone) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        String response;
        if (tasks.size() != 0) {
            response = "I managed to retrieve your previous data!\nHere it is: \n" + this.list();
        } else {
            response = "As a new user, try to add task and I will store them for you!";
        }
        return response;
    }
}
