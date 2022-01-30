package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {

    public String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public void saveData() throws IOException {
        try {
            File file = new File(this.filePath);

            boolean hasCreatedDirectory = file.getParentFile().mkdirs();
            boolean hasCreated = file.createNewFile();
            FileWriter fileWriter = new FileWriter(file, false);
            for (Task task : TaskList.dukeList) {
                String text = "";
                String description = task.getDescription();
                //task.isDone = false;
                if (task instanceof ToDo) {
                    text = "T | " + (task.getIsDone() ? "1" : "0") + " | " + description + '\n';
                } else if (task instanceof Event) {
                    Event event = ((Event) task);
                    text = "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription().trim() + " | " + event.getTime() + '\n';
                } else {
                    Deadline deadline = ((Deadline) task);
                    text = "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription().trim() + " | " + deadline.getTime() + '\n';
                }
                fileWriter.write(text);
                fileWriter.flush();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loadData() throws IOException {
        File file = new File(this.filePath);
        boolean hasCreateDirectory = file.getParentFile().mkdirs();
        boolean hasCreated = file.createNewFile();

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] commandWords = input.split(" \\| ", 4);
            Task task = null;
            switch (commandWords[0]) {
            case "D":
                task = new Deadline(commandWords[2], commandWords[3]);
                display(input);
                break;
            case "E":
                task = new Event(commandWords[2], commandWords[3]);
                display(input);
                break;
            case "T":
                task = new ToDo(commandWords[2]);
                String text = "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + '\n';
                display(input);
                break;
            default:
                System.err.println("error!!");
                break;
            }

                if (commandWords[1].equals("1")) {
                    task.isComplete();
                }
                TaskList.dukeList.add(task);
            }
            sc.close();
        }

        public void display (String string){
            System.out.println(string);
        }
    }
