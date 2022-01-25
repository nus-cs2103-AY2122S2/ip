import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final String filePath;
    private final Path path;
    
    public Storage() {
        filePath = "tasks.txt";
        path = Paths.get(filePath);
    }
    
    public String[] loadTasksFromFile() throws DukeException {
        if (!Files.exists(path)) {
            return new String[0];
        }
        
        try {
            BufferedReader bufferReader = new BufferedReader(Files.newBufferedReader(path));
            
            int numberOfTasks = Integer.parseInt(bufferReader.readLine());
            String[] data = new String[numberOfTasks];
            
            for (int i = 0; i < numberOfTasks; i++) {
                data[i] = bufferReader.readLine();
            }
            
            return data;
        } catch (NumberFormatException e) {
            throw new DukeException("Encounter incorrect file format when reading " + filePath);
        } catch (IOException e) {
            throw new DukeException("Failed to load tasks from " + filePath);
        }
    }

    public void saveToFile(String... data) throws DukeException {
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(
                    path, CREATE, TRUNCATE_EXISTING, WRITE);
            
            bufferedWriter.write(String.valueOf(data.length));
            bufferedWriter.newLine();
            for (int i = 0; i < data.length; i++) {
                bufferedWriter.write(data[i]);
                bufferedWriter.newLine();
            }
            
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new DukeException("Failed to write tasks to " + filePath);
        }
    }
}
