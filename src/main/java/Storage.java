import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    protected File directory;
    protected File file;

    public Storage(String directoryPath, String filePath) {
        this.directory = new File(directoryPath);
        this.file = new File(filePath);
    }

    public LinkedList<Task> load() {
        LinkedList<Task> tasks = new LinkedList<>();
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String type = scanner.next();
                String status = scanner.next();
                String description = scanner.nextLine().strip();
                switch (type) {
                    case "[T]" :
                        Todo task = new Todo(description);
                        if (status.equals("[X]")) {
                            task.markAsDone();
                        }
                        tasks.add(task);
                        break;
                    case "[D]" :
                        String[] by = description.split("by: ");
                        SimpleDateFormat format = new SimpleDateFormat("MMM d yyyy");
                        try {
                            Date date = format.parse(by[1].substring(0, by[1].length() - 1));
                            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            Deadline deadline = new Deadline(by[0].substring(0, by[0].length() - 2), localDate);
                            if (status.equals("[X]")) {
                                deadline.markAsDone();
                            }
                            tasks.add(deadline);
                        } catch (ParseException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "[E]" :
                        String[] venue = description.split("at: ");
                        Event event = new Event(venue[0].substring(0, venue[0].length() - 2), venue[1].substring(0, venue[1].length() - 1));
                        if (status.equals("[X]")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default :
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            return tasks;
        }
    }

    public void create(Task task) {
        try {
            FileWriter writer = new FileWriter(file.getPath(), true);
            writer.write(task.toString() + "\n");
            writer.close();
            Ui.create(task);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String command) {
        try {
            String path = file.getPath();
            String temp = "./data/temp.txt";
            File tempFile = new File(temp);
            tempFile.createNewFile();
            int ptr = 0;
            FileWriter writer = new FileWriter(temp, true);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                ptr++;
                if (ptr == id) {
                    String type = scanner.next();
                    String status = scanner.next();
                    String description = scanner.nextLine().strip();
                    if (command.equals("mark")) {
                        status = "[X]";
                        writer.write(type + " " + status + " " + description + "\n");
                    } else if (command.equals("unmark")) {
                        status = "[-]";
                        writer.write(type + " " + status + " " + description + "\n");
                    } else {
                    }
                } else {
                    writer.write(scanner.nextLine() + "\n");
                }
            }
            writer.close();
            file.delete();
            File dump = new File(path);
            tempFile.renameTo(dump);
            if (ptr < id) {
                throw new DukeException("This is an invalid task id, sir.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
