import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Duke {
    public static void main(String[] args) throws IOException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String intro = "Hello! I'm Duke \n" +
                    "What can I do for you? \n";
        System.out.println(intro);
        String task = "";
        FileWriter fw = new  FileWriter("test.txt",true);
        File f1 = new File("test.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        List<Task> ls = new ArrayList<>();

        while(br.readLine() != null) {
            task = br.readLine();
            String[] different = task.split("|");
            if(different[0] == "T") {
                todo next = new todo(different[0]);
                if(different[1] == "1") {
                    next.isDone = true;
                }
                ls.add(next);
            } else if (different[0] == "D") {
                Deadline next = new Deadline(different[2], different[3]);
                if(different[1] == "1") {
                    next.isDone = true;
                }
                ls.add(next);
            } else {
               Event next = new Event(different[2], different[3]);
               if(different[1] == "1") {
                next.isDone = true;
            }
            ls.add(next);
            }
        }
        fr.close();
        br.close();
        PrintWriter pr1 = new PrintWriter(f1);
        pr1.print("");
        pr1.close();

        while(true) {
            try { 
                Scanner sc = new Scanner(System.in);
                Task n = new Task(sc.nextLine());
                if(n.description.equals("bye")) {
                    break;
                } else if (n.description.equals("list")) {
                    System.out.println("Here are the things you've tried to do\n");
                    for(int i = 1; i <= ls.size(); i++) {
                        System.out.println(i + " " + ls.get(i -1));
                    }
                } else if(n.description.startsWith("mark")) {
                    int x = Integer.parseInt(n.description.substring(5));
                    Task edited = ls.remove(x -1);
                    edited.done();
                    ls.add(x-1, edited);
                    for(int i = 1; i <= ls.size(); i++) {
                        System.out.println(i + " " + ls.get(i -1));
                    }
                } else if (n.description.startsWith("unmark")) {
                    int x = Integer.parseInt(n.description.substring(7));
                    Task edited = ls.remove(x -1);
                    edited.undone();
                    ls.add(x-1, edited);
                    for(int i = 1; i <= ls.size(); i++) {
                        System.out.println(i + " " + ls.get(i -1));
                    }
                } else if(n.description.startsWith("todo")) {
                    todo a = new todo(n.description.substring(5));
                    if(a.description.equals("")) {
                        throw new DukeException("Nothings here");
                    } else {
                        ls.add(a);
                        System.out.println("Added the following todo " + a);
                    }

                } else if(n.description.startsWith("deadline")) {
                    String[] parts = n.description.substring(9).split(" /by ");
                    Deadline d = new Deadline(parts[0], convert(parts[1]));
                    ls.add(d);
                    System.out.println("Added the following deadline " + d);
                } else if(n.description.startsWith("event")) {
                    String[] parts = n.description.substring(6).split(" /at ");
                    Event e = new Event(parts[0], convert(parts[1]));
                    ls.add(e);
                    System.out.println("Added the following event " + e);
                } else if(n.description.startsWith("delete")) {
                    String number = n.description.substring(7);
                    int x = Integer.parseInt(number);
                    Task item = ls.remove(x-1);
                    System.out.println("I've removed the item from the list, stop being so lazy");
                    System.out.println(item);
                    System.out.println("you now have " + ls.size() + " things to do");
                } else {
                    throw new DukeException("I dont know what you want but this aint it");
                }
            } catch (DukeException error){
                System.out.println("OOPS! " + error.getMessage());
            } catch (IndexOutOfBoundsException a) {
                System.out.println("Nothing inside");
            }
        } 
        System.out.println("Bye. Hope to see you again");
        BufferedWriter out = new BufferedWriter(fw);
        for (Task task2 : ls) {
            System.out.println("test");
            out.write(task2.toString());
            out.append("\n");
            //out.write("Pain");
        }
            out.close();
    }

    static LocalDate convert(String date) throws DukeException {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Smth is wrong" + e);
        }
    }
}
