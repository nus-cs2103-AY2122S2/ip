package aeromon;

import aeromon.task.Deadline;
import aeromon.task.Event;
import aeromon.task.Task;
import aeromon.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles the loading and saving tasks in the file.
 */
public class Storage {
    private File localTasks;

    /**
     * Constructs the Storage object.
     * @param fileLocation the path of the file.
     */
    public Storage(String fileLocation) {
        this.localTasks = new File(fileLocation);
    }

    /**
     * Scans and returns the list of Tasks retrieved from the file.
     * @return the ArrayList of Tasks in the file.
     * @throws AeromonException when an error occurs when reading the file.
     */
    public ArrayList<Task> getFile() throws AeromonException {
        try {
            if (localTasks.createNewFile()) {
                return new ArrayList<>();
            } else {
                ArrayList<Task> tasks = new ArrayList<>();
                Scanner fileScanner = new Scanner(localTasks);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

                while (fileScanner.hasNextLine()) {
                    String content = fileScanner.nextLine();
                    String[] info = content.split(" / ");
                    switch (info[0]) {
                        case "T": {
                            ToDo task = new ToDo(info[2]);
                            tasks.add(task);
                            break;
                        }
                        case "D": {
                            Deadline task = new Deadline(info[2], LocalDate.parse(info[3], dateTimeFormatter));
                            tasks.add(task);
                            break;
                        }
                        case "E": {
                            Event task = new Event(info[2], LocalDate.parse(info[3],dateTimeFormatter));
                            tasks.add(task);
                            break;
                        }
                    }
                }
                fileScanner.close();
                return tasks;
            }
        } catch (IOException e) {
            throw new AeromonException("The file had some error and I wasn't able to read it properly >.<");
        }
    }

    /**
     * Saves the Tasks into the file.
     * @param tasks the tasks to be saved.
     */
    public void saveFile(ArrayList<Task> tasks) {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += tasks.get(i).toOutputFormat() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter("./data/localTasks.txt");
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ohnoz! Something went terribly wrong!");
            e.printStackTrace();
        }
    }
}
