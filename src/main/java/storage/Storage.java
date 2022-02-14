package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import notes.Note;
import notes.NoteList;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Interface that deals with pushing or pulling tasks from the storage.
 */
public class Storage {

    private String taskPath;
    private String notePath;

    /**
     * Storage class constructor.
     * @param taskPath provides path for the task data file.
     * @param notePath provides path for the note data file.
     */
    public Storage(String taskPath, String notePath) {
        this.taskPath = taskPath;
        this.notePath = notePath;
    }

    /**
     * Retrieves tasks from the storage (if any) and adds them to a new task list.
     * @return list of tasks retrieved from the storage, else an empty list.
     * @throws DukeException If data file does not exist.
     */
    public ArrayList<Task> setUpTaskData() throws DukeException {
        try {
            FileReader myObj = new FileReader(this.taskPath);
            BufferedReader br = new BufferedReader(myObj);
            String line;
            ArrayList<Task> tasks = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split("/");
                Task task;
                switch (lineArray[0]) {
                case "T":
                    task = new Todo(lineArray[2]);
                    break;
                case "E":
                    task = new Event(lineArray[2], lineArray[3]);
                    break;
                default:
                    task = new Deadline(lineArray[2], lineArray[3]);
                }
                if (lineArray[1].equals("X")) {
                    task.setAsDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Could not reach file.");
        }
    }

    /**
     * Retrieves notes from the storage (if any) and adds them to a new note list.
     * @return list of notes retrieved from the storage, else an empty list.
     * @throws DukeException If data file does not exist.
     */
    public ArrayList<Note> setUpNoteData() throws DukeException {
        try {
            FileReader myObj = new FileReader(this.notePath);
            BufferedReader br = new BufferedReader(myObj);
            String line;
            ArrayList<Note> notes = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                Note newNote = new Note(line);
                notes.add(newNote);
            }
            return notes;
        } catch (IOException e) {
            throw new DukeException("Could not reach file.");
        }
    }

    /**
     * Adds or removes tasks from storage according to changes made by user.
     * @param taskList updated list of tasks.
     */
    public void updateTaskData(TaskList taskList) {
        try {
            FileWriter taskPath = new FileWriter(this.taskPath);
            taskPath.flush();
            for (int i = 0; i < taskList.getTasks().size(); i++) {
                taskPath.write(taskList.getTasks().get(i).symbol() + "/"
                        + taskList.getTasks().get(i).getStatusIcon() + "/"
                        + taskList.getTasks().get(i).toString() + "\n");
            }
            taskPath.close();
        } catch (IOException e) {
            System.out.println("Could not reach file.");
        }
    }

    /**
     * Adds or removes notes from storage according to changes made by user.
     * @param noteList updated list of notes.
     */
    public void updateNoteData(NoteList noteList) {
        try {
            FileWriter notePath = new FileWriter(this.notePath);
            notePath.flush();
            for (int i = 0; i < noteList.getNotes().size(); i++) {
                notePath.write(noteList.getNotes().get(i).toString() + "\n");
            }
            notePath.close();
        } catch (IOException e) {
            System.out.println("Could not reach file.");
        }
    }
}
