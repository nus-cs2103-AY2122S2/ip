package pac.storage;

import pac.task.Deadline;
import pac.task.Event;
import pac.PacException;
import pac.task.Task;
import pac.task.TaskList;
import pac.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final File file;

    public Storage(String filePath) throws IOException, PacException {

        File folder = new File(filePath.split("/", 2)[0]);
        if (!folder.exists()) {
            if(!folder.mkdir()) {
                throw new PacException("Something went wrong while creating data file.");
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            if(!file.createNewFile()) {
                throw new PacException("Something went wrong while creating data file.");
            }
        }

        this.file = file;
    }

    public void writeTasks(TaskList tasks) throws IOException{
        String textToWrite = "";
        FileWriter fw = new FileWriter(file);
        for (Task t : tasks.getTasks()) {
            textToWrite = textToWrite + t.toWrite();
        }
        fw.write(textToWrite);
        fw.close();
    }

    public ArrayList<Task> readTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String textToRead = sc.nextLine();
            String[] textArray = textToRead.split("~");
            switch (textArray[0]) {
            case "T":
                tasks.add(new ToDo(textArray[2], textArray[1].equals("1")));
                break;
            case "E":
                tasks.add(new Event(textArray[2], textArray[3],
                        textArray[1].equals("1")));
                break;
            case "D":
                tasks.add(new Deadline(textArray[2], textArray[3],
                        textArray[1].equals("1")));
                break;
            default:
                break;
            }
        }
        return tasks;
    }
}
