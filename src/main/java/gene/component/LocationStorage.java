package gene.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gene.location.Location;

/**
 * The storage class. handles all storage/file actions.
 *
 * @author Eugene Chia
 * @version 1.0
 * @since 2022-01-12
 */
public class LocationStorage {
    private final ArrayList<Location> itemList = new ArrayList<>(0);
    private Path absolutePath;
    private final Path folderPath;
    private final String fileName;
    private File targetFile;

    /**
     * The storage constructor
     * Gets property of user directory first, creates a data folder path and
     * stores the absolute path for actual storage file path to be appended.
     *
     * @param fileName string for the name of file to save user input
     */
    public LocationStorage(String fileName) {
        String currentDir = System.getProperty("user.dir");
        Path currentPath = Path.of(currentDir + File.separator + "data");
        this.folderPath = currentPath.toAbsolutePath();
        this.absolutePath = currentPath.toAbsolutePath();
        this.fileName = fileName;
    }

    /**
     * Creates the folder directory using absolute path.
     */
    public void createDirectory() {
        try {
            this.absolutePath = Files.createDirectories(this.absolutePath);
        } catch (FileAlreadyExistsException err) {
            //
        } catch (IOException err) {
            err.printStackTrace();
        } catch (SecurityException err) {
            err.printStackTrace();
        }
    }

    /**
     * Creates the target file for saving user input. If file already created,
     * no worries
     */
    public void createFile() {
        try {
            this.absolutePath = this.absolutePath.resolve(this.fileName);
            Files.createFile(this.absolutePath);
        } catch (FileAlreadyExistsException err) {
            //its ok if file already exists.
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    /**
     * Reads the file if it exists, it should since it was initiated upon
     * start up. Else, error would have been thrown. This method catches errors
     * too
     * First, a file reader is created from the absolute path of the file,
     * then a buffered reader is created from this
     * Formatter to read saved date time format is created and from there
     * for every line in the file, a to do, event or deadline task is created
     * and added into Gene's list
     *
     * @return full list of existing tasks from the target file
     */
    public ArrayList<Location> readFile() { // to do / here / 0
        try {
            FileReader fileReader = new FileReader(this.absolutePath.toString());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //write to arraylist
                //create task
                String[] tokens = line.split(" / ");
                boolean isMarked = tokens[tokens.length - 1].equals("1");
                switch(tokens[0]) {
                case "location":
                    String locationName = tokens[1];
                    String locationPostalCode = tokens[2];
                    String locationType = tokens[3];
                    this.itemList.add(new Location(locationName, locationPostalCode, locationType));
                    break;
                default:
                    break;
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        this.targetFile = new File(this.absolutePath.toString());
        return this.itemList;
    }

    /**
     * This method handles the deletion of a task from the target file.
     *
     * @param index the line to delete
     */
    public void deleteLineToFile(int index) {
        File inputFile = new File(this.absolutePath.toString());
        File tempFile = new File(this.folderPath.resolve("tempLoc.txt").toString());

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            List<String> lines = Files.readAllLines(this.absolutePath, StandardCharsets.UTF_8);

            String targetLine = lines.get(index);
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(targetLine)) {
                    continue;
                }
                writer.write(currentLine + "\n");
            }

            reader.close();
            writer.close();

            if (!inputFile.delete()) {
                System.out.println("cannot delete sia");
            };

            if (!tempFile.renameTo(inputFile)) {
                System.out.println(inputFile.getAbsolutePath() + "cannot rename sia");
            };

            this.targetFile = tempFile;

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * This method handles the editing of a task in the target file. Marking
     * or un marking depends on the mark parameter provided.
     *
     * @param index index of task to edit
     * @param mark mark or un mark the task
     */
    public void updatesToFile(int index, String mark) {
        //use already written text in file to edit.
        String targetLine = "";
        try {
            List<String> lines = Files.readAllLines(this.absolutePath, StandardCharsets.UTF_8);
            targetLine = lines.get(index);
            List<String> tokens = Arrays.asList(targetLine.split(" / "));
            tokens.set(tokens.size() - 1, mark);
            targetLine = String.join(" / ", tokens);
            lines.set(index, targetLine);
            Files.write(this.absolutePath, lines, StandardCharsets.UTF_8);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * This method handles the writing to target file for hte execution of an
     * add command. This method also parses and formats instructions the kinda
     * optimises for less storage space. (Instead of UX)
     * @param locationName
     * @param postalCode
     * @param locType
     * @param isMarked
     */
    public void writeToFile(String locationName, String postalCode, String locType, boolean isMarked) {
        String mark = isMarked ? "1" : "0";
        String toWrite = locationName + " / " + postalCode + " / " + locType;
        try {
            FileWriter fw = new FileWriter(this.absolutePath.toString(), true);
            BufferedWriter bufferedWriter = new BufferedWriter(fw);
            String newLine = "";
            if (this.targetFile.length() > 0) {
                newLine = "\n";
            }
            toWrite = "location / " + toWrite + " / " + mark;
            bufferedWriter.append(newLine + toWrite);
            bufferedWriter.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
