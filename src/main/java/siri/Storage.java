package siri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Storage.java reused and edited from Brigette Santoso E0564307

/**
 * Storage handles loading and saving of the user commands into siri.txt.
 */
public class Storage {

    static final int FIRST_INDEX = 0;
    static final int SECOND_INDEX = 1;
    static final int THIRD_INDEX = 2;
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws InvalidInputException {
        try {
            File file = new File(filePath);

            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String taskDetails;
                ArrayList<Task> tasks = new ArrayList<>();

                while ((taskDetails = br.readLine()) != null) {
                    Task task;
                    String[] taskDetailsArr  = taskDetails.split("\\s\\|\\s");
                    String taskInitials = taskDetailsArr[FIRST_INDEX];
                    String taskDescription = taskDetailsArr[SECOND_INDEX];

                    switch (taskInitials) {
                    case "T":
                        task = new Todo(taskDescription, taskInitials);
                        break;
                    case "D": {
                        String taskDate = taskDetailsArr[THIRD_INDEX];
                        task = new Deadline(taskDescription, taskInitials, taskDate);
                        break;
                    }
                    case "E": {
                        String taskDate = taskDetailsArr[THIRD_INDEX];
                        task = new Event(taskDescription, taskInitials, taskDate);
                        break;
                    }
                    default:
                        throw new InvalidInputException("Please enter a valid task type with description.");
                    }
                    tasks.add(task);
                }
                return tasks;

            } else {
                return new ArrayList<>();
            }

        } catch (IOException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws InvalidInputException {
        try {
            File file = new File("./data/siri.txt");
            //file.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (Task task: tasks) {

                String taskInitials = task.initialLetter;
                if (taskInitials.equals("T")) {
                    String taskDescription = task.description;
                    String taskToWrite = taskInitials + " | " + taskDescription + "\n";
                    bw.write(taskToWrite);
                } else if (taskInitials.equals("D")) {
                    String taskDescription = task.description;
                    String taskDeadline = ((Deadline)task).deadline;
                    String taskToWrite = taskInitials + " | " + taskDescription + " | " + taskDeadline + "\n";
                    bw.write(taskToWrite);
                } else if (taskInitials.equals("E")) {
                    String taskDescription = task.description;
                    String taskDate = ((Event)task).eventDate;
                    String taskToWrite = taskInitials + " | " + taskDescription + " | " + taskDate + "\n";
                    bw.write(taskToWrite);
                }
            }
            bw.close();

        } catch (IOException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
}