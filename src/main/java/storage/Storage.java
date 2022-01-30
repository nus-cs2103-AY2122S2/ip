package storage;

import exception.DukeException;
import task.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> setUpData() throws DukeException {
        try {
            FileReader myObj = new FileReader(this.path);
            BufferedReader br = new BufferedReader(myObj);
            String line;
            ArrayList<Task> tasks = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] lineArray = line.split("/");
                Task task;
                switch (lineArray[0]) {
                    case "T":
                        task = new Todo(lineArray[2]);
                        break;
                    case "E":
                        task = new Event(lineArray[2], lineArray[3]);
                        break;
                    default:
                        task = new Deadline(lineArray[2], lineArray[3]);
                }
                if (lineArray[1].equals("X")) {
                    task.setAsDone();
                }
                tasks.add(task);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("File does not exist.");
        }
    }

    public void updateData(TaskList tasklist) {
        try {
            FileWriter myObj = new FileWriter(this.path);
            myObj.flush();
            for (int i = 0; i < tasklist.tasks.size(); i++) {
                myObj.write(tasklist.tasks.get(i).symbol() + "/" + tasklist.tasks.get(i).getStatusIcon() + "/" +
                        tasklist.tasks.get(i).toString() + "\n");
            }
            myObj.close();
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
    }

}
