package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;

import java.time.format.DateTimeFormatter;

public class Storage {
    private TaskList taskList;
    /**
     * instantiates duke.Storage object that processes a list of task
     * @param TaskList taskList which is a list of task
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * deals with reading file
     * @throws FileNotFoundException when file cannot be found
     */
    public void load() throws FileNotFoundException {
        try {
            String filePath = "Data/Duke.txt";
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split("\\|"); //split input by |
                String task = split[0];
                String mark = split[1];
                switch (task) {
                    case "T":
                        Todo todo = new Todo(split[2]);
                        if (mark.equals("1")) {
                            todo.setAsMarked();
                        }
                        taskList.add(todo);
                        break;
                    case "D":
                        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
                        LocalDateTime newTime = LocalDateTime.parse(split[3], format1);
                        Deadline deadline = new Deadline(split[2], newTime); // changes here
                        if (mark.equals("1")) {
                            deadline.setAsMarked();
                        }
                        taskList.add(deadline);
                        break;
                    case "E":
                        DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
                        LocalDateTime newTime2 = LocalDateTime.parse(split[3], format2);
                        Event event = new Event(split[2], newTime2);
                        if (mark.equals("1")) {
                            event.setAsMarked();
                        }
                        taskList.add(event);
                        break;
                }
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }


    /**
     * deals with writing file
     * @throws IOException issue with writing file.
     */
    public void save() throws IOException {
        try {
            String filePath = "Data/Duke.txt";
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); ++i) {
                Task task = taskList.get(i);
                String done = task.getStatusIcon().equals("X") ? "1" : "0";
                String write = "|" + done + "|" + task.getDescription();
                if (task instanceof Todo) {
                    write = "T" + write;
                } else if (task instanceof Deadline) {
                    write = "D" + write + "|" + ((Deadline) task).getBy();
                } else if (task instanceof Event) {
                    write = "E" + write + "|" + ((Event) task).getAt();
                }
                fw.write(write);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

