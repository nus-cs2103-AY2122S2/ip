package duke;

import myTasks.Deadline;
import myTasks.Event;
import myTasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void CreateFile() {
        try {
            File myObj = new File(this.filepath);
            if (myObj.createNewFile()) {
                System.out.println("Files created\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTasks(List<Task> list) {
        try {
            FileWriter myWriter = new FileWriter(this.filepath);
            for (Task task : list) {
                String completed = task.isDone ? "1|" : "0|";
                if (task instanceof Deadline) {
                    myWriter.write("D|" + completed + task.description + "|" + ((Deadline) task).dateTime + "\n");
                } else if (task instanceof Event) {
                    myWriter.write("E|" + completed + task.description + "|" + ((Event) task).dateTime + "\n");
                } else {
                    myWriter.write("T|" + completed + task.description + "\n");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> loadTasks() throws DukeException {
        List<String> list = new ArrayList<>();
        try {
            File myObj = new File(this.filepath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                list.add(myReader.nextLine());
            }
            myReader.close();
            System.out.println("Files Loaded Successfully\n");
        } catch (FileNotFoundException e) {
            CreateFile();
            throw new DukeException("No file found. Creating new file...");
        }
        return list;
    }
}