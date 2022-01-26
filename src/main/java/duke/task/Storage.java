package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public Storage() {
    }

    public void checkFile() {
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {
        }

        try {
            Files.createFile(Paths.get("data/duke.txt"));
        } catch (IOException ignored) {
        }
    }

    public void saveFile(ArrayList<duke.task.Task> toDoList) {
        try {
            StringBuilder textToAdd = new StringBuilder();
            for (duke.task.Task task : toDoList) {
                textToAdd.append(task.toText());
            }

            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException ex) {
            checkFile();
            saveFile(toDoList);
        }
    }

    public ArrayList<duke.task.Task> readFile() throws duke.task.LoadingException {
        File dataFile = new File("data/duke.txt");
        ArrayList<duke.task.Task> toDoList = new ArrayList<>(100);
        try {
            Scanner s = new Scanner(dataFile);
            String[] taskLine;
            while (s.hasNext()) {
                taskLine = s.nextLine().split(" \\| ");

                switch (taskLine[0]) {
                case "T":
                    taskLine[2].trim();
                    toDoList.add(new duke.task.ToDo(taskLine[2]));
                    break;
                case "D":
                    toDoList.add(new duke.task.Deadline(taskLine[2], taskLine[3]));
                    break;
                case "E":
                    toDoList.add(new duke.task.Event(taskLine[2], taskLine[3]));
                    break;
                }

                if (taskLine[1].equals("1")) {
                    toDoList.get(toDoList.size() - 1).mark();
                }
            }

            return toDoList;
        } catch (FileNotFoundException ignored) {
        } catch (IndexOutOfBoundsException ex) {
            throw new duke.task.LoadingException("Index Out of Bounds");
        }
        return new ArrayList<duke.task.Task> (100);
    }
}
