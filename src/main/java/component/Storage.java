package component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.DeadLines;
import tasks.Events;
import tasks.Tasks;
import tasks.ToDos;

/**
 * A class that belongs to the DukeComponent Package.
 * This class deals with loading tasks from a pre-constructed file and saving tasks to the file
 * from the Duke program.
 */
public class Storage {
    private final String storageLocation;

    /**
     * Constructs Storage.
     * @param pathName Relative path to the file.
     */
    public Storage(String pathName) {
        this.storageLocation = Paths.get(System.getProperty("user.dir"), pathName).toString();
    }

    /**
     * Loads the content of the file to a TaskList so that it could be passed to Duke.
     * @return TaskList that is updated with contents from the previous Duke instances.
     */
    public ArrayList<Tasks> load() {
        ArrayList<Tasks> list = new ArrayList<Tasks>();
        File storageFile = new File(storageLocation);

        try {
            Scanner storageContent = new Scanner(storageFile);
            modifyList(list, storageContent);
            storageContent.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no cache, "
                    + "duke will be initialised as per normal.");
        }
        return list;
    }

    /**
     * Modifies the TaskList
     * @param list TaskList to be modified.
     * @param storageContent TaskList to be stored.
     */
    private void modifyList(ArrayList<Tasks> list, Scanner storageContent) {
        while (storageContent.hasNext()) {
            String nextLineOfContent = storageContent.nextLine();
            String[] splitNextLineByEmptySpaces = nextLineOfContent.split("|");
            String commandType = splitNextLineByEmptySpaces[0];
            boolean isMarked = splitNextLineByEmptySpaces[2].equals("1");
            int startingIndexOfDescription = nextLineOfContent.lastIndexOf("|") + 1;
            String description = nextLineOfContent.substring(startingIndexOfDescription);

            switch (commandType) {
            case "D":
                list.add(new DeadLines(
                        nextLineOfContent.substring(4, startingIndexOfDescription - 1),
                        isMarked, description));
                break;
            case "E":
                list.add(new Events(nextLineOfContent.substring(4, startingIndexOfDescription - 1),
                        isMarked, description));
                break;
            default: //case "T":
                list.add(new ToDos(description, isMarked));
                break;
            }
        }
    }

    /**
     * Writes the Tasks into the file for storage.
     * @param arr TaskList containing all the Tasks required for storage.
     */
    public void addTask(TaskList arr) {
        try {
            File f = new File(storageLocation);
            FileWriter fw = new FileWriter(f);
            for (Tasks t : arr) {
                fw.write(t.cacheString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
