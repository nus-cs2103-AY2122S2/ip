package duke.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import java.util.ArrayList;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.ToDo;
import duke.commands.Task;


public class Storage {

    private String dirPath;
    private String filePath;

    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFileContents() throws IOException {
        if (!new File(this.dirPath).exists()) {
            Files.createDirectory(Path.of(dirPath));
        }

        if (!new File(this.filePath).exists()) {
            File newFile = new File(filePath);
            newFile.createNewFile();
        }

        ArrayList<Task> taskArr = new ArrayList<Task>();

        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/main/data/duke.txt");
            BufferedReader reader = java.nio.file.Files.newBufferedReader(path);
            String line;
            while((line = reader.readLine()) != null) {
                String[] temp = line.split("#");
                String command = temp[0];
                boolean isMarked = temp[1].equals("X");
                String desc = temp[2];
                Task t;
                if (command.equals("T")) {
                    t = new ToDo(desc);
                } else if (command.equals("D")) {
                    String by = temp[3];
                    t = new Deadline(desc, by);
                } else {
                    String time = temp[3];
                    t = new Event(desc, time);
                }

                if (isMarked) {
                    t.markDone();
                } else {
                    t.markUndone();
                }

                taskArr.add(t);

            }
        } catch (NoSuchFileException e) {
            System.out.println("File not found");
        }
        return taskArr;
    }

    public static ArrayList<Task> saveToFile(ArrayList<Task> taskArr) {
        try {
            java.nio.file.Path path = java.nio.file.Paths.get("src/main/data/duke.txt");
            BufferedWriter writer = java.nio.file.Files.newBufferedWriter(path);
            for (Task t : taskArr) {
                String toSave;
                String separator = "#";
                String command = t.getType();
                String mark = t.getStatusIcon();
                String desc = t.getDescription();
                String dateTime = (t.getDate().replace(" ", "-") + " " + t.getTime()).trim();
                if (command.equals("D") | command.equals("E")) {
                    toSave = String.join(separator, command, mark, desc, dateTime);
                } else {
                    toSave = String.join(separator, command, mark, desc);
                }
                writer.write(toSave + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskArr;
    }
}
