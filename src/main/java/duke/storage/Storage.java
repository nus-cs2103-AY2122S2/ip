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
    private static final int PREFIX_LENGTH = 7;

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

    private ArrayList<Tag> parseTags(String command) {
        ArrayList<Tag> resultTagList = new ArrayList<>();
        int indexOfTag = command.lastIndexOf(" | ");
        if (indexOfTag == -1) {
            return resultTagList;
        } else {
            String tagString = command.substring(indexOfTag + 3);
            String[] tagStringArray = tagString.split(" ");
            for (int k = 0; k < tagStringArray.length; k++) {
                tagStringArray[k] = tagStringArray[k].substring(1);
            }
            for (String str : tagStringArray) {
                Tag tag = new Tag(str);
                resultTagList.add(tag);
            }
        }
        return resultTagList;
    }

    /**
     * Parses the command with isDone attribute. Return ToDo, Event, or Deadline.
     *
     * @param command String string of command
     * @return Task associated with the loaded String.
     */
    private Task parseTask(String command) {
        String typeAndIsDone = command.substring(0, PREFIX_LENGTH - 1);
        String type = typeAndIsDone.substring(1, 2); // D, E, or T
        String doneString = typeAndIsDone.substring(4, 5);
        ArrayList<Tag> tagList = parseTags(command);
        int indexOfTag = command.lastIndexOf(" | ");

        boolean isDone;
        if (doneString.equals("X")) {
            isDone = true;
        } else {
            isDone = false;
        }
        String contentString = "";
        if (tagList.isEmpty()) {
            contentString = command.substring(PREFIX_LENGTH);
        } else {
            contentString = command.substring(PREFIX_LENGTH, indexOfTag);
        }

        if (type.equals("T")) {
            return parseToDo(contentString, isDone, tagList);
        } else if (type.equals("E")) {
            return parseEvent(contentString, isDone, tagList);
        } else {
            return parseDeadline(contentString, isDone, tagList);
        }
    }

    /**
     * Parses the given command into a ToDo object.
     *
     * @param command String of command, including only content.
     * @param isDone  Boolean on whether the task is marked as done.
     * @param tagList ArrayList of tags.
     * @return ToDo object.
     */
    private ToDo parseToDo(String command, boolean isDone, ArrayList<Tag> tagList) {
        String content = command;
        return new ToDo(content, isDone, tagList, ui);
    }

    /**
     * Parses the given command into a Event object.
     *
     * @param command String of command, including only content and date.
     * @param isDone  Boolean on whether the task is marked as done.
     * @param tagList ArrayList of tags.
     * @return Event object.
     */
    private Event parseEvent(String command, boolean isDone, ArrayList<Tag> tagList) {
        int dateMarkerIndex = command.lastIndexOf(" (at: ");
        String content = command.substring(0, dateMarkerIndex);
        String dateString = command.substring(dateMarkerIndex + 6, command.length() - 1);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
        return new Event(content, dateTime, isDone, tagList, ui);
    }

    /**
     * Parses the given command into a Deadline object.
     *
     * @param command String of command, including only content and date.
     * @param isDone  Boolean on whether the task is marked as done.
     * @param tagList ArrayList of tags.
     * @return Deadline object.
     */
    private Deadline parseDeadline(String command, boolean isDone, ArrayList<Tag> tagList) {
        int dateMarkerIndex = command.lastIndexOf(" (by: ");
        String content = command.substring(0, dateMarkerIndex);
        String dateString = command.substring(dateMarkerIndex + 6, command.length() - 1);
        LocalDateTime dateTime = LocalDateTime.parse(dateString, Ui.OUTPUT_FORMATTER);
        return new Deadline(content, dateTime, isDone, tagList, ui);
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
                Task parsedTask = parseTask(line);
                taskList.add(parsedTask);
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
            System.out.println("File error: not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: cannot save file");
        }
    }
}
