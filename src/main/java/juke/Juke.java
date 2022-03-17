
package juke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.stage.Stage;

public class Juke extends Application {

    @Override
    public void start(Stage stage) throws IOException {

//        File txt = new File("data/Juke.txt");
//        Scanner scan = new Scanner(txt);
//        ArrayList<Task> data = new ArrayList<Task>() ;
//        while(scan.hasNextLine()){
//            data.add(Parser.stringToTask(scan.nextLine()));
//        }
        Storage s = new Storage();
//        for (Task t : s.load()) {
//            System.out.println(Parser.taskToString(t));
//        }
        new UI().layout(stage, s.load());
    }

}
