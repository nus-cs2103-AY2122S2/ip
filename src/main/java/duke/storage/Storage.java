package duke.storage;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String home = System.getProperty("user.home");
    private static File saveFile;

    public Storage() {
        try {
            Path dir = Paths.get(home + "/ip/data/");

            // check if /ip/data directory exists, if not create
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
            }

            // check if the file in /ip/data exists, if not create
            Path p = Paths.get(home + "/ip/data/duke.txt");
            if (!Files.exists(p)) {
                Files.createFile(p);
            }

            saveFile = new File(p.toString());
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(saveFile);

            // load data from the save file
            while (s.hasNextLine()) {
                String task = s.nextLine();
                String[] parsedTask = task.split(" \\| ");
                switch (parsedTask[0]) {
                case "T": {
                    Todo t = new Todo(parsedTask[2]);
                    if (parsedTask[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                    break;
                }
                case "D": {
                    String done = parsedTask[1];
                    String desc = parsedTask[2];
                    String date = parsedTask[3];
                    String time = parsedTask[4];
                    if (!date.equals("0") && !time.equals("0")) {
                        Deadline d = new Deadline(desc, LocalDate.parse(date), LocalTime.parse(time));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    } else if (!date.equals("0")) {
                        Deadline d = new Deadline(desc, LocalDate.parse(date));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    } else {
                        Deadline d = new Deadline(desc, LocalTime.parse(time));
                        if (done.equals("1")) {
                            d.markAsDone();
                        }
                        tasks.add(d);
                    }
                    break;
                }
                case "E": {
                    String done = parsedTask[1];
                    String desc = parsedTask[2];
                    String date = parsedTask[3];
                    String time = parsedTask[4];
                    if (!date.equals("0") && !time.equals("0")) {
                        Event e = new Event(desc, LocalDate.parse(date), LocalTime.parse(time));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    } else if (!date.equals("0")) {
                        Event e = new Event(desc, LocalDate.parse(date));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    } else {
                        Event e = new Event(desc, LocalTime.parse(time));
                        if (done.equals("1")) {
                            e.markAsDone();
                        }
                        tasks.add(e);
                    }
                    break;
                }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt");
            for (int i = 0; i < tasks.listSize(); i++) {
                fw.write(tasks.getTask(i).getAppendData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void append(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(home + "/ip/data/duke.txt", true);
            fw.write(textToAppend + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
