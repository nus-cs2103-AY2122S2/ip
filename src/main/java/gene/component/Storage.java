package gene.component;

import gene.task.DeadlineTask;
import gene.task.EventTask;
import gene.task.Task;
import gene.task.TodoTask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    private final ArrayList<Task> itemList = new ArrayList<>(0);
    private Path absolutePath;
    private final Path folderPath;
    private final String fileName;
    private File targetFile;

    public Storage(String fileName) {
        String currentDir = System.getProperty("user.dir");
        Path currentPath = Path.of(currentDir + File.separator + "data");
        this.folderPath = currentPath.toAbsolutePath();
        this.absolutePath = currentPath.toAbsolutePath();
        this.fileName = fileName;
    }


    public void createDirectory() {
        try {
            this.absolutePath = Files.createDirectories(this.absolutePath);
        } catch (IOException err) {
            System.out.println(err.getMessage() + " create dir");
        }
    }

    public void createFile() {
        try {
            this.absolutePath = this.absolutePath.resolve(this.fileName);
            Files.createFile(this.absolutePath);
        } catch (IOException err) {
            //its ok if file already exists.
        }
    }

    public ArrayList<Task> readFile() {
        try {
            FileReader fileReader = new FileReader(this.absolutePath.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            String line;
            while((line  = bufferedReader.readLine()) != null) {
                //write to arraylist
                //create task
                String[] tokens = line.split(" / ");
                boolean isMarked = tokens[tokens.length - 1].equals("1");
                switch(tokens[0]) {
                    case "todo":
                        String todoTitle = tokens[1];
                        this.itemList.add(new TodoTask(todoTitle, isMarked));
                        break;
                    case "event":
                        String eventTitle = tokens[1];
                        String eventDeadline = tokens[2];
                        this.itemList.add(new EventTask(eventTitle, LocalDateTime.parse(eventDeadline, formatter), isMarked));
                        break;
                    case "deadline":
                        String deadlineTitle = tokens[1];
                        String deadlineDeadline = tokens[2];
                        this.itemList.add(new DeadlineTask(deadlineTitle, LocalDateTime.parse(deadlineDeadline, formatter), isMarked));
                        break;
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (Exception err) {
            System.out.println(err.getMessage() + " create reader");
        }

        this.targetFile = new File(this.absolutePath.toString());

        return this.itemList;
    }


    public void deleteLineToFile(int index) {
        File inputFile = new File(this.absolutePath.toString());
        File tempFile = new File(this.folderPath.resolve("temp.txt").toString());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            List<String> lines = Files.readAllLines(this.absolutePath, StandardCharsets.UTF_8);

            String targetLine = lines.get(index);
            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equals(targetLine)) continue;
                writer.write(currentLine);
            }

            reader.close();
            writer.close();

            if (!inputFile.delete()) {
                System.out.println("cannot delete sia");
            };

            if (!tempFile.renameTo(inputFile)) {
                System.out.println(inputFile.getAbsolutePath() + "cannot rename sia");
            };

            this.targetFile = tempFile;

        } catch(Exception err) {
            err.printStackTrace();
        }
    }

    public void updatesToFile(int index, String mark) {
        //use already written text in file to edit.
        String targetLine = "";
        try {
            List<String> lines = Files.readAllLines(this.absolutePath, StandardCharsets.UTF_8);
            targetLine = lines.get(index);
            List<String> tokens = Arrays.asList(targetLine.split(" / "));
            tokens.set(tokens.size() - 1, mark);
            targetLine = String.join(" / ", tokens);
            lines.set(index, targetLine);
            Files.write(this.absolutePath, lines, StandardCharsets.UTF_8);
        } catch (Exception err) {

        }
    }

    public void writeToFile(String taskKey, String taskType, boolean isMarked) {
        String mark = isMarked ? "1" : "0";
        String toWrite = "";
        try {
            FileWriter fw = new FileWriter(this.absolutePath.toString(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            String newLine = "";
            if (this.targetFile.length() > 0) {
                newLine = "\n";
            }
            switch(taskType) {
                case "T":
                    String[] todoTokens = taskKey.split("todo ");
                    toWrite = "todo / " + todoTokens[1] + " / " + mark;
                    bufferedWriter.append(newLine + toWrite);
                    break;
                case "E":
                    String[] eventTokens = taskKey.split("event ");
                    toWrite = eventTokens[1];
                    String[] eSplit = toWrite.split(" /at ");
                    toWrite = String.join(" / ", eSplit );
                    toWrite = "event / " + toWrite + " / " + mark;
                    bufferedWriter.append(newLine + toWrite);
                    break;
                case "D":
                    String[] deadlineTokens = taskKey.split("deadline ");
                    toWrite = deadlineTokens[1];
                    String[] dSplit = toWrite.split(" /by ");
                    toWrite = String.join(" / ", dSplit );
                    toWrite = "deadline / " + toWrite + " / " + mark;
                    bufferedWriter.append(newLine + toWrite);
                    break;
            }
            bufferedWriter.close();
        } catch(Exception err) {
            err.printStackTrace();
        }
    }
}
