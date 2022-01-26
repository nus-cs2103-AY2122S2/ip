package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private Ui ui;
    private String filePath;

    public Storage(String path) {
        this.filePath = path;
        this.ui = new Ui();
    }

    public void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public void setInFile(int lineNumber, String data) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber - 1, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }

    public void writeToFile(List<Task> taskList) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : taskList) {
            stringBuilder.append(task.taskDescriptionForFile()
                    + System.lineSeparator());
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(stringBuilder.toString());
        fw.close();
    }

    public List<String> loadFileContents() throws DukeException, IOException {
        boolean gotError = false;
        try {
            Path path = Paths.get(filePath);
            Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException err) {
            gotError = true;
            throw new DukeException("Error loading file!");
        } finally {
            if (!gotError) {
                Path path = Paths.get(filePath);
                List<String> data = Files.readAllLines(path, StandardCharsets.UTF_8);
                return data;
            } else {
                return null;
            }
        }
    }
}
