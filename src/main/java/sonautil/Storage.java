package sonautil;

import task.*;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;
import java.util.Scanner;


public class Storage {
    public String path;

    public Storage(String path) {
        this.path = path;
    }

    public void executeCommand(String[] command) throws IOException, DukeException {

        String keyword = command[0];
        switch (keyword) {
            case "todo":
                writeToFile("T | 0 | " + command[1]);
                break;
            case "event":
                writeToFile("E | 0 | " + command[1] + " | " + command[2]);
                break;
            case "deadline":
                writeToFile("D | 0 | " + command[1] + " | " + command[2]);
                break;
            case "delete":
                deleteFromFile(Integer.parseInt(command[1]));
                break;
            case "mark":
                changeMarking("0", "1", Integer.parseInt(command[1]));
                break;
            case "unmark":
                changeMarking("1", "0", Integer.parseInt(command[1]));
                break;
        }
    }

    public void deleteFromFile(int taskToDelete) {
        String oldFileName = "src/main/data/duke.txt";
        String tempFileName = "src/main/data/tempDuke.txt";
        File tempFile = new File(tempFileName);

        int indexAccessed = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(oldFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (indexAccessed != taskToDelete) {
                    if (first) {
                        bw.write(line);
                        first = false;
                    } else {
                        bw.write("\n" + line);
                    }
                }
                indexAccessed ++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile = new File(oldFileName);
        oldFile.delete();

        File newFile = new File(tempFileName);
        newFile.renameTo(oldFile);
    }

    public void writeToFile(String taskToAdd) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        long line = Files.lines(Path.of(path)).count();
        if (line == 0) {
            fw.write(taskToAdd);
        } else {
            fw.write(System.lineSeparator() + taskToAdd);
        }
        fw.close();
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        try {
            return fileContentProcess();
        } catch (FileNotFoundException e) {
            Ui.fileNotFoundMessage();
            createNewFile();
            return new ArrayList<>();
        }
    }

    public ArrayList<Task> fileContentProcess() throws FileNotFoundException {
        File f = new File(path);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>(100);
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskDetail = task.split(" \\| ");

            switch (taskDetail[0]) {
                case "T":
                    tasks.add(new Todo(taskDetail[2], Integer.parseInt(taskDetail[1])));
                    break;

                case "D":
                    tasks.add(new Deadline(taskDetail[2],
                            Integer.parseInt(taskDetail[1]), LocalDateTime.parse(taskDetail[3])));
                    break;

                case "E":
                    tasks.add(new Event(taskDetail[2],
                            Integer.parseInt(taskDetail[1]), LocalDateTime.parse(taskDetail[3])));
                    break;
            }
        }
        return tasks;
    }

    public void createNewFile() throws IOException {
        File f = new File("src/main/data/duke.txt");
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

    private void changeMarking(String init, String goal, int taskToMark) throws IOException {
        String oldFileName = path;
        String tempFileName = "src/main/data/tempDuke.txt";
        File tempFile = new File(tempFileName);
        long lineCount = Files.lines(Path.of(path)).count();
        int taskToChange = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(oldFileName));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (taskToChange == lineCount - 1) { //if it is the last line
                    if (taskToChange == taskToMark) {
                        line = line.replaceFirst(init, goal);
                    }
                    bw.write(line);
                } else if (taskToChange == taskToMark) {
                    line = line.replaceFirst(init, goal);
                    bw.write(line + "\n");
                } else {
                    bw.write(line + "\n");
                }
                taskToChange++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File oldFile = new File(oldFileName);
        oldFile.delete();

        File newFile = new File(tempFileName);
        newFile.renameTo(oldFile);
    }




}
