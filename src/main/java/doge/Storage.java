package doge;

import doge.exception.DogeException;
import doge.DateTime;
import doge.task.Deadline;
import doge.task.Event;
import doge.task.Task;
import doge.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Represents the local storage that stores the TaskList.
 */
public class Storage {

    /**
     * Constructor for class Storage
     */
    public Storage()  {
    }

    /**
     * Saves the tasks into local storage.
     *
     * @param tasks the list of tasks
     * @throws DogeException if writing to local storage fails
     */
    public void save(ArrayList<Task> tasks) throws DogeException {
        try {
            FileWriter fw = new FileWriter("./data/doge.txt");
            for (Task curr : tasks) {
                fw.write(System.lineSeparator() + curr.toString());
            }
            fw.close();
        } catch (IOException e) {
            throw new DogeException("Write to storage file failed!");
        }
    }

    /**
     * Loads the tasks from local storage.
     *
     * @return the list of tasks
     * @throws DogeException if it fails to create a new storage file or the parsing of data failed
     */
    public ArrayList<Task> load() throws DogeException {
        try {
            Path p = Paths.get("./data");
            Files.createDirectories(p);
        } catch (IOException e) {
            throw new DogeException("Failed to create new directory!");
        }

        try {
            File f = new File( "./data/doge.txt");
            ArrayList<Task> temp = new ArrayList<>();

            if (f.createNewFile()) {
                System.out.println("<NOTICE> Storage file does not exist! Creating one right now...");
            } else {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String[] curr = s.nextLine().split( "\\|");
                    String taskStatus = curr.length > 1 ? curr[1].trim() : null;
                    String[] dateTime;
                    Task currTask;
                    Date date;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    switch(curr[0].trim()) {
                    case "T":
                        currTask = new Todo(curr[2].trim());
                        if (taskStatus.equals("X")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    case "D":
                        dateTime = curr[3].trim().substring(9).trim().split(" ");
                        date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateTime[0]);
                        currTask = new Deadline(curr[2].trim(),
                                DateTime.getDateTime(sdf.format(date) + " " + dateTime[1]));
                        if (taskStatus.equals("X")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    case "E":
                        dateTime = curr[3].trim().substring(3).trim().split(" ");
                        date = new SimpleDateFormat("dd-MMM-yyyy").parse(dateTime[0]);
                        currTask = new Event(curr[2].trim(),
                                DateTime.getDateTime(sdf.format(date) + " " + dateTime[1]));
                        if (taskStatus.equals("X")) {
                            currTask.mark();
                        }
                        temp.add(currTask);
                        break;
                    }
                }
            }
            return temp;
        } catch (IOException e) {
            throw new DogeException("Failed to create new storage file!");
        } catch (ParseException e) {
            throw new DogeException("Failed to parse date!");
        }
    }
}
