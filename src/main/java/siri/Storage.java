package siri;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

class Storage {
    //deals with loading tasks from the file and saving tasks in the file
    private File dirFile;
    private File dataFile;

    private String fileDir;
    private String filePath;


    public Storage(String filePath) {

        if (filePath == "") {
            this.filePath = "data/data.txt";
        } else {
            this.filePath = filePath;
        }
        this.fileDir = filePath.substring(0, filePath.lastIndexOf('/'));
        this.dirFile = new File(fileDir);
        this.dataFile = new File(filePath);
    }

    public String load() throws SiriException {
        String loadedData = "";

        if (dirFile.exists()) {
            if (dataFile.exists()) {
                try {
                    Scanner sc = new Scanner(dataFile);

                    while (sc.hasNextLine()) {
                        loadedData = loadedData + sc.nextLine() + "\n";
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

            } else {
                throw new SiriException("File does not exist!!");
            }
        } else {
            //dirFile don't exist
            dirFile.mkdir();
            throw new SiriException("Directory and file does not exist!!");
        }

        if (loadedData.trim() == "") {
            throw new SiriException("No data found!!");
        } else {
            return loadedData;
        }
    }

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