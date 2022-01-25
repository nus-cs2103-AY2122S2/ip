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
    private final File save;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Storage() {
        Path pathName = Paths.get("./data/Duke.txt");
        this.save = new File(pathName.toString());
        try {
            if (save.getParentFile().mkdir()) {
                save.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(new DukeException("Failed to create directory " + save.getParent()));
        }
    }

    /**
     * returns an arraylist populated with pre-saved tasks from data/duke.txt
     * @return list populated with pre-saved tasks
     */
    public ArrayList<Task> loadList() {
        FileDecoder decoder = new FileDecoder();
        try {
            Scanner s = new Scanner(save);
            while (s.hasNextLine()) {
                tasks.add(decoder.decode(s.nextLine()));
            }
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tasks;
    }

    /**
     * updates the data/duke.txt file after changes are made to the tasklist
     * @param currentTasks updated tasklist
     */
    public void updateTasks(ArrayList<Task> currentTasks) {
        try {
            FileWriter fileWriter = new FileWriter(save);
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
