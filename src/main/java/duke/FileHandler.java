package duke;

import duke.parser.Parser;
import duke.task.Task;

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
            Task task = Parser.textToTask(input);
            arr.add(task);
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

    /**
     * Clears an entire file.
     *
     * @param filePath The path to the file that will be cleared.
     * @throws IOException If I/O error occurs.
     */
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

    /**
     * Inserts a line into a file at a given index.
     *
     * @param filePath A string with the path to the written file.
     * @param index The file's line index that will be inserted to.
     * @param lineToInsert  The line that will be inserted to the file.
     * @throws IOException If I/O error occurs.
     */
    public static void insertToFile(String filePath, int index, String lineToInsert)
            throws IOException {
        File tempFile = new File("./data/temp.txt");
        if (!tempFile.exists()) {
            tempFile.createNewFile();
        }
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        String curr;
        int count = 0;
        while ((curr = reader.readLine()) != null) {
            if (count + 1 == index) {
                writer.write(lineToInsert + "\n");
            }
            String trimmedLine = curr.trim();
            writer.write(curr + "\n");
            count++;
        }
        writer.close();
        reader.close();
        copyToFile(tempFile, file);
    }
}
