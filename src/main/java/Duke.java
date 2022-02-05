import myPackage.Parser;
import myPackage.Storage;
import myPackage.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        //Task c = new Task("HELLO");
        TaskList.list = new ArrayList<>();
        Storage.checkFile();
        Storage.load("data/duke.txt");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        int listCount = TaskList.list.size();
        Parser.parseCommand();
        //String[] list = new String[100];

    }
}
