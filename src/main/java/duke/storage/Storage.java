package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Tag;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Saves files to data/duke.txt and loads saved file.
 */
public class Storage {
    private final String filePath;
    private final Ui ui;

    /**
     * Instantiates a Storage object with filePath and ui.
     *
     * @param filePath String Path of the file.
     * @param ui       Ui UI object.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Loads the duke.txt file to get data from previous runs of duke.Duke.
     *
     * @return ArrayList of Tasks ArrayList that is populated with duke.txt save file.
     * @throws FileNotFoundException Exception thrown when file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        BufferedReader bufferedReader = null;
        if (!file.exists()) {
            try {
                File directory = new File(file.getParentFile().getAbsolutePath());
                directory.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
                    int indexOfTag = line.lastIndexOf(" | ");
                    if (indexOfTag == -1) {
                        if (!type.equals("T")) {
                            String content = "";
                            String dateString = "";
                            if (type.equals("D")) {
                                content = line.substring(7, line.lastIndexOf(" (by: "));
                                dateString = line.substring(line.lastIndexOf("by: ") + 4, line.lastIndexOf(")"));
                                LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                                if (isDone.equals("X")) {
                                    taskList.add(new Deadline(content, dateTime, true, ui));
                                } else {
                                    taskList.add(new Deadline(content, dateTime, ui));
                                }
                            } else {
                                content = line.substring(7, line.lastIndexOf(" (at: "));
                                dateString = line.substring(line.lastIndexOf("at: ") + 4, line.lastIndexOf(")"));
                                LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                                if (isDone.equals("X")) {
                                    taskList.add(new Event(content, dateTime, true, ui));
                                } else {
                                    taskList.add(new Event(content, dateTime, ui));
                                }
                            }
                        } else {
                            if (isDone.equals("X")) {
                                String content = line.substring(7);
                                taskList.add(new ToDo(content, true, ui));
                            } else {
                                String content = line.substring(7);
                                taskList.add(new ToDo(content, ui));
                            }
                        }
                    } else {
                        String tagString = line.substring(indexOfTag + 3);
                        String[] tagArray = tagString.split(" ");
                        ArrayList<Tag> tags = new ArrayList<>();
                        for (int k = 0; k < tagArray.length; k++) {
                            tagArray[k] = tagArray[k].substring(1);
                        }
                        for (String str : tagArray) {
                            Tag tag = new Tag(str);
                            tags.add(tag);
                        }
                        if (!type.equals("T")) {
                            String content = "";
                            String dateString = "";
                            if (type.equals("D")) {
                                content = line.substring(7, line.lastIndexOf(" (by: "));
                                dateString = line.substring(line.lastIndexOf("by: ") + 4, line.lastIndexOf(")"));
                                LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                                if (isDone.equals("X")) {
                                    taskList.add(new Deadline(content, dateTime, true, tags, ui));
                                } else {
                                    taskList.add(new Deadline(content, dateTime, false, tags, ui));
                                }
                            } else {
                                content = line.substring(7, line.lastIndexOf(" (at: "));
                                dateString = line.substring(line.lastIndexOf("at: ") + 4, line.lastIndexOf(")"));
                                LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
                                if (isDone.equals("X")) {
                                    taskList.add(new Event(content, dateTime, true, tags, ui));
                                } else {
                                    taskList.add(new Event(content, dateTime, false, tags, ui));
                                }
                            }
                        } else {
                            if (isDone.equals("X")) {
                                String content = line.substring(7, indexOfTag);
                                taskList.add(new ToDo(content, true, tags, ui));
                            } else {
                                String content = line.substring(7, indexOfTag);
                                taskList.add(new ToDo(content, false, tags, ui));
                            }
                        }
                    }

                }
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Saves the current tasks into data/duke.txt.
     *
     * @param taskList taskList object that contains all tasks to be saved
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                File directory = new File(file.getParentFile().getAbsolutePath());
                directory.mkdirs();
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
