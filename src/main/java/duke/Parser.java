package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * The Parser class handles the parsing and interaction between Duke and files on the disk.
 * This is the equivalent to the 'Storage' class on the example.
 *
 * @author Rdac0
 */
public class Parser {

    private Echo echo = new Echo();
    private File file;
    private Scanner scanner;
    private Memory memory;
    private FileWriter fileWriter;

    private String loadError = "";
    private String regex = " ~~ ";

    /**
     * Creates a Parser that is connected to a Memory.
     *
     * @param file File object to be passed to Scanner and FileWriter.
     * @param memory Memory object to access.
     */
    public Parser(File file, Memory memory) {

        this.file = file;

        try {
            this.scanner = new Scanner(file);
        } catch (IOException e) {
            echo.echoString(e.getMessage());
        }

        this.memory = memory;

    }

    /**
     * Loads the data file onto Memory.
     */
    public void load() {

        echo.echoString("Loading data...");

        String errorMessage = "These lines have been skipped:\n";
        int loadUnsuccess = 0;

        while (scanner.hasNext()) {
            String temp = scanner.nextLine();
            String[] tempStrArray = temp.split(regex, 0);

            switch(tempStrArray[0]) {

            case "T":
                if (tempStrArray.length < 3) {
                    errorMessage = errorMessage + temp + "\n";
                    loadUnsuccess++;
                } else {
                    memory.addTask(tempStrArray[2]);
                    if (tempStrArray[1].equals("1")) {
                        memory.setDone(memory.getSize());
                    }
                }
                break;

            case "D":
                if (tempStrArray.length < 4) {
                    errorMessage = errorMessage + temp + "\n";
                    loadUnsuccess++;
                } else {
                    memory.addDeadline(tempStrArray[2], tempStrArray[3]);
                    if (tempStrArray[1].equals("1")) {
                        memory.setDone(memory.getSize());
                    }
                }
                break;

            case "E":
                if (tempStrArray.length < 4) {
                    errorMessage = errorMessage + temp + "\n";
                    loadUnsuccess++;
                } else {
                    memory.addEvent(tempStrArray[2], tempStrArray[3]);
                    if (tempStrArray[1].equals("1")) {
                        memory.setDone(memory.getSize());
                    }
                }
                break;

            default:
                errorMessage = errorMessage + temp + "\n";
                loadUnsuccess++;
                break;
            }
        }

        if (loadUnsuccess > 0) {
            echo.echoString(errorMessage);
        }
    }

    /**
     * Updates the data file from Memory.
     */
    public void updateAll() {

        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            echo.echoString(e.getMessage());
        }

        String toAdd = "";
        int size = memory.getSize();

        for (int i = 0; i < size; i++) {
            Task task = memory.getTask(i);
            String tempName;
            LocalDate tempTime;
            String tempDone;

            tempName = task.getName();
            if (task.getDone()) {
                tempDone = "1";
            } else {
                tempDone = "0";
            }

            if (task instanceof Deadline) {
                tempTime = task.getTime();
                toAdd = toAdd + "D" + regex + tempDone + regex
                        + tempName + regex + tempTime + System.lineSeparator();
            } else if (task instanceof Event) {
                tempTime = task.getTime();
                toAdd = toAdd + "E" + regex + tempDone + regex
                        + tempName + regex + tempTime + System.lineSeparator();
            } else {
                toAdd = toAdd + "T" + regex + tempDone + regex
                        + tempName + System.lineSeparator();
            }
        }

        try {
            fileWriter.write(toAdd);
            fileWriter.close();
        } catch (IOException e) {
            echo.echoString(e.getMessage());
        }
    }
}
