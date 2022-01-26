package duke;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filepath;
    private String filename;

    public Storage() {
        this.filepath = System.getProperty("user.dir") + File.separator + "data";
        this.filename = "duke.Storage.txt";
        try {
            if (!Files.exists(Paths.get(filepath))) {
                Files.createDirectories(Paths.get(filepath));
            }
            if (!Files.exists(Paths.get(filepath + File.separator + filename))) {
                File file = new File(filepath + File.separator + filename);
                boolean result = file.createNewFile();
                if (!result) {
                    System.out.println("Unable to create file");
                }
            }
        } catch (IOException e) {
            System.out.println("Unable to create path/file");
        }
    }

    public ArrayList<Task> readFromStorage() {
        ArrayList<Task> listOfTasks = new ArrayList<>();

        String st;
        Task task = new Task("null");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filepath + File.separator + this.filename));
            st = reader.readLine();
            while (st != null) {
                String[] compactTask = st.split("-");
                switch (compactTask[0]) {
                    case "T":
                        task = new ToDos(compactTask[2]);
                        if (compactTask[1].equals("1")) {
                            task.mark();
                        }
                        break;
                    case "D":
                        task = new DeadLines(compactTask[2], compactTask[3]);
                        if (compactTask[1].equals("1")) {
                            task.mark();
                        }
                        break;
                    case "E":
                        task = new Event(compactTask[2], compactTask[3]);
                        if (compactTask[1].equals("1")) {
                            task.mark();
                        }
                        break;
                }
                listOfTasks.add(task);
                st = reader.readLine();
                }
            reader.close();
            } catch (DukeException ex) {
                System.out.println("IOException while trying to read from file");
            } catch (IOException ex) {
                System.out.println("Error in storage format");
                ex.printStackTrace();
            }
        return listOfTasks;
    }

    public void writeToStorage(TaskList listOfTasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filepath + File.separator + this.filename));
            for (Task i : listOfTasks.getList()) {
                try {
                    writer.write(formatTask(i));
                } catch (IOException e) {
                    System.out.println("Unable to write");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Unable to create writer");
            e.printStackTrace();
        }
    }

    private String formatTask(Task task) {
        ArrayList<String> toFormat = task.makeCompact();
        String formattedString;

        formattedString = String.join("-", toFormat);
        formattedString = formattedString + "\n";
        return formattedString;
    }
}
