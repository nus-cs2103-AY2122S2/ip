package storage;

import backend.FileDecoder;
import exception.DukeException;
import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File saveFile;
    private final ArrayList<Task> TASKS = new ArrayList<>();

    public Storage() {
        Path pathName = Paths.get("./data/Duke.txt");
        this.saveFile = new File(pathName.toString());
        try {
            if (saveFile.getParentFile().mkdir()) {
                saveFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(new DukeException("Failed to create directory " + saveFile.getParent()));
        }
    }

    /**
     * returns an arraylist populated with pre-saved tasks from data/duke.txt
     * @return list populated with pre-saved tasks
     */
    public ArrayList<Task> loadList() {
        FileDecoder decoder = new FileDecoder();
        try {
            Scanner s = new Scanner(saveFile);
            while (s.hasNextLine()) {
                TASKS.add(decoder.decode(s.nextLine()));
            }
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return TASKS;
    }

    /**
     * updates the data/duke.txt file after changes are made to the tasklist
     * @param currentTasks updated tasklist
     */
    public void updateSavedTasks(ArrayList<Task> currentTasks) {
        try {
            FileWriter fileWriter = new FileWriter(saveFile);
            String allItems = "";
            for (int i = 0; i < currentTasks.size(); i++) {
                allItems = allItems + currentTasks.get(i).toString() + "\n";
            }
            fileWriter.write(allItems);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(new DukeException("Storage Error!"));
        }
    }
}
