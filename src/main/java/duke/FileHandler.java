package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileHandler class provides methods for Java File object utility.
 */
public class FileHandler {

    /**
     * Writes some input text to a file.
     *
     * @param filePath    A string with the path to the written file.
     * @param textToWrite A string with the text to be written.
     * @throws IOException If I/O error occurs.
     */
    public static void writeToFile(String filePath, String textToWrite) throws IOException {
        FileWriter fWriter = new FileWriter(filePath, true);
        fWriter.write(textToWrite);
        fWriter.close();
    }

    /**
     * Reads all text from the "duke.txt" file.
     *
     * @param arr An ArrayList to contain all the newly created Task objects.
     * @throws FileNotFoundException If file being read does not exist.
     */
    public static void readFromFile(ArrayList<Task> arr) throws FileNotFoundException {
        File f = new File("./data/Duke.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {

            String input = s.nextLine();
            if (input.trim().length() == 0) {
                continue;
            }

            char identifier = input.charAt(0);
            if (identifier == 'T') {
                int status = Character.getNumericValue(input.charAt(2));
                Todo td = Todo.createTodo(status, input.substring(4));
                arr.add(td);
            } else if (identifier == 'D') {
                int status = Character.getNumericValue(input.charAt(2));
                int seperator = input.lastIndexOf('/');
                String description = input.substring(4, seperator);
                String date = input.substring(seperator + 1);
                Deadline dl = Deadline.createDeadline(status, description, date);
                arr.add(dl);
            } else {
                int status = Character.getNumericValue(input.charAt(2));
                int seperator = input.lastIndexOf('/');
                String description = input.substring(4, seperator);
                String date = input.substring(seperator + 1);
                Event ev = Event.createEvent(status, description, date);
                arr.add(ev);
            }
        }
        s.close();
    }

    /**
     * Edits lines from a file.
     *
     * @param filePath A string with the path to the written file.
     * @param oldLine  The line to be replaced in the file.
     * @param newLine  The line that will replace the old line in the file.
     * @throws IOException If I/O error occurs.
     */
    public static void editFile(String filePath, String oldLine, String newLine) throws IOException {

        File tempFile = new File("./data/temp.txt");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }

        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String curr;

        while ((curr = reader.readLine()) != null) {
            String trimmedLine = curr.trim();
            if (trimmedLine.equals(oldLine)) {
                curr = newLine;
            }

            writer.write(curr + "\n");
        }
        writer.close();
        reader.close();
        copyToFile(tempFile, file);
    }

    /**
     * Copies an entire file to another file.
     *
     * @param src    The source file whose contents will be copied.
     * @param target The target file whih will be copied over to.
     * @throws IOException If I/O error occurs.
     */
    public static void copyToFile(File src, File target) throws IOException {
        FileWriter fWriter = new FileWriter(target.getAbsolutePath());
        File tempFile = new File(src.getAbsolutePath());
        Scanner s = new Scanner(tempFile);
        while (s.hasNext()) {
            String lineOfText = s.nextLine();
            if (lineOfText.trim().length() == 0) {
                continue;
            }
            fWriter.write(lineOfText + "\n");
        }
        fWriter.close();
        s.close();
    }

    public static void clearFile(String filePath) throws IOException {
        File tempFile = new File("./data/temp.txt");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        FileWriter fWriter = new FileWriter(filePath);
        fWriter.write("");
        fWriter.close();
        FileWriter tempWriter = new FileWriter("./data/temp.txt");
        tempWriter.write("");
        tempWriter.close();
    }
}
