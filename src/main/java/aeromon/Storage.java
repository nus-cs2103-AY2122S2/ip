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

    private final File localTasks;

    /**
     * Constructs the Storage object.
     *
     * @param fileLocation the path of the file.
     */
    public Storage(String fileLocation) {
        this.localTasks = new File(fileLocation);
    }

    /**
     * Scans and returns the list of Tasks retrieved from the file.
     *
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
                    String[] tokens = content.split(" / ");

                    switch (tokens[0]) { //tokens[0] indicates the task type
                    case "T":
                        ToDo toDo;

                        if (tokens[1].equals("1")) {
                            toDo = new ToDo(tokens[2], true);
                        } else {
                            toDo = new ToDo(tokens[2], false);
                        }

                        tasks.add(toDo);
                        break;

                    case "D":
                        Deadline deadline;

                        if (tokens[1].equals("1")) {
                            deadline = new Deadline(tokens[2], true, LocalDate.parse(tokens[3], dateTimeFormatter));
                        } else {
                            deadline = new Deadline(tokens[2], false, LocalDate.parse(tokens[3], dateTimeFormatter));
                        }

                        tasks.add(deadline);
                        break;

                    case "E":
                        Event event;

                        if (tokens[1].equals("1")) {
                            event = new Event(tokens[2], true, LocalDate.parse(tokens[3], dateTimeFormatter));
                        } else {
                            event = new Event(tokens[2], false, LocalDate.parse(tokens[3], dateTimeFormatter));
                        }

                        tasks.add(event);
                        break;

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
     *
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
