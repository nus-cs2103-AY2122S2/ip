package Duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Disk {
    private final String path;
    private final ListStorage myStorage;

    public Disk(String path, ListStorage myStorage) {
        this.path = path;
        this.myStorage = myStorage;
    }

    public void saveToDisk() {
        try{
            BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
            String tasks = "";
            for (Task task : this.myStorage.myTasks) {
                if (task instanceof Event) {
                    Event currEvent = (Event) task;
                    tasks += 'E' + "," + task.isDone() + "," + task.description + "," + currEvent.at + '\n';
                } else if (task instanceof Deadline) {
                    Deadline currDeadline = (Deadline) task;
                    tasks += 'D' + "," + task.isDone() + "," + task.description +  "," + currDeadline.by + '\n';
                } else if (task instanceof ToDo){
                    tasks += 'T' + "," + task.isDone() + "," + task.description + '\n';
                }
            }
            taskWriter.write(tasks);
            taskWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to save task");
        }
    }

    public void loadFromDisk() throws IOException {
        File file = new File(this.path);
        file.getParentFile().mkdir();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String cmd = fileScanner.nextLine(); // e.g. T | 1 | description
            String [] separator = cmd.split(",", 4);
            Task currTask = null;
            switch(separator[0]) {
                case "T":
                    currTask = new ToDo(separator[2]);
                    break;
                case "D":
                    currTask = new Deadline(separator[2], separator[3]);
                    break;
                case "E":
                    currTask = new Event(separator[2], separator[3]);
            }
            if(separator[1].equals("1")) {
                assert currTask != null;
                currTask.markAsDone();
            }
            assert currTask != null;
            myStorage.addToList(currTask);
        }
        fileScanner.close();
    }
}
