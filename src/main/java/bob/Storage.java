package bob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Storage object handles the storage and modification of entries in the Bob program.
 */
public class Storage {
    private final File file;

    private Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Static factory method to create a new Storage object.
     *
     * @param directoryPath String representing path to directory in which file is saved.
     * @param filePath      String representing path to saved file.
     * @return Newly created Storage object with save file initialized.
     */
    public static Storage createStorage(String directoryPath, String filePath) {
        Storage newStorage = new Storage(filePath);
        File directory = new File(directoryPath);
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!newStorage.file.exists()) {
                newStorage.file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newStorage;
    }

    /**
     * Loads all saved entries in the file associated with this Storage instance into a list.
     *
     * @return A TaskList loaded from the saved file.
     */
    public TaskList load() {
        List<Task> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                String[] tokens = nextLine.strip().split("\\|");
                String taskType = tokens[0];
                boolean isDone = tokens[1].equals("1");
                String taskDescription = tokens[2];

                Task newTask;
                switch (taskType) {
                case ("T"):
                    newTask = new ToDo(taskDescription, isDone);
                    break;

                case ("E"):
                    String[] taskDescriptionTime = taskDescription.split("/at ");
                    String eventTaskName = taskDescriptionTime[0];
                    String time = taskDescriptionTime[1];

                    newTask = new Event(eventTaskName, time, isDone);
                    break;

                case ("D"):
                    String[] taskDescriptionSplit = taskDescription.split("/by ");
                    String deadlineTaskName = taskDescriptionSplit[0];
                    String deadline = taskDescriptionSplit[1];

                    newTask = new Deadline(deadlineTaskName, deadline, isDone);
                    break;

                default:
                    throw new CorruptedEntryException("Saved file has corrupted entries! :(");
                }
                list.add(newTask);
            }
        } catch (CorruptedEntryException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new TaskList(list);
    }

    /**
     * Converts a given list of tasks into a String and writes it to the file associated with this Storage instance.
     *
     * @param tasks List of tasks to be written to file.
     */
    public void writeTaskListToFile(TaskList tasks) {
        StringBuilder tasksString = new StringBuilder();
        
        for (int i = 0; i < tasks.size(); i++) {
            tasksString.append(tasks.get(i).generateSavedEntry());
            tasksString.append("\n");
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(tasksString.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file!:( : " + e.getMessage());
        }
    }
}
