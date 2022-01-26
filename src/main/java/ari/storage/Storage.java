package main.java.ari.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import main.java.ari.command.Command;
import main.java.ari.command.MarkCommand;
import main.java.ari.parser.Parser;
import main.java.ari.tasks.Task;
import main.java.ari.tasks.TaskList;

public class Storage { // deals with loading tasks from the file and saving tasks in the file
    public String path;
    protected File filePath;

    public Storage(String path) {
        this.path = path;
    }

    public Storage() {
    }

    public void setFile(String filePath) {
        path = filePath;

        this.filePath = new File(filePath);

        if (!this.filePath.exists()) {
            createFile();
        }
    }

    private void createFile() {
        String[] paths = path.split("/");
        String tempPath = "";
        for (String p : paths) {
            tempPath += p + "/";
            File file = new File(tempPath);
            if (p.contains(".")) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("An error has occurred in Storage class");
                    break;
                }
            } else {
                file.mkdir();
            }
        }
    }

    public void save(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();

        try {
            FileWriter wFile = new FileWriter(this.filePath);
            for (Task task : tasks) {
                wFile.write(task.writeToFile());
                wFile.write(System.lineSeparator());
            }
            wFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public TaskList load() {
        File file = new File(path);
        TaskList taskList = new TaskList();

        try {
            Scanner reader = new Scanner(file);
            Parser parser = new Parser();
            // add the data in the file to the list
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] todos = parser.fileBreakdown(line);

                Command cmd = parser.fileParse(todos[0], todos[2]);
                cmd.setTaskList(taskList);
                cmd.execute();

                if (todos[1].equals("1")) {
                    cmd = new MarkCommand(taskList.getSize());
                    cmd.setTaskList(taskList);
                    cmd.execute();
                }
            }

            reader.close();
        } catch (FileNotFoundException fileEx) {
            // if file is not found don't do anything
        }

        return taskList;
    }
}
