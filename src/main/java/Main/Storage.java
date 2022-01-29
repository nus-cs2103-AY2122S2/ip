package Main;

import Task.Task;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import Task.ToDo;
import Task.Deadline;
import Task.Event;

public class Storage {

    protected String savePath;

    public Storage(String s) {
        this.savePath = System.getProperty("user.dir") + "/" + s;
    }

    public void saveFile(String textToAdd) {

        if (savePath.contains("/")) {
            File dataFolder = new File(savePath.substring(0,savePath.lastIndexOf("/")));

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
        int countLst = 0;
        while (c.hasNextLine()) {
            String strLine = c.nextLine();
            String[] strWords =  strLine.split("[|]");

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
                }

                if (strWords[1].equals("1")) {
                    emptyTasks.get(countLst).markDone();
                }

                countLst++;

        }
        c.close();
        return emptyTasks;
    }


}
