
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
        Task t;
        String[] strarr = s.split(" ");
        if (s.contains("[X]")) {
            if (s.contains("[T]")) {
                t = new Todo(strarr[1]);
            } else if (s.contains("[E]")) {
                t = new Event(strarr[1], strarr[2] + " " + strarr[3]);
            } else {
                t = new Deadline(strarr[1], strarr[2] + " " + strarr[3]);
            }
            t.markAsDone();
        } else {
            if (s.contains("[T]")) {
                t = new Todo(strarr[2]);
            } else if (s.contains("[E]")) {
                t = new Event(strarr[2], strarr[3] + " " + strarr[4]);
            } else {
                t = new Deadline(strarr[2], strarr[3] + " " + strarr[4]);
            }

        }
        return t;
    }

    private static Contact parseContact(String s) {
        String[] strarr = s.split(" ");
        return new Contact(strarr[0], strarr[1]);
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
