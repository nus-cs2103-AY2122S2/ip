package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {
    public void writeData(TaskList taskList) {
        Path dirPath = Paths.get("data");
        Path filePath = Paths.get("data/taskList.txt");
        boolean fileExists = java.nio.file.Files.exists(filePath);
        try {
            Files.createDirectories(dirPath);
            if (!fileExists) {
                Files.createFile(filePath);
            }
            BufferedWriter myWriter = Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
            myWriter.write(taskList.toData());
            myWriter.close();
        } catch (IOException e) {
            System.out.print(e.getMessage());
            System.out.println("got an IOException");
        }
    }

    public void readData(TaskList taskList) {
        Path myPath = Paths.get("data/taskList.txt");
        boolean directoryExists = java.nio.file.Files.exists(myPath);
        if (directoryExists) {
            try {
                BufferedReader myReader = Files.newBufferedReader(myPath);
                myReader.lines().forEach(x -> {
                    String[] parsed = x.split(":");
                    String taskType = parsed[0];
                    String isFinished = parsed[1];
                    Task curTask;
                    if (taskType.equals("T")) {
                        curTask = new Todo(parsed[2]);
                    } else if (taskType.equals("D")) {
                        LocalDate date = LocalDate.parse(parsed[3], DateTimeFormatter.ISO_DATE);
                        curTask = new Deadline(parsed[2], date);
                    } else {
                        LocalDate time = LocalDate.parse(parsed[3], DateTimeFormatter.ISO_DATE);
                        curTask = new Event(parsed[2], time);
                    }
                    taskList.addTask(curTask);
                    if (isFinished.equals("1")) {
                        taskList.markFinished(taskList.size());
                    }
                });

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Savefile corrupted\n");
            }
        }
    }
}
