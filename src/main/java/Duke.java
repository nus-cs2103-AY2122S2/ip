import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;
import exceptions.DukeTodoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.io.*;
//import java.util.*;

public class Duke {

    private static void getFileContent(String filePath) throws FileNotFoundException {
        // print all contents in file
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println((s.nextLine()));
        }
    }

    public static void writeToFile(List<Task> taskList, String filepath) throws IOException {
        FileWriter fw = new FileWriter(filepath);

        // go through all Task object and write to filepath
        for (int i = 1; i <= taskList.size(); i++) {
            fw.write(i + ". " + taskList.get(i - 1));
            fw.write('\n');
        }
        fw.close();
    }


    public static void main(String[] args) throws DukeException, DukeDeadlineException, IOException {

        String filepath = "src/main/data/duke.txt";

        // check directory
        File directory = new File("src/main/data");
        if (!directory.exists()) {
            System.out.println("No such directory but let me make one for you !");
            directory.mkdir();
        }

        // check directory
        File file = new File(filepath);

        System.out.println("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                "Nice to meet you dear:>\n" +
                "What can I do for you?");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input
        List<Task> all = new ArrayList<Task>(); //ArrayList of Task

        while (myObj.hasNextLine()) {
            input = myObj.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!"); //ending sentence
                System.exit(0);
            } else { //check input not "bye"
                String[] words = input.split(" ", 2);

                if (input.equals("list")) { //if list
                    writeToFile(all, filepath);
                    System.out.println("Here are the tasks in your list:");

                    // check file exist, if exists > list
                    try {
                        getFileContent(filepath);
                    } catch (FileNotFoundException e) {
                        System.out.println("I can't find duke.txt:( but I shall make one for you!");
                    }
                } else if (words[0].equals("mark")) {
                    int n = Integer.parseInt(words[1]);

                    all.get(n - 1).markDone();
                    // reload new content to duke.txt
                    try {
                        writeToFile(all, filepath);
                    } catch (IOException e) {
                        System.out.println("Something went wrong when update file duke.txt:(");
                    }


                } else if (words[0].equals("unmark")) {
                    int n = Integer.parseInt(words[1]);

                    // call Task method, mark task as not done
                    all.get(n - 1).unMarkDone();

                    // reload new content to duke.txt
                    try {
                        writeToFile(all, filepath);
                    } catch (IOException e) {
                        System.out.println("Something went wrong when update file duke.txt:(");
                    }

                } else if (words[0].equals("delete")) {
                    int n = Integer.parseInt(words[1]);

                    // remove Task from List
                    all.remove(n - 1);
                    try {
                        System.out.println("Now you have " + all.size() + " tasks in the list.");
                        writeToFile(all, filepath);
                    } catch (IOException e) {
                        System.out.println("Something went wrong when adding to file duke.txt:(");
                    }
                } else {
                    // handle adding of Tasks or ending statement
                    if (words[0].equals("todo")) {
                        try {
                            ToDo item = new ToDo(words[1]);
                            all.add(item);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(item);

                            // update storage file
                            writeToFile(all, filepath);
                            System.out.println(" Now you have " + all.size() + " tasks in the list.");

                        } catch (IOException e) {
                            System.out.println("Something went wrong when adding to file duke.txt:(");
                        } catch (Exception e) {
                            DukeTodoException error = new DukeTodoException("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(error.getMessage());
                        }
                    } else if (words[0].equals("deadline")) {
                        try {
                            Deadline item = Deadline.setDeadline(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(item);

                                // update storage file
                                writeToFile(all, filepath);
                                System.out.println(" Now you have " + all.size() + " tasks in the list."); //print when new task added

                            }
                        } catch (Exception e) {
                            DukeDeadlineException d = new DukeDeadlineException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    } else if (words[0].equals("event")) {
                        try {
                            Event item = Event.setEvent(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(item);

                                // update storage file
                                writeToFile(all, filepath);
                                System.out.println(" Now you have " + all.size() + " tasks in the list."); //print when new task added

                            }
                        } catch (IOException e) {
                            System.out.println("Something went wrong when adding to file duke.txt:(");
                        } catch (Exception e) {
                            DukeEventException d = new DukeEventException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    } else {

                        // if not within listed commands
                        DukeException d = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(d.getMessage());
                    }

                }

            }
        }
    }
}
