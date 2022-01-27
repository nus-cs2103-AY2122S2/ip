package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    String fileName;
    final String DATA_PATH = "data";

    public Storage(String fileName) {
        File directory = new File(DATA_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        this.fileName = fileName;
    }

    public void WriteToFile(String text) {
        try {
            File myFile = new File(DATA_PATH + "/" + fileName);
            FileWriter fw = new FileWriter(myFile, true); // Boolean is for append mode
            fw.write(text);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to file");
        }
    }

    public void OverwriteFile(List<Task> tasks) {
        try {
            File myFile = new File(DATA_PATH + "/" + fileName);
            FileWriter fw = new FileWriter(myFile); // Boolean is for append mode
            String text = new String();
            for (int i = 0;i < tasks.size();i++) {
                text += tasks.get(i).getFileString();
            }
            fw.write(text);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to file");
        }
    }

    public List<Task> ParseFile() throws DukeException {
        try {
            File myFile = new File(DATA_PATH + "/" + fileName);
            if (myFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(myFile));
                String tempLine;
                List<Task> list = new ArrayList<>();
                while ((tempLine = br.readLine()) != null) {
                    // Split[0] = identifier, [1] = isDone, [2] = Description, [3] = at or by
                    String [] split = tempLine.split("\\|");
                    if (split[0].equals("T")) {
                        list.add(new Todo(split[2],split[1].equals("1")));
                    }
                    else if (split[0].equals("D")) {
                        list.add(new Deadline(split[2],split[1].equals("1"), split[3]));
                    }
                    else if (split[0].equals("E")) {
                        list.add(new Event(split[2],split[1].equals("1"), split[3]));
                    }
                }
                br.close();
                return list;
            }
        }
        catch (IOException e) {
            throw new DukeException("An error occurred while reading file");
        }
        return null;
    }
}
