
package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import contact.Contact;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Storage {

    private final File taskFilePath;
    private final File contactFilePath;

    /**
     * @param taskFilePath String of path to file to store task
     * @param contactFilePath String of path to file to store contacts
     */
    public Storage(String taskFilePath, String contactFilePath) {
        this.taskFilePath = new File(taskFilePath);
        this.contactFilePath = new File(contactFilePath);

    }

    /**
     * read the text file and converts the content into an ArrayList of Tasks
     * @param tasks List of tasks
     */

    public void loadTaskFile(List<Task> tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(taskFilePath);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            Task t = parseTextFile(s);
            tasks.add(t);
        }
    }

    /**
     * read the text file and converts the content into an ArrayList of Contacts
     * @param contacts List of contacts
     * @throws FileNotFoundException throws exception if contactFilePath is not found
     */

    public void loadContacts(List<Contact> contacts) throws FileNotFoundException {
        Scanner sc = new Scanner(contactFilePath);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            Contact c = parseContact(s);
            contacts.add(c);
        }
    }

    private static Task parseTextFile(String s) {
        if (s.contains("[T]")) {
            return parseTodo(s);
        } else {
            return parseTimedEvent(s);
        }

    }

    private static Task parseTodo(String s) {
        String[] strarr = s.split(" ");
        String task = "";
        Task t;
        if (s.contains("[X]")) {
            for (int i = 1; i < strarr.length; i++) {
                task += strarr[i];
                if (i != strarr.length - 1) {
                    task += " ";
                }
            }
            t = new Todo(task);
            t.markAsDone();
        } else {
            for (int i = 2; i < strarr.length; i++) {
                task += strarr[i];
                if (i != strarr.length - 1) {
                    task += " ";
                }
            }
            t = new Todo(task);

        }
        return t;
    }

    private static Task parseTimedEvent(String s) {
        Task t;
        String[] strarr = s.split(" ");
        String task = "";
        String date = strarr[strarr.length - 2];
        String time = strarr[strarr.length - 1];
        if (s.contains("[X]")) {
            for (int i = 1; i < strarr.length - 2; i++) {
                task += strarr[i];
                if (i != strarr.length - 3) {
                    task += " ";
                }
            }
            if (s.contains("[E]")) {
                t = new Event(task, date + " " + time);
            } else {
                t = new Deadline(task, date + " " + time);
            }
            t.markAsDone();
        } else {
            for (int i = 2; i < strarr.length - 2; i++) {
                task += strarr[i];
                if (i != strarr.length - 3) {
                    task += " ";
                }
            }
            if (s.contains("[E]")) {
                t = new Event(task, date + " " + time);
            } else {
                t = new Deadline(task, date + " " + time);
            }

        }
        return t;
    }

    private static Contact parseContact(String s) {
        String[] strarr = s.split(" ");
        String name = "";
        String contact = "";
        for (int i = 0; i < strarr.length; i++) {
            if (i == strarr.length - 1) {
                contact = strarr[i];
            } else {
                name += strarr[i];
                if (i != strarr.length - 2) {
                    name += " ";
                }
            }
        }
        return new Contact(name, contact);
    }

    /**
     * writes the tasks into text file before ending the program
     * @param tasks List of tasks
     */

    public void writeToTaskFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.taskFilePath);
        StringBuilder s = new StringBuilder();
        for (Task t: tasks) {
            s.append(t.storageString()).append("\n");
        }


        fw.write(s.toString());
        fw.close();
    }

    /**
     * writes the contacts into text file before ending the program
     * @param contacts List of tasks
     */

    public void writeToContactFile(List<Contact> contacts) throws IOException {
        FileWriter fw = new FileWriter(this.contactFilePath);
        StringBuilder s = new StringBuilder();
        for (Contact c: contacts) {
            s.append(c.storageString()).append("\n");
        }


        fw.write(s.toString());
        fw.close();
    }
}
