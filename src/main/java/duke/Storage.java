package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task list from file
     *
     * @return List of task in file
     * @throws IOException
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>(100);
        boolean isCorrectLength;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            File file = new File("./data/duke.txt");
            Scanner fileData = new Scanner(file);
            while (fileData.hasNextLine()) {
                String data = fileData.nextLine();
                String[] dataSplited = data.split(",");
                String taskType = dataSplited[0].toUpperCase();
                boolean isDone = dataSplited[1].equals("1");

                switch (taskType) {
                case "T":
                    isCorrectLength = (dataSplited.length == 3);
                    if (!isCorrectLength) {
                        System.out.println("data: " + data + " not in correct format!");
                        break;
                    }
                    Task todo = new Todo(dataSplited[2], isDone);
                    tasks.add(todo);
                    break;
                case "D":
                    isCorrectLength = (dataSplited.length == 4);
                    if (!isCorrectLength) {
                        System.out.println("data: " + data + " not in correct format!");
                        break;
                    }
                    LocalDateTime deadlineDateFormatted = LocalDateTime.parse(dataSplited[3], format);
                    Task deadline = new Deadline(dataSplited[2], deadlineDateFormatted, isDone);
                    tasks.add(deadline);
                    break;
                case "E":
                    isCorrectLength = (dataSplited.length == 4);
                    if (!isCorrectLength) {
                        System.out.println("data: " + data + " not in correct format!");
                        break;
                    }
                    LocalDateTime eventDateFormatted = LocalDateTime.parse(dataSplited[3], format);
                    Task event = new Event(dataSplited[2], eventDateFormatted, isDone);
                    tasks.add(event);
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            }
            fileData.close();
            return tasks;
        } catch (FileNotFoundException e) {
            assert filePath != null : "File path is not supposed to be null.";

            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            return tasks;
        }
    }

    /**
     * Add task to file
     *
     * @param data
     * @throws IOException
     */
    public void addToFile(String data) throws IOException {
        File file = new File(filePath);
        Writer output = new BufferedWriter(new FileWriter(file, true));
        output.append(data + "\n");
        output.close();
    }

    /**
     * Delete task from file
     *
     * @param index
     * @throws IOException
     */
    public void deleteFileData(int index) throws IOException {

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuffer buffer = new StringBuffer();
        int currLine = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (currLine != index) {
                buffer.append(line + System.lineSeparator());
            }
            currLine++;
        }
        String fileContents = buffer.toString();
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }

    /**
     * Mark task as done/undone in file
     *
     * @param index
     * @param isDone
     * @throws IOException
     */
    public void updateTaskStatus(int index, boolean isDone) throws IOException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        //store the string content of task to be updated
        String task = null;
        StringBuffer buffer = new StringBuffer();
        while (index > 0) {
            task = scanner.nextLine();
            index -= 1;
        }
        scanner.close();
        String newData = null;
        //store updated string
        if (isDone) {
            newData = task.replaceFirst("0", "1");
        } else {
            newData = task.replaceFirst("1", "0");
        }

        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            buffer.append(scanner.nextLine() + System.lineSeparator());
        }
        String fileContents = buffer.toString();
        fileContents = fileContents.replaceAll(task, newData);
        FileWriter writer = new FileWriter(filePath);
        writer.append(fileContents);
        writer.flush();
    }
}
