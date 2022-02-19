package siri;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Storage.java reused and edited from Brigette Santoso E0564307
public class Storage {
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
                    String taskInitials = taskDetailsArr[0];
                    String taskDescription = taskDetailsArr[1];
                    if (taskInitials.equals("T")) {
                        task = new Todo(taskDescription, taskInitials);
                    } else if (taskInitials.equals("D")) {
                        String taskDate = taskDetailsArr[2];
                        task = new Deadline(taskDescription, taskInitials, taskDate);
                    } else if (taskInitials.equals("E")) {
                        String taskDate = taskDetailsArr[2];
                        task = new Event(taskDescription, taskInitials, taskDate);
                    } else {
                        throw new InvalidInputException("Please enter a valid task type with description.");
                    }
                    tasks.add(task);
                }
                return tasks;
            } else {
                return new ArrayList<Task>();
            }
        }
        catch (IOException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) throws InvalidInputException {
        try {
            File file = new File("./data/siri.txt");
            file.getParentFile().mkdirs();
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
        }
        catch (IOException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
}