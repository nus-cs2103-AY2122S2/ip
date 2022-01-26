package duke.storage;

import duke.task.*;
import duke.ui.Ui;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        FileReader fileReader = new FileReader(file);
        bufferedReader = new BufferedReader(fileReader);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.length() == 0) {
                    return taskList;
                } else {
                    String typeAndIsDone = line.substring(0, 6);
                    String type = typeAndIsDone.substring(1, 2); // D, E, or T
                    String isDone = typeAndIsDone.substring(4, 5);
                    if (!type.equals("T")) {
                        String content = "";
                        String dateString = "";
                        if (type.equals("D")) {
                            content = line.substring(7, line.lastIndexOf(" (by: "));
                            dateString = line.substring(line.lastIndexOf("by: ") + 4, line.lastIndexOf(")"));
                            LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.formatter);
                            if (isDone.equals("X")) {
                                taskList.add(new Deadline(content, dateTime, true));
                            } else {
                                taskList.add(new Deadline(content, dateTime));
                            }
                        } else {
                            content = line.substring(7, line.lastIndexOf(" (at: "));
                            dateString = line.substring(line.lastIndexOf("at: ") + 4, line.lastIndexOf(")"));
                            LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.formatter);
                            if (isDone.equals("X")) {
                                taskList.add(new Event(content, dateTime, true));
                            } else {
                                taskList.add(new Event(content, dateTime));
                            }
                        }
                    } else {
                        String content = line.substring(7);
                        taskList.add(new ToDo(content));
                    }
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : taskList.getTasks()) {
                bufferedWriter.write(task.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("    File error: not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("    Error: cannot save file");
        }
    }
}
