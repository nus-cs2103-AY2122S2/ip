package main.io;

import main.tasks.Deadline;
import main.tasks.Event;
import main.tasks.Task;
import main.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    protected String filename;
    protected String dirname;

    public Storage(String dirname, String filename) {
        this.filename = filename;
        this.dirname = dirname;
        this.createFile();
    }

    private String getCurrentDirectory() {
        return System.getProperty("user.dir");
    }

    private String getDirname() { return this.dirname; }

    private String getFilename() { return this.filename; }

    private Path getDirPath() {
        return Paths.get(this.getCurrentDirectory(), this.getDirname());
    }

    private Path getFilePath() {
        return Paths.get(this.getCurrentDirectory(),
                String.format("%s/%s", this.getDirname(), this.getFilename()));
    }

    private void createFile(){
        try {
            if (!Files.exists(this.getDirPath())) {
                Files.createDirectories(this.getDirPath());
            }
            if (!Files.exists(this.getFilePath())) {
                Files.createFile(this.getFilePath());
            }
        } catch (IOException e) {
            System.out.printf("Error while trying to create save file: ", e.getMessage());
        }
    }

    public void readFile() {
        try {
            File dukeFile = new File(String.format("%s/%s", this.getDirname(), this.getFilename()));
            Scanner dukeReader = new Scanner(dukeFile);
            while (dukeReader.hasNextLine()) {
                String taskString = dukeReader.nextLine();
                String[] taskStringArray = taskString.split("~");
                this.addToTasks(taskStringArray);
            }
            dukeReader.close();
        }  catch (IOException e) {
            System.out.printf("Error while trying to read save file: ", e.getMessage());
        }
    }

    public void addToTasks(String[] taskStringArray) {
        String type = taskStringArray[0];
        boolean isDone = taskStringArray[1].equals("1");
        String description = taskStringArray[2];
        switch (type) {
        case "T":
            Task.addTask(new ToDo(description, isDone));
            break;
        case "D":
            Task.addTask(new Deadline(description, taskStringArray[3], isDone));
            break;
        case "E":
            Task.addTask(new Event(description, taskStringArray[3], isDone));
            break;
        }
    }

    public void writeFile() {
        try {
            FileWriter dukeWriter = new FileWriter(String.format("%s/%s", this.getDirname(), this.getFilename()));
            for (int i = 0; i < Task.getTasksCount(); i++) {
                if (i != Task.getTasksCount()) {
                    dukeWriter.write(Task.getTask(i).toStoreString() + "\n");
                }
            }
            dukeWriter.close();
        } catch (IOException e) {
            System.out.printf("Error while trying to write save file: ", e.getMessage());
        }
    }
}
