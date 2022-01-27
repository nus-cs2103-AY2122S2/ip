package tesseract.main;

import tesseract.main.TaskList;
import tesseract.main.TesseractException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected File file;
    protected String path;
    protected boolean updated;

    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
        this.updated = false;
    }

    public List<String> getStorage() throws TesseractException {
        List<String> tasks = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNext()) {
                tasks.add(sc.nextLine()); // sc.nextLine()).split("@", 4);
            }
        } catch (IOException e) {
            try {
                this.file.createNewFile();
            } catch (IOException err) {
                throw new TesseractException("Not joking but I cannot create a memory for you.. \n" +
                        "You mind changing a laptop?");
            }
        }
        return tasks;
    }

    public void needUpdate() {
        this.updated = true; // only update if there is change (new task added)
    }

    public boolean isUpdated() {
        return this.updated;
    }

    public void updateStorage(TaskList taskList, int numOfTasks) throws TesseractException {
        try {
            FileWriter fw = new FileWriter(this.path);
            for (int i = 0; i < numOfTasks; i++) {
                fw.write(taskList.get(i).toMemoryString());
                fw.write(System.lineSeparator());
            }
            fw.close();
            this.updated = false; // storage is up-to-date
        } catch (IOException e) {
            throw new TesseractException("Sorry but I cannot upload your list of tasks into memory due to" +
                    "some unforeseen errors :(\n Try command force exit (to be added in later)");
        }
    }
}
