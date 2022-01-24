import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Siri {
    private static Scanner sc;
    private static ToDoList toDoList;
    private static File dirFile;
    private static File dataFile;
    private static final String fileDir = "../data";
    private static final String filePath = "../data/siri.txt";
    private static FileWriter fileWriter;

    public static void main(String[] args) {
        toDoList = new ToDoList();
        boolean fileExist = true;

        /*
            Check whether the file directory exist, if don't exist, create the directory, if exist,
            check whether the data file exist and load the data file if it exist.
        */
        dirFile = new File(fileDir);
        dataFile = new File(filePath);

        if (dirFile.exists()) {
            if (dataFile.exists()) {
            // Load data into toDoList
                try {
                    sc = new Scanner(dataFile);

                    while (sc.hasNextLine()) {
                        String nxtLine = sc.nextLine();
                        toDoList.startUpAddTask(nxtLine);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                fileExist = false;
            }
        } else {
            // Create directory
            dirFile.mkdir();
        }

        /* 
            Check whether the data file exist, if exist, loads the existing data while starting up
            the application.
        */

        sc = new Scanner(System.in);


        String logo = "   -----      O    -----      O\n" +
                      " /   _   \\   __   |       \\   __\n" + 
                      " |  | |__|  |  |  |   O   |  |  |\n" +
                      " |   ----\\  |  |  |       /  |  |\n" +
                      "  \\ __   |  |  |  |   ---    |  |\n" +
                      " |---|   |  |  |  |       \\  |  |\n" +
                      "  \\______/  |__|  |___|\\___\\ |__|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        runApp();

        try {
            if (fileExist == false) {
                dataFile.createNewFile();
            } else {
                dataFile.delete();
                dataFile.createNewFile();
            }
        } catch (IOException ioe) {
                System.out.println("Error creating data file!");
                System.out.println(ioe.getMessage());
        }

        try {
            fileWriter = new FileWriter(dataFile);
            fileWriter.write(toDoList.saveData());
            fileWriter.close();
        } catch (IOException ioe) {
            System.out.println("Error writing to file!");
        }


    }

    private static void runApp() {
        boolean toRun = true;

        while (toRun) {
            try {
                String inputText = sc.nextLine();

                if (inputText.equals("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    toRun = false;
                } else {
                    toDoList.handleCommand(inputText);
                }
            } catch (SiriException se) {
                System.out.println(se.getMessage());
            }
        }
    }

}
