package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.exception.DukeException;

public class TaskList {
    private final ArrayList<Task> tasks;
    private int numberOfTasks;
    
    public TaskList() {
        tasks = new ArrayList<>();
        numberOfTasks = 0;
    }

    public void populateWith(String[] data) throws DukeException {
        try {
            numberOfTasks = data.length;
            for (int i = 0; i < numberOfTasks; i++) {
                String tokens[] = data[i].split(",");
                if (tokens[0].equals("T")) {
                    tasks.add(new ToDo(tokens[1]));
                } else if (tokens[0].equals("E")) {
                    tasks.add(new Event(tokens[1], tokens[2]));
                } else if (tokens[0].equals("D")) {
                    tasks.add(new Deadline(tokens[1], LocalDate.parse(tokens[2])));
                } else {
                    throw new IllegalArgumentException();
                }
                if (tokens[3].equals("X")) {
                    tasks.get(i).mark();
                }
            }
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | DateTimeParseException e) {
            throw new DukeException("Encounter incorrect file format when loading tasks from storage");
        }
    }
    
    public String[] formatAsFileData() {
        String[] data = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            data[i] = tasks.get(i).toFileFormat();
        }
        return data;
    }
    
    public int getNumberOfTasks() {
        return numberOfTasks;
    }
    
    public boolean isValidTaskIndex(int index) {
        return index >= 0 && index < numberOfTasks;
    }
    
    public String getDescriptionOfTaskAtIndex(int index) {
        return tasks.get(index).toString();
    }
    
    public void addToDoTask(String description) {
        Task toDoTask = new ToDo(description);
        tasks.add(toDoTask);
        numberOfTasks++;
    }

    public void addDeadlineTask(String description, LocalDate date) {
        Task deadlineTask = new Deadline(description, date);
        tasks.add(deadlineTask);
        numberOfTasks++;
    }

    public void addEventTask(String description, String at) {
        Task eventTask = new Event(description, at);
        tasks.add(eventTask);
        numberOfTasks++;
    }
    
    public void markTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        tasks.get(taskIndex).mark();
    }

    public void unmarkTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        tasks.get(taskIndex).unmark();
    }

    public Task deleteTask(int taskIndex) throws DukeException {
        if (!isValidTaskIndex(taskIndex)) {
            throw new DukeException("The task index provided is invalid");
        }
        
        Task taskToBeDeleted = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        numberOfTasks--;
        
        return taskToBeDeleted;
    }
}
