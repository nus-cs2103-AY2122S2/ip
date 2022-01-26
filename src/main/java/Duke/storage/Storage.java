package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import duke.ui.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Storage {
    private String saveFilePath;

    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    public ArrayList<Task> load() throws DukeException{
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdir();
            saveFile.createNewFile();
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] currTaskLine = currLine.split("\\|");
                switch (currTaskLine[0]) {
                case "T":
                    ToDo todoTask = new ToDo(currTaskLine[2]);
                    if (currTaskLine[1].equals("1")) {
                        todoTask.setChecked();
                    }
                    taskList.add(todoTask);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(currTaskLine[2], currTaskLine[3]);
                    if (currTaskLine[1].equals("1")) {
                        deadlineTask.setChecked();
                    }
                    taskList.add(deadlineTask);
                    break;
                case "E":
                    Event eventTask = new Event(currTaskLine[2], currTaskLine[3]);
                    if (currTaskLine[1].equals("1")) {
                        eventTask.setChecked();
                    }
                    taskList.add(eventTask);
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException("Opps! An error occurred. @.@");
        }
        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws DukeException{
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).saveToFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred. @.@");
        }
    }
}
