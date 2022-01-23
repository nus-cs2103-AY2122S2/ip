package spike.storage;

import spike.*;
import spike.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Saves and loads task list to and from the hard disk.
 */
public class Storage {
    private String directory;
    private String filePath;

    public Storage(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws SpikeException {
        // Check first whether the data folder exists, if not, create it
        File dataDir = new File("data/");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        // Check whether the data file exists, if not, create it
        File dataFile = new File("data/Spike.txt");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new SpikeException("Sorry, I couldn't create the task list file for you.");
            }
        }

        // Load data from the file if it is not empty
        if (!(dataFile.length() == 0)) {
            try {
                ArrayList<Task> tasks = new ArrayList<>();
                Scanner fileRead = new Scanner(dataFile);
                while (fileRead.hasNextLine()) {
                    String currLine = fileRead.nextLine();
                    String[] info = currLine.split(" \\| ");
                    Task task = null;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    switch (info[0]) {
                    case "T":
                        task = new ToDo(info[2]);
                        break;
                    case "E":
                        task = new Event(info[2], LocalDateTime.parse(info[3], dtf));
                        break;
                    case "D":
                        task = new Deadline(info[2], LocalDateTime.parse(info[3], dtf));
                        break;
                    default:
                        break;
                    }
                    tasks.add(task);
                    if (info[1].equals("1")) {
                        task.markAsDone();
                    }
                }
                return tasks;
            } catch (FileNotFoundException e) {
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Saves the latest task list into hard disk when user exits.
     */
    public void saveChanges(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("data/Spike.txt");
            String latestList = "";
            for (Task task : tasks.getTasks()) {
                latestList = latestList + task.toFileFormat() + "\n";
            }
            fw.write(latestList);
            fw.close();
        } catch (IOException e) {
            System.out.println("Oops, something went wrong with saving your file :(");
        }
    }
}
