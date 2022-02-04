import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {
    private ArrayList<Task> itemList = new ArrayList<>(0);
    private final Storage geneStorage;

    //read file and initialize arraylist: if dont have existing file, create.
    public TaskList(Storage geneStorage) {
        this.geneStorage = geneStorage;
    }

    public void initFile() {
        geneStorage.createDirectory();
        geneStorage.createFile();
        this.itemList = geneStorage.readFile();
    }

    //set
    public void set(int index, Task targetTask) {
        this.itemList.set(index, targetTask);
    }
    //get
    public Task get(int index) {
        return this.itemList.get(index);
    }
    //add
    public void add(Task targetTask) {
        this.itemList.add(targetTask);
    }
    //size

    public int size() {
        return this.itemList.size();
    }

    public void remove(int index) {
        this.itemList.remove(index);
    }

}
