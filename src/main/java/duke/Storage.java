package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {

    String filePath;
    File file;
    boolean isFileOpen = false;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        String basePath = new File("").getAbsolutePath();
        //file = new File( basePath + "/" + filePath);

        String[] path = filePath.split("/");
        for (String s : path) {
            basePath = basePath + "/" + s;
            if (!s.contains(".txt")) {
                file = new File(basePath);
                file.mkdirs();
            } else {
                file = new File(basePath);
                isFileOpen = true;
            }
        }
    }

    @Override
    public String toString() {
        if (isFileOpen) {
            return "Success";
        } else {
            return "No success";
        }
    }

    public ArrayList<Task> load() throws IOException, ParseException, DukeException {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        ArrayList<Task> taskArrayList = new ArrayList<>();

        if (file.length() == 0) {
            return taskArrayList;
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                if (currentLine.contains("[T]")) {
                    ToDos todo;
                    if (currentLine.charAt(4) == 'X') {
                        todo = new ToDos(currentLine.substring(6), true);
                        taskArrayList.add(todo);
                    } else {
                        todo = new ToDos(currentLine.substring(6));
                        taskArrayList.add(todo);
                    }
                } else if (currentLine.contains("[D]")) {
                    String date = currentLine.substring(currentLine.indexOf('(') + 5, currentLine.indexOf(')'));
                    Date dueDate = (Date) formatter.parse(date);

                    Deadline deadline;
                    if (currentLine.charAt(4) == 'X') {
                        deadline = new Deadline(currentLine.substring(6, currentLine.indexOf('(') - 1), true, dueDate);
                    } else {
                        deadline = new Deadline(currentLine.substring(6, currentLine.indexOf('(') - 1), dueDate);
                    }
                    taskArrayList.add(deadline);
                } else if (currentLine.contains("[E]")) {
                    String date = currentLine.substring(currentLine.indexOf('(') + 5, currentLine.indexOf(')'));
                    Date dueDate = (Date) formatter.parse(date);
                    Event event;
                    if (currentLine.charAt(4) == 'X') {
                        event = new Event(currentLine.substring(6, currentLine.indexOf('(') - 1), true, dueDate);
                    } else {
                        event = new Event(currentLine.substring(6, currentLine.indexOf('(') - 1), dueDate);
                    }
                    taskArrayList.add(event);
                } else {
                    throw new DukeException("Something wrong with loading file");
                }
            }
            return taskArrayList;
        }
    }

    public void appendTask(Task task) throws IOException {
        FileWriter appendWriter = new FileWriter(file, true);
        String toPrint = task.toString() + task.getStatus() +  task.getDescription();
        System.out.println("writing");
        appendWriter.write(toPrint + "\n");
        appendWriter.close();
    }

    public void rewriteTask(TaskList taskList) throws IOException {
        FileWriter rewriteWriter = new FileWriter(file, false);
        for (Task task: taskList.taskArrayList) {
            String toPrint = task.toString() + task.getStatus() +  task.getDescription();
            rewriteWriter.write(toPrint + "\n");
        }

        rewriteWriter.close();
    }
}
