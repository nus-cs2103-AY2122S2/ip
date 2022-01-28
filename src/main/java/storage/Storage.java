package storage;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import tasks.Task;
import validation.DateValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String input) {
        this.filePath = input;
    }

    public void readFile() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File file = new File(this.filePath);

            if (file.createNewFile()) {
                System.out.println("New file created at " + filePath);
            }
        } catch (IOException e) {
            System.out.println("IO Exception error occurred, unable to create file " + filePath);
        }
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);

            for (Task currTask : tasks) {
                String description = currTask.getDescription();
                int isComplete = currTask.isComplete() ? 1 : 0;
                String saveText;

                if (currTask instanceof ToDo) {
                    saveText = "T | " + isComplete + " | " + description;
                } else if (currTask instanceof Deadline) {
                    saveText = "D | " + isComplete + " | " + description + " | " + ((Deadline) currTask).getDeadlineBy();
                } else if (currTask instanceof Event) {
                    saveText = "E | " + isComplete + " | " + description + " | " + ((Event) currTask).getEventBy();
                } else {
                    fw.close();
                    throw new DukeException("Invalid task instance: " + currTask);
                }
                fw.write(saveText + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred while saving your data. Please try again");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File loadFile = new File(filePath);
            Scanner readFile = new Scanner(loadFile);
            int count = 0;

            while(readFile.hasNext()) {
                try {
                    String savedData[] = readFile.nextLine().split(" \\| ");
                    String commandType = savedData[0];
                    String commandLine;

                    switch(commandType) {
                        case "T":
                            commandLine = "todo " + savedData[2];
                            tasks.add(count, new ToDo(commandLine));
                            break;
                        case "D":
                            commandLine = "deadline " + savedData[2];
                            tasks.add(count, new Deadline(commandLine, DateValidator.convertDate(savedData[3])));
                            break;
                        case "E":
                            commandLine = "event " + savedData[2];
                            tasks.add(count, new Event(commandLine, DateValidator.convertDate(savedData[3])));
                            break;
                        default:
                            throw new DukeException("Unable to parse file: " + filePath);
                    }
                    if (savedData[1].equals("1")) {
                        tasks.get(count).setComplete();
                    }
                    count++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Unable to parse line " + (count + 1) + " of " + filePath);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("IO exception error when loading data " + filePath);
        }
        return tasks;
    }
}
