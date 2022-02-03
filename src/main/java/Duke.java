import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import myPackage.*;
import exceptions.DukeException;

public class Duke {
    public static void main(String[] args) throws IOException {
        //Task c = new Task("HELLO");
        TaskList.list = new ArrayList<>();
        Storage.checkFile();
        Storage.load("data/duke.txt");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        int listCount = TaskList.list.size();

        //String[] list = new String[100];

    }
}
