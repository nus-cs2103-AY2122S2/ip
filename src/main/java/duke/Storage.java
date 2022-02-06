package duke;

import duke.task.*;

import java.io.*;

public class Storage {
    private String dataPath;
    private File file;


    public Storage(String dataPath) {
        this.dataPath = dataPath;
        checkFolderAndFile();
    }

    public void checkFolderAndFile() {
        try {
            File directory = new File("data");
            directory.mkdir();
            this.file = new File(this.dataPath);
            if (file.createNewFile()) {
                System.out.println("File is not found. A new file has been created for you.");
            } else {
                System.out.println("File is found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeToFile(TaskList taskList) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.file, false));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    printWriter.println("D | " + deadline.getStatusIcon() + " | " + deadline.getDescription() + " | " + deadline.getDateAndTime());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    printWriter.println("E | " + event.getStatusIcon() + " | " + event.getDescription() + " | " + event.getDateAndTime());
                } else if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    printWriter.println("T | " + todo.getStatusIcon() + " | " + todo.getDescription());
                }
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }

    public TaskList readFromFile() {
        TaskList taskList = new TaskList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] taskDetails = currentLine.split(" \\| ");
                Task task;
                if (currentLine.charAt(0) == 'D') {
                    task = new Deadline(taskDetails[2], taskDetails[3]);
                } else if (currentLine.charAt(0) == 'E') {
                    task = new Event(taskDetails[2], taskDetails[3]);
                } else {
                    task = new Todo(taskDetails[2]);
                }
                if (taskDetails[1].equals("[X]")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                currentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
