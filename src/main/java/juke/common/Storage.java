package juke.common;

import juke.PrintHelper;

import java.io.File;
import java.io.IOException;

public class Storage {
    private static final String PATH_NAME = "data/juke.txt";
    
    private File file = new File(PATH_NAME);;
    
    public Storage() {
        this.initialiseFile();
    }
    
    private void initialiseFile() {
        try {
            if (this.file.getParentFile().mkdirs()) {
                PrintHelper.getInstance().formattedPrint("Directories not found. Creating new directories.");
            }
            if (this.file.createNewFile()) {
                PrintHelper.getInstance().formattedPrint("File not found. Creating new file.");
            }
        } catch (IOException e) {
            PrintHelper.getInstance().errorPrint(e);
        } catch (SecurityException e) {
            PrintHelper.getInstance().errorPrint(e);
        }
    }
}
