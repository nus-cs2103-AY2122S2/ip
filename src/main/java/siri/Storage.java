package siri;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that deals with storing and loading of data.
 */
class Storage {
    private File directory;
    private File dataFile;

    private String fileDirectoryPath;
    private String filePath;


    /**
     * Constructor of Storage class.
     *
     * @param filePath string representation of the file path to allow loading from the file.
     * If file doesn't exist, file will be created based on the path provided.
     * If empty String is provided, cwd will create a data folder and a data.txt file inside to be saved.
     */
    public Storage(String filePath) {

        if (filePath == "") {
            this.filePath = "data/data.txt";
        } else {
            this.filePath = filePath;
        }
        this.fileDirectoryPath = this.filePath.substring(0, filePath.lastIndexOf('/'));
        this.directory = new File(fileDirectoryPath);
        this.dataFile = new File(filePath);
    }

    /**
     * Loads the file data (if there is a valid data file).
     *
     * @return String representation of the data being loaded.
     * @throws SiriException if file doesn't exist, directory doesn't exist, or when file consist no data.
     */
    public String load() throws SiriException {
        String loadedData = "";

        if (directory.exists()) {
            if (dataFile.exists()) {
                try {
                    Scanner sc = new Scanner(dataFile);

                    while (sc.hasNextLine()) {
                        loadedData = loadedData + sc.nextLine() + "\n";
                    }

                    sc.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } else {
                throw new SiriException("File does not exist!!");
            }
        } else {
            //dirFile don't exist
            directory.mkdir();
            throw new SiriException("Directory and file does not exist!!");
        }

        if (loadedData.trim() == "") {
            throw new SiriException("No data found!!");
        } else {
            return loadedData;
        }
    }

    /**
     * Saves data passed into the destination as stated when Storage instance is being initialised.
     *
     * @param dataToSave String representation of data to be saved.
     */
    public void save(String dataToSave) {
        try {
            if (dataFile.exists() == false) {
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
            FileWriter fileWriter = new FileWriter(dataFile);
            fileWriter.write(dataToSave);
            fileWriter.close();
        } catch (IOException ioe) {
            System.out.println("Error writing to file!");
        }
    }

}
