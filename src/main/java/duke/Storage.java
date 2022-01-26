package duke;

import duke.ChiException;
import duke.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String dataFilePath;
    private String dataFolderPath;
    private File dataFile;


    public Storage(String path) {
        this.dataFilePath = path;
        String[] rPath = path.split("/");
        StringBuilder s = new StringBuilder("./");
        for (int i = 0; i < rPath.length  - 1;i++) {
            s.append(rPath[i]);
        }
        this.dataFolderPath = s.toString();
    }

    private Task manageFileData(String task) {
        // We assume the task stored inside is correct
        String[] splitTask = task.split("\\|");
        for (int i = 0; i < splitTask.length;i++) {
            splitTask[i] = splitTask[i].trim();
        }
        if (splitTask[0].equals("T")) {
            if (splitTask[1].equals("1")) {
                return new Todo(splitTask[2], true);
            }
            return new Todo(splitTask[2], false);
        } else if (splitTask[0].equals("D")) {
            if (splitTask.length == 5) {
                return new Deadline(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
            } else {
                return new Deadline(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        splitTask[1].equals("1"));
            }
        } else {
            if (splitTask.length == 6) {
                return new Event(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.parse(splitTask[4], DateTimeFormatter.ofPattern("HH:mm")),
                        LocalTime.parse(splitTask[5], DateTimeFormatter.ofPattern("HH:mm")), splitTask[1].equals("1"));
            } else {
                return new Event(splitTask[2], LocalDate.parse(splitTask[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        splitTask[1].equals("1"));
            }
        }
    }

    public ArrayList<Task> load() throws IOException, ChiException {
        try {
            ArrayList<Task> loadedTasks = new ArrayList<>();
            this.dataFile = new File(dataFilePath);
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String task = s.nextLine();
                Task t = manageFileData(task);
                loadedTasks.add(t);
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            File dataFolder = new File(dataFolderPath);
            if (!dataFolder.isDirectory()) {
                dataFolder.mkdir();
            }
            File newDataFile = new File(dataFilePath);
            newDataFile.createNewFile();
            throw new ChiException("File not found, restart program nyan!");
        }

    }

    public void updateFile(Task task, TaskList tl, String type) throws IOException {
        FileWriter fw;
        switch(type) {
            case "mark":
            case "unmark":
            case "delete":
                fw = new FileWriter(dataFilePath);
                fw.write(tl.getTaskStore());
                fw.close();
                break;
            case "event":
            case "deadline":
            case "todo":
                fw = new FileWriter(dataFilePath, true);
                fw.write(task.writeToFile());
                fw.close();
                break;
        }
    }

}
