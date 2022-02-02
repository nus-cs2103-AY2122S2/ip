import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File file;
    private static String buffer = " xxx ";

    enum Action {
        ADD, MODIFY, DELETE, RESET;
    }

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public String[] load() throws DukeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String fileContent = "";
            String line = reader.readLine();

            while (line != null) {
                fileContent = fileContent + line + System.lineSeparator();

                line = reader.readLine();
            }

            String[] tasksArr = fileContent.split(System.lineSeparator());
            reader.close();

            return tasksArr;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Issue reading file!! D:");
        }
    }

    public void updateAfterAdd(Task task) throws DukeException {
        try {
            int marked = task.isDone ? 1 : 0;

            FileWriter writer = new FileWriter(file, true);
            writer.write(task.type + buffer + marked + buffer + task.description + "\n");
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Cannot update addition in save file!! :-(");
        }
    }

    public void updateAfterMark(int idx) throws DukeException {
        this.modifyFile(idx, true);
    }

    public void updateAfterUnmark(int idx) throws DukeException {
        this.modifyFile(idx, false);
    }

    public void updateAfterDelete(int idx) throws DukeException {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(file));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter != idx) {
                    content = content + line + System.lineSeparator();
                }

                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(file);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update deletion in save file!! D:");
        }
    }

    public void resetFile() throws DukeException {
        this.reset();
    }

    private void modifyFile(int idx, boolean mark) throws DukeException {
        try {
            int lineCounter = 0;
            BufferedReader saveReader = new BufferedReader(new FileReader(file));
            String content = "";
            String line = saveReader.readLine();

            while (line != null) {
                if (lineCounter == idx) {
                    if (line.charAt(6) == '1') {
                        if (!(mark)) {
                            line = line.replaceFirst("xxx 1 xxx", "xxx 0 xxx");
                        }
                    } else {
                        if (mark) {
                            line = line.replaceFirst("xxx 0 xxx", "xxx 1 xxx");
                        }
                    }
                }

                content = content + line + System.lineSeparator();
                line = saveReader.readLine();
                lineCounter++;
            }

            FileWriter saveWriter = new FileWriter(file);
            saveWriter.write(content);

            saveReader.close();
            saveWriter.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Save file not found!! :-(");
        } catch (IOException e) {
            throw new DukeException("Cannot update edits in save file!! D:");
        }
    }

    private void reset() throws DukeException {
        try {
            FileWriter writer = new FileWriter(file, false);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Cannot reset the save file!! D:");
        }
    }
}