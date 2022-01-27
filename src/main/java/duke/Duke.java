package duke;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 Class to start the Duke chatbot which supports adding tasks to a list
 */

public class Duke {
    public static void main(String[] args) throws IOException {
        Ui.run();
    }

}
