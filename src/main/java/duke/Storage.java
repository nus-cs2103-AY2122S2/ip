package duke;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * A class to manage the saving and loading of files in the <code>Duke</code> program
 */
public class Storage {
    
    private String filepath;
    
    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the save file from this.filepath and returns an ArrayList<Task> that represents the save file.
     *
     * @return ArrayList<Task> that represents the save file.
     * @throws DukeException If the duke.txt file doesn't exist.
     */
    public ArrayList<Task> loadTaskList() throws DukeException {
        try {
            File f = new File(this.filepath);
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<Task>();
            
            while(sc.hasNext()) {
                tasks.add(Parser.getTask(sc.nextLine()));
            }
            
            sc.close();
            
            return tasks;

        } catch (FileNotFoundException e) {

            throw new DukeException("Problem loading data");

        }
    }

    public ArrayList<Note> loadNoteList() throws DukeException {
        try {
            File f = new File(this.filepath);
            Scanner sc = new Scanner(f);
            ArrayList<Note> notes = new ArrayList<>();

            while(sc.hasNext()) {
                notes.add(Parser.getNote(sc.nextLine()));
            }

            sc.close();

            return notes;
        } catch (FileNotFoundException e) {

            throw new DukeException("Problem loading data");

        }
    }

    /**
     * Takes in a list of tasks and save them in a duke.txt file.
     * If duke.txt file already exists, the new save will overwrite the existing one.
     *
     * @param tasks List of tasks
     */
    public void saveTasks(TaskList tasks) {

        String saveFormat = "";

        for (Task t : tasks.getTasks()) {
            if (t instanceof ToDo) {
                saveFormat += "T#";
                saveFormat += t.isDone ? "true" : "false";
                saveFormat += "#" + t.description + "\n";
                continue;
            } else if (t instanceof Deadline) {
                saveFormat += "D";
            } else if (t instanceof Event) {
                saveFormat += "E";
            }
            saveFormat += "#";

            saveFormat += t.isDone ? "true" : "false";
            saveFormat += "#";

            saveFormat += t.description;
            saveFormat += "#";

            saveFormat += t.time.get();
            saveFormat += "\n";
        }

        try {
            File f = new File(filepath);
            if (!f.exists()) {
                File dir = f.getParentFile();
                if (!dir.exists()) {
                    dir.mkdir();
                }

                f.createNewFile();

            }

            FileWriter fw = new FileWriter(filepath);
            fw.write(saveFormat);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error while saving file.\n" + e);
        }
    }

    public void saveNotes(NoteList notes) {

        String saveFormattedNotes = "";

        for (Note n : notes.getNotes()) {
            saveFormattedNotes += n + "\n";
        }

        try {
            File f = new File(filepath);
            if (!f.exists()) {
                File dir = f.getParentFile();
                System.out.println(dir);
                if (!dir.exists()) {
                    dir.mkdir();
                }

                f.createNewFile();

            }

            FileWriter fw = new FileWriter(filepath);
            fw.write(saveFormattedNotes);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error while saving file.\n" + e);
        }
    }
}
