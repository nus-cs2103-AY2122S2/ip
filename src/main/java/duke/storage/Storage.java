package duke.storage;

import duke.exceptions.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the application.
 * Supports the loading of and saving of data to storage file.
 */
public class Storage {
    private static final String home = System.getProperty("user.home");
    private static File saveFile;

    /**
     * Checks if a data folder and save file exists in the specified directory.
     * If not, create the folder and save file.
     *
     * @param dirPath the path of the data folder, relative to user's home directory
     */
    public Storage(String dirPath) throws DukeException {
        try {
            Path dir = Paths.get(home + dirPath);

            // check if /ip/data directory exists, if not create
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }

            // check if the file in /ip/data exists, if not create
            Path p = Paths.get(home + dirPath + "/duke.txt");
            if (!Files.exists(p)) {
                Files.createFile(p);
            }

            saveFile = new File(p.toString());
        } catch (IOException e) {
            throw new DukeException("There was a problem with creating a save file at " + home + dirPath + ". " +
                    "Please ensure that I have access to the directory.");
        }
    }

    /**
     * Loads up the data from the save file on the system to an ArrayList of tasks.
     *
     * @return an ArrayList of tasks
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(saveFile);

            // load data from the save file
            while (s.hasNextLine()) {
                String task = s.nextLine();
                String[] parsedTask = task.split(" \\| ");
                switch (parsedTask[0]) {
                case "T": {
                    Todo t = new Todo(parsedTask[2]);
                    if (parsedTask[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    break;
                }
                case "D": {
                    String done = parsedTask[1];
                    String desc = parsedTask[2];
                    String date = parsedTask[3];
                    String time = parsedTask[4];
                    if (!date.equals("0") && !time.equals("0")) {
                        Deadline d = new Deadline(desc,
                                LocalDate.parse(date),
                                LocalTime.parse(time));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    } else if (!date.equals("0")) {
                        Deadline d = new Deadline(desc, LocalDate.parse(date));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    } else {
                        Deadline d = new Deadline(desc, LocalTime.parse(time));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    }
                    break;
                }
                case "E": {
                    String done = parsedTask[1];
                    String desc = parsedTask[2];
                    String date = parsedTask[3];
                    String time = parsedTask[4];
                    if (!date.equals("0") && !time.equals("0")) {
                        Event e = new Event(desc,
                                LocalDate.parse(date),
                                LocalTime.parse(time));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    } else if (!date.equals("0")) {
                        Event e = new Event(desc, LocalDate.parse(date));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    } else {
                        Event e = new Event(desc, LocalTime.parse(time));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    }
                    break;
                }
                }
            }
        } catch (IOException e) {
            throw new DukeException("There was a problem loading your save file!");
        }
        return tasks;
    }

    /**
     * Saves tasks into the save file.
     *
     * @param tasks a TaskList containing tasks
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt");
            for (int i = 0; i < tasks.listSize(); i++) {
                fw.write(tasks
                        .getTask(i)
                        .getAppendData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was a problem saving data to your save file!");
        }
    }

    /**
     * Appends specified tasks to the end of the save file.
     *
     * @param textToAppend the task to be appended
     */
    public void append(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt",
                    true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was a problem writing to your save file!");
        }
    }
}
