import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DukeFile {
    BufferedReader reader;
    Path dukePath;

    public DukeFile() throws IOException {
        dukePath = Paths.get("data/duke.txt");
        if (!Files.exists(dukePath)) {
            Files.createDirectory(Paths.get("data/"));
            Files.createFile(dukePath);
        }
        reader = Files.newBufferedReader(dukePath);
    }

    protected TaskList initialize() {
        TaskList tl = new TaskList();
        try {
            while (true) {
                String entry = reader.readLine();
                if (entry == null) {
                    break;
                }
                String[] entrySplit = entry.split(" \\| ");
                if (entrySplit[0].equals("T")) {
                    if (entrySplit[1].equals("1")) {
                        tl.addTodo(new Todo(entrySplit[2], true));
                    } else {
                        tl.addTodo(new Todo(entrySplit[2], false));
                    }
                } else if (entrySplit[0].equals("D")) {
                    if (entrySplit[1].equals("1")) {
                        tl.addDeadline(new Deadline(entrySplit[2], entrySplit[3], true));
                    } else {
                        tl.addDeadline(new Deadline(entrySplit[2], entrySplit[3], false));
                    }
                } else {
                    // Event
                    if (entrySplit[1].equals("1")) {
                        tl.addEvent(new Event(entrySplit[2], entrySplit[3], true));
                    } else {
                        tl.addEvent(new Event(entrySplit[2], entrySplit[3], false));
                    }
                }
            }
            return tl;
        } catch (IOException e) {
            System.out.println("Error reading file, starting over!");
            return new TaskList();
        } catch (DateTimeParseException e) {
            System.out.println("Date(s) in unexpected format, unable to parse, starting over!");
            return new TaskList();
        }
    }

    protected void addTask(Task t) throws IOException {
        String newTask = t.convertToFileFormat() + "\n";
        Files.write(dukePath, newTask.getBytes(), StandardOpenOption.APPEND);
    }

    protected void deleteTask(int taskIndex) throws IOException {
        List<String> currList = Files.readAllLines(dukePath);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < currList.size(); i++) {
            if (i != taskIndex) {
                newList.add(currList.get(i));
            }
        }
        Files.delete(dukePath);
        Files.write(dukePath, newList);
    }

    protected void modifyTask(int taskIndex) throws IOException {
        List<String> currList = Files.readAllLines(dukePath);
        List<String> newList = new ArrayList<>();
        for (int i = 0; i < currList.size(); i++) {
            if (i != taskIndex) {
                newList.add(currList.get(i));
            } else {
                String[] entrySplit = currList.get(i).split(" \\| ");
                int part = 0;
                StringBuilder newLine = new StringBuilder();
                while (part < entrySplit.length) {
                    if (part != 0) {
                        newLine.append(" | ");
                    }
                    if (part == 1) {
                        if (entrySplit[1].equals("0")) {
                            newLine.append("1");
                        } else {
                            newLine.append("0");
                        }
                    } else {
                        newLine.append(entrySplit[part]);
                    }
                    part++;
                }
                newList.add(newLine.toString());
            }
        }
        Files.delete(dukePath);
        Files.write(dukePath, newList);
    }
}
