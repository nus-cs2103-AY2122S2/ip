package tsundere;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Storage for managing save file.
 */
public class Storage {

    protected String savePath;

    /**
     * Creates a new storage class.
     *
     * @param s path to the save file.
     */
    public Storage(String s) {
        this.savePath = System.getProperty("user.dir") + "/" + s;
    }

    /**
     * Saves the tasks into the save file.
     *
     * @param textToAdd the tasks for saving.
     */
    public void saveFile(String textToAdd) {

        if (savePath.contains("/")) {
            File dataFolder = new File(savePath.substring(0, savePath.lastIndexOf("/")));

            if (!dataFolder.exists()) {
                //if data folder does not exists, create folder
                dataFolder.mkdirs();
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(savePath));
            bw.write(textToAdd);
            bw.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Reads the save file and return the list of tasks read.
     *
     * @return the list of saved task.
     * @throws TsundereException if the save file is corrupted.
     */
    public List<Task> load() throws TsundereException {
        List<Task> emptyTasks = new ArrayList<>();
        Path saveFile = Path.of(savePath);
        String returnStr = "";
        try {
            returnStr = Files.readString(saveFile);
        } catch (IOException e) {
            //no save file, no need read
            return emptyTasks;
        }
        Scanner c = new Scanner(returnStr);
        int i = 0;
        while (c.hasNextLine()) {
            String strLine = c.nextLine();
            String[] strWords = strLine.split("[|]");
            switch (strWords[0]) {
            case "T" :
                if (strWords.length < 3) {
                    throw new TsundereException("Save File Corrupted... One task can't be read!");
                }
                emptyTasks.add(new ToDo(strWords[2]));
                break;
            case "E":
                if (strWords.length < 4) {
                    throw new TsundereException("Save File Corrupted... One task can't be read!");
                }
                emptyTasks.add(new Event(strWords[2], strWords[3]));
                break;
            case "D":
                if (strWords.length < 4) {
                    throw new TsundereException("Save File Corrupted... One task can't be read!");
                }
                emptyTasks.add(new Deadline(strWords[2], strWords[3]));
                break;
            default:
            }

            if (strWords[1].equals("1")) {
                emptyTasks.get(i).markDone();
            }

            i++;

        }
        c.close();
        return emptyTasks;
    }


}
