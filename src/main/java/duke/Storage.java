package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Handles hard disk storage for task list.
 */

public class Storage {

    private Path directory;
    private Path filePath;
    private Path contactsPath;

    /**
     * Constructs storage object for task list.
     *
     * @param directory Directory file is in.
     * @param filePath Full path to file including file name.
     * @param contactsPath Full path to contacts list file including file name.
     */
    public Storage(Path directory, Path filePath, Path contactsPath) {
        this.directory = directory;
        this.filePath = filePath;
        this.contactsPath = contactsPath;
    }

    /**
     * Loads list of tasks from file.
     *
     * @return List of tasks.
     * @throws DukeException If file not found or file data corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File dir = directory.toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = filePath.toFile();
            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String taskString = fileScanner.nextLine();
                Task task = constructTask(taskString);
                tasks.add(task);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return tasks;
    }

    /**
     * Constructs Task given data string.
     *
     * @return Task object.
     * @throws DukeException If string is not in correct format.
     */
    public Task constructTask(String taskString) throws DukeException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] data = taskString.split(",");
        String type = data[0];
        Boolean status = Boolean.parseBoolean(data[1]);
        String text = data[2];

        if (type.equals("T")) {
            return new Todo(text, status);
        } else if (type.equals("D")) {
            LocalDateTime date = LocalDateTime.parse(data[3], dateFormatter);
            return new Deadline(text, status, date);
        } else if (type.equals("E")) {
            LocalDateTime date = LocalDateTime.parse(data[3], dateFormatter);
            return new Event(text, status, date);
        } else {
            throw new DukeException("Cannot parse task list file data.");
        }
    }

    /**
     * Writes task list to file.
     *
     * @throws DukeException If couldn't write to file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File file = filePath.toFile();
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(tasks.toDataString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write to file");
        }
    }

    /**
     * Writes task list to file.
     *
     * @throws DukeException If couldn't write to file.
     */
    public void save(ContactList contacts) throws DukeException {
        try {
            File file = contactsPath.toFile();
            file.createNewFile();
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(contacts.toDataString());
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("Couldn't write to file");
        }
    }

    /**
     * Loads list of contacts from file.
     *
     * @return List of contacts.
     * @throws DukeException If file not found or file data corrupted.
     */
    public ArrayList<Contact> loadContacts() throws DukeException {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        try {
            File dir = directory.toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = contactsPath.toFile();
            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String contactString = fileScanner.nextLine();
                Contact contact = constructContact(contactString);
                contacts.add(contact);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        return contacts;
    }



    /**
     * Constructs Contact given data string.
     *
     * @return Contact object.
     * @throws DukeException If string is not in correct format.
     */
    public Contact constructContact(String contactString) throws DukeException {
        String[] data = contactString.split(",");
        String name = data[0];
        String telegram = data[1];

        if (data.length != 2) {
            throw new DukeException("Invalid contact format.");
        } else {
            return new Contact(name, telegram);
        }
    }

}
