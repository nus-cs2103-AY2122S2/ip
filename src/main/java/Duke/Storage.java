package Duke;

import Task.Task;
import Task.ToDo;
import Task.Deadline;
import Task.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

public class Storage {

    protected Path filePath;

    public Storage() {
        try {
            Path dirToCreate = Paths.get("").resolve("output");
            Path subDir = Files.createDirectories(dirToCreate); // create subdirectory for output list
            this.filePath = Paths.get(subDir + "/" + "list.txt");
            boolean fileExists = Files.exists(this.filePath);
            if (!fileExists) { // if file does not exist, create the file
                Files.createFile(this.filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>(); // init arraylist

        try {
            FileReader fr = new FileReader(this.filePath.toString());
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String currLine;

            while ((currLine = br.readLine()) != null) {
                int taskInt = currLine.indexOf("[") + 1;
                char typeOfTask = currLine.charAt(taskInt);
                int doneInt = taskInt + 3;
                char taskDone = currLine.charAt(doneInt);
                String task = currLine.substring(doneInt + 3);
                if (typeOfTask == 'T') {
                    ToDo toDo = new ToDo(task);
                    if (taskDone == 'X') {
                        toDo.complete();
                    }
                    list.add(toDo);
                } else if (typeOfTask == 'D') {
                    int start = task.indexOf(":");
                    String date = task.substring((start + 2), (task.length() - 1));
                    Deadline deadline = new Deadline(task.substring(0, start - 4), date);
                    if (taskDone == 'X') {
                        deadline.complete();
                    }
                    list.add(deadline);
                } else if (typeOfTask == 'E') {
                    int start = task.indexOf(":");
                    String date = task.substring((start + 2), (task.length() - 1));
                    Event event = new Event(task.substring(0, start - 4), date);
                    if (taskDone == 'X') {
                        event.complete();
                    }
                    list.add(event);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        return list;
    }

    public void store(ArrayList<Task> list) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);

        for (int i = 0; i < list.size(); i++) {
            writer.println((i + 1) + "." + list.get(i));
        }
        String outputList = stringWriter.toString();

        try {
            FileWriter myWriter = new FileWriter(this.filePath.toString());
            myWriter.write(outputList);
            myWriter.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
