import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Parser class handles the parsing and interaction between Duke and files on the disk.
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

    public Parser(File file, Memory memory) {

        this.file = file;

        try {
            this.scanner = new Scanner(file);
        } catch (IOException e) {
            echo.echoString(e.getMessage());
        }

        this.memory = memory;

    }

    public void load() {

        echo.echoString("Loading data...");

        String errorMessage = "These lines have been skipped:\n";
        int loadSuccess = 0;
        int loadUnsuccess = 0;

        while(scanner.hasNext()) {
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
                        loadSuccess++;
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
                        loadSuccess++;
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
                        loadSuccess++;
                    }
                    break;

                default:
                    errorMessage = errorMessage + temp + "\n";
                    loadUnsuccess++;
                    break;
            }
        }

        int loadTotal = loadSuccess + loadUnsuccess;

        echo.echoString("Loading data complete!");
        echo.echoString("Successful: " + loadSuccess
                + ", Unsuccessful: " + loadUnsuccess
                + ". Total: " + loadTotal);
        if (loadUnsuccess > 0) {
            echo.echoString(errorMessage);
        }
    }

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
            String tempTime;
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
